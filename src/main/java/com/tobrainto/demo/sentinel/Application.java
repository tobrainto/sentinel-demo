package com.tobrainto.demo.sentinel;

import com.alibaba.csp.sentinel.command.CommandCenterProvider;
import com.alibaba.csp.sentinel.transport.CommandCenter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PreDestroy;

@SpringBootApplication
@Slf4j
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @PreDestroy
    private void destroy() {
        // Shutdown CommandCenter
        CommandCenter commandCenter = CommandCenterProvider.getCommandCenter();
        if (commandCenter != null) {
            try {
                commandCenter.stop();
            }catch (Exception e) {
            }
        }
    }

}
