package com.study.designPatterns.factory.abstractFactory;

//实现类：
public class MailSender implements Sender {
	@Override
	public void Send() {
		System.out.println("this is mailsender!");
	}
}
