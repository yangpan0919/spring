package com.study.zhuweihong;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PigDemo {


    public static void main(String[] args) {
        System.out.println(parseDE("einhundertsechs", ""));
        System.out.println(parseDE("zehnhunderteinhundert", ""));

        List<String>  list = new ArrayList<>();
        list.add("一");
        list.add("二");
        list.add("三");
        list.add("四");
        list.add("五");
        list.add("六");
        list.add("七");
        list.add("八");
        list.add("九");
        list.add("十");
        list.add("百");
        list.add("千");
//        list.add("");
        Collections.sort(list);
        System.out.println(list);
    }


    public static String parseDEResult(String str, int num) {
        if (str.length() > 1) {
            int maxBit = findMaxBit(str);

        }
        return  null;

    }


    public static int findMaxBit(String str) {
        String substring = str.substring(0, 1);
        String substring2 = str.substring(1, 2);
            return 0;

    }

    /**
     * str 只能值德语的数字
     *
     * @param str
     * @param result
     * @return
     */
    public static String parseDE(String str, String result) {
        String temp = str.substring(0, 3);
        switch (temp) {
            case "und":
                return parseDE(str.substring(3), result);
            case "nul":
                return result + "零";
            case "ein":
                if (str.length() == 4) {
                    return result + "一";
                }
                return parseDE(str.substring(3), result + "一");
            case "zwe":
                if (str.length() == 4) {
                    return result + "二";
                }
                return parseDE(str.substring(4), result + "二");
            case "dre":
                if (str.length() == 4) {
                    return result + "三";
                }
                return parseDE(str.substring(4), result + "三");
            case "vie":
                if (str.length() == 4) {
                    return result + "四";
                }
                return parseDE(str.substring(4), result + "四");
            case "Fün":
                if (str.length() == 4) {
                    return result + "五";
                }
                return parseDE(str.substring(4), result + "五");
            case "sec":
                if (str.length() == 5) {
                    return result + "六";
                }
                return parseDE(str.substring(4), result + "六");
            case "sie":
                if (str.length() == 6) {
                    return result + "七";
                }
                if (str.startsWith("sieben")) {
                    return parseDE(str.substring(6), result + "七");
                }
                return parseDE(str.substring(4), result + "七");
            case "ach":
                if (str.length() == 4) {
                    return result + "八";
                }
                return parseDE(str.substring(4), result + "八");
            case "neu":
                if (str.length() == 4) {
                    return result + "九";
                }
                return parseDE(str.substring(4), result + "九");
            case "zeh":
                if (str.length() == 4) {
                    return result + "十";
                }
                return parseDE(str.substring(4), result + "十");
            case "elf":
                return result + "十一";
            case "Zwö":
                return result + "十二";
            case "zwa":
                return result + "二十";
            case "ßig":
                return result + "十";
            case "zig":
                return result + "十";
            case "hun":
                if (str.length() == 7) {
                    return result + "百";
                }
                return parseDE(str.substring(7), result + "百");
            case "tau":
                if (str.length() == 7) {
                    return result + "千";
                }
                return parseDE(str.substring(7), result + "千");
            default:
                return result;
        }

    }


}
