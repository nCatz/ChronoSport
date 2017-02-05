package com.ncatz.chronosport.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yeray697 on 2/02/17.
 */

public class ChronoRepetitionElement extends ChronoElement {
    private int repetitions;

    public ChronoRepetitionElement(String name, int repetitions) {
        super(name);
        this.repetitions = repetitions;
    }

    ChronoRepetitionElement(Parcel in) {
        super(in);
        repetitions = in.readInt();
    }

    public int getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    public static final Parcelable.Creator<ChronoRepetitionElement> CREATOR = new Parcelable.Creator<ChronoRepetitionElement>() {
        public ChronoRepetitionElement createFromParcel(Parcel in) {
            return new ChronoRepetitionElement(in);
        }

        public ChronoRepetitionElement[] newArray(int size) {
            return new ChronoRepetitionElement[size];
        }
    };

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(ChronoElement.CLASS_TYPE_REPETITION);
        super.writeToParcel(out, flags);
        out.writeInt(repetitions);
    }
}
