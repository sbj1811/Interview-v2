package com.zumepizza.interview.data;

import androidx.lifecycle.LiveData;

import com.zumepizza.interview.data.api.ApiConnection;
import com.zumepizza.interview.data.local.PizzaItemDao;
import com.zumepizza.interview.model.Pizza;
import com.zumepizza.interview.model.PizzaItem;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataRepository {

    private static final String TAG = DataRepository.class.getSimpleName();
    private static boolean gotDataFromApi;
    private PizzaItemDao pizzaItemDao;


    public DataRepository(PizzaItemDao pizzaItemDao) {
        this.pizzaItemDao = pizzaItemDao;

    }

    public void getPizzaFromApi() {
        if (!gotDataFromApi) {
            ApiConnection.getApi().getPizzas().enqueue(new Callback<List<Pizza>>() {
                @Override
                public void onResponse(Call<List<Pizza>> call, Response<List<Pizza>> response) {
                    Pizza pizzas = response.body().get(0);
                    if (pizzas != null) {
                        Observable.fromCallable(() -> {
                            if (pizzas.getChefSChoice() != null) {
                                for (PizzaItem item : pizzas.getChefSChoice()) {
                                    setPizzaItemParameters(item, 0);
                                    pizzaItemDao.save(item);
                                }
                            }
                            if (pizzas.getClassics() != null) {
                                for (PizzaItem item : pizzas.getClassics()) {
                                    setPizzaItemParameters(item, 1);
                                    pizzaItemDao.save(item);
                                }
                            }
                            if (pizzas.getSignature() != null) {
                                for (PizzaItem item : pizzas.getSignature()) {
                                    setPizzaItemParameters(item, 2);
                                    pizzaItemDao.save(item);
                                }
                            }
                            if (pizzas.getVegetarian() != null) {
                                for (PizzaItem item : pizzas.getVegetarian()) {
                                    setPizzaItemParameters(item, 3);
                                    pizzaItemDao.save(item);
                                }
                            }
                            gotDataFromApi = true;
                            return false;
                        })
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe();

                    }
                }

                @Override
                public void onFailure(Call<List<Pizza>> call, Throwable t) {

                }
            });
        }
    }

    private void setPizzaItemParameters(PizzaItem item, int i) {
        item.setCategory(i);
        item.setPrice("$" + item.getPrice());
        item.setQuantity("0");
        item.setSpicy();
    }

    public LiveData<List<PizzaItem>> getCategoryPizzaItemFromDb(Integer category) {
        return pizzaItemDao.getCategoryPizzaItems(category);
    }

    public LiveData<PizzaItem> getPizzaItemWithIdFromDb(int id) {
        return pizzaItemDao.getPizzaItemWithId(id);
    }

    public void savePizzaItemToDb(PizzaItem pizzaItem) {
        Observable.fromCallable(() -> {
            pizzaItemDao.save(pizzaItem);
            return false;
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();

    }

    public LiveData<List<String>> getAllQuantities() {
        return pizzaItemDao.getAllQuantity();
    }

}
