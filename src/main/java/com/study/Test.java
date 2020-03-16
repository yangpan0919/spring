package com.study;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Test {
    public volatile int inc = 0;

    public void increase() {
        inc++;
    }

    public static void main(String[] args) {

        List<List<String>> list = new ArrayList<>();  //改list为你的所有数据
        List<String> tempList = new ArrayList<>();
        List<String> tempList1 = new ArrayList<>();
        List<String> tempList2 = new ArrayList<>();
        List<String> tempList3 = new ArrayList<>();
        List<String> tempList4 = new ArrayList<>();
        List<String> tempList5 = new ArrayList<>();

        tempList.add("1");
        tempList.add("2");

        tempList1.add("3");
        tempList1.add("4");


        tempList2.add("2");
        tempList2.add("4");

        tempList3.add("7");
        tempList3.add("8");

        tempList4.add("5");
        tempList4.add("3");

        tempList5.add("1");
        tempList5.add("3");




        list.add(tempList);
        list.add(tempList1);
        list.add(tempList2);
        list.add(tempList3);
        list.add(tempList4);
        list.add(tempList5);


        for (int i = 0; i < list.size(); i++) {
            List<String> sourceList = list.get(i);

            for (int j = 0; j < list.size(); j++) {
                if (j == i) {
                    continue;
                }
                List<String> targetList = list.get(j);
                if (needMerge(sourceList, targetList)) {//判断两个list是否需要合并,true  需要合并, false  不合并
                    if (i > j) {
                        targetList.addAll(sourceList);
                        list.remove(i);
                        i--;
                        break;
                    } else {
                        sourceList.addAll(targetList);
                        list.remove(j);
                        j--;
                    }
                }
            }
        }
        System.out.println(list.size());
        //addAll 合并操作用的是需要去重
        for (int i = 0; i < list.size(); i++) {
            List<String> strings = list.get(i);
            Set<String> set = new HashSet<>(strings);
            strings = new ArrayList<>(set);
            list.set(i, strings);
        }
        //结束
        System.out.println(list);
    }

    private static boolean needMerge(List<String> sourceList, List<String> targetList) {
        for (int i = 0; i < sourceList.size(); i++) {
            for (int j = 0; j < targetList.size(); j++) {
                if (sourceList.get(i).equals(targetList.get(j))) {
                    return true;
                }
            }
        }
        return false;
    }
}