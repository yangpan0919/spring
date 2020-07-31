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
        unitMap.put("h", 3600);
        unitMap.put("m", 60);

        unitMap.put("quarto", 900);
        unitMap.put("quarti", 900);

        unitMap.put("minuto", 60);
        unitMap.put("minuti", 60);

        unitMap.put("secondo", 1);
        unitMap.put("secondi", 1);

        //天
        unitMap.put("giorno", 86400);
        unitMap.put("giorni", 86400);
        //周
        unitMap.put("settimana", 604800);
        unitMap.put("settimane", 604800);

        //月 30天
        unitMap.put("mese", 18144000);
        unitMap.put("mesi", 18144000);
        //年 360天/12个月
        unitMap.put("anno", 31104000);
        unitMap.put("anni", 31104000);

    }

    public static void main(String[] args) {
        System.out.println(parseYiDaLiNum("mezzora"));
//        System.out.println(parseYiDaLiNum("1 1/2 h 1m"));
//        System.out.println(parseYiDaLiNum("1 1/2h 1m"));
        System.out.println(parseYiDaLiNum("seicentoventinove secondi"));
        System.out.println(parseYiDaLiNum("sessantaseimilatrecentonovantanove secondi"));
//        System.out.println(parseYiDaLiNum("3.5 secondi"));
//        System.out.println(parseYiDaLiNum("1 2/2h 1m"));
//        System.out.println(parseYiDaLiNum("1/2 h 1m"));
//        System.out.println(parseYiDaLiNum("1/2h 1m"));
//        System.out.println(parseYiDaLiNum("2 h 1m"));
//        System.out.println(parseYiDaLiNum("2h 1m"));
        System.out.println(parseYiDaLiNum("diecimilecentoventitre secondi"));
        System.out.println(parseYiDaLiNum("ventuno secondi"));
        System.out.println(parseYiDaLiNum("un quarto d'ora"));//没有兼容 一刻钟
////        System.out.println(parseYiDaLiNum("quattro ore mezza"));
//        System.out.println(parseYiDaLiNum("cinque virgola duecento secondi"));
//        System.out.println(parseYiDaLiNum("uno minuto"));
////        System.out.println(parseYiDaLiNum("un milione di secondi"));
////        System.out.println(parseYiDaLiNum("mille minuti"));
////        System.out.println(parseYiDaLiNum("mille duecento secondi"));
    }

    //"Mezz'ora"

    /**
     * 将意大利语中的时间段转换成秒
     *
     * @param text
     * @return
     */
    public static double parseYiDaLiNum(String text) {
        text = text.trim();
        List<Double> numList = new ArrayList<>(10);
        List<Integer> unitList = new ArrayList<>(10);
        //Un quarto d'ora 处理一刻钟的d' 以及Un'ora 一个小时中的链接符'
        String[] s = text.replace("d'", " ").replace("di ", " ").replace("'", " ").split(" ");

        double temp = -1d;

        if (s.length == 1 && "mezzora".equals(s[0])) {
            return 1800d;
        }

        for (int i = 0; i < s.length; i++) {
            String s1 = s[i];
            if (StringUtils.isEmpty(s1)) {
                continue;
            }
            //判断是否是2h
            String s2 = "^[0-9]+[.]{0,1}[0-9]*[a-z]+$";

            if (s1.matches(s2)) {
                temp = -1d;
                String[] arr = splitUnit(s1);
                if (arr == null) {
                    continue;
                }
                try {
                    double v = Double.parseDouble(arr[0]);
                    Integer integer = unitMap.get(arr[1]);
                    if (integer == null) {
                        loger.error("数字解析异常:" + s1);
                        continue;
                    }
                    numList.add(v);
                    unitList.add(integer);
                } catch (NumberFormatException e) {
                    loger.error("数字解析异常:" + s1);
                }
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

            if (s1.equals("virgola") && temp != -1) {//有小数点，例如：cinque virgola duecento secondi
                i++;
                double v = handlerNum(-1d, s[i], new ArrayList());
                int index = numList.size() - 1;
                temp = Double.parseDouble(numList.get(index).intValue() + "." + (int) v);

                numList.set(index, temp);
                continue;
            }

            Integer integer = unitMap.get(s1);
            if (integer == null) {

                if (s1.indexOf(".") != s1.lastIndexOf(".")) {
                    return -1d;
                }
                //分数的只要支持数字+空格+1/2+时间单位  判断是否为这种格式
                String s3 = "^[0-9]+[/][0-9]+[a-z]*$";
                if (s1.matches(s3)) {
                    String[] arr = splitUnit(s1);
                    if (arr == null) {
                        temp = -1d;
                        continue;
                    }
                    try {
                        String[] split = arr[0].split("/");

                        double v = Double.parseDouble(split[0]) / Double.parseDouble(split[1]);
                        integer = unitMap.get(arr[1]);
                        if ("".equals(arr[1])) {

                        } else if (integer == null) {
                            loger.error("数字解析异常:" + s1);
                            temp = -1d;
                            continue;
                        } else {
                            unitList.add(integer);
                        }

                        if (temp == -1d) {
                            numList.add(v);
                            continue;
                        }
                        numList.set(numList.size() - 1, temp + v);
                        temp = -1d;

                    } catch (NumberFormatException e) {
                        loger.error("数字解析异常:" + s1);
                    }
                    continue;
                }

                if (i == s.length - 1) {//以数字结尾的时长  quattro ore e mezza
                    double num = parseYiDaLiToNum(s[i]);
                    int index = numList.size() - 1;
                    numList.set(index, numList.get(index) + num);
                    continue;
                }
                temp = handlerNum(temp, s1, numList);
                continue;
            }
//时间+单位  时间+单位  把时间+单位当成一轮解析,如果满足,标志位重置为-1,然后进入下一轮
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
//            str = str.replaceAll("[.]", "").replace(",", ".");

            double temp = Double.parseDouble(str);
            if (result == -1d) {
                numList.add(temp);
            } else {
                temp = temp + result;
                numList.set(numList.size() - 1, temp);
            }
            return temp;
        } catch (NumberFormatException e) {
        }

        if (str.length() == 2) {
//                //un
            numList.add(1d);
            return 1d;
        }
        //不是单位，说明是数字，进行解析
        if (result == -1d) {//如果说是数字+单位,就给标识符-1
            result = parseYiDaLiToNum(str);
            numList.add(result);
        } else {//如果是数字1+数字2+单位,这边就是解析数字2的
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
                .replace("vent", "20")
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
                .replace("cento", "!00")
                .replace("mille", "!000")
                .replace("mile", "!000")
                .replace("mila", "!000")
                .replace("milione", "!000000")
                .replace("milioni", "!000000")

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
                .replace("dieci", "!0")
                .replace("uno", "1");

    }

    /**
     * 处理占位符 ！
     * 同时处理 一万零一百二十三  之类的拼接数字，
     * 零太多了，需要确认最大位数
     *
     * @param str
     * @return
     */
    private static List<String> claerPlaceholder(String str) {
        List<String> result = new ArrayList<>(10);
        String[] arr = str.split("!");
        List<String> strList = new ArrayList<>(10);
        for (String s : arr) {
            if (StringUtils.isEmpty(s)) {
                continue;
            }
            strList.add(s);
        }

        if (strList.size() < 2) {
            for (String s : arr) {
                result.add(s);
            }
            return result;

        }
        List<Integer> list = new ArrayList<>(10);
        for (String s : strList) {
            list.add(findCount(s));
        }
        List<Integer> linkNumList = linkNum(list, str);
        int index = 0;
        for (int i = 0; i < linkNumList.size(); i++) {
            Integer integer = linkNumList.get(i);
            StringBuilder sb = new StringBuilder();
            for (int j = index; j < integer + index; j++) {
                sb.append(strList.get(j));
            }
            index = integer + index;
            String s = sb.toString();
            if (i != linkNumList.size() - 1) {
                while (!s.endsWith("0")) {
                    strList.set(index, s.substring(s.length() - 1) + strList.get(index));
                    s = s.substring(0, s.length() - 1);
                }
            }
            result.add(s);

        }
        return result;


    }

    /**
     * 判断是否0需要拼接
     *
     * @param list 分割后数字0的个数
     * @param str
     * @return
     */
    private static List<Integer> linkNum(List<Integer> list, String str) {
        List<Integer> result = new ArrayList<>();
        Integer temp = list.get(0);
        result.add(1);
        for (int i = 1; i < list.size(); i++) {
            int index = result.size() - 1;
            if (list.get(i) > temp) {
                result.set(index, result.get(index) + 1);
            } else if (list.get(i) < temp) {
                result.add(1);
            } else {
                loger.warn("数字解析有问题：" + str);
            }
            temp = list.get(i);
        }
        return result;

    }

    /**
     * 找出0的个数
     *
     * @param str
     * @return
     */
    private static int findCount(String str) {
        int result = 0;
        int index = str.indexOf("0");
        if (index < 0) {
            return result;
        }
        str = str.substring(index);
        String[] split = str.split("");
        for (String s : split) {
            if ("0".equals(s)) {
                result++;
                continue;
            }
            break;
        }
        return result;

    }

    /**
     * 将意大利数字解析之后的字符串二次解析
     * 例如：0016 -->116
     * 10100014
     *
     * @param numStr
     * @return
     */
    private static double parseNum(String numStr) {
        List<String> strList = claerPlaceholder(numStr);
        List<Double> resultList = new ArrayList<>(10);

        for (String str : strList) {
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
            double result = gerResult(list, str);
            resultList.add(result);

        }
        double result = 0d;
        for (Double aDouble : resultList) {
            result += aDouble;
        }
        return result;
    }

    private static double gerResult(List<StringBuilder> list, String str) {
        double result = 0d;
        int zeroCount = findCount(list.get(0).toString());
        List<Integer> resultTemp = new ArrayList<>();
        resultTemp.add(Integer.parseInt(list.get(0).toString()));
        for (int i = 1; i < list.size(); i++) {
            StringBuilder stringBuilder = list.get(i);
            int count = findCount(stringBuilder.toString());
            try {
                if (count > zeroCount) {
                    //60  6000  六万六
                    String s = stringBuilder.toString();
                    s = s.substring(0, s.length() - count);
                    int i1 = resultTemp.size() - 1;
                    resultTemp.set(i1, (resultTemp.get(i1) + Integer.parseInt(s)) * power(10, count));

                } else {
                    resultTemp.add(Integer.parseInt(stringBuilder.toString()));
                }
            } catch (NumberFormatException e) {
                loger.error("parseNum数字解析异常：" + str, e);
            }
            zeroCount = count;
        }
        for (Integer integer : resultTemp) {
            result += integer;
        }
        return result;
    }

    public static int power(int a, int b) {
        int power = 1;
        for (int c = 0; c < b; c++)
            power *= a;
        return power;
    }
}
