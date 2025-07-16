package com.mycompany.stockview;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StockViewApplication {
    public static void main(String[] args) {
        SpringApplication.run(StockViewApplication.class, args);
    }
}
