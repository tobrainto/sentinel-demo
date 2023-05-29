package com.tobrainto.demo.sentinel.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import java.util.concurrent.*;

@Controller
@Slf4j
public class ThreadPoolTestController {

    private RejectedExecutionHandler rejectedExecutionHandler = (r, executor) -> log.warn("队列溢出，丢弃任务");
    private ThreadPoolExecutor threadPoolExecutor;

    @PostConstruct
    public void init() {
        threadPoolExecutor = new ThreadPoolExecutor(5, 10,
                100L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(10), Executors.defaultThreadFactory(), rejectedExecutionHandler);
        threadPoolExecutor.allowCoreThreadTimeOut(true);
    }


    @GetMapping("/add")
    @ResponseBody
    public String xxx() {
        threadPoolExecutor.submit(() -> {
            try {
                log.info("begin");
                Thread.sleep(1);
                log.info("done");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        return "ok";
    }


//    @PreDestroy
//    private void destroy() {
//        threadPoolExecutor.shutdown();
//    }


}
