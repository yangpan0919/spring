//package com.study.zhuweihong;
//
//import org.springframework.util.CollectionUtils;
//import org.springframework.util.StringUtils;
//import sun.plugin2.message.Message;
//
//import java.text.DecimalFormat;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
///**
// * 时间计量实体分析类
// */
//public class DurationEntityAnalyzer {
//    private static final String TAG = "FrDurationEntityAnalyzer";
//
//    @Override
//    public void analyze(Message message) {
//        String txt = " " + message.getOriMsg() + " ";
//        if (StringUtils.isBlank(text)) {
//            LogUtil.e(TAG, "process text is null");
//            return;
//        }
//
//        List<DurationBean> durationList = new ArrayList<>(Constant.INIT_LENGTH);
//        RuleEngine engine = new RuleEngine();
//        engine.setText(text);
//        engine.setMultiMatch(true);
//        if (RuleEngineAdapter.excuteRuleEngine(engine, "DurationEntity_FRExecuter")) {
//            List<Map<String, Object>> temSessionLists = engine.getSessionList();
//            if (CollectionUtils.isEmpty(temSessionLists)) {
//                return;
//            }
//            for (Map<String, Object> sessions : temSessionLists) {
//                String durationStr = (String) sessions.get("duration");
//                if (StringUtils.isEmpty(durationStr)) {
//                    continue;
//                }
//                int durationIndex = (int) sessions.get("durationIndex");
//                DurationBean durationBean = new DurationBean();
//                int sequence = CommonFun.getSequence(text, durationStr, durationIndex);
//                durationBean.setOriText(durationStr.trim());
//                durationBean.setSequence(sequence);
//
//                //归一化时长单位为秒
//                if (setDuration(durationBean, durationStr)) {
//                    durationList.add(durationBean);
//                }
//            }
//        }
//        if (!CollectionUtils.isEmpty(durationList)) {
//            SessionManager.addEntity(ConstantEntity.SYS_DURATION_KEY, message, durationList);
//        }
//    }
//
//    private boolean setDuration(DurationBean durationBean, String duration) {
//        String durationTmp = "duration.";
////        String durationTmp = duration.replaceAll("(.*?)(\\sheures?|minutes?|secondes?)")
//        double time = DurationUtils.transToSecond(durationTmp, "FR");
//        if (time >= 0) {
//            DecimalFormat format = new DecimalFormat("0.##");
//            durationBean.setNumber(format.format(time));
//            return true;
//        }
//        return false;
//    }
//
//
//}
//
//
//
//
//
//}
