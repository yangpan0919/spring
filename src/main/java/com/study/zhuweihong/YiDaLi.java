package com.study.zhuweihong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YiDaLi {

    private static Logger loger = LoggerFactory.getLogger(YiDaLi.class);

    private static Map<String, Double> numMap = new HashMap<>();

    private static Map<String, Integer> unitMap = new HashMap<>();//时间单位

    static {
        numMap.put("zero", 0d);
        numMap.put("uno", 1d);
        numMap.put("due", 2d);
        numMap.put("tre", 3d);
        numMap.put("quattro", 4d);
        numMap.put("cinque", 5d);
        numMap.put("sei", 6d);
        numMap.put("sette", 7d);
        numMap.put("otto", 8d);
        numMap.put("nove", 9d);
        numMap.put("dieci", 10d);
        numMap.put("undici", 11d);
        numMap.put("dodici", 12d);
        numMap.put("tredici", 13d);
        numMap.put("quattordici", 14d);
        numMap.put("quindici", 15d);
        numMap.put("sedici", 16d);
        numMap.put("diciassette", 17d);
        numMap.put("diciotto", 18d);
        numMap.put("diciannove", 19d);
        numMap.put("venti", 20d);
//        numMap.put("trenta", 30d);
//        numMap.put("quaranta", 40d);
//        numMap.put("cinquanta", 50d);
//        numMap.put("sessanta", 60d);
//        numMap.put("settanta", 70d);
//        numMap.put("ottanta", 80d);
//        numMap.put("novanta", 90d);
//        numMap.put("cento", 100d);
//
//        numMap.put("mille", 1000d);
//
//        numMap.put("mila", 1000d);
//
//        numMap.put("milione", 1000000d);
//
//        numMap.put("milioni", 1000000d);

        numMap.put("mezz", 0.5d);
        numMap.put("mezzo", 0.5d);
        numMap.put("mezza", 0.5d);


        unitMap.put("ora", 3600);
        unitMap.put("ore", 3600);

        unitMap.put("quarto", 900);
        unitMap.put("quarti", 900);

        unitMap.put("minuto", 60);
        unitMap.put("minuti", 60);

        unitMap.put("secondo", 1);
        unitMap.put("secondi", 1);

    }

    public static void main(String[] args) {
        System.out.println(parseYiDaLiNum("due ore trenta minuti"));
        System.out.println(parseYiDaLiNum("un quarto d'ora"));//没有兼容 一刻钟
        System.out.println(parseYiDaLiNum("quattro ore mezza"));
        System.out.println(parseYiDaLiNum("cinque secondi"));
        System.out.println(parseYiDaLiNum("un minuto"));
        System.out.println(parseYiDaLiNum("un milione di secondi"));
        System.out.println(parseYiDaLiNum("mille minuti"));
        System.out.println(parseYiDaLiNum("mille duecento secondi"));
    }

    //"Mezz'ora"

    /**
     * 将意大利语中的时间段转换成秒
     *
     * @param text
     * @return
     */
    public static double parseYiDaLiNum(String text) {

        List<Double> numList = new ArrayList();
        List<Integer> unitList = new ArrayList();
        //Un quarto d'ora 处理一刻钟的d' 以及Un'ora 一个小时中的链接符'
        String[] s = text.replace("d'", " ").replace("di ", " ").replace("'", " ").split(" ");

        double temp = -1d;

        for (int i = 0; i < s.length; i++) {
            String s1 = s[i];
            if (StringUtils.isEmpty(s1)) {
                continue;
            }
//            if (s1.length() == 1) {
//                i++;
//                //需要解析  1200  mille e duecento //Tre ore e mezza 三个半小时
//                double num = parseYiDaLiToNum(s[i]);
//                int index = numList.size() - 1;
//                if (i == s.length - 1) {//以数字结尾的时长  quattro ore e mezza
//                    numList.set(index, numList.get(index) + num);
//                } else { //due ore e trenta minuti
//                    numList.add(num);
//                    temp = num;
//                }
//
//                continue;
//            }
            if (s1.length() == 2) {
//                //un
                temp = 1d;
                numList.add(temp);
                continue;
            }

            Integer integer = unitMap.get(s1);
            if (integer == null) {
                if (i == s.length - 1) {//以数字结尾的时长  quattro ore e mezza
                    double num = parseYiDaLiToNum(s[i]);
                    int index = numList.size() - 1;
                    numList.set(index, numList.get(index) + num);
                    continue;
                }
                temp = handlerNum(temp, s1, numList);
                continue;
            }

            temp = -1d;
            //说明是时间单位
            if (unitList.size() == numList.size()) {//un quarto d'ora   兼容 一刻钟
                continue;
            }
            unitList.add(integer);
        }
        double result = 0d;
        if (unitList.size() == numList.size()) {
            for (int i = 0; i < numList.size(); i++) {
                result += unitList.get(i) * numList.get(i);
            }
            return result;
        }
        loger.error("数字+单位格式不一致" + numList + ":" + unitList);
        return -1d;
    }

    /**
     * 处理时间
     *
     * @param result  上一个处理的时间
     * @param str     需要处理的时间字符串
     * @param numList 存储时间的list
     * @return
     */
    private static double handlerNum(double result, String str, List<Double> numList) {
        try {
            //需要判断逗号和点，例如2.5 是否是德语中的写法2,5  或者100000 是否是德语中的 100.000 :是则需要这句，否则不需要
            str = str.replaceAll("[.]", "").replace(",", ".");

            result = Double.parseDouble(str);
            return result;
        } catch (NumberFormatException e) {
        }
        //不是单位，说明是数字，进行解析
        if (result == -1d) {
            result = parseYiDaLiToNum(str);
            numList.add(result);
        } else {
            double v = parseYiDaLiToNum(str);
            result = v > result ? result * v : result + v;// Un milione di ore 一百万个小时 两个数字连得
            numList.set(numList.size() - 1, result);
        }
        return result;
    }

    /**
     * 解析意大利语中的数字字符，转换成阿拉伯数字
     *
     * @param str 意大利语中的数字字符
     * @return
     */
    private static double parseYiDaLiToNum(String str) {
        Double num = numMap.get(str);
        if (num != null) {
            return num;
        }
        String s = parseYiDaLiToNumStepTwo(str);
        return parseNum(s);
    }

    /**
     * 将意大利数字转换成数字字符串
     * 例如：centosedici -->0016
     *
     * @param str
     * @return
     */
    public static String parseYiDaLiToNumStepTwo(String str) {
        return str.replace("venti", "20")
                .replace("trenta", "30")
                .replace("trent", "30")
                .replace("quaranta", "40")
                .replace("quarant", "40")
                .replace("cinquanta", "50")
                .replace("cinquant", "50")
                .replace("sessanta", "60")
                .replace("sessant", "60")
                .replace("settanta", "70")
                .replace("settant", "70")
                .replace("ottanta", "80")
                .replace("ottant", "80")
                .replace("novanta", "90")
                .replace("novant", "90")
                .replace("cento", "00")
                .replace("mille", "000")
                .replace("mila", "000")
                .replace("milione", "000000")
                .replace("milioni", "000000")

                .replace("undici", "11")
                .replace("dodici", "12")
                .replace("tredici", "13")
                .replace("quattordici", "14")
                .replace("quindici", "15")
                .replace("sedici", "16")
                .replace("diciassette", "17")
                .replace("diciotto", "18")
                .replace("diciannove", "19")

                .replace("due", "2")
                .replace("tre", "3")
                .replace("quattro", "4")
                .replace("cinque", "5")
                .replace("sei", "6")
                .replace("sette", "7")
                .replace("otto", "8")
                .replace("nove", "9")
                .replace("dieci", "0");

    }

    /**
     * 将意大利数字解析之后的字符串二次解析
     * 例如：0016 -->116
     *
     * @param str
     * @return
     */
    private static double parseNum(String str) {
        if (str.startsWith("0")) {
            str = "1" + str;
        }
        List<StringBuilder> list = new ArrayList<>(5);
        String[] split = str.split("");
        int index = 0;//list集合中的最大下标
        StringBuilder sb = new StringBuilder();
        sb.append(split[0]);
        list.add(sb);

        int temp = 0; //上一个非0数字的下标
        for (int i = 1; i < split.length; i++) {
            String s = split[i];
            if (s.equals("0")) {
                list.set(index, sb.append(s));
                continue;
            }
            if (temp == i - 1) {//处理15000中的15
                temp++;
                sb.append(s);
                continue;
            }
            sb = new StringBuilder();
            temp = i;
            sb.append(s);
            list.add(sb);
            index++;
        }
        double result = 0d;
        for (StringBuilder stringBuilder : list) {
            result += Integer.parseInt(stringBuilder.toString());

        }
        return result;
    }

}
