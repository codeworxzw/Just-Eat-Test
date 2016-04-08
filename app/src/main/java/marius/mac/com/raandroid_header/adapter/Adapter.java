package marius.mac.com.raandroid_header.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import marius.mac.com.raandroid_header.R;
import marius.mac.com.raandroid_header.model.Restaurant;


/**
 * Created by Marius on 04/04/2016.
 */
public class Adapter extends ArrayAdapter<Restaurant> {
    private static final String TAG = Adapter.class.getSimpleName();
    @Inject
    Picasso getPicasso;
    //String url="https://gist.githubusercontent.com/hart88/198f29ec5114a3ec3460/raw/8dd19a88f9b8d24c23d9960f3300d0c917a4f07c/";
    private Context context;
    private List<Restaurant> flowerList;

    public Adapter(Context context, int resource, List<Restaurant> objects) {
        super(context, resource, objects);
        this.context = context;
        this.flowerList = objects;
    }

    @Override
    public int getCount() {
        return flowerList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.row, parent, false);
        Restaurant res = flowerList.get(position);
        TextView tv = (TextView) view.findViewById(R.id.title);
        tv.setText(res.getName());

        ImageView img = (ImageView) view.findViewById(R.id.thumbnail);
        getPicasso.with(getContext())
                .load(res.getLogo().get(0).getStandardResolutionURL())
                .resize(100, 100)
                .into(img);

        Log.i(TAG, "getView: URL: " + res.getLogo().get(0).getStandardResolutionURL());

    /*    // genre
        String genreStr = "";
        for (String str : movies.getGenre()) {
            genreStr += str + ", ";
        }
        genreStr = genreStr.length() > 0 ? genreStr.substring(0,
                genreStr.length() - 2) : genreStr;
        tvGenre.setText(genreStr);

*/

        //    TextView tvRating = (TextView) view.findViewById(R.id.rating);
        //   tvRating.setText(res.getCuisineTypes().toString());

        //    TextView tvYear = (TextView) view.findViewById(R.id.releaseYear);
        //  tvYear.setText(res.getNumberOfRatings().toString());

        return view;
    }
}