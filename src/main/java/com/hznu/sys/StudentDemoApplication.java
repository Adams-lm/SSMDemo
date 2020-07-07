package com.hznu.sys;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author LIN
 */
@MapperScan("com.hznu.sys.mapper")
@EnableTransactionManagement
@SpringBootApplication
public class StudentDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(StudentDemoApplication.class, args);
    }
}
