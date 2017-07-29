package com.letit0or1.akimaleo.eyedoctor.colorblind.entity

import android.content.Context
import android.util.Log
import com.letit0or1.akimaleo.eyedoctor.R
import java.util.*

//Class using for accessing data in application storage and structuring it not in
//view controllers
class DataCollection {
    companion object {
        //singleton class
        val instance = DataCollection()
    }

    var context: Context? = null

    //get all fields of drawable pat
    //get Strings data from a String resources
    //collecting data ind ArrayList
    /* make use of resourceId for accessing Drawables here *///        sorting collection and adding description reference for image
    val data: ArrayList<DataItem>
        get() {
            val drawableResources = R.drawable()
            val c = R.drawable::class.java
            val fields = c.declaredFields

            val data = ArrayList<DataItem>()
            val desk = context!!.resources.getStringArray(R.array.diagnostic_data)

            for (i in 0..fields.size) {
                val resourceId: Int
                try {
                    if (fields[i].name.startsWith("plate")) {
                        resourceId = fields[i].getInt(drawableResources)
                        data.add(DataItem(null, resourceId))
                    } else
                        continue
                } catch (e: Exception) {
                    continue
                }
            }

            Collections.sort<DataItem>(data)
            for (i in data.indices) {
                data.get(i).setDescription(desk[i])
                val plateNumberObject = Integer.parseInt(DataCollection.instance.context!!.getResources().getResourceEntryName(data.get(i).getImageResource()).replace("plate", "").intern())
                Log.i("num", "" + plateNumberObject)
                when (i) {
                    0 -> data.get(i).setAnswer(intArrayOf(12, 0, 0))
                    1 -> data.get(i).setAnswer(intArrayOf(8, 3, 0))
                    2 -> data.get(i).setAnswer(intArrayOf(29, 70, 0))
                    3 -> data.get(i).setAnswer(intArrayOf(5, 2, 0))
                    4 -> data.get(i).setAnswer(intArrayOf(3, 5, 0))
                    5 -> data.get(i).setAnswer(intArrayOf(15, 17, 0))
                    6 -> data.get(i).setAnswer(intArrayOf(74, 21, 0))
                    7 -> data.get(i).setAnswer(intArrayOf(6, 0, 0))
                    8 -> data.get(i).setAnswer(intArrayOf(45, 0, 0))
                    9 -> data.get(i).setAnswer(intArrayOf(5, 0, 0))
                    10 -> data.get(i).setAnswer(intArrayOf(7, 0, 0))
                    11 -> data.get(i).setAnswer(intArrayOf(16, 0, 0))
                    12 -> data.get(i).setAnswer(intArrayOf(73, 0, 0))
                    13 -> data.get(i).setAnswer(intArrayOf(0, 5, 0))
                    14 -> data.get(i).setAnswer(intArrayOf(0, 45, 0))
                    15 -> data.get(i).setAnswer(intArrayOf(26, 2, 0))
                    16 -> data.get(i).setAnswer(intArrayOf(42, 2, 0))
                }
            }

            return data
        }


}
