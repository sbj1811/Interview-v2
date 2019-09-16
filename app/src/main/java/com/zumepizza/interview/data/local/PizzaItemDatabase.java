package com.zumepizza.interview.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.zumepizza.interview.model.PizzaItem;

@Database(entities = {PizzaItem.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class PizzaItemDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "pizzaitems";
    private static final Object LOCK = new Object();
    private static PizzaItemDatabase sInstance;

    public static PizzaItemDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = Room.databaseBuilder(context.getApplicationContext(), PizzaItemDatabase.class, PizzaItemDatabase.DATABASE_NAME)
                        .build();
            }
        }
        return sInstance;
    }


    public abstract PizzaItemDao pizzaItemDao();
}
