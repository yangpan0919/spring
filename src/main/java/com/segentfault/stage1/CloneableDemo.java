package com.segentfault.stage1;

public class CloneableDemo {

    public static void main(String[] args) throws CloneNotSupportedException {


        String s1 = "123";

        String s2 = new String("123");
        System.out.println(s1 == s2);

        Data data = new Data();

        data.setValue(1);
        data.setDesc("Hello,World");

        Data copy = data.clone();

        System.out.println(copy.getValue());

        System.out.println(data.getDesc() == copy.getDesc());

    }

}

/**
 * Cloneable 标记接口，标记可以被clone
 */
class Data implements Cloneable {


    private int value;

    private String desc;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public Data clone() throws CloneNotSupportedException {
        Data copy = (Data) super.clone();
        //深拷贝
        copy.setDesc(new String(this.desc));
        return copy;
    }
}
