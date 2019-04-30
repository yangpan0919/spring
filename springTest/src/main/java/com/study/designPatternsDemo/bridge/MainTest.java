package com.study.designPatternsDemo.bridge;

import com.study.designPatternsDemo.bridge.brideg.TvControl;
import com.study.designPatternsDemo.bridge.brideg.NewTvControl;
import com.study.designPatternsDemo.bridge.control.LGControl;
import com.study.designPatternsDemo.bridge.control.SharpControl;
import com.study.designPatternsDemo.bridge.control.SonyControl;

/**
 * @author yp
 * @data 2019/3/27 17:43
 */
public class MainTest {
    public static void main(String[] args) {
        TvControl mLGTvControl;
        TvControl mSonyTvControl;
        mSonyTvControl = new TvControl(new SonyControl());
        mLGTvControl = new TvControl(new LGControl());
        mLGTvControl.onOff();
        mLGTvControl.nextChannel();
        mLGTvControl.nextChannel();
        mLGTvControl.preChannel();
        mLGTvControl.onOff();
        mSonyTvControl.onOff();
        mSonyTvControl.preChannel();
        mSonyTvControl.preChannel();
        mSonyTvControl.preChannel();
        mSonyTvControl.onOff();

        NewTvControl mSharpTvControl;
        mSharpTvControl = new NewTvControl(new SharpControl());
        mSharpTvControl.onOff();
        mSharpTvControl.nextChannel();
        mSharpTvControl.setChannel(9);
        mSharpTvControl.setChannel(28);
        mSharpTvControl.back();
        mSharpTvControl.onOff();
    }
}
