package com.study.designPatterns.factory.staticFactory;

import com.study.designPatterns.factory.simple.MailSender;
import com.study.designPatterns.factory.simple.Sender;
import com.study.designPatterns.factory.simple.SmsSender;

//工厂类：
public class SendFactory {

	public static Sender produceMail(){
		return new MailSender();
	}
	
	public static Sender produceSms(){
		return new SmsSender();
	}
}