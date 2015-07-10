package by.collider.prototype;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class CardsPagerAdapter extends FragmentPagerAdapter {
    enum Categories {
        PRONOUNS("Местоимения"), MODALS("Модальность"), ACTIONS("Действия"), OBJECTS("Объекты");

        private final String text;

        Categories(final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }
    }

    public CardsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new PageFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("page_position", position);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return Categories.values().length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return Categories.values()[position].toString();
    }
}
