package com.example.l3_backend;

import com.example.l3_backend.mapper.ListMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@SpringBootApplication
public class L3BackendApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(L3BackendApplication.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("==========chạy xong==========");
//        LocalDate localDate = LocalDate.parse("2020-05-13",DateTimeFormatter.ISO_LOCAL_DATE);
//        System.out.println(localDate);
//        LocalDate localDate1 = LocalDate.parse("2020/05/12",DateTimeFormatter.ofPattern("yyyy/MM/dd"));
//        System.out.println(localDate1);
//
//        if (localDate.isBefore(localDate1)){
//            System.out.println("dung");
//        } else {
//            System.out.println("sai");
//        }
//        System.out.println();
    }
}
