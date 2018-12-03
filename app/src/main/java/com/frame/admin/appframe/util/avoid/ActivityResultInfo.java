package com.frame.admin.appframe.util.avoid;

import android.content.Intent;

/**
 * @author :lazyMing
 * email   :407555147@qq.com
 * date    :2018/9/17
 * desc    :活动返回实体
 * address :
 * update  :
 */
public class ActivityResultInfo {

    private int resultCode;
    private Intent resultData;

    public ActivityResultInfo() {
    }

    public ActivityResultInfo(int resultCode, Intent resultData) {
        this.resultCode = resultCode;
        this.resultData = resultData;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public Intent getResultData() {
        return resultData;
    }

    public void setResultData(Intent resultData) {
        this.resultData = resultData;
    }
}
