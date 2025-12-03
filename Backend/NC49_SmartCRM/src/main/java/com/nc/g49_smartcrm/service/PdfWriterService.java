package com.nc.g49_smartcrm.service;

import jakarta.servlet.http.HttpServletResponse;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

@Service
public class PdfWriterService {
    private static final int MARGIN_LEFT = 40;
    private static final int START_Y = 750;
    private static final int LINE_HEIGHT = 20;

    public interface PdfContentWriter {
        void write(PDPageContentStream stream, int y) throws Exception;
    }

    //metodo generico para crear un pdf
    public void createPdf(HttpServletResponse response, String filename, PdfContentWriter writer) throws Exception {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=" + filename);

        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        PDPageContentStream stream = new PDPageContentStream(document, page);
        stream.setFont(PDType1Font.HELVETICA, 12);

        int y = START_Y;

        // función para escribir y manejar saltos de página
        PdfContentWriter wrapper = (s, currentY) -> {
            if (currentY < 50) {
                s.close();
                PDPage newPage = new PDPage();
                document.addPage(newPage);
                PDPageContentStream newStream = new PDPageContentStream(document, newPage);
                newStream.setFont(PDType1Font.HELVETICA, 12);
                writer.write(newStream, START_Y);
            } else {
                writer.write(s, currentY);
            }
        };

        wrapper.write(stream, y);

        stream.close();
        document.save(response.getOutputStream());
        document.close();
    }

    public void writeLine(PDPageContentStream stream, String text, int y) throws Exception {
        stream.beginText();
        stream.newLineAtOffset(MARGIN_LEFT, y);
        stream.showText(text);
        stream.endText();
    }
}
