package com.frame.admin.appframe.util.avoid;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;

/**
 * @author :lazyMing
 * email   :407555147@qq.com
 * date    :2018/9/17
 * desc    :规避onActivityForResulto
 * address :
 * update  :
 */
public class AvoidOnResult {

    private static final String TAG = "AvoidOnResult";

    private AvoidOnResultFragment mAvoidOnResultFragment;

    public AvoidOnResult(@NonNull Fragment fragment) {
        this(fragment.getActivity());
    }

    public AvoidOnResult(@NonNull Activity activity) {
        mAvoidOnResultFragment = getAvoidOnResultFragment(activity);
    }

    private AvoidOnResultFragment getAvoidOnResultFragment(Activity activity) {
        AvoidOnResultFragment avoidOnResultFragment = findAvoidOnResultFragment(activity);
        if (avoidOnResultFragment == null) {
            avoidOnResultFragment = new AvoidOnResultFragment();
            FragmentManager fragmentManager = activity.getFragmentManager();
            fragmentManager
                    .beginTransaction()
                    .add(avoidOnResultFragment, TAG)
                    .commitAllowingStateLoss();
            fragmentManager.executePendingTransactions();
        }
        return avoidOnResultFragment;
    }

    private AvoidOnResultFragment findAvoidOnResultFragment(Activity activity) {
        return (AvoidOnResultFragment) activity.getFragmentManager().findFragmentByTag(TAG);
    }

    public void startForResult(Class<?> clazz, Callback callback) {
        Intent intent = new Intent(mAvoidOnResultFragment.getActivity(), clazz);
        startForResult(intent, callback);
    }

    public void startForResult(Intent intent, Callback callback) {
        mAvoidOnResultFragment.startForResult(intent, callback);
    }

    public Observable<ActivityResultInfo> startForResult(Class<?> clazz) {
        final Intent intent = new Intent(mAvoidOnResultFragment.getActivity(), clazz);
        return startForResult(intent);
    }

    public Observable<ActivityResultInfo> startForResult(Intent intent) {
        return mAvoidOnResultFragment.startForResult(intent);
    }

    /**
     * 回调接口
     */
    public interface Callback {

        /**
         * 回调结果
         *
         * @param resultCode 结果code
         * @param data       结果data
         */
        void onActivityResult(int resultCode, Intent data);
    }
}
