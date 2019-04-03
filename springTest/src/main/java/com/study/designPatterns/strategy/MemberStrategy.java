package com.study.designPatterns.strategy;

/**
 * Created by Administrator on 2019/4/3.
 */
//抽象折扣类：
public interface MemberStrategy {
    /**
     * 计算图书的价格
     * @param booksPrice    图书的原价
     * @return    计算出打折后的价格
     */
    public double calcPrice(double booksPrice);

    default void test(){
        System.out.println(this.toString());
    }

}
