package marius.mac.com.raandroid_header.presenters;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import marius.mac.com.raandroid_header.R;
import marius.mac.com.raandroid_header.model.Example;
import marius.mac.com.raandroid_header.observables.IRestaurant;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Marius on 04/04/2016.
 */
public class RestaurantPresenter {
    //Composite Subscription
    @Inject
    IRestaurant mIRestaurantInterface;
    private final RestaurantView mView;

    public RestaurantPresenter(RestaurantView view) {
        this.mView = view;
    }

    public Subscription retrieveRestaurants(String postcode) {

        Subscription subscription = mIRestaurantInterface.getRestraurent(postcode)
                .timeout(1000, TimeUnit.MILLISECONDS)
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends Example>>() {
                    @Override
                    public Observable<? extends Example> call(Throwable throwable) {
                        mView.displayRxError(R.string.rx_error);
                        return Observable.error(throwable);
                    }
                })
                .retry()
                .distinct()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Example>() {
                    @Override
                    public void onCompleted() {
                        mView.hidePDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.hidePDialog();
                    }

                    @Override
                    public void onNext(Example example) {
                        mView.setListAdapter(example.getRestaurants());
                    }
                });
        return subscription;
    }

    public int getAdapterListSize() {
        return mView.getAdapterListSize();
    }

    public void onSearchButtonClicked() {
        String searchText = mView.getSearchViewValue();
        if (searchText.isEmpty()) {
            mView.showSearchEmptyErrorText(R.string.search_empty_text_error);
            return;
        }
        mView.searchByPostcode(searchText);
    }
}
