package com.ncatz.chronosport.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yeray697 on 2/02/17.
 */

public abstract class ChronoElement implements Parcelable{

    public static final int CLASS_TYPE_TIMER = 1;
    public static final int CLASS_TYPE_REPETITION = 2;

    private int id;
    private String name;

    public ChronoElement (String name){
        this.name = name;
    }

    protected ChronoElement(Parcel in) {
        id = in.readInt();
        name = in.readString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(id);
        out.writeString(name);
    }

    public static final Creator<ChronoElement> CREATOR = new Creator<ChronoElement>() {
        @Override
        public ChronoElement createFromParcel(Parcel source) {

            return ChronoElement.getConcreteClass(source);
        }

        @Override
        public ChronoElement[] newArray(int size) {
            return new ChronoElement[size];
        }
    };

    private static ChronoElement getConcreteClass(Parcel source) {
        switch (source.readInt()) {
            case CLASS_TYPE_TIMER:
                return new ChronoTimeElement(source);
            case CLASS_TYPE_REPETITION:
                return new ChronoRepetitionElement(source);
            default:
                return null;
        }
    }
}
