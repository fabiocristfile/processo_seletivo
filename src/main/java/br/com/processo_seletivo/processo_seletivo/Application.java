package br.com.processo_seletivo.processo_seletivo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * Spring Boot application starter class
 */

@SpringBootApplication(scanBasePackages = "br.com.processo_seletivo.processo_seletivo")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
