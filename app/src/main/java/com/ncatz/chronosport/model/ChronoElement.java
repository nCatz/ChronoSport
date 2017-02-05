package com.ncatz.chronosport.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yeray697 on 2/02/17.
 */

public abstract class ChronoElement implements Parcelable{
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
}
