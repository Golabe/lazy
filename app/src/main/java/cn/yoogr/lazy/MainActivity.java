package cn.yoogr.lazy;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import cn.yoogr.lazy.adapter.PagerAdapter;
import cn.yoogr.lazy.fragment.AFragment;
import cn.yoogr.lazy.fragment.BFragment;
import cn.yoogr.lazy.fragment.CFragment;

public class MainActivity extends RxAppCompatActivity {
    private ViewPager mViewPager;
    private List<Fragment> mFragments;
    private PagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mViewPager = findViewById(R.id.view_pager);
        mFragments = new ArrayList<>();

        mFragments.add(new AFragment());
        mFragments.add(new BFragment());
        mFragments.add(new CFragment());

        mAdapter = new PagerAdapter(getSupportFragmentManager(), mFragments);
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setAdapter(mAdapter);
    }
}
