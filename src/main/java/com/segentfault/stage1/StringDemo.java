package com.segentfault.stage1;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringDemo {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        String value = "Hello";

        String value2 = new String("Hello");

        System.out.println(value);

        System.out.println(value2);

        char[] chars = "World".toCharArray();

        Field valueFiled = String.class.getDeclaredField("value");

        valueFiled.setAccessible(true);

        valueFiled.set(value2,chars);

        System.out.println(value);

        System.out.println(value2);

        if(value.equals(value2)){
            System.out.println("3333333333333333");
        }

        String[] strs = {"1","q","w"};
        List<String> list = Arrays.asList(strs);
        list.add("r");


    }
}
