package com.zumepizza.interview.ui.main;


import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.facebook.stetho.Stetho;
import com.zumepizza.interview.R;
import com.zumepizza.interview.databinding.ActivityMainBinding;
import com.zumepizza.interview.ui.details.DetailsViewModel;
import com.zumepizza.interview.utils.FactoryUtils;


// * See "Instructions" text file

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    MainViewModel mainViewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainViewModelFactory factory = FactoryUtils.getMainViewModelFactory(this);
        mainViewModel = ViewModelProviders.of(this,factory).get(MainViewModel.class);
        mainViewModel.init();
        mainViewModel.getPizza();

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setHandler(this);
        binding.setManager(getSupportFragmentManager());

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build()
        );


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem item = menu.findItem(R.id.cart);
        MenuItemCompat.setActionView(item, R.layout.actionbar_badge_layout);
        View actionView = MenuItemCompat.getActionView(item);
        TextView tv = (TextView) actionView.findViewById(R.id.cart_badge);
        Log.e(TAG, "onCreateOptionsMenu: HERE");
        mainViewModel.getTotalQuantity().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String quantity) {
                Log.e(TAG, "onChanged: HERE "+quantity);
                tv.setText(quantity);
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.cart) {
            Log.e(TAG, "onOptionsItemSelected: HERE");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
