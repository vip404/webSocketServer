package com.bwck.mytest.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import com.bwck.mytest.R;
import com.bwck.mytest.viewPagers.FirstViewPager;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * @ClassName ViewPagerTestActivity
 * @Description TODO
 * @Author 14133
 * @Date 2019/5/14 17:51
 * @Version 1.0
 **/
public class ViewPagerTestActivity extends Activity {


    private ViewPager viewPager;
    private ArrayList<View> viewArrayList = new ArrayList<View>();;
    private FirstViewPager mAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_view);
        viewPager  = findViewById(R.id.my_view_pager);

        LayoutInflater layoutInflater =  getLayoutInflater();
        viewArrayList.add(layoutInflater.inflate(R.layout.fragment_first,null));
        viewArrayList.add(layoutInflater.inflate(R.layout.fragment_second,null));
        viewArrayList.add(layoutInflater.inflate(R.layout.fragment_third,null));
        FirstViewPager firstViewPager = new FirstViewPager(viewArrayList);
        viewPager.setAdapter(firstViewPager);
    }
}
