package marius.mac.com.raandroid_header;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import marius.mac.com.raandroid_header.adapter.Adapter;
import marius.mac.com.raandroid_header.model.Restaurant;
import marius.mac.com.raandroid_header.presenters.RestaurantPresenter;
import marius.mac.com.raandroid_header.presenters.RestaurantView;
import marius.mac.com.raandroid_header.utilities.RxUtils;
import rx.subscriptions.CompositeSubscription;
/**
 * Created by Marius on 04/04/2016.
 */
public class MainActivity extends AppCompatActivity implements RestaurantView {

    @Bind(R.id.etSearchField)
    EditText etSearchField;
    @Bind(R.id.btSearch)
    Button btSearch;
    @Bind(R.id.lvRestaurants)
    ListView listView;
    private RestaurantPresenter presenter;
    private List<Restaurant> mRestaurantList;
    private ProgressDialog pDialog;


    /**
     * Subscription that represents a group of Subscriptions that are unsubscribed together.
     */
    private CompositeSubscription _subscriptions = new CompositeSubscription();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new RestaurantPresenter(this);
        ((MyApp) getApplication()).getApiComponent().inject(presenter);
        ButterKnife.bind(this);
//        setSupportActionBar(search_bar);
        setupSearchbar();
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
//        pDialog.show();
//        _subscriptions.add(presenter.retrieveRestaurants(Constants.TEST_POSTCODE));

    }

    private void setupSearchbar() {
        etSearchField.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.setFocusable(true);
                v.setFocusableInTouchMode(true);
                return false;
            }
        });

        btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onSearchButtonClicked();
            }
        });
    }

//    private void setupSearchView() {
//        searchView.setQueryHint(getString(R.string.searchview_hint_text));
////        searchView.setIconifiedByDefault(false);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                _subscriptions.add(presenter.retrieveRestaurants(query));
//                return true;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//        });
//    }

    @Override
    public void onResume() {
        super.onResume();
        _subscriptions = RxUtils.getNewCompositeSubIfUnsubscribed(_subscriptions);
    }

    @Override
    public void onPause() {
        super.onPause();
        RxUtils.unsubscribeIfNotNull(_subscriptions);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

    @Override
    public void setListAdapter(List<Restaurant> restaurants) {
        mRestaurantList = restaurants;
        System.out.println("Got: " + " (" + Thread.currentThread().getName() + ")");
        Adapter adapt = new Adapter(this, R.layout.row, mRestaurantList);
        listView.setAdapter(adapt);
    }

    @Override
    public void displayRxError(int resId) {
        Toast.makeText(this, getString(resId).toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public String getSearchViewValue() {
        return etSearchField.getText().toString();
    }

    @Override
    public void searchByPostcode(String query) {
        _subscriptions.add(presenter.retrieveRestaurants(query));
    }

    @Override
    public int getAdapterListSize() {
        return listView.getAdapter().getCount();
    }

    @Override
    public void showSearchEmptyErrorText(int resId) {
        etSearchField.setError(getString(resId));
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
