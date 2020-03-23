package com.study.zhuweihong;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YiDaLi {


    private static Map<String, Double> numMap = new HashMap<>();

    private static Map<String, Integer> unitMap = new HashMap<>();//时间单位

    static {
//        numMap.put("zero", 1d);
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

    //"Mezz'ora"
    public static double parseYiDaLiNum(String text) {

        List<Double> numList = new ArrayList();
        List<Integer> unitList = new ArrayList();
        //Un quarto d'ora 处理一刻钟的d' 以及Un'ora 一个小时中的链接符'
        String[] s = text.replace("d'", " ").replace("'", " ").split(" ");

        double temp = -1d;

        for (int i = 0; i < s.length; i++) {
            String s1 = s[i];
            if (StringUtils.isEmpty(s1)) {
                continue;
            }
            if (s1.length() == 1) {
                i++;
                Double num = numMap.get(s[i]);

                if (num != null) {//Tre ore e mezza 三个半小时
                    int index = numList.size() - 2;
                    numList.set(index, numList.get(index) + num);
                }
                continue;
            }
            if (s1.length() == 2) {
                if ("di".equals(s1)) {//Un milione di ore 一百万个小时
                    continue;
                }
                //un
                numList.add(1d);
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
                    temp = parseYiDaLiToNum(s1, 0d);
                } else {
                    // Un milione di ore 一百万个小时 两个数字连得
                    temp = temp * parseYiDaLiToNum(s1, 0d);
                }

                continue;

            }

            temp = -1d;
            //说明是时间单位
            unitList.add(integer);
        }

    }

    /**
     * 解析意大利语中的数字字符，转换成阿拉伯数字
     *
     * @param str 意大利语中的数字字符
     * @return
     */
    private static double parseYiDaLiToNum(String str, double result) {
        Double num = numMap.get(str);
        if (num != null) {

            return result + num;
        }

        String temp = str.substring(0, 3);
        switch (temp) {
            case "":
                break;

        }

        return 0;
    }
}
