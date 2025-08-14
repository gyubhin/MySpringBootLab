package com.rookies3.MySpringbootLab.runner;

import com.rookies3.MySpringbootLab.property.MyEnvironment;
import com.rookies3.MySpringbootLab.property.MyPropProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

@Component
public class MyPropRunner implements ApplicationRunner {
    @Value("${myprop.username}")
    private String username;

    @Value("${myprop.port}")
    private int port;

    @Autowired
    private MyPropProperties properties;

    @Autowired
    private MyEnvironment myEnvironment;

    private static final Logger logger = LoggerFactory.getLogger(MyPropRunner.class);

    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Properties myprop.username = " + username);
        System.out.println("Properties myprop.port = " + port);

        System.out.println("MyPropProperties.getusername() = " + properties.getUsername());
        System.out.println("MyPropProperties.getport() = " + properties.getPort());

        System.out.println("현재 환경 모드: " + myEnvironment.getMode());

        logger.info("현재 환경 모드: {}", myEnvironment.getMode());
        logger.info("myprop.username = {}", username);
        logger.debug("myprop.username ={}", username);
    }
}
