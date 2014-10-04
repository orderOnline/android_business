package com.invsol.getfoody.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.invsol.getfoody.fragments.ProfileDetailsFragment;
import com.invsol.getfoody.fragments.ProfileReviewsFragment;

public class ProfilePagerAdapter  extends FragmentPagerAdapter{

	public ProfilePagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int index) {
		switch (index) {
        case 0:
            return new ProfileDetailsFragment();
        case 1:
        	return new ProfileReviewsFragment();
        }
 
        return null;
	}

	@Override
	public int getCount() {
		return 2;
	}
	
}
