package com.study.designPatternsDemo.interpreter.cls;

import java.util.HashMap;

/**
 * @author yp
 * @data 2019/4/2 17:21
 */
public class VarExpression extends AbstractExpression {
    private String key;
    public VarExpression(String key){
        this.key = key;
    }
    @Override
    public Float interpreter(HashMap<String, Float> var) {
        return var.get(this.key);
    }
}
