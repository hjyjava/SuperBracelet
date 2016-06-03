package com.huang.superbracelet.activity.home;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.huang.superbracelet.R;
import com.huang.superbracelet.activity.home.fragment.ContactsFragment;
import com.huang.superbracelet.activity.home.fragment.DynamicFragment;
import com.huang.superbracelet.activity.home.fragment.InfoFragment;
import com.huang.superbracelet.adapter.TabTitleAdapter;
import com.huang.superbracelet.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页
 * Created by 黄家远 on 16/5/5.
 */
public class HomeActivity extends BaseActivity {

    private Toolbar home_toolbar;
    private NavigationView home_nv;
    private DrawerLayout home_dl;
    private TabLayout home_tl;
    private ViewPager home_vp;

    private ContactsFragment contactsFragment;
    private DynamicFragment dynamicFragment;
    private InfoFragment infoFragment;

    private List<Fragment> mFranments = new ArrayList<>();
    private List<String> mNames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initWedgit();
    }

    @Override
    protected void initWedgit() {
        home_toolbar = (Toolbar) findViewById(R.id.home_toolbar);
        home_nv = (NavigationView) findViewById(R.id.home_nv);
        home_dl = (DrawerLayout) findViewById(R.id.home_dl);
        home_tl = (TabLayout) findViewById(R.id.home_tl);
        home_vp = (ViewPager) findViewById(R.id.home_vp);

        home_nv.setNavigationItemSelectedListener(naviListener);

        mNames.add("消息");
        mNames.add("联系人");
        mNames.add("动态");
        contactsFragment = ContactsFragment.newInstance();
        dynamicFragment = DynamicFragment.newInstance();
        infoFragment = InfoFragment.newInstance();
        mFranments.add(contactsFragment);
        mFranments.add(dynamicFragment);
        mFranments.add(infoFragment);

        TabTitleAdapter adapter = new TabTitleAdapter(getSupportFragmentManager(), mFranments, mNames);
        home_vp.setAdapter(adapter);
        home_vp.setOffscreenPageLimit(2);
        home_tl.setupWithViewPager(home_vp);
    }

    private NavigationView.OnNavigationItemSelectedListener naviListener = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.info_item:
                    home_vp.setCurrentItem(0);
                    break;
                case R.id.contacts_item:
                    home_vp.setCurrentItem(1);
                    break;
                case R.id.dynamic_item:
                    home_vp.setCurrentItem(2);
                    break;
            }
            //关闭DrawerLayout回到主界面选中的tab的fragment页
            home_dl.closeDrawer(home_nv);
            return false;
        }
    };

}
