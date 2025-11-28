package com.nc.g49_smartcrm;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@OpenAPIDefinition(
        servers = {
                @Server(url = "http://localhost:8080", description = "SmartCRM App")
        }
)
public class Nc49SmartCrmApplication {

    public static void main(String[] args) {
        SpringApplication.run(Nc49SmartCrmApplication.class, args);
    }

}
