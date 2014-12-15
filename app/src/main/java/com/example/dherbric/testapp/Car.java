package com.example.dherbric.testapp;

import android.os.Parcel;
import android.os.Parcelable;

import com.hannesdorfmann.parcelableplease.ParcelBagger;
import com.hannesdorfmann.parcelableplease.annotation.Bagger;
import com.hannesdorfmann.parcelableplease.annotation.ParcelablePlease;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 14.11.2014.
 *
 * @author dherbric
 */
@ParcelablePlease
public class Car implements Parcelable {
    @Override public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", properties=" + properties +
                '}';
    }

    String name;
    String type;
    @Bagger(HashMapBagger.class)
    HashMap<String, String> properties;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public HashMap<String, String> getProperties() {
        return properties;
    }

    public void setProperties(HashMap<String, String> properties) {
        this.properties = properties;
    }

    @Override public int describeContents() {
        return 0;
    }

    @Override public void writeToParcel(Parcel dest, int flags) {
        CarParcelablePlease.writeToParcel(this, dest, flags);
    }

    public static final Creator<Car> CREATOR = new Creator<Car>() {
        public Car createFromParcel(Parcel source) {
            Car target = new Car();
            CarParcelablePlease.readFromParcel(target, source);
            return target;
        }

        public Car[] newArray(int size) {
            return new Car[size];
        }
    };

    public static class HashMapBagger implements ParcelBagger<HashMap<String, String>> {

        @Override public void write(HashMap<String, String> value, Parcel out, int flags) {
            if (value == null) {
                out.writeInt(0);
                return;
            }
            out.writeInt(value.size());
            //write Keys
            for (Map.Entry<String, String> entry : value.entrySet()) {
                out.writeString(entry.getKey());
                out.writeString(entry.getValue());
            }
        }

        @Override public HashMap<String, String> read(Parcel in) {
            int cnt = in.readInt();
            if (cnt == 0) return null;
            HashMap<String, String> map = new HashMap<>(cnt);
            for (int i = 0; i < cnt; i++) {
                map.put(in.readString(), in.readString());
            }
            return null;
        }
    }
}
