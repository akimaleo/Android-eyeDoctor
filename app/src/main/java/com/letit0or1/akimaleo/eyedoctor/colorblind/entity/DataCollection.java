package com.letit0or1.akimaleo.eyedoctor.colorblind.entity;

import android.content.Context;
import android.util.Log;

import com.letit0or1.akimaleo.eyedoctor.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
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
            switch (i) {
                case 0:
                    data.get(i).setAnswer(new int[]{12, 0, 0});
                    break;
                case 1:
                    data.get(i).setAnswer(new int[]{8, 3, 0});
                    break;
                case 2:
                    data.get(i).setAnswer(new int[]{29, 70, 0});
                    break;
                case 3:
                    data.get(i).setAnswer(new int[]{5, 2, 0});
                    break;
                case 4:
                    data.get(i).setAnswer(new int[]{3, 5, 0});
                    break;
                case 5:
                    data.get(i).setAnswer(new int[]{15, 17, 0});
                    break;
                case 6:
                    data.get(i).setAnswer(new int[]{74, 21, 0});
                    break;
                case 7:
                    data.get(i).setAnswer(new int[]{6, 0, 0});
                    break;
                case 8:
                    data.get(i).setAnswer(new int[]{45, 0, 0});
                    break;
                case 9:
                    data.get(i).setAnswer(new int[]{5, 0, 0});
                    break;
                case 10:
                    data.get(i).setAnswer(new int[]{7, 0, 0});
                    break;
                case 11:
                    data.get(i).setAnswer(new int[]{16, 0, 0});
                    break;
                case 12:
                    data.get(i).setAnswer(new int[]{73, 0, 0});
                    break;
                case 13:
                    data.get(i).setAnswer(new int[]{0, 5, 0});
                    break;
                case 14:
                    data.get(i).setAnswer(new int[]{0, 45, 0});
                    break;
                case 15:
                    data.get(i).setAnswer(new int[]{26, 2, 0});
                    break;
                case 16:
                    data.get(i).setAnswer(new int[]{42, 2, 0});
                    break;
            }
        }

        return data;
    }

    public Context getContext() {
        return context;
    }

}
