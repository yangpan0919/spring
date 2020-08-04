package com.study.zhuweihong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PigDemo3 {

    private static Logger loger = LoggerFactory.getLogger(PigDemo3.class);


    public static void main(String[] args) {
        DayOfWeek dayOfWeek = LocalDateTime.now().getDayOfWeek();
        System.out.println(dayOfWeek);
        String s = "lkjlkjkj";
        int index = s.indexOf(".");
        int index1 = s.lastIndexOf(".");
        if (index != index1) {
            System.out.println("至少有两个小数点");
        }
//        System.out.println(parseDE("einhundertdreiunddreißig", ""));//eins hundert sechs
//        System.out.println(parseDE("sechzehn", ""));//eins hundert sechs
//        System.out.println(changeTimeToSecond("eine minute und neunundzwanzig sekunden"));
        System.out.println(changeTimeToSecond("sechsundsechzigtausenddreihundertneunundneunzig sekunden"));
        System.out.println(changeTimeToSecond("sechsundachtzigtausenddreihundertneunundneunzig sekunden"));
        System.out.println(changeTimeToSecond("dreihundertneunundfünfzig sekunden"));
//        System.out.println(changeTimeToSecond("2 0,5 sekunden"));
//        System.out.println(changeTimeToSecond("2 und 2,5 sekunden"));
//        System.out.println(changeTimeToSecond("1 1/2h 1m"));
//        System.out.println(changeTimeToSecond("1 2/2 h 1m"));
//        System.out.println(changeTimeToSecond("1 2/2h 1m"));
//        System.out.println(changeTimeToSecond("1/2 h 1m"));
//        System.out.println(changeTimeToSecond("1/2h 1m"));
//        System.out.println(changeTimeToSecond("2 h 1m"));
//        System.out.println(changeTimeToSecond("2h 1m"));
//
//        System.out.println(changeTimeToSecond("2e45h"));
//        System.out.println(changeTimeToSecond("2 h"));
//        System.out.println(changeTimeToSecond("anderthalb minuten 100,100,002 sekunden"));//有问题，一点半分钟/一分半钟
//        System.out.println(changeTimeToSecond("anderthalb minuten"));//有问题，一点半分钟/一分半钟
//        System.out.println(changeTimeToSecond("35 sekunden"));//有问题，einer 没有兼容
//        System.out.println(changeTimeToSecond("einer halben stunde"));//有问题，einer 没有兼容
//        System.out.println(changeTimeToSecond("neuneinhalb sekunde"));
//        System.out.println(changeTimeToSecond("zwei stunden und dreißig sekunden"));
//        System.out.println(changeTimeToSecond("einhunderttausend sekunde"));
//        System.out.println(changeTimeToSecond("700.000 sekunde"));
//        System.out.println(changeTimeToSecond("dreiundfünfzig sekunde"));
//        System.out.println(changeTimeToSecond("eine halbe stunde"));
//        System.out.println(changeTimeToSecond("eine halbe minute"));
//        System.out.println(changeTimeToSecond("2,5 minute"));

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
     * 是2h 的处理方式
     *
     * @param text
     * @return
     */
    private static double parse2H(String text) {
        String[] arr = splitUnit(text);
        if (arr == null) {
            return 0;
        }
        try {
            double v = Double.parseDouble(arr[0]);
            Integer integer = unitMap.get(arr[1]);
            if (integer == null) {
                loger.error("数字解析异常:" + text);
                return 0;
            }
            return (v * integer);
        } catch (NumberFormatException e) {
            loger.error("数字解析异常:" + text);
        }
        return 0;
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
            //判断是否是2h
            String s2 = "^[0-9]+[.]{0,1}[0-9]*[a-z]+$";
            if (s1.matches(s2)) {
                temp = -1d;
                result += parse2H(s1);
                continue;
            }
            Integer integer = unitMap.get(s1);
            if (s1.equals("komma") && temp != -1) {//有小数点，例如：dreizehn komma sechzehn sekunden
                i++;
                double v = handleNum(-1d, s, i);
                temp = Double.parseDouble((int) temp + "." + (int) v);
                continue;
            }
            if (integer == null) {
                //分数的只要支持数字+空格+1/2+时间单位  判断是否为这种格式
                String s3 = "^[0-9]+[/][0-9]+[a-z]*$";
                if (s1.matches(s3)) {
                    double[] doubles = handlerNum(temp, result, s1);
                    temp = doubles[0];
                    result = doubles[1];
                    continue;
                }
                temp = handleNum(temp, s, i);
                continue;
            }
            //是单位，temp在上一个循环解析处理了，加到result中
            result += (temp * integer);
            temp = -1d;
        }
        return result;
    }

    /**
     * 处理分数的只要支持数字+空格+1/2+时间单位
     *
     * @param temp
     * @param result
     * @param text
     * @return
     */
    private static double[] handlerNum(double temp, double result, String text) {
        double[] data = new double[2];
        data[0] = temp;
        data[1] = result;
        String[] arr = splitUnit(text);
        if (arr == null) {
            data[0] = -1d;
            return data;
        }
        try {
            String[] split = arr[0].split("/");
            double v = Double.parseDouble(split[0]) / Double.parseDouble(split[1]);
            Integer integer = unitMap.get(arr[1]);
            //是否有单位
            boolean hasUnit = true;
            if ("".equals(arr[1])) {
                hasUnit = false;
            } else if (integer == null) {
                loger.error("数字解析异常:" + text);
                data[0] = -1d;
                return data;
            }

            if (temp == -1d) {
                if (hasUnit) {
                    data[1] += v * integer;
                } else {
                    data[0] = v;
                }
                return data;
            }
            temp = temp + v;
            if (hasUnit) {
                data[0] = -1d;
                data[1] += temp * integer;
            }
        } catch (NumberFormatException e) {
            loger.error("数字解析异常:" + text);
        }
        return data;
    }

    /**
     * 拆分数字和单位粘在一起的缩写
     *
     * @param str
     * @return
     */
    private static String[] splitUnit(String str) {
        char[] chars = str.toCharArray();
        int index = -1;
        StringBuffer sb = new StringBuffer();
        for (int j = chars.length - 1; j >= 0; j--) {
            if (chars[j] < 58) {
                index = j + 1;
                break;
            }
        }
        if (index == -1) {
            //异常
            loger.error("解析到异常数据:" + str);
            return null;
        }
        for (int i = index; i < chars.length; i++) {
            sb.append(chars[i]);
        }

        String[] result = new String[2];
        result[0] = str.replace(sb.toString(), "");
        result[1] = sb.toString();
        return result;
    }

    private static double handleNum(double temp, String[] str, int i) {
        String num = str[i];
        try {
            //需要判断逗号和点，例如2.5 是否是德语中的写法2,5  或者100000 是否是德语中的 100.000 :是则需要这句，否则不需要
            num = num.replaceAll("[.]", "").replace(",", ".");

            double aDouble = Double.parseDouble(num);
            if (temp == -1d) {
                return aDouble;
            } else {
                return temp + aDouble;
            }


        } catch (NumberFormatException e) {
        }
        //不是单位，说明是数字，进行解析
        if (num.equals("und")) {
            return temp;
        } else if (temp == -1d) {
            temp = parseDEToNum(num);
        } else if ((num.equals("halbe") || num.equals("halben")) &&
                (str[i - 1].equals("eine") || str[i - 1].equals("einer") || str[i - 1].equals("einen"))) {
            //类似于半小时之类的存在 eine halbe stunde
            temp = 0.5d;
        } else {
            // zwei vier stunden   两点四个小时
            loger.warn("特殊处理的消息类型：" + temp + ":" + num);
            temp += parseDEToNum(num) / 10;
        }
        return temp;
    }


    private static Map<String, Integer> unitMap = new HashMap<>();//时间单位

    static {

        unitMap.put("stunden", 3600);
        unitMap.put("stunde", 3600);

        unitMap.put("halbstundigen", 1800);

        unitMap.put("h", 3600);
        unitMap.put("m", 60);
        unitMap.put("viertel", 900);
        unitMap.put("viertelstunde", 900);
        unitMap.put("minuten", 60);
        unitMap.put("minute", 60);
        unitMap.put("sekunde", 1);
        unitMap.put("sekunden", 1);
        //天
        unitMap.put("tages", 86400);
        unitMap.put("tage", 86400);
        unitMap.put("tag", 86400);
        //周
        unitMap.put("woche", 604800);
        unitMap.put("wochen", 604800);

        //月 30天
        unitMap.put("monat", 18144000);
        unitMap.put("monate", 18144000);
        //年 360天/12个月
        unitMap.put("jahr", 31104000);
        unitMap.put("jahre", 31104000);
        //年 365天
//        unitMap.put("jahr", 31536000);
//        unitMap.put("jahre", 31536000);
        //komma


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
        double result = 0;
        try {
            parseDE(text, list1, list2);
        } catch (IndexOutOfBoundsException e) {
            loger.error("无法解析的数值：" + text, e);
            return result;
        }
        List<Double> list = new ArrayList<>(10);
        changeToNum(list, list1, list2);

        for (Double integer : list) {
            result += integer;
        }
        return result;
    }

    /**
     * @param list  需要的结果数字集合
     * @param list1 对应的具体数字  例如：100 对应的是1
     * @param list2 对应的位数 例如：100 的对应为2
     */
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
                } else if (list2.get(1) == 1 && list2.get(0) == 0) { //二十九 neunundzwanzig
                    i = list1.get(0) + list1.get(1) * 10;
                } else {//两百
                    i = list1.get(0) * power(10, list2.get(0)) * power(10, list2.get(1));
                }
                list.add(i);
            }
        } else if (maxBit == 2) {
            handleTwoMaxBit(list, list1, list2);
        } else if (maxBit == 0) {
            if (list2.get(0) == 10000) {//单独的数字10
                list.add(list1.get(0) * power(10, 1));
            } else if (list1.get(0) == -0.5d) {//0.5 例如：半天  halben tag
                list.add(0.5d);
            } else {
                //单个数字，例如35中的5,或者数字3，一百，一千
                list.add(list1.get(0) * power(10, list2.get(0)));

            }
        } else if (maxBit == 3) {//sechsundsechzigtausenddreihundertneunundneunzig  六十六万三百九十九
            List<Double> list11 = list1.subList(0, maxBit);
            List<Integer> list22 = list2.subList(0, maxBit);
            changeToNum(list, list11, list22);
            int size = list.size() - 1;
            list.set(size, list.get(size) * power(10, list2.get(maxBit)));
        } else {
            loger.error("出现的特殊情况需要兼容");
        }
        if (list1.size() > maxBit + 1) {
            List<Double> list11 = list1.subList(maxBit + 1, list1.size());
            List<Integer> list22 = list2.subList(maxBit + 1, list2.size());
            changeToNum(list, list11, list22);
        }
    }

    /**
     * 处理最大位数，在2的数字
     *
     * @param list
     * @param list1
     * @param list2
     */
    private static void handleTwoMaxBit(List<Double> list, List<Double> list1, List<Integer> list2) {
        double i;
        if (list2.get(1) == list2.get(0) && list2.get(1) == list2.get(2)) {
            //两个半小时
            i = list1.get(0) + list1.get(1) + list1.get(2);
        } else if (list2.get(1) == list2.get(0)) {
            //数字53
            i = Double.parseDouble((list1.get(1).intValue() + "" + list1.get(0)));
        } else {
            //数字十万
            i = list1.get(0) * power(10, list2.get(0)) * list1.get(1) * power(10, list2.get(1)) * list1.get(2) * power(10, list2.get(2));
        }
        list.add(i);
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

    /**
     * 在位数的集合中找到，最大的位数
     *
     * @param list 位数的集合
     * @return
     */
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
     * str 只能解析德语的数字
     *
     * @param str    德语的字符串
     * @param result 对应的具体数字  例如：100 对应的是1
     * @param num    对应的位数 例如：100 的对应为2
     * @return
     */
    public static void parseDE(String str, List<Double> result, List<Integer> num) {//对于zehnminütigen  十分钟，需要匹配minütigen，然后说明这个是单位，传递数据，需要threadlocal
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
                if (str.length() == 4 || str.length() == 3 || str.length() == 5) {
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
            default:
                parseDEStepOne(temp, str, result, num);

        }
    }

    /**
     * @param temp   str的前三位字符
     * @param str    需要解析字符串
     * @param result 对应的具体数字  例如：100 对应的是1
     * @param num    对应的位数 例如：100 的对应为2
     */
    private static void parseDEStepOne(String temp, String str, List<Double> result, List<Integer> num) {
        switch (temp) {
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
                if (str.startsWith("sechs")) {
                    parseDE(str.substring(5), result, num);
                } else {
                    parseDE(str.substring(4), result, num);
                }
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
            default:
                parseDEStepTwo(temp, str, result, num);
        }
    }

    /**
     * @param temp   str的前三位字符
     * @param str    需要解析字符串
     * @param result 对应的具体数字  例如：100 对应的是1
     * @param num    对应的位数 例如：100 的对应为2
     */
    private static void parseDEStepTwo(String temp, String str, List<Double> result, List<Integer> num) {
        switch (temp) {
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
            default:
                parseDEStepThree(temp, str, result, num);
        }
    }

    /**
     * @param temp   str的前三位字符
     * @param str    需要解析字符串
     * @param result 对应的具体数字  例如：100 对应的是1
     * @param num    对应的位数 例如：100 的对应为2
     */
    private static void parseDEStepThree(String temp, String str, List<Double> result, List<Integer> num) {
        switch (temp) {
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
            case "and":
                result.add(1.5d);
                num.add(0);
                if ("anderthalb".equals(str)) {
                    return;
                }
                loger.error("不支持的数字格式：" + str);
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
                loger.error("没有匹配到的字符：" + str);
                return;
        }
    }
}
