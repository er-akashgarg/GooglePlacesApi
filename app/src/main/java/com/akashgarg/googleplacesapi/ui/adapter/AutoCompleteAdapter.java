package com.akashgarg.googleplacesapi.ui.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.text.style.CharacterStyle;
import android.text.style.StyleSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.akashgarg.googleplacesapi.MMConverter.LanguageConverter;
import com.akashgarg.googleplacesapi.MMConverter.MMText;
import com.akashgarg.googleplacesapi.R;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.model.AutocompleteSessionToken;
import com.google.android.libraries.places.api.model.RectangularBounds;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsResponse;
import com.google.android.libraries.places.api.net.PlacesClient;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by Akash Garg on 10/29/19.
 */


public class AutoCompleteAdapter extends ArrayAdapter<AutocompletePrediction> implements Filterable {

    private static final CharacterStyle STYLE_BOLD = new StyleSpan(Typeface.BOLD);

    private ArrayList<AutocompletePrediction> mResultList;
    private PlacesClient placesClient;

    public AutoCompleteAdapter(Context context, PlacesClient placesClient) {
        super(context, R.layout.row_street_details, R.id.text_street_name);
        this.placesClient = placesClient;
    }

    @Override
    public int getCount() {
        return mResultList.size();
    }

    @Override
    public AutocompletePrediction getItem(int position) {
        return mResultList.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View row = super.getView(position, convertView, parent);

        AutocompletePrediction item = getItem(position);

        TextView textView1 = row.findViewById(R.id.text_street_name);
        TextView textView2 = row.findViewById(R.id.text_street_sub_name);


//        TextView textView1 = row.findViewById(android.R.id.text1);
//        TextView textView2 = row.findViewById(android.R.id.text2);
        if (item != null) {
//            textView1.setText(item.getPrimaryText(null));
//            textView2.setText(item.getSecondaryText(null));

            textView1.setText("" + LanguageConverter.uni2zg(item.getPrimaryText(STYLE_BOLD).toString()));
            textView2.setText("" + LanguageConverter.uni2zg(item.getSecondaryText(STYLE_BOLD).toString()));
        }

        return row;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                FilterResults results = new FilterResults();

                // We need a separate list to store the results, since
                // this is run asynchronously.
                ArrayList<AutocompletePrediction> filterData = new ArrayList<>();

                // Skip the autocomplete query if no constraints are given.
                if (charSequence != null) {
                    // Query the autocomplete API for the (constraint) search string.
                    filterData = getAutocomplete(charSequence);
                }

                results.values = filterData;
                if (filterData != null) {
                    results.count = filterData.size();
                } else {
                    results.count = 0;
                }

                return results;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults results) {

                if (results != null && results.count > 0) {
                    // The API returned at least one result, update the data.
                    mResultList = (ArrayList<AutocompletePrediction>) results.values;
                    notifyDataSetChanged();
                } else {
                    // The API did not return any results, invalidate the data set.
                    notifyDataSetInvalidated();
                }
            }

            @Override
            public CharSequence convertResultToString(Object resultValue) {
                // Override this method to display a readable result in the AutocompleteTextView
                // when clicked.
                if (resultValue instanceof AutocompletePrediction) {
                    return ((AutocompletePrediction) resultValue).getFullText(null);
                } else {
                    return super.convertResultToString(resultValue);
                }
            }
        };
    }

    private ArrayList<AutocompletePrediction> getAutocomplete(CharSequence constraint) {

        //Create a RectangularBounds object.
//        RectangularBounds bounds = RectangularBounds.newInstance(
//                new LatLng(-33.880490, 151.184363),
//                new LatLng(-33.858754, 151.229596));


        String inputString = null;
        if (MMText.isTextZawGyiProbably(constraint.toString())) {
            inputString = LanguageConverter.zg2uni(constraint.toString());
        } else
            inputString = LanguageConverter.zg2uni(LanguageConverter.uni2zg(constraint.toString()));


        RectangularBounds bounds = RectangularBounds.newInstance(
                new LatLng(16.871311, 96.199379),
                new LatLng(20.292068, 98.894180));

        final FindAutocompletePredictionsRequest.Builder requestBuilder =
                FindAutocompletePredictionsRequest.builder()
                        .setQuery(inputString)
                        .setCountry("MM") //Use only in specific country
                        .setLocationBias(bounds)  // Call either setLocationBias() OR setLocationRestriction().
//                        .setLocationRestriction(bounds)
                        .setSessionToken(AutocompleteSessionToken.newInstance())
                        .setTypeFilter(TypeFilter.ADDRESS);

        Task<FindAutocompletePredictionsResponse> results =
                placesClient.findAutocompletePredictions(requestBuilder.build());


        //Wait to get results.
        try {
            Tasks.await(results, 60, TimeUnit.SECONDS);
        } catch (ExecutionException | InterruptedException | TimeoutException e) {
            e.printStackTrace();
        }

        if (results.isSuccessful()) {
            if (results.getResult() != null) {
                return (ArrayList<AutocompletePrediction>) results.getResult().getAutocompletePredictions();
            }
            return null;
        } else {
            return null;
        }
    }
}
