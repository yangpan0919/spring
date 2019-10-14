package com.study.multi_threaded;

import java.time.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorTest {
    private ScheduledExecutorService scheduExec;

    private static String[] args;

    ScheduledExecutorTest(String[] args){
        this.scheduExec =  Executors.newScheduledThreadPool(1);
        this.start = System.currentTimeMillis();
        this.args = args;
    }


    public long start;

    ScheduledExecutorTest(){
        this.scheduExec =  Executors.newScheduledThreadPool(1);
        this.start = System.currentTimeMillis();
    }
    public void timerTwo(){

        LocalDate tomorrow = LocalDate.now().plusDays(1);

        LocalTime time = LocalTime.of(9,0);
        LocalDateTime hours = LocalDateTime.of(tomorrow,time);
        long tip = hours.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        long now = Instant.now(Clock.system(ZoneOffset.of("+8"))).toEpochMilli();

        long initialDelay = tip - now;

        scheduExec.scheduleWithFixedDelay(new Runnable() {
            public void run() {
                System.out.println("每天九点执行一次"+ LocalDateTime.now());
            }
        },initialDelay,86400000,TimeUnit.MILLISECONDS);

    }

    public static void main(String[] args) {
        ScheduledExecutorTest test = new ScheduledExecutorTest();
        test.timerTwo();
    }
}
