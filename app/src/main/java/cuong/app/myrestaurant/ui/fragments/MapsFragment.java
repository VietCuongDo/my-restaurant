package cuong.app.myrestaurant.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import cuong.app.myrestaurant.R;
import cuong.app.myrestaurant.data.Restaurant;
import cuong.app.myrestaurant.ui.DataCommunication;
import cuong.app.myrestaurant.ui.HowGoodWasTheRestaurantDescriptor;
import cuong.app.myrestaurant.ui.adapter.RestaurantListAdapter;

public class MapsFragment extends Fragment implements OnMapReadyCallback {

    DataCommunication mCallback;
    SupportMapFragment mapSupportFragment;
    RestaurantListAdapter resListAdapter;
    List<Restaurant> listOfRestaurants;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (DataCommunication) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement DataCommunication");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mapSupportFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapAllRestaurants);
        mapSupportFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        LatLng Cracow = new LatLng(52.489471, -1.898575);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Cracow, 12));

        resListAdapter = mCallback.getRestaurantListAdapter();
        if(resListAdapter == null) return;

        listOfRestaurants = resListAdapter.getmRestaurants();

        for (int i = 0; i < listOfRestaurants.size(); i++) {
            Restaurant restaurant = listOfRestaurants.get(i);
            double latitude = Double.parseDouble(restaurant.getLocationLatitude());
            double longitude = Double.parseDouble(restaurant.getLocationLongitude());
            LatLng position = new LatLng(latitude, longitude);

            String restaurantEvaluation = restaurant.getHowGoodIsTheRestaurant();
            BitmapDescriptor bitmapDescriptor;
            switch (restaurantEvaluation) {
                case HowGoodWasTheRestaurantDescriptor.GOOD:

                    bitmapDescriptor = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN);

                    googleMap.addMarker(new MarkerOptions()
                            .position(position)
                            .icon(bitmapDescriptor)
                            .title(restaurant.getRestaurantName())
                            .snippet(restaurant.getRestaurantCommentary()));
                    break;
                case HowGoodWasTheRestaurantDescriptor.OK:

                    bitmapDescriptor = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW);

                    googleMap.addMarker(new MarkerOptions()
                            .position(position)
                            .icon(bitmapDescriptor)
                            .title(restaurant.getRestaurantName())
                            .snippet(restaurant.getRestaurantCommentary()));
                    break;
                case HowGoodWasTheRestaurantDescriptor.BAD:

                    bitmapDescriptor = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED);

                    googleMap.addMarker(new MarkerOptions()
                            .position(position)
                            .icon(bitmapDescriptor)
                            .title(restaurant.getRestaurantName())
                            .snippet(restaurant.getRestaurantCommentary()));
                    break;
            }
        }
    }
}