package cuong.app.myrestaurant.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import cuong.app.myrestaurant.R;
import cuong.app.myrestaurant.ui.adapter.BookingListAdapter;
import cuong.app.myrestaurant.ui.adapter.MealListAdapter;
import cuong.app.myrestaurant.ui.adapter.RestaurantListAdapter;
import cuong.app.myrestaurant.ui.fragments.bookings.BookingsFragment;
import cuong.app.myrestaurant.ui.fragments.meals.MealsFragment;
import cuong.app.myrestaurant.ui.fragments.restaurants.RestaurantsFragment;
import cuong.app.myrestaurant.ui.fragments.MapsFragment;

public class MainActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener, DataCommunication {


    private String resName, howGood, commentary, latitude, longitude, resTime, resDate, bookName, resAddress;
    private RestaurantListAdapter restaurantListAdapter;
    private BookingListAdapter bookingListAdapter;
    private MealListAdapter mealListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        loadFragment(new BookingsFragment());
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_home, fragment).commit();

            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        Fragment fragment = null;

        switch (menuItem.getItemId()) {

            case R.id.navigation_bookings:
                fragment = new BookingsFragment();
                break;

            case R.id.navigation_restaurants:
                fragment = new RestaurantsFragment();
                break;

            case R.id.navigation_meals:
                fragment = new MealsFragment();
                break;

            case R.id.navigation_map:
                fragment = new MapsFragment();
                break;
        }

        return loadFragment(fragment);
    }

    @Override
    public String getResName() {
        return resName;
    }

    @Override
    public String getAddress() { return resAddress; }

    @Override
    public String getBookName() { return bookName; }

    @Override
    public String getTheTime() { return resTime; }

    @Override
    public String getTheDate() { return resDate; }

    @Override
    public String getHowGood() {
        return howGood;
    }

    @Override
    public String getCommentary() {
        return commentary;
    }

    @Override
    public String getLatitude() {
        return latitude;
    }

    @Override
    public String getLongitude() {
        return longitude;
    }

    @Override
    public RestaurantListAdapter getRestaurantListAdapter() {
        return restaurantListAdapter;
    }

    @Override
    public BookingListAdapter getBookingListAdapter() {
        return bookingListAdapter;
    }

    @Override
    public MealListAdapter getMealListAdapter() {
        return mealListAdapter;
    }

    @Override
    public void setResName(String name) {
        this.resName = name;
    }

    @Override
    public void setBookName (String bookName) { this.bookName = bookName; }

    @Override
    public void setTheDate(String date) { this.resDate = date; }

    @Override
    public void setTheTime(String time) { this.resTime = time; }

    @Override
    public void setTheAddress (String address) { this.resAddress = address; }

    @Override
    public void setHowGood(String howGood) {
        this.howGood = howGood;
    }

    @Override
    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    @Override
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    @Override
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public void setRestaurantListAdapter(RestaurantListAdapter restaurantListAdapter) {
        this.restaurantListAdapter = restaurantListAdapter;
    }

    @Override
    public void setBookingListAdapter(BookingListAdapter adapter) {
        this.bookingListAdapter = adapter;
    }
}
