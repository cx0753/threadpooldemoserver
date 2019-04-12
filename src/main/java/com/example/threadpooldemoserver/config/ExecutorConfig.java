package com.example.threadpooldemoserver.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;

@Configuration
@EnableAsync
public class ExecutorConfig {

    private static final Logger logger = LoggerFactory.getLogger(ExecutorConfig.class);

    @Bean
    public Executor asyncServiceExecutor() {
        logger.info("---线程池创建开始---");
        ThreadPoolTaskExecutor executor = new VisiableThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);//核心
        executor.setMaxPoolSize(5);//最大
        executor.setQueueCapacity(99999);//队列大小
        executor.setThreadNamePrefix("async-service-");//线程前缀名
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //执行初始化
        executor.initialize();
        return executor;
    }


    public static void main(String[] args) {
        ExecutorConfig.method04();
    }

    public static void method04(){
        ExecutorService mSingleThreadPool = Executors.newSingleThreadExecutor();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for(int i = 0;i < 7;i++) {
            final int number = i;
            mSingleThreadPool.execute(new Runnable() {

                @Override
                public void run() {
                    System.out.println("现在的时间:"+format.format(new Date())+"第"+number+"个线程");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            });

        }
    }

    public static void method03(){
        ExecutorService mFixedThreadPool = Executors.newFixedThreadPool(5);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for(int i = 0;i < 7;i++ ) {
            final int index = i;
            mFixedThreadPool.execute(new Runnable() {

                @Override
                public void run() {
                    System.out.println("时间是:"+format.format(new Date())+"第" +index +"个线程" +Thread.currentThread().getName());
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

        }
    }

    public static void methoe02() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 7; i++) {
            final int index = i;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("第" + index + "个线程" + Thread.currentThread().getName());
                }
            });

        }

    }

    public static void methoe01() {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(1);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("现在是北京时间：" + format.format(new Date()));
            }
        }, 0, 4, TimeUnit.SECONDS);
        /*scheduledThreadPool.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("现在是北京时间：" + format.format(new Date()));
            }
        }, 4, TimeUnit.SECONDS);*/
    }
}
