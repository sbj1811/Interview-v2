package com.zumepizza.interview.ui.details;

import android.util.Log;
import android.widget.TextView;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.zumepizza.interview.R;
import com.zumepizza.interview.bindings.CreateDynomicTextView;
import com.zumepizza.interview.data.DataRepository;
import com.zumepizza.interview.model.PizzaItem;
import com.zumepizza.interview.model.Topping;
import com.zumepizza.interview.ui.main.ListAdapter;
import com.zumepizza.interview.ui.main.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class DetailsViewModel extends ViewModel {

    private static final String TAG = DetailsViewModel.class.getSimpleName();
    private DataRepository repository;
    private int position;
    private String quantity;
    private PizzaItem pizzaItem;
    private ToppingListAdapter adapter;
    private List<Topping> toppings;
    public ObservableField<String> detailsImageUrl = new ObservableField<>();


    public void init(){
        adapter = new ToppingListAdapter(this,R.layout.topping_list_item);
    }

    public ToppingListAdapter getAdapter() {
        return adapter;
    }

    public DetailsViewModel(DataRepository repository) {
        this.repository = repository;
    }

    public LiveData<PizzaItem> getPizzaItemFromRepo(int id){
        return repository.getPizzaItemWithIdFromDb(id);
    }

    public void setToppingsInAdapter(List<Topping> toppings) {
        this.toppings = toppings;
        adapter.swapResults(toppings);
        adapter.notifyDataSetChanged();
    }


    public void setPizzaItem(PizzaItem pizzaItem) {
        this.pizzaItem = pizzaItem;
        imageUrlUpdated(pizzaItem.getAssets().getProductDetailsPage().get(0).getUrl());
    }

    public PizzaItem getPizzaItem() {
        return this.pizzaItem;
    }

    public void imageUrlUpdated(String url) {
        detailsImageUrl.set(url);
    }

    public Topping getTopping(int index){
        return toppings.get(index);
    }


    public void saveQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void savePizzaItem(PizzaItem pizzaItem){
        pizzaItem.setQuantity(String.valueOf(Integer.valueOf(pizzaItem.getQuantity())+Integer.valueOf(quantity)));
        repository.savePizzaItemToDb(pizzaItem);
    }

}
