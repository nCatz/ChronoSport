package com.ncatz.chronosport.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yeray697 on 2/02/17.
 */

public class ChronoTimeElement extends ChronoElement {
    private int time;

    public ChronoTimeElement(String name, int time) {
        super(name);
        this.time = time;
    }

    private ChronoTimeElement(Parcel in) {
        super(in);
        time = in.readInt();
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public static final Parcelable.Creator<ChronoTimeElement> CREATOR = new Parcelable.Creator<ChronoTimeElement>() {
        public ChronoTimeElement createFromParcel(Parcel in) {
            return new ChronoTimeElement (in);
        }

        public ChronoTimeElement[] newArray(int size) {
            return new ChronoTimeElement[size];
        }
    };

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        super.writeToParcel(out, flags);
        out.writeInt(time);
    }
}
