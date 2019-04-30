package com.study.designPatternsDemo.visitor.mode;

/**
 * @author yp
 * @data 2019/4/9 17:29
 */
public interface Visitor {
    /**
     * 访问
     * @param element 元素
     */
    abstract public void visit(Element element);
}
