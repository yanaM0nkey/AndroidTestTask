package com.gmail.ioanna.data;


import android.app.Activity;
import android.util.Log;

import com.gmail.ioanna.data.dbEntity.Percents;
import com.gmail.ioanna.data.dbEntity.State;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;


//вообще по хорошему надо класс пихать в domain
public class GetPercentsFromJson {

    public ArrayList<Percents> getPercents(Activity activity) {

        String json;

        try {
            InputStream stream = activity.getAssets().open("percents.json");
            // Log.e("ex1", stream.toString());
            int size = stream.available();
            byte[] buffer = new byte[size];
            stream.read(buffer);
            stream.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException ex) {
            Log.e("ex2", ex.toString());
            return null;
        }

        Percents[] percents = new Gson().fromJson(json, Percents[].class);
        return new ArrayList<>(Arrays.asList(percents));
    }
}
