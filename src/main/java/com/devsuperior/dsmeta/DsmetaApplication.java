package com.devsuperior.dsmeta;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class DsmetaApplication {

    @Autowired
    private SaleRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(DsmetaApplication.class, args);
    }

}