package com.frame.admin.appframe;

import android.content.Intent;
import android.os.Bundle;

import com.frame.admin.appframe.ui.login.LoginActivity;
import com.frame.admin.skeleton.Skeleton;
import com.frame.admin.skeleton.SkeletonScreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class TestActivity extends AppCompatActivity {

    private SkeletonScreen mSkeletonScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.hello_world).setOnClickListener(v -> {
            if (mSkeletonScreen != null) {
                mSkeletonScreen.hide();
            }
            startActivity(new Intent(TestActivity.this, LoginActivity.class));
        });

        RecyclerView rv = findViewById(R.id.rv_test);
        rv.setLayoutManager(new LinearLayoutManager(this));
        mSkeletonScreen = Skeleton.bind(rv)
                .shimmer(true)
                .angle(20)
                .frozen(true)
                .duration(1200)
                .count(4)
                .load(R.layout.my_demo_view)
                .show();
    }
}
