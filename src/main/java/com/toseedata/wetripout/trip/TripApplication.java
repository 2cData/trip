package com.toseedata.wetripout.trip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class TripApplication {

    public static void main(String[] args) {
        SpringApplication.run(TripApplication.class, args);
    }

    @Bean
    public AuditorAware<String> auditorProvider() {
        /*
        for Spring Security, get the currently logged-in username with
        SecurityContextHolder.getContext.getAuthentication().getName()
         */
        return () -> Optional.ofNullable("test_user");
    }
}
