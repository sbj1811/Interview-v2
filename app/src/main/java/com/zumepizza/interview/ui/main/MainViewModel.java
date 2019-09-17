package com.zumepizza.interview.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.zumepizza.interview.R;
import com.zumepizza.interview.data.DataRepository;
import com.zumepizza.interview.model.PizzaItem;

import java.util.List;

public class MainViewModel extends ViewModel {

    private static final String TAG = MainViewModel.class.getSimpleName();
    public String resultImageUrl;
    private DataRepository repository;
    private ListAdapter adapter;
    private List<PizzaItem> pizzaItemList;

    public MainViewModel(DataRepository repository) {
        this.repository = repository;
    }

    public void init() {
        adapter = new ListAdapter(R.layout.fragment_item, this);
    }

    public ListAdapter getAdapter() {
        return adapter;
    }

    public PizzaItem getPizzaItem(int position) {
        imageUrlUpdated(pizzaItemList.get(position).getAssets().getMenu().get(0).getUrl());
        return pizzaItemList.get(position);
    }

    public void setPizzaItemInAdapter(List<PizzaItem> pizzaItems) {
        pizzaItemList = pizzaItems;
        adapter.swapResults(pizzaItems);
        adapter.notifyDataSetChanged();
    }

    public LiveData<List<PizzaItem>> getPizzaItems(int position) {
        return repository.getCategoryPizzaItemFromDb(position);
    }

    public LiveData<List<String>> getTotalQuantity() {
        return repository.getAllQuantities();
    }

    public void getPizza() {
        repository.getPizzaFromApi();
    }

    public void imageUrlUpdated(String url) {
        resultImageUrl = url;
    }

    public void savePizzaItem(PizzaItem pizzaItem) {
        pizzaItem.setQuantity(String.valueOf(Integer.valueOf(pizzaItem.getQuantity()) + 1));
        repository.savePizzaItemToDb(pizzaItem);
    }

}
