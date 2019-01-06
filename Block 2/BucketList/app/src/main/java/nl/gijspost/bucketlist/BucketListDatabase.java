package nl.gijspost.bucketlist;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {BucketList.class}, version = 1)
public abstract class BucketListDatabase extends RoomDatabase {

    public abstract BucketListDao bucketListDao();
    private final static String DB_NAME = "bucketlist_db";

    //Static instance
    private static BucketListDatabase instance;
    public static BucketListDatabase getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context, BucketListDatabase.class, DB_NAME).allowMainThreadQueries().build();
        }
        return instance;
    }
}
