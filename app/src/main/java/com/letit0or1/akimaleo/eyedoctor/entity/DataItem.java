package com.letit0or1.akimaleo.eyedoctor.entity;

/**
 * Created by akimaleo on 15.01.17.
 */

public class DataItem implements Comparable {
    private String description;
    private int imageResource;

    public DataItem(String description, int imageResource) {
        this.description = description;
        this.imageResource = imageResource;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    @Override
    public int compareTo(Object o) {
        int plateNumberObject = Integer.parseInt(DataCollection.getInstance().getContext().getResources().getResourceEntryName(((DataItem) o).getImageResource()).replace("plate", "").intern());
        int plateNumberThis = Integer.parseInt(DataCollection.getInstance().getContext().getResources().getResourceEntryName(imageResource).replace("plate", "").intern());
        return new Integer(plateNumberThis).compareTo(new Integer(plateNumberObject));
    }
}
