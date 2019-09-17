package com.zumepizza.interview.ui.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.HapticFeedbackConstants;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.zumepizza.interview.R;
import com.zumepizza.interview.databinding.ActivityDetailsBinding;
import com.zumepizza.interview.utils.FactoryUtils;

public class DetailsActivity extends AppCompatActivity {

    private static final String TAG = DetailsActivity.class.getSimpleName();
    private static final String ID = "is";
    private int id;
    private DetailsViewModel detailsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        DetailsViewModelFactory factory = FactoryUtils.getDetailsViewModelFactory(this);
        detailsViewModel = ViewModelProviders.of(this, factory).get(DetailsViewModel.class);
        detailsViewModel.init();

        if (savedInstanceState == null) {
            Intent intent = getIntent();
            Bundle extras = intent.getExtras();
            id = extras.getInt(ID);
        } else {
            id = savedInstanceState.getInt(ID);
        }

        detailsViewModel.getPizzaItemFromRepo(id).observe(this, pizzaItem -> {
            detailsViewModel.setPizzaItem(pizzaItem);
            ActivityDetailsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_details);
            binding.setViewModel(detailsViewModel);
            detailsViewModel.setToppingsInAdapter(pizzaItem.getToppings());
            binding.detailCollapsingToolbar.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
            ElegantNumberButton button = (ElegantNumberButton) findViewById(R.id.quantity_picker);
            button.setOnClickListener((ElegantNumberButton.OnClickListener) view -> {
                String quantity = button.getNumber();
                detailsViewModel.saveQuantity(quantity);
            });
            Vibrator vib = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
            binding.addToOrderBtn.setOnClickListener(click -> {
                detailsViewModel.savePizzaItem(pizzaItem);
                vib.vibrate(50);
                binding.addToOrderBtn.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                Toast.makeText(this, this.getString(R.string.added_to_cart), Toast.LENGTH_SHORT).show();
                onBackPressed();
            });
        });


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(ID, id);
        super.onSaveInstanceState(outState);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
