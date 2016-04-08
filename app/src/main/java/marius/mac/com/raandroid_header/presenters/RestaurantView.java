package marius.mac.com.raandroid_header.presenters;

import java.util.List;

import marius.mac.com.raandroid_header.model.Restaurant;

/**
 * Created by Marius on 04/04/2016.
 */
public interface RestaurantView {
    void hidePDialog();

    void setListAdapter(List<Restaurant> restaurants);

    void displayRxError(int resId);

    String getSearchViewValue();

    void searchByPostcode(String query);

    int getAdapterListSize();

    void showSearchEmptyErrorText(int resId);
}
