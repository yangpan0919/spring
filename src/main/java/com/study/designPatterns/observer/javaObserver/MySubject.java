package com.study.designPatterns.observer.javaObserver;

import java.util.Observable;

/**
 * Observable自己提供了   添加  删除   通知   的方法
 */
public class MySubject extends Observable {


	public void  wantMethod(){
		this.setChanged();   //必须设置，才可以进行通知通知    通知会自动clear掉这个状态
		this.notifyObservers("通知所有的观察者");
	}

}