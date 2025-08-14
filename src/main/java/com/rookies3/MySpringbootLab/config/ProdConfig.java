package com.rookies3.MySpringbootLab.config;

import com.rookies3.MySpringbootLab.property.MyEnvironment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
public class ProdConfig {
    @Bean
    public MyEnvironment myEnvironment(){
        return new MyEnvironment("운영환경");
    }
}

