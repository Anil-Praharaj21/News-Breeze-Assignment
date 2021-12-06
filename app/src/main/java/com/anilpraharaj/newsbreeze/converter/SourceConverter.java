package com.anilpraharaj.newsbreeze.converter;

import androidx.room.TypeConverter;

import com.anilpraharaj.newsbreeze.entity.Source;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * @author anilpraharaj on 05/12/21
 */
public class SourceConverter {

    @TypeConverter
    public static Source toSourceObject(String value) {
        Type sourceObject = new TypeToken<Source>() {}.getType();
        return new Gson().fromJson(value, sourceObject);
    }

    @TypeConverter
    public static String toString(Source source) {
        Gson gson = new Gson();
        String json = gson.toJson(source);
        return json;
    }
}
