package com.zumepizza.interview.data.local;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zumepizza.interview.model.Assets;


import com.zumepizza.interview.model.Classifications;
import com.zumepizza.interview.model.Topping;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class Converters {

    @TypeConverter
    public static List<Topping> stringToToppingList(String data) {
        Gson gson = new Gson();
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<Topping>>() {
        }.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String toppingListToString(List<Topping> someObjects) {
        Gson gson = new Gson();
        return gson.toJson(someObjects);
    }


    @TypeConverter
    public static Assets stringToAssets(String data) {
        Gson gson = new Gson();
        if (data == null) {
            return null;
        }

        Type listType = new TypeToken<Assets>() {
        }.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String AssetsToString(Assets someObjects) {
        Gson gson = new Gson();
        return gson.toJson(someObjects);
    }

    @TypeConverter
    public static Classifications stringToClassifications(String data) {
        Gson gson = new Gson();
        if (data == null) {
            return null;
        }

        Type listType = new TypeToken<Classifications>() {
        }.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String ClassificationsToString(Classifications someObjects) {
        Gson gson = new Gson();
        return gson.toJson(someObjects);
    }


}
