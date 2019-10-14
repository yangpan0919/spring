package com.study.multi_threaded;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

/**
 * Created by Administrator on 2019/4/9.
 * //获得当前进程ID
 */
public class ProcessIdDemo {
    public static void main(String[] args) {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        String name = runtimeMXBean.getName();
        String pid = name.substring(0,name.indexOf("@"));
        System.out.println("java9 之前的方法   当前进程ID: " + pid);
    }
}
