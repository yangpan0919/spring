package com.study.zhuweihong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PigDemo3 {

    private static Logger loger = LoggerFactory.getLogger(PigDemo3.class);


    public static void main(String[] args) {
//        System.out.println(parseDE("einhundertdreiunddreißig", ""));//eins hundert sechs
//        System.out.println(parseDE("sechzehn", ""));//eins hundert sechs

        System.out.println(changeTimeToSecond("zwei und vier sekunde"));
        System.out.println(changeTimeToSecond("einhunderttausend sekunde"));
        System.out.println(changeTimeToSecond("700.000 sekunde"));
        System.out.println(changeTimeToSecond("dreiundfünfzig sekunde"));
        System.out.println(changeTimeToSecond("eine halbe stunde"));
        System.out.println(changeTimeToSecond("eine halbe minute"));
        System.out.println(changeTimeToSecond("2,5 minute"));

//        System.out.println(parseDEToNum("dreizehneinhalb"));//eins hundert sechs
//        System.out.println(parseDEToNum("einhundertsechs"));//eins hundert sechs
//        System.out.println(parseDEToNum("eintausendeinhundert"));
//        System.out.println(parseDEToNum("einhundertdreizehn"));//eins hundert zehn drei
//        System.out.println(parseDEToNum("elftausendeinhundertelf"));
//        System.out.println(parseDEToNum("zwei"));
//        System.out.println(parseDEToNum("elftausendeinhundertelf"));
//        System.out.println(parseDETime("einhundertsechs"));
//        System.out.println(parseDETime("einhundertdreiunddreißig"));
//        parseDEToNum2("eintausendeinhundert");
//        parseDEToNum2("einhundertdreizehn");//eins hundert zehn drei
//        parseDEToNum2("elftausendeinhundertelf");
//        parseDEToNum2("zwei");


    }

    /**
     * 该方法只能用来解析，时间段，时间段格式为：时间+单位
     *
     * @param text
     * @return
     */
    public static double changeTimeToSecond(String text) {
        double result = 0d;
        String[] s = text.split(" ");
        double temp = -1d;

        for (int i = 0; i < s.length; i++) {
            String s1 = s[i];
            if (StringUtils.isEmpty(s1)) {
                continue;
            }
            Integer integer = unitMap.get(s1);
            if (integer == null) {
                try {
                    //需要判断逗号和点，例如2.5 是否是德语中的写法2,5  或者100000 是否是德语中的 100.000 :是则需要这句，否则不需要
                    s1 = s1.replaceAll("[.]", "").replace(",", ".");

                    temp = Double.parseDouble(s1);
                    continue;
                } catch (NumberFormatException e) {
                }
                //不是单位，说明是数字，进行解析
                if (temp == -1d) {
                    temp = parseDEToNum(s1);
                } else if (s1.equals("halbe") && s[i - 1].equals("eine")) {
                    //类似于半小时之类的存在 eine halbe stunde
                    temp = 0.5d;

                } else if (s1.equals("und")) {
                    continue;
                } else {
                    // zwei vier stunden   两点四个小时
                    loger.warn("特殊处理的消息类型：" + temp + ":" + s1);
                    temp += parseDEToNum(s1)/10;
                }
                continue;
            }
            //是单位，temp在上一个循环解析处理了，加到result中
            result += (temp * integer);
            temp = -1d;

        }

        return result;

    }


    private static Map<String, Integer> unitMap = new HashMap<>();//时间单位

    static {

        unitMap.put("stunden", 3600);
        unitMap.put("stunde", 3600);
        unitMap.put("viertel", 900);
        unitMap.put("viertelstunde", 900);
        unitMap.put("minuten", 60);
        unitMap.put("minute", 60);
        unitMap.put("sekunde", 1);
        unitMap.put("sekunden", 1);

    }

    /**
     * 德语数字直接转换成阿拉伯数字
     *
     * @param text
     * @return
     */
    public static double parseDEToNum(String text) {
        //数值 和list2一一对应，例如：1 --> list1(1),list2(0);
        List<Double> list1 = new ArrayList<>(10);
        //10的n次方
        List<Integer> list2 = new ArrayList<>(10);

        parseDE(text, list1, list2);
        List<Double> list = new ArrayList<>(10);
        changeToNum(list, list1, list2);
        double result = 0;
        for (Double integer : list) {
            result += integer;
        }
        return result;
    }


    public static void changeToNum(List<Double> list, List<Double> list1, List<Integer> list2) {
        int maxBit = findMaxBit(list2);
        if (maxBit == 1) {
            if (list2.get(1) == 10000) {
                //数字十几
                list.add(list1.get(0) + 10);
            } else {
                double i = 0d;
                if (list1.get(1) == -0.5d) {//0.5
                    i = (list1.get(0) + list1.get(1)) * power(10, list2.get(0)) * power(10, list2.get(1));
                } else {//两百
                    i = list1.get(0) * power(10, list2.get(0)) * power(10, list2.get(1));
                }


                list.add(i);
            }
        } else if (maxBit == 2) {
            double i = 0d;
            if (list2.get(1) == list2.get(0)) {
                //数字53
                i = Double.parseDouble((list1.get(1).intValue() + "" + list1.get(0)));
            } else {
                //数字十万
                i = list1.get(0) * power(10, list2.get(0)) * list1.get(1) * power(10, list2.get(1)) * list1.get(2) * power(10, list2.get(2));
            }


            list.add(i);

        } else if (maxBit == 0) {
            //单个数字，例如35中的5,或者数字3，一百，一千
            list.add(list1.get(0) * power(10, list2.get(0)));
        }
        if (list1.size() > maxBit + 1) {
            list1 = list1.subList(maxBit + 1, list1.size());
            list2 = list2.subList(maxBit + 1, list2.size());
            changeToNum(list, list1, list2);
        }
    }

    /**
     * a的b次方
     *
     * @param a
     * @param b
     * @return
     */
    public static int power(int a, int b) {
        int power = 1;
        for (int c = 0; c < b; c++)
            power *= a;
        return power;
    }


    public static int findMaxBit(List<Integer> list) {
        if (list.size() == 0) {
            return -1;
        } else if (list.size() == 1) {
            return 0;
        }
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) < list.get(i - 1)) {
                return i - 1;
            }
        }
        return list.size() - 1;

    }


    /**
     * str 只能值德语的数字
     *
     * @param str
     * @param result
     * @return
     */
    public static void parseDE(String str, List<Double> result, List<Integer> num) {
        String temp = str.substring(0, 3);
        switch (temp) {
            case "und":
                parseDE(str.substring(3), result, num);
                break;
            case "nul":
                result.add(0d);
                num.add(0);
                break;
            case "hal":
                result.add(-0.5d);
                num.add(0);
                break;
            case "ein":
                result.add(1d);
                num.add(0);
                if (str.length() == 4 || str.length() == 3) {
                    return;
                }
                parseDE(str.substring(3), result, num);
                break;
            case "zwe":
                result.add(2d);
                num.add(0);
                if (str.length() == 4) {
                    return;
                }
                parseDE(str.substring(4), result, num);
                break;
            case "dre":
                result.add(3d);
                num.add(0);
                if (str.length() == 4) {
                    return;
                }
                parseDE(str.substring(4), result, num);
                break;
            case "vie":
                result.add(4d);
                num.add(0);
                if (str.length() == 4) {
                    return;
                }
                parseDE(str.substring(4), result, num);
                break;
            case "fün":
                result.add(5d);
                num.add(0);
                if (str.length() == 4) {
                    return;
                }
                parseDE(str.substring(4), result, num);
                break;
            case "sec":
                result.add(6d);
                num.add(0);
                if (str.length() == 5) {
                    return;
                }
                parseDE(str.substring(4), result, num);
                break;
            case "sie":
                result.add(7d);
                num.add(0);
                if (str.length() == 6) {
                    return;
                }
                if (str.startsWith("sieben")) {
                    parseDE(str.substring(6), result, num);
                }
                parseDE(str.substring(4), result, num);
                break;
            case "ach":
                result.add(8d);
                num.add(0);
                if (str.length() == 4) {
                    return;
                }
                parseDE(str.substring(4), result, num);
                break;
            case "neu":
                result.add(9d);
                num.add(0);
                if (str.length() == 4) {
                    return;
                }
                parseDE(str.substring(4), result, num);
                break;
            case "zeh":
                result.add(1d);
                num.add(10000);
                if (str.length() == 4) {
                    return;
                }
                parseDE(str.substring(4), result, num);
                break;
            case "elf":
                result.add(11d);
                num.add(0);
                if (str.length() == 3) {
                    return;
                }
                parseDE(str.substring(3), result, num);
                break;
            case "zwö":
                result.add(12d);
                num.add(0);
                if (str.length() == 5) {
                    return;
                }
                parseDE(str.substring(5), result, num);
                break;
            case "zwa":
                result.add(2d);
                num.add(1);
                if (str.length() == 7) {
                    return;
                }
                parseDE(str.substring(7), result, num);
                break;
            case "ßig":
                result.add(1d);
                num.add(1);
                if (str.length() == 3) {
                    return;
                }
                parseDE(str.substring(3), result, num);
                break;
            case "zig":
                result.add(1d);
                num.add(1);
                if (str.length() == 3) {
                    return;
                }
                parseDE(str.substring(3), result, num);
                break;
            case "hun":
                result.add(1d);
                num.add(2);
                if (str.length() == 7) {
                    return;
                }
                parseDE(str.substring(7), result, num);
                break;
            case "tau":
                result.add(1d);
                num.add(3);
                if (str.length() == 7) {
                    return;
                }
                parseDE(str.substring(7), result, num);
                break;
            case "Mil":
                if (str.equals("Million") || str.equals("Millionen")) {
                    result.add(1d);
                    num.add(6);
                    return;
                } else if (str.startsWith("Millionen")) {
                    result.add(1d);
                    num.add(6);
                    parseDE(str.substring(9), result, num);
                }
                result.add(1d);
                num.add(6);
                parseDE(str.substring(8), result, num);
                break;
            default:
                return;
        }

    }
}
