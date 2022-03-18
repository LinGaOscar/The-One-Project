package com.oscar.oneap;

import com.oscar.oneap.repository.SysUserRolesRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = SysUserRolesRepository.class)
public class OneApApplication {

    public static void main(String[] args) {
        SpringApplication.run(OneApApplication.class, args);
    }

}
