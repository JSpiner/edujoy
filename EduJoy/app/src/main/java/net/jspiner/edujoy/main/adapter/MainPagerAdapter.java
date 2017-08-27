package net.jspiner.edujoy.main.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import net.jspiner.edujoy.main.tab.main.MainFragment;

public class MainPagerAdapter extends FragmentStatePagerAdapter {

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new MainFragment();
                break;
            case 1:
                fragment = new MainFragment();
                break;
            case 2:
                fragment = new MainFragment();
                break;
            case 3:
                fragment = new MainFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "main";
            case 1:
                return "recent";
            case 2:
                return "famous";
            case 3:
                return "main";
        }
        return super.getPageTitle(position);
    }
}
