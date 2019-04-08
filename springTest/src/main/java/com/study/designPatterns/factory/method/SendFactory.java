package com.study.designPatterns.factory.method;

import com.study.designPatterns.factory.simple.MailSender;
import com.study.designPatterns.factory.simple.Sender;
import com.study.designPatterns.factory.simple.SmsSender;

//改动下SendFactory类就行，如下：
public class SendFactory {

    public Sender produceMail(){
        return new MailSender();
    }

    public Sender produceSms(){
        return new SmsSender();
    }
}