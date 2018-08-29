package com.zcf.nanyangranzhi;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.widget.Toast;

import com.yinglan.alphatabs.AlphaTabsIndicator;
import com.zcf.nanyangranzhi.adapter.MainAdapter;
import com.zcf.nanyangranzhi.base.BaseActivity;
import com.zcf.nanyangranzhi.fragment.ContactsFragment;
import com.zcf.nanyangranzhi.fragment.EmailFragment;
import com.zcf.nanyangranzhi.fragment.MainFragment;
import com.zcf.nanyangranzhi.fragment.MyFragment;
import com.zcf.nanyangranzhi.utils.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    private MainAdapter mainAdapter;
    @BindView(R.id.viewPager)
    CustomViewPager viewPager;
    @BindView(R.id.alphaIndicator)
    AlphaTabsIndicator alphaIndicator;

    List<Fragment> fragmentList = new ArrayList<>();
    private Fragment emailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        ButterKnife.bind(this);
//        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());
//        StatusBarCompat.compat(this,getResources().getColor(R.color.color_2d97fb));
        emailFragment = EmailFragment.newInstance();
        fragmentList.add(MainFragment.newInstance());
        fragmentList.add(ContactsFragment.newInstance());
        fragmentList.add(emailFragment);
        fragmentList.add(MyFragment.newInstance());

        mainAdapter = new MainAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setOffscreenPageLimit(1);

        viewPager.setAdapter(mainAdapter);
        alphaIndicator.setViewPager(viewPager);


    }

    @Override
    public int getview() {
        return R.layout.activity_main;
    }

    private long timeMillis;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - timeMillis) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                timeMillis = System.currentTimeMillis();
            } else {
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 302) {
            emailFragment.setUserVisibleHint(true);
        }
    }
}
