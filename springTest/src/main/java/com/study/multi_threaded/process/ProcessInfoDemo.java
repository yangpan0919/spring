package com.study.multi_threaded.process;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadMXBean;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * Created by Administrator on 2019/4/9.
 * 获取进程信息
 */
public class ProcessInfoDemo {
    public static void main(String[] args) {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();

        long startTime = runtimeMXBean.getStartTime();
        Instant instant = Instant.ofEpochMilli(startTime);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss E");

        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();



        System.out.println(localDateTime.format(dtf));

        long uptime = runtimeMXBean.getUptime();
        System.out.println("当前进程启动时间： " + localDateTime);
        System.out.println("当前进程上线时间： " + uptime);
        System.out.println("当前进程线程数量： " + threadMXBean.getThreadCount());


        ManagementFactory.getMemoryManagerMXBeans().forEach(memoryManagerMXBean -> {

        });

        System.exit(9); //退出进程

    }
}
