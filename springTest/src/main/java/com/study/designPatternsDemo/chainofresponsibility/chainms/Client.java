package com.study.designPatternsDemo.chainofresponsibility.chainms;

/**
 * @author yp
 * @data 2019/4/1 20:28
 */
public class Client {
    public Client(){

    }
    public PurchaseRequest sendRequest(int type, int number, float price){
        return new PurchaseRequest(type, number, price);
    }
}
