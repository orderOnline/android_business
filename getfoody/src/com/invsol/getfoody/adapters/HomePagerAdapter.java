package com.invsol.getfoody.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.invsol.getfoody.fragments.DeclinedOrdersFragment;
import com.invsol.getfoody.fragments.DispatchedOrdersFragment;
import com.invsol.getfoody.fragments.OngoingOrdersFragment;
import com.invsol.getfoody.fragments.RecentOrdersFragment;

public class HomePagerAdapter extends FragmentPagerAdapter{

	public HomePagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int index) {
		switch (index) {
        case 0:
            return new RecentOrdersFragment();
        case 1:
        	return new OngoingOrdersFragment();
        case 2:
        	return new DispatchedOrdersFragment();
        case 3:
        	return new DeclinedOrdersFragment();
        }
 
        return null;
	}

	@Override
	public int getCount() {
		return 4;
	}

}