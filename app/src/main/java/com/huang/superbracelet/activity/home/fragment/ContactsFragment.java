package com.huang.superbracelet.activity.home.fragment;

import android.view.LayoutInflater;
import android.view.View;

import com.huang.superbracelet.R;
import com.huang.superbracelet.base.BaseFragment;

/**
 * Created by 黄家远 on 16/4/19.
 */
public class ContactsFragment extends BaseFragment{


    public static ContactsFragment newInstance() {
        ContactsFragment fragment = new ContactsFragment();
        return fragment;
    }


    @Override
    protected void initWidget() {

    }

    @Override
    protected View buildMainView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.contacts_fragment_layout,null);
    }
}
