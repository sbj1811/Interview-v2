package com.zumepizza.interview.ui.details;

import android.util.Log;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.zumepizza.interview.R;
import com.zumepizza.interview.data.DataRepository;
import com.zumepizza.interview.model.PizzaItem;
import com.zumepizza.interview.model.Topping;

import java.util.List;

public class DetailsViewModel extends ViewModel {

    private static final String TAG = DetailsViewModel.class.getSimpleName();
    public ObservableField<String> detailsImageUrl = new ObservableField<>();
    private DataRepository repository;
    private String quantity = "1";
    private PizzaItem pizzaItem;
    private ToppingListAdapter adapter;
    private List<Topping> toppings;


    public DetailsViewModel(DataRepository repository) {
        this.repository = repository;
    }

    public void init() {
        adapter = new ToppingListAdapter(this, R.layout.topping_list_item);
    }

    public ToppingListAdapter getAdapter() {
        return adapter;
    }

    public LiveData<PizzaItem> getPizzaItemFromRepo(int id) {
        return repository.getPizzaItemWithIdFromDb(id);
    }

    public void setToppingsInAdapter(List<Topping> toppings) {
        this.toppings = toppings;
        adapter.swapResults(toppings);
        adapter.notifyDataSetChanged();
    }

    public PizzaItem getPizzaItem() {
        return this.pizzaItem;
    }

    public void setPizzaItem(PizzaItem pizzaItem) {
        this.pizzaItem = pizzaItem;
        imageUrlUpdated(pizzaItem.getAssets().getProductDetailsPage().get(0).getUrl());
    }

    public void imageUrlUpdated(String url) {
        detailsImageUrl.set(url);
    }

    public Topping getTopping(int index) {
        return toppings.get(index);
    }


    public void saveQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void savePizzaItem(PizzaItem pizzaItem) {
        pizzaItem.setQuantity(String.valueOf(Integer.valueOf(pizzaItem.getQuantity()) + Integer.valueOf(quantity)));
        repository.savePizzaItemToDb(pizzaItem);
    }

}
