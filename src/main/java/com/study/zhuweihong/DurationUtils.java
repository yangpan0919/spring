//package com.study.zhuweihong;
//
//import com.fasterxml.jackson.databind.deser.DataFormatReaders;
//import com.sun.javafx.collections.MappingChange;
//import org.apache.tomcat.util.bcel.classfile.Constant;
//import org.omg.CORBA.INTERNAL;
//import org.springframework.util.CollectionUtils;
//import org.springframework.util.StringUtils;
//
//import java.nio.file.ProviderNotFoundException;
//import java.util.*;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public abstract class DurationUtils {
//    private static final String TAG = "DurationUtils";
//    private static final String BLANK = " ";
//    private static final double UNKNOWDOUBLEDATA = -1;
//    private static final double DEFAULT_VALUE = -1;
//    private static final int HOUR_TO_MINUTE = 60;
//    private static final int MINUTE_TO_SECOND = 60;
//    private static final int QUARTER_MINUTE = 15;
//    private static final int YEAR_TO_MONTH = 12;
//    private static final int MONTH_TO_DAY = 30;
//    private static final int WEEK_TO_DAY = 7;
//    private static final int DAY_TO_HOUR = 24;
//    private static final int BASIC_TO_NUM = 10;
//
//
//    /**
//     * 转换为秒
//     * timeStr  时间字符串
//     * languageCode 语言信息
//     */
//    public static double transToSecond(String timeStr, String languageCode) {
//        if (timeStr == null) {
//            return UNKNOWDOUBLEDATA;
//        }
//        String timeSpan = timeStr;
//
//        Map<String, Pattern> patMap = DurationConstant.PATTERN_MAP.get(languageCode);
//        if (MapUtils.isEmpty(patMap)) {
//            return UNKNOWDOUBLEDATA;
//        }
//        Map<String, Double> paramMap = new HashMap<String, Double>();
//        Pattern hourPat = patMap.get(DurationConstant.HOUR_KEY);
//        timeSpan = processUnitValue("hour", timeSpan, hourPat, paramMap, languageCode);
//
//        Pattern quarterPat = patMap.get(DurationConstant.QUARTER_KEY);
//        timeSpan = processUnitValue("hour", timeSpan, quarterPat, paramMap, languageCode);
//
//        Pattern minutePat = patMap.get(DurationConstant.MINUTE_KEY);
//        timeSpan = processUnitValue("hour", timeSpan, minutePat, paramMap, languageCode);
//
//        Pattern secondPat = patMap.get(DurationConstant.SECOND_KEY);
//        timeSpan = processUnitValue("hour", timeSpan, secondPat, paramMap, languageCode);
//
//        double hour = paramMap.get("hour");
//        double quarter = paramMap.get("quarter");
//        double minute = paramMap.get("minute");
//        double second = paramMap.get("second");
//        double total = (hour * HOUR_TO_MINUTE * MINUTE_TO_SECOND) + (quarter * QUARTER_MINUTE)
//                + (minute * MINUTE_TO_SECOND) + second;
//        if (total < 0) {
//            total = UNKNOWDOUBLEDATA;
//        }
//        return total;
//
//    }
//
//    /**
//     * 转换为秒,时间到年
//     *
//     * @param timeStr      时间字符串
//     * @param languageCode 语言信息
//     * @return 转换后的秒数
//     */
//    public static double transYearToSecond(String timeStr, String languageCode) {
//        if (timeStr == null) {
//            return UNKNOWDOUBLEDATA;
//        }
//        String timeSpan = timeStr.replaceAll("(.*?)(\\syears?|months?|weeks?|days?)(\\sand\\sa\\shalf)[\\s]?]");
//        double yearTotal = 0;
//        Map<String, Pattern> patMap = DurationConstant.PATTERN_MAP.get(languageCode);
//        if (MapUtils.isNotEmpty(patMap)) {
//            Map<String, Double> paramMap = new HashMap<String, Double>();
//            Pattern yearPat = patMap.get(DurationConstant.YEAR_KEY);
//            timeSpan = processUnitValue("year", timeSpan, yearPat, paramMap, languageCode);
//
//            Pattern monthPat = patMap.get(DurationConstant.MONTH_KEY);
//            timeSpan = processUnitValue("year", timeSpan, monthPat, paramMap, languageCode);
//
//            Pattern weekPat = patMap.get(DurationConstant.WEEK_KEY);
//            timeSpan = processUnitValue("year", timeSpan, weekPat, paramMap, languageCode);
//
//            Pattern dayPat = patMap.get(DurationConstant.DAY_KEY);
//            timeSpan = processUnitValue("year", timeSpan, dayPat, paramMap, languageCode);
//
//            double year = paramMap.get("year");
//            double month = paramMap.get("month");
//            double week = paramMap.get("week");
//            double day = paramMap.get("day");
//
//            yearTotal = ((year * YEAR_TO_MONTH * MONTH_TO_DAY) + (month * MONTH_TO_DAY) + (week * WEEK_TO_DAY) + day * DAY_TO_HOUR * HOUR_TO_MINUTE * MINUTE_TO_SECOND);
//
//        }
//        double hourTotal = transToSecond(timeSpan, languageCode);
//        double total = 0;
//        if ((yearTotal >= 0) && (hourTotal >= 0)) {
//            total = yearTotal + hourTotal;
//        } else if (yearTotal > 0) {
//            total = yearTotal;
//        } else if (hourTotal > 0) {
//            total = hourTotal;
//        } else {
//            total = UNKNOWDOUBLEDATA;
//        }
//        return total;
//    }
//
//
//    private static String processUnitValue(String unit, String timeSpan, Pattern pattern, Map<String, Double> paramMap, String languageCode) {
//        String tmpTimeSpan = timeSpan;
//        double value = 0;
//        try {
//            if (!StringUtils.isBlank(timeSpan)) {
//                Matcher matcher = pattern.matcher(timeSpan);
//                if (!matcher.matches()) {
//                    paramMap.put(unit, value);
//                    return tmpTimeSpan;
//                }
//                String number = matcher.group(Constant.GROUP_SECOND);
//                if (StringUtils.isBlank(number)) {
//                    value = transWordsToNum(number, languageCode);
//                    tmpTimeSpan = tmpTimeSpan.substring(matcher.end(Constant.GROUP_FIRST));
//                }
//            }
//        } catch (NumberFormatException e) {
//            LogUtil.d(TAG, "number is not double");
//        }
//        paramMap.put(unit, value);
//        return tmpTimeSpan;
//
//    }
//
//    private static double transWordsToNum(String duration, String languageCode) {
//        String tmpDuration = normalizedText(duration, languageCode);
//        ArrayList<String> numList = preProcessNum(tmpDuration, languageCode);
//        return countValue(numList, languageCode);
//
//    }
//
//    private static ArrayList<String> preProcessNum(String duration, String languageCode) {
//        String tmpText = duration.replaceAll("[\\s]+", BLANK);
//        tmpText = tmpText.replaceAll("(?<![\\d])[,]", BLANK);
//        tmpText = tmpText.toLowerCase(Locale.ENGLISH);
//        String[] textArray = tmpText.split(BLANK);
//
//        Map<String, Integer> numbersMap = DurationConstant.NUMBERS_MAP.get(languageCode);
//        Map<String, Integer> numberUnitMap = DurationConstant.NUMBER_UNIT_MAP.get(languageCode);
//
//        //替换文本中的英文数字单词为数字,百千万等单位单词保留
//        ArrayList<String> numList = new ArrayList<String>(Constant.INIT_LENGTH);
//        boolean isDecimalFlag = false;
//        StringBuffer decimaBuff = new StringBuffer();
//        for (String text : textArray) {
//            //替换单个单词数字,比如one替换1
//            if (!MapUtils.isEmpty(numbersMap) && numbersMap.containsKey(text)) {
//                //为小数部分数字时,先存buf,待小数部分结束时,一起添加
//                if (isDecimalFlag) {
//                    decimaBuff.append(numbersMap.get(text));
//                } else {
//                    numList.add(text);
//                }
//                continue;
//            }
//            //百千万或者纯数字等暂保留原单词
//            if (!MapUtils.isEmpty(numberUnitMap) && numberUnitMap.containsKey(text) || text.matches("[\\d]+|[\\d]+[.][\\d]+")) {
//                //当遇到单位数词时,小数部分结束
//                if (isDecimalFlag) {
//                    numList.add(decimaBuff.toString());
//                    isDecimalFlag = false;
//                }
//                numList.add(text);
//                continue;
//            }
//            if (text.matches("(?:[.])")) {
//                isDecimalFlag = true;
//                decimaBuff.append("0.");
//            }
//        }
//        //小数部分在最后场景
//        if (isDecimalFlag) {
//            numList.add(decimaBuff.toString());
//        }
//        return numList;
//
//    }
//    private static double countValue(ArrayList<String> numList, String languageCode) {
//        if (CollectionUtils.isEmpty(numList)){
//            return DEFAULT_VALUE;
//        }
//        Map<String,Integer> numberUnitMap = DurationConstant.NUMBER_UNIT_MAP.get(languageCode);
//
//        double total = 0;
//        double tmpNum = 0;
//        int j = 0;
//        try {
//            for (int i = 0; i < numList.size() ; i++) {
//                String num = numList.get(i);
//                if (i==0 && numberUnitMap.containsKey(num)){
//                    tmpNum = Math.pow(BASIC_NUM,numberUnitMap.get(num));
//                    continue;
//                }
//                //为单位数字时,单位前的数字总和和单位值相乘
//                //比如1 hunderd 21 million,为前面的1 hunderd 21相加为121后,和后面的million的值相乘
//                if (numberUnitMap.containsKey(num) && isUnit(numList,num,i,languageCode)){
//                    tmpNum*=Math.pow(BASIC_NUM,numberUnitMap.get(num));
//                    total += tmpNum;
//                    tmpNum = 0;
//                    j=i;
//                }
//                //为单位单词,但当前句中,不是作为单位数词存在,则直接累计
//                //比如1 hunderd 21 million 中单位数词为million,hunderd不是
//                else if (numberUnitMap.containsKey(num)){
//                    tmpNum = tmpNum*Math.pow(BASIC_NUM,numberUnitMap.get(num));
//                }
//                //非单位数字时,直接相加累计
//                else {
//                    tmpNum += Double.parseDouble(num);
//                }
//            }
//            //最后单词非单位词时,需加上,比如1 hunderd 21,hunderd作为单位词,21需最后加上
//            if (j != numList.size()-1 || j==0){
//                total += tmpNum;
//            }
//            return total;
//        }catch (NumberFormatException e){
//            LogUtil.d(TAG,"number is not double");
//        }
//        return DEFAULT_VALUE;
//    }
//
//    private static boolean isUnit(ArrayList<String> numList, String num, int index, String languageCode) {
//        if (CollectionUtils.isEmpty(numList)){
//            return true;
//        }
//        Map<String,Integer> numberUnitMap = DurationConstant.NUMBER_UNIT_MAP.get(languageCode);
//
//        //循环当前单位单词后的单位,如果存在值大于当前单位的单词,则说明当前单位单词非单词存在.
//        //比如one hunderd and twenty-one million ,当前单词是hunderd时,后面的million才是单位单词
//        for (int i=index+1;i<numList.size();i++){
//            String nextNum = numList.get(i);
//            if (!numberUnitMap.containsKey(nextNum)){
//                continue;
//            }
//            if (numberUnitMap.get(nextNum) > numberUnitMap.get(num)){
//                return false;
//            }
//        }
//        return true;
//    }
//
//    private static String normalizedText(String text, String languageCode) {
//        List<String> keyList = new ArrayList<String>(Constant.INIT_LENGTH);
//        Map<String , Integer> numberUnitMap = DurationConstant.NUMBER_UNIT_MAP.get(languageCode);
//        for (String key: numberUnitMap.keySet()) {
//            keyList.add(key);
//        }
//        Map<String ,Integer> numbersMap = DurationConstant.NUMBERS_MAP.get(languageCode);
//        for (String key: numbersMap.keySet()) {
//            keyList.add(key);
//        }
//        //将key按照长度从大到小排序
//        keyList.sort((o1, o2) -> o2.length()-o1.length());
//
//        //将排序后的keyList,替换到文本中,前后加空格,方便后面做拆分
//        String tmpText = text;
//        for (String keyStr:keyList) {
//            tmpText = tmpText.replaceAll("[\\s]+",BLANK);
//            String[] textArray = tmpText.split(BLANK);
//            StringBuffer textBuffer = new StringBuffer();
//            for (String txt:textArray) {
//                //单个单词为可识别的数字,则不再做处理,以免单个数字单词被拆分为其他的多个数字单词
//                //比如法语中:
//                if (keyList.contains(txt)){
//                    textBuffer.append(BLANK+txt+BLANK);
//                    continue;
//                }
//                if (txt.contains(keyStr)){
//                    textBuffer.append(txt.replace(keyStr,BLANK+keyStr+BLANK));
//                    continue;
//                }
//                textBuffer.append(BLANK+txt+BLANK);
//            }
//            tmpText = textBuffer.toString();
//        }
//        return tmpText;
//
//    }
//
//
//}
