package com.study.designPatternsDemo.adapter.adapter;

import com.study.designPatternsDemo.adapter.duck.Duck;
import com.study.designPatternsDemo.adapter.turkey.WildTurkey;

/**
 * @author yp
 * @data 2019/3/15 19:42
 */
public class TurkeyAdapter2 extends WildTurkey implements Duck {
    public void quack() {
        super.gobble();
    }

    @Override
    public void fly() {
        super.fly();
        super.fly();
        super.fly();
    }
}
