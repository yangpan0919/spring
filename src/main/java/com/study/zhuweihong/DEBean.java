package com.study.zhuweihong;

import java.util.ArrayList;
import java.util.List;

public class DEBean {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            list.add(i + "");
        }


        String result = "";
        for (int i = 0; i < list.size(); i++) {
            String item = list.get(i);
            result = result + item;
        }
        System.out.println(result);


    }
}
