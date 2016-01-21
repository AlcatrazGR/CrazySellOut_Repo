package codebrains.crazysellout.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import codebrains.crazysellout.Fragments.GoogleMapsFragment;

import codebrains.crazysellout.Fragments.ProductsListAvtivity;
import codebrains.crazysellout.Fragments.UserFavoritesFragment;


/**
 * Created by Vasilhs on 1/21/2016.
 */
public class UserTabsAdapter extends FragmentStatePagerAdapter{

    private int TOTAL_TABS = 3;

    public UserTabsAdapter(FragmentManager fm) {
        super(fm);
        // TODO Auto-generated constructor stub
    }

    @Override
    public Fragment getItem(int index) {
        // TODO Auto-generated method stub
        switch (index) {
            case 0:
                return new ProductsListAvtivity();

            case 1:
                return new UserFavoritesFragment();

            case 2:
                return new GoogleMapsFragment();

        }

        return null;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return TOTAL_TABS;
    }

}
