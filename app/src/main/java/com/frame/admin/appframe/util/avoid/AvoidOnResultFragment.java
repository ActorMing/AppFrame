package com.frame.admin.appframe.util.avoid;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;

/**
 * @author :lazyMing
 * email   :407555147@qq.com
 * date    :2018/9/17
 * desc    :
 * address :
 * update  :
 */
public class AvoidOnResultFragment extends Fragment {

    private Map<Integer, PublishSubject<ActivityResultInfo>> mSubjects = new HashMap<>();
    private Map<Integer, AvoidOnResult.Callback> mCallbacks = new HashMap<>();

    public AvoidOnResultFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    /**
     * startForResult by callback method
     *
     * @param intent   意图
     * @param callback 回调接口
     */
    public void startForResult(@NonNull Intent intent, @NonNull AvoidOnResult.Callback callback) {
        final int key = callback.hashCode();
        mCallbacks.put(key, callback);
        startActivityForResult(intent, key);
    }

    /**
     * startForResult by rxJava method
     *
     * @param intent
     * @return
     */
    public Observable<ActivityResultInfo> startForResult(@NonNull Intent intent) {
        final PublishSubject<ActivityResultInfo> subject = PublishSubject.create();
        final int key = subject.hashCode();
        return subject.doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {
                mSubjects.put(key, subject);
                startActivityForResult(intent, key);
            }
        });
    }


    /**
     * 活动结果处理
     *
     * @param requestCode 请求码
     * @param resultCode  响应码
     * @param data        结果数据
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // rxJava 方式处理
        PublishSubject<ActivityResultInfo> subject = mSubjects.remove(requestCode);
        if (subject != null) {
            subject.onNext(new ActivityResultInfo(resultCode, data));
            subject.onComplete();
        }

        // callback 方式处理
        AvoidOnResult.Callback callback = mCallbacks.remove(requestCode);
        if (callback != null) {
            callback.onActivityResult(resultCode, data);
        }
    }
}
