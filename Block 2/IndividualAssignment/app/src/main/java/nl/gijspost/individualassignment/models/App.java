package nl.gijspost.individualassignment.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;


@Entity(tableName = "apps")
public class App implements Parcelable{

    @PrimaryKey(autoGenerate = true)
    private int Id;
    @ColumnInfo(name="appid")
    private int appId;
    @ColumnInfo(name="name")
    private String name;

    public App(int appId, String name) {
        this.appId = appId;
        this.name = name;
    }

    // Constructor for parceable
    private App(Parcel in) {
        if (in.readByte() == 0) {
            appId = 0;
        } else {
            appId = in.readInt();
        }
        name = in.readString();
    }

    public static final Creator<App> CREATOR = new Creator<App>() {
        @Override
        public App createFromParcel(Parcel in) {
            return new App(in);
        }

        @Override
        public App[] newArray(int size) {
            return new App[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (appId == 0) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(appId);
        }
        dest.writeString(name);
    }

    public int getId() { return Id; }

    public void setId(int Id) { this.Id = Id; }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
