package com.example.rakna.raknagraduationproject.abdoCode;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.rakna.raknagraduationproject.R;

public class OwnerPreviewAdapter extends FragmentPagerAdapter {


    private Context mContext;


    public OwnerPreviewAdapter(Context context,FragmentManager fm) {
        super(fm);
        mContext = context ;
    }


    // Do not know which Fragment will be displayed , by position
    @Override
    public Fragment getItem(int position) {

        if (position == 0) {
            return new OwnerPreviewFragment();
        }else {
            return new OwnerNotificationFragment();
        }
    }

    // Number of fragment created
    @Override
    public int getCount() {
        return 2;
    }

    // when return the fragment to show it also return the page title
    // to add it in tab.
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.category_Owner_preview);
        }else {
            return mContext.getString(R.string.category_owner_notification);
        }
    }
}
