//package com.study.zhuweihong;
//
//import java.util.Map;
//import java.util.regex.Pattern;
//
//public abstract class DurationUtils {
//    private static final String TAG = "DurationUtils";
//    private static final String BLANK = " ";
//    private static final double UNKNOWDOUBLEDATE = -1;
//    private static final double DEFAULT_VALUE = -1;
//    private static final int HOUR_TO_MINUTE = 60;
//    private static final int MINUTE_TO_SECOND = 60;
//    private static final int QUARTER_MINUTE = 15;
//    private static final int YEAR_TO_MONTH = 12;
//    private static final int MONTH_TO_DAY = 30;
//    private static final int WEEK_TO_DAY =7;
//    private static final int DAY_TO_HOUR = 24;
//    private static final int BASIC_TO_NUM = 10;
//
//    /**
//     * 转换为秒
//     * timeStr  时间字符串
//     * languageCode 语言信息
//     */
//    public static double transToSecond(String timeStr, String languageCode){
//        if (timeStr == null){
//            return UNKNOWDOUBLEDATE;
//        }
//        String timeSpan = timeStr;
//
//        Map<String, Pattern> patMap = DurationConstant.PATTERN_MAP.get(languageCode);
//
//
//    }
//
//}
