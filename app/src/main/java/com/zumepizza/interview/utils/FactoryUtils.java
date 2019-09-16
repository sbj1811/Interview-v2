package com.zumepizza.interview.utils;

import android.content.Context;

import com.zumepizza.interview.data.DataRepository;
import com.zumepizza.interview.data.local.PizzaItemDatabase;
import com.zumepizza.interview.ui.details.DetailsViewModelFactory;
import com.zumepizza.interview.ui.main.MainViewModelFactory;

public class FactoryUtils {

    public static MainViewModelFactory getMainViewModelFactory(Context context) {
        PizzaItemDatabase pizzaItemDatabase = PizzaItemDatabase.getInstance(context.getApplicationContext());
        return new MainViewModelFactory(new DataRepository(pizzaItemDatabase.pizzaItemDao()));
    }

    public static DetailsViewModelFactory getDetailsViewModelFactory(Context context) {
        PizzaItemDatabase pizzaItemDatabase = PizzaItemDatabase.getInstance(context.getApplicationContext());
        return new DetailsViewModelFactory(new DataRepository(pizzaItemDatabase.pizzaItemDao()));
    }
}

