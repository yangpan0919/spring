package com.study.multi_threaded.process;

import java.io.IOException;
import java.io.InputStream;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

/**
 * Created by Administrator on 2019/4/9.
 */
public class ChildProcessDemo {

    public static void main(String[] args) throws IOException {

        // IDEA(主进程)  ->  ChildProcessDemo ->  Windows 计算器（calc)
        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
        System.out.println(operatingSystemMXBean.getName());

        if(operatingSystemMXBean.getName().startsWith("Windows")){
            Runtime.getRuntime().exec("calc");   //运行里的指令


//            Process process = Runtime.getRuntime().exec("dir");//运行里的指令
//            InputStream inputStream = process.getInputStream();
//            int data =0;
//            while ((data = inputStream.read())>-1){
//                System.out.println(data);
//            }

        }
//        Runtime.getRuntime().exec("");
    }
}
