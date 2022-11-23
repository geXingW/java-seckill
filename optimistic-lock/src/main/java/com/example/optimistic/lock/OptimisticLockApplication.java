package com.example.optimistic.lock;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.common.mapper")
@SpringBootApplication(scanBasePackages = {"com.example"})
public class OptimisticLockApplication {

    public static void main(String[] args) {
        SpringApplication.run(OptimisticLockApplication.class, args);
    }

}
