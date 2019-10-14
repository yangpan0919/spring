package com.study.designPatterns.strategy;
//高级会员类
public class AdvancedMemberStrategy implements MemberStrategy {
 
   private String name="我是高级会员";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AdvancedMemberStrategy{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public double calcPrice(double booksPrice) {
        
        System.out.println("对于高级会员的折扣为20%");
        return booksPrice * 0.8;
    }
}