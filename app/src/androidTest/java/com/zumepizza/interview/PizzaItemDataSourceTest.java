package com.zumepizza.interview;

import androidx.room.Room;
import androidx.test.InstrumentationRegistry;

import com.zumepizza.interview.data.local.PizzaItemDatabase;
import com.zumepizza.interview.model.Classifications;
import com.zumepizza.interview.model.PizzaItem;
import com.zumepizza.interview.model.Topping;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PizzaItemDataSourceTest {

    private PizzaItemDatabase pizzaItemDatabase;

    @Before
    public void initDb() throws Exception {
        pizzaItemDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(), PizzaItemDatabase.class).build();

    }

    @After
    public void closeDb() throws Exception {
        pizzaItemDatabase.close();
    }

    @Test
    public void insertAndGetAlert() {
        PizzaItem pizzaItem = createUser();
        pizzaItemDatabase.pizzaItemDao().save(pizzaItem);
        PizzaItem newPizzaItem = pizzaItemDatabase.pizzaItemDao().getPizzaItemForTest().get(0);
        assertEquals(pizzaItem.getName(), newPizzaItem.getName());
        assertEquals(pizzaItem.getId(), newPizzaItem.getId());
        assertEquals(pizzaItem.getPrice(), newPizzaItem.getPrice());
        assertEquals(pizzaItem.getSpicy(), newPizzaItem.getSpicy());
        assertEquals(pizzaItem.getMenuDescription(), newPizzaItem.getMenuDescription());
        assertEquals(pizzaItem.getClassifications().getGlutenFree(), newPizzaItem.getClassifications().getGlutenFree());
        assertEquals(pizzaItem.getClassifications().getVegetarian(), newPizzaItem.getClassifications().getVegetarian());
        assertEquals(pizzaItem.getToppings().get(0).getName(), newPizzaItem.getToppings().get(0).getName());
    }

    private PizzaItem createUser() {
        PizzaItem pizzaItem = new PizzaItem();
        pizzaItem.setSpicy(true);
        pizzaItem.setPrice("11.99");
        pizzaItem.setId(1);
        pizzaItem.setName("Veg Supreme");
        pizzaItem.setMenuDescription("Veg pizza with best toppings!");
        Classifications classifications = new Classifications();
        classifications.setGlutenFree(true);
        classifications.setVegetarian(true);
        pizzaItem.setClassifications(classifications);
        Topping topping = new Topping();
        topping.setName("pineapple");
        List<Topping> toppingList = new ArrayList<>();
        toppingList.add(topping);
        pizzaItem.setToppings(toppingList);
        return pizzaItem;
    }


}
