package com.bignerdranch.android.criminalintent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by weichengyongxiao on 27/02/2018.
 */

public abstract class SingleFragmentActivity extends AppCompatActivity{

    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = createFragment();
            // transaction:事务
            // 下面的语句可以理解为创建一个新的fragment事务，执行一个fragment添加操作，然后提交该事务
            fm.beginTransaction().add(R.id.fragment_container, fragment).commit();
        }
    }
}
