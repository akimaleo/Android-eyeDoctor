package com.letit0or1.akimaleo.eyedoctor.entity;

import android.content.Context;
import android.util.Log;

import com.letit0or1.akimaleo.eyedoctor.R;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

//Class using for accessing data in application storage and structuring it not in
//view controllers
public class DataCollection {
    //singleton class
    private static DataCollection ourInstance = new DataCollection();
    private Context context;

    public static DataCollection getInstance() {
        return ourInstance;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    private DataCollection() {
    }

    public ArrayList<DataItem> getData() {
//get all fields of drawable pat
        final R.drawable drawableResources = new R.drawable();
        final Class<R.drawable> c = R.drawable.class;
        final Field[] fields = c.getDeclaredFields();

        ArrayList<DataItem> data = new ArrayList();
        //get Strings data from a String resources
        String[] desk = context.getResources().getStringArray(R.array.diagnostic_data);

        //collecting data ind ArrayList
        for (int i = 0, max = fields.length; i < max; i++) {
            final int resourceId;
            try {
                if (fields[i].getName().startsWith("plate")) {
                    resourceId = fields[i].getInt(drawableResources);
                    data.add(new DataItem(null, resourceId));

                } else continue;
            } catch (Exception e) {
                continue;
            }
        /* make use of resourceId for accessing Drawables here */
        }
//        sorting collection and adding description reference for image
        Collections.sort(data);
        for (int i = 0; i < data.size(); i++) {
            data.get(i).setDescription(desk[i]);
            int plateNumberObject = Integer.parseInt(DataCollection.getInstance().getContext().getResources().getResourceEntryName((data.get(i)).getImageResource()).replace("plate", "").intern());
            Log.i("num", "" + plateNumberObject);
        }
        return data;
    }

    public Context getContext() {
        return context;
    }
}
