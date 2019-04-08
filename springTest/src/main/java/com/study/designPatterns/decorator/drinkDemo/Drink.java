package com.study.designPatterns.decorator.drinkDemo;

/**
 * Created by Administrator on 2019/4/8.
 */
public abstract class Drink {

    public String  description = "";
    private  float price = 0f;

    public String getDescription() {
        return this.description +"-"+this.price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    //计算价格的递归调用
    public abstract  float cost();
}
