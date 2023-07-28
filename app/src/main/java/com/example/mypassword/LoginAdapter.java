package com.example.mypassword;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class LoginAdapter extends FragmentPagerAdapter {

    private final int totalTabs;

    public LoginAdapter(FragmentManager fragmentManager, Context context, int totalTabs) {
        super(fragmentManager);
        this.totalTabs = totalTabs;
    }

    @Override
    public int getCount() {
        return totalTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new LoginTabFragment();
            case 1:
                return new SignUpTabFragment();
            default:
                return null;
        }
    }
}
