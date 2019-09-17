package com.zumepizza.interview.ui.main;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.facebook.stetho.Stetho;
import com.zumepizza.interview.R;
import com.zumepizza.interview.bindings.CountDrawable;
import com.zumepizza.interview.databinding.ActivityMainBinding;
import com.zumepizza.interview.utils.FactoryUtils;

import java.util.List;


// * See "Instructions" text file

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    MainViewModel mainViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainViewModelFactory factory = FactoryUtils.getMainViewModelFactory(this);
        mainViewModel = ViewModelProviders.of(this, factory).get(MainViewModel.class);
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
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        setCount(this, menu);
        return true;
    }

    public void setCount(Context context, Menu menu) {
        MenuItem menuItem = menu.findItem(R.id.cart);
        LayerDrawable icon = (LayerDrawable) menuItem.getIcon();
        CountDrawable badge;

        Drawable reuse = icon.findDrawableByLayerId(R.id.ic_group_count);
        if (reuse != null && reuse instanceof CountDrawable) {
            badge = (CountDrawable) reuse;
        } else {
            badge = new CountDrawable(context);
        }
        mainViewModel.getTotalQuantity().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                int c = 0;
                for (String s : strings) {
                    c += Integer.valueOf(s);
                }
                badge.setCount(String.valueOf(c));
            }
        });
        icon.mutate();
        icon.setDrawableByLayerId(R.id.ic_group_count, badge);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.cart) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
