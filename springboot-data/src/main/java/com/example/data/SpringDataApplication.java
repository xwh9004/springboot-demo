package com.example.data;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p><b>Description:</b>  TODO
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 17:21 on 2020/3/3
 * @version V0.1
 * @classNmae SpringDataApplication
 */
@MapperScan(basePackages = "com.example.data.mapper.*")
@SpringBootApplication(scanBasePackageClasses =SpringDataApplication.class )
public class SpringDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataApplication.class,args);
    }


}
