package com.study.helloworld;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	
	public static void main(String[] args) {
		
		//1. ���� IOC ����
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		//2. �� IOC �����л�ȡ bean ʵ��
		HelloWorld helloWorld = (HelloWorld) ctx.getBean("helloWorld");
		
		//3. ���� bean �ķ���
		helloWorld.hello();
		
		HelloWorld helloWorld2 = (HelloWorld) ctx.getBean("helloWorld");
		System.out.println(helloWorld == helloWorld2);
		
		//4. �ر�����
		ctx.close();
	}
	
}
