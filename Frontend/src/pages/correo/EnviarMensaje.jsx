import ArrowForwardIcon from '@mui/icons-material/ArrowForward';
import DeleteIcon from '@mui/icons-material/Delete';

export default function EnviarMensaje () {
    return (
        <>
            <div style={{marginTop: '2%'}} className="mb-3">
                <label for="exampleFormControlInput1" className="form-label">PARA</label><DeleteIcon/>
                <input type="email" className="form-control" id="exampleFormControlInput1" placeholder="name@example.com"></input>
            </div>
            <div className="mb-3">
                <label for="exampleFormControlTextarea1" className="form-label"></label>
                <textarea className="form-control" id="exampleFormControlTextarea1" rows="3" placeholder='Asunto'></textarea>
            </div>
            <div>
                <button style={{backgroundColor: '#097DB3' , color:'white' , fontWeight:'bold' , border: 'none' , borderRadius: '8%'}} ><ArrowForwardIcon/>Enviar</button>
            </div>
        </>
    )
}
