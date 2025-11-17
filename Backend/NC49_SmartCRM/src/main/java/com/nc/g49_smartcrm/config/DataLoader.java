package com.nc.g49_smartcrm.config;


import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Profile("dev")
public class DataLoader implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(DataLoader.class);

    @Override
    public void run(String... args) throws Exception {

        logger.info("DataLoader started");

        //TODO Crear User

        //TODO Crear Contactos

        //TODO Crar Conversación

        logger.info("Data load finished");
    }
}
