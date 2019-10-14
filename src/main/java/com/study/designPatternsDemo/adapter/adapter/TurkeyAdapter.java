package com.study.designPatternsDemo.adapter.adapter;

import com.study.designPatternsDemo.adapter.duck.Duck;
import com.study.designPatternsDemo.adapter.turkey.Turkey;

/**
 * @author yp
 * @data 2019/3/15 19:25
 */
public class TurkeyAdapter implements Duck {
    private Turkey turkey;
    public TurkeyAdapter(Turkey turkey){
        this.turkey = turkey;
    }
    public void quack() {
        turkey.gobble();
    }

    public void fly() {
        for (int i=0; i<6; i++){
            turkey.fly();
        }
    }
}
