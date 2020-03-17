package com.study.zhuweihong;

import org.springframework.util.StringUtils;

import java.util.*;

public class PigDemo2 {


    public static void main(String[] args) {

        System.out.println(parseDEToNum("einhundertdreiunddreißig"));//eins hundert sechs
        System.out.println(parseDEToNum("einhundertsechs"));//eins hundert sechs
        System.out.println(parseDEToNum("eintausendeinhundert"));
        System.out.println(parseDEToNum("einhundertdreizehn"));//eins hundert zehn drei
        System.out.println(parseDEToNum("elftausendeinhundertelf"));
        System.out.println(parseDEToNum("zwei"));

        parseDEToNum2("einhundertsechs");//eins hundert sechs
        parseDEToNum2("einhundertdreiunddreißig");//eins hundert sechs
        parseDEToNum2("eintausendeinhundert");
        parseDEToNum2("einhundertdreizehn");//eins hundert zehn drei
        parseDEToNum2("elftausendeinhundertelf");
        parseDEToNum2("zwei");


    }

    private static Map<String, String> unitMap = new HashMap<>();//时间单位

    /**
     * 德语数字直接转换成阿拉伯数字
     *
     * @param text
     * @return
     */
    public static int parseDEToNum(String text) {

        List<Integer> list1 = new ArrayList<>(10);
        List<Integer> list2 = new ArrayList<>(10);
        parseDE(text, list1, list2);
        List<Integer> list = new ArrayList<>(10);
        changeToNum(list, list1, list2);
        int result = 0;
        for (Integer integer : list) {
            result += integer;
        }
        return result;
    }

    public static void parseDEToNum2(String text) {
        List<String> unitKey = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        unitMap.forEach((x, y) -> {
            unitKey.add(x);
        });
        String[] arr = text.split(" ");
        for (String s : arr) {
            String trim = s.trim();
            if (StringUtils.isEmpty(trim)) {
                continue;
            }
            temp.add(trim);

        }
        for (int i = 0; i < temp.size(); i++) {
            String s = temp.get(i);
            if (unitKey.contains(s)) {
                continue;
            }
            List<Integer> list1 = new ArrayList<>(10);
            List<Integer> list2 = new ArrayList<>(10);
            parseDE(s, list1, list2);
            List<Integer> list = new ArrayList<>(10);
            changeToNum(list, list1, list2);
            Collections.sort(list);


            System.out.println(list);


        }

    }


    public static void changeToNum(List<Integer> list, List<Integer> list1, List<Integer> list2) {
        int maxBit = findMaxBit(list2);
        if (maxBit == 1) {
            if (list2.get(1) == 10000) {
                //数字十几
                list.add(list1.get(0) + 10);
            } else {
                //两百
                int i = list1.get(0) * power(10, list2.get(0)) * power(10, list2.get(1));
                list.add(i);
            }
        } else if (maxBit == 2) {
            //数字53
            int i = Integer.parseInt(list1.get(1) + "" + list1.get(0));
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
            case "Mil":
                if (str.equals("Million") || str.equals("Millionen")) {
                    return result + "百万";
                } else if (str.startsWith("Millionen")) {
                    return parseDE(str.substring(9), result + "百万");
                }
                return parseDE(str.substring(7), result + "百万");
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
    public static void parseDE(String str, List<Integer> result, List<Integer> num) {
        String temp = str.substring(0, 3);
        switch (temp) {
            case "und":
                parseDE(str.substring(3), result, num);
                break;
            case "nul":
                result.add(0);
                num.add(0);
                break;
            case "ein":
                result.add(1);
                num.add(0);
                if (str.length() == 4) {
                    return;
                }
                parseDE(str.substring(3), result, num);
                break;
            case "zwe":
                result.add(2);
                num.add(0);
                if (str.length() == 4) {
                    return;
                }
                parseDE(str.substring(4), result, num);
                break;
            case "dre":
                result.add(3);
                num.add(0);
                if (str.length() == 4) {
                    return;
                }
                parseDE(str.substring(4), result, num);
                break;
            case "vie":
                result.add(4);
                num.add(0);
                if (str.length() == 4) {
                    return;
                }
                parseDE(str.substring(4), result, num);
                break;
            case "Fün":
                result.add(5);
                num.add(0);
                if (str.length() == 4) {
                    return;
                }
                parseDE(str.substring(4), result, num);
            case "sec":
                result.add(6);
                num.add(0);
                if (str.length() == 5) {
                    return;
                }
                parseDE(str.substring(4), result, num);
                break;
            case "sie":
                result.add(7);
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
                result.add(8);
                num.add(0);
                if (str.length() == 4) {
                    return;
                }
                parseDE(str.substring(4), result, num);
                break;
            case "neu":
                result.add(9);
                num.add(0);
                if (str.length() == 4) {
                    return;
                }
                parseDE(str.substring(4), result, num);
                break;
            case "zeh":
                result.add(1);
                num.add(10000);
                if (str.length() == 4) {
                    return;
                }
                parseDE(str.substring(4), result, num);
                break;
            case "elf":
                result.add(11);
                num.add(0);
                if (str.length() == 3) {
                    return;
                }
                parseDE(str.substring(3), result, num);
                break;
            case "Zwö":
                result.add(12);
                num.add(0);
                if (str.length() == 5) {
                    return;
                }
                parseDE(str.substring(5), result, num);
                break;
            case "zwa":
                result.add(2);
                num.add(1);
                if (str.length() == 7) {
                    return;
                }
                parseDE(str.substring(7), result, num);
                break;
            case "ßig":
                result.add(1);
                num.add(1);
                if (str.length() == 3) {
                    return;
                }
                parseDE(str.substring(3), result, num);
                break;
            case "zig":
                result.add(1);
                num.add(1);
                if (str.length() == 3) {
                    return;
                }
                parseDE(str.substring(3), result, num);
                break;
            case "hun":
                result.add(1);
                num.add(2);
                if (str.length() == 7) {
                    return;
                }
                parseDE(str.substring(7), result, num);
                break;
            case "tau":
                result.add(1);
                num.add(3);
                if (str.length() == 7) {
                    return;
                }
                parseDE(str.substring(7), result, num);
                break;
            case "Mil":
                if (str.equals("Million") || str.equals("Millionen")) {
                    result.add(1);
                    num.add(6);
                    return;
                } else if (str.startsWith("Millionen")) {
                    result.add(1);
                    num.add(6);
                    parseDE(str.substring(9), result, num);
                }
                parseDE(str.substring(7), result, num);
                break;
            default:
                return;
        }

    }
}
