package com.study.zhuweihong;

import org.springframework.util.StringUtils;

import java.util.*;

public class PigDemo {


    public static void main(String[] args) {
//        System.out.println(parseDE("einhundertdreiunddreißig", ""));//eins hundert sechs
//        System.out.println(parseDE("sechzehn", ""));//eins hundert sechs

        System.out.println(changeTimeToSecond("dreizehneinhalb "));

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

    public static double changeTimeToSecond(String text) {
        double result = 0d;
        String[] s = text.split(" ");
        double temp = 1d;
        for (int i = 0; i < s.length; i++) {
            String s1 = s[i];
            if (StringUtils.isEmpty(s1)) {
                continue;
            }
            Integer integer = unitMap.get(s1);
            if (integer == null) {
                temp = parseDEToNum(s1);
                continue;
            }
            result += (temp * integer);

        }

        return result;

    }

    private static Map<String, Double> numMap = new HashMap<>();


    private static Map<String, Integer> unitMap = new HashMap<>();//时间单位
    static {
        numMap.put("eins", 1d);
        numMap.put("zwei", 2d);
        numMap.put("drei", 3d);
        numMap.put("vier", 4d);
        numMap.put("fünf", 5d);
        numMap.put("sechs", 6d);
        numMap.put("sieben", 7d);
        numMap.put("acht", 8d);
        numMap.put("zehn", 10d);
        numMap.put("elf", 11d);
        numMap.put("zwölf", 12d);
        numMap.put("dreizehn", 13d);
        numMap.put("vierzehn", 14d);
        numMap.put("fünfzehn", 15d);
        numMap.put("sechzehn", 16d);
        numMap.put("siebzehn", 17d);
        numMap.put("achtzehn", 18d);
        numMap.put("neunzehn", 19d);
        numMap.put("zwanzig", 20d);
        numMap.put("dreißig", 30d);
        numMap.put("vierzig", 40d);
        numMap.put("fünfzig", 50d);
        numMap.put("sechzig", 60d);
        numMap.put("siebzig", 70d);
        numMap.put("achtzig", 80d);
        numMap.put("neunzig", 90d);
        numMap.put("hundert", 100d);
        numMap.put("tausend", 1000d);
        numMap.put("millionen", 1000000d);
        numMap.put("halb", -0.5d);

        unitMap.put("stunden", 3600);
        unitMap.put("viertel", 900);
        unitMap.put("minuten", 60);
        unitMap.put("sekunde", 1);

    }

    /**
     * 德语数字直接转换成阿拉伯数字
     *
     * @param text
     * @return
     */
    public static double parseDEToNum(String text) {

        List<Double> list1 = new ArrayList<>(10);
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

    /**
     * 解析text 例如: 八小时五十六分
     *
     * @param text
     * @return
     */
    public static String parseDEToNum2(String text) {
        List<String> unitKey = new ArrayList<>();
        List<String> resultList = new ArrayList<>();
        unitMap.forEach((x, y) -> {
            unitKey.add(x);
        });
        String[] arr = text.split(" ");
        for (String s : arr) {
            String trim = s.trim();
            if (StringUtils.isEmpty(trim)) {
                continue;
            }
            resultList.add(trim);

        }
        if (resultList.size() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < resultList.size(); i++) {
            String s = resultList.get(i);
            if (unitKey.contains(s)) {
                sb.append(s + " ");
                continue;
            }

            String result = parseDE(s, "");
            result = parseResult(result);

            sb.append(result + " ");

        }

        return sb.toString().trim();

    }

    /**
     * 将数字解析成一个一个的中间用空格分开
     * einhundertdreiunddreißig  -->  eins hundert drei dreißig
     *
     * @param time
     * @return
     */
    public static String parseDETime(String time) {
        String result = parseDE(time, "");
        result = parseResult(result);
        return result;
    }

    /**
     * 处理ßig,zig,zehn结尾的几十几
     *
     * @param str
     * @return
     */
    private static String parseResult(String str) {

        String[] s = str.split(" ");
        for (int i = 1; i < s.length; i++) {
            String s1 = s[i];
            if (StringUtils.isEmpty(s)) {
                continue;
            }
            if (s1.equals("zehn") || s1.equals("ßig") || s1.equals("zig")) {
                if (checkNum(s[i - 1])) {
                    s[i - 1] = s[i - 1] + s[i];
                    s[i] = "";
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length - 1; i++) {
            String s1 = s[i];
            if (StringUtils.isEmpty(s)) {
                continue;
            }
            sb.append(s1).append(" ");
        }
        sb.append(s[s.length - 1]);

        return sb.toString();
    }

    /**
     * 判断是否德语的个位数
     *
     * @param num
     * @return
     */
    private static boolean checkNum(String num) {
        switch (num) {
            case "hundert":
                return false;
            case "tausend":
                return false;
            case "Million":
                return false;
            default:
                return true;
        }
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
            //数字53
            double i = Double.parseDouble((list1.get(1).intValue() + "" + list1.get(0)));
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

    //
//
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

    //
    public static String parseDEResult(String str, int num) {
        if (str.length() > 1) {
            int maxBit = findMaxBit(str);

        }
        return null;

    }


    public static int findMaxBit(String str) {
        String substring = str.substring(0, 1);
        String substring2 = str.substring(1, 2);
        return 0;

    }

    /**
     * str 只能是德语的数字
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
                return result + "null";
            case "hal":
                if (str.length() == 4) {
                    return result + "halb";
                }
                return parseDE(str.substring(4), result + "halb ");
            case "ein":
                if (str.length() == 4 || str.length() == 3) {
                    return result + "eins";
                }
                return parseDE(str.substring(3), result + "eins ");
            case "zwe":
                if (str.length() == 4) {
                    return result + "zwei";
                }
                return parseDE(str.substring(4), result + "zwei ");
            case "dre":
                if (str.length() == 4) {
                    return result + "drei";
                }
                return parseDE(str.substring(4), result + "drei ");
            case "vie":
                if (str.length() == 4) {
                    return result + "vier";
                }
                return parseDE(str.substring(4), result + "vier ");
            case "Fün":
                if (str.length() == 4) {
                    return result + "Fünf";
                }
                return parseDE(str.substring(4), result + "Fünf ");
            case "sec":
                if (str.length() == 5) {
                    return result + "sechs";
                }
                return parseDE(str.substring(4), result + "sechs ");
            case "sie":
                if (str.length() == 6) {
                    return result + "sieben";
                }
                if (str.startsWith("sieben")) {
                    return parseDE(str.substring(6), result + "sieben ");
                }
                return parseDE(str.substring(4), result + "sieben ");
            case "ach":
                if (str.length() == 4) {
                    return result + "acht";
                }
                return parseDE(str.substring(4), result + "acht ");
            case "neu":
                if (str.length() == 4) {
                    return result + "neun";
                }
                return parseDE(str.substring(4), result + "neun ");
            case "zeh":
                if (str.length() == 4) {
                    return result + "zehn";
                }
                return parseDE(str.substring(4), result + "zehn ");
            case "elf":
                return result + "elf";
            case "Zwö":
                return result + "zwölf";
            case "zwa":
                return result + "zwanzig";
            case "ßig":
                return result + "ßig";
            case "zig":
                return result + "zig";
            case "hun":
                if (str.length() == 7) {
                    return result + "hundert";
                }
                return parseDE(str.substring(7), result + "hundert ");
            case "tau":
                if (str.length() == 7) {
                    return result + "tausend";
                }
                return parseDE(str.substring(7), result + "tausend ");
            case "Mil":
                if (str.equals("Million")) {
                    return result + "Millionen";
                } else if (str.equals("Millionen")) {
                    return result + "Millionen";
                } else if (str.startsWith("Millionen")) {
                    return parseDE(str.substring(9), result + "Millionen ");
                }
                return parseDE(str.substring(7), result + "Millionen ");
            default:
                return result;
        }

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
            case "Fün":
                result.add(5d);
                num.add(0);
                if (str.length() == 4) {
                    return;
                }
                parseDE(str.substring(4), result, num);
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
            case "Zwö":
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
