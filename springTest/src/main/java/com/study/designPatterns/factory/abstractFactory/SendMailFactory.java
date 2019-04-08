package com.study.designPatterns.factory.abstractFactory;

//工厂类：
public class SendMailFactory implements Provider {

	@Override
	public Sender produce(){
		return new MailSender();
	}
}
