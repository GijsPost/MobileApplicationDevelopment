package nl.gijspost.individualassignment;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import nl.gijspost.individualassignment.models.App;
import nl.gijspost.individualassignment.models.AppDao;

@Database(entities = {App.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract AppDao appDao();
    private final static String DB_NAME = "app_db";

    //Static instance
    private static AppDatabase instance;
    public static AppDatabase getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context, AppDatabase.class, DB_NAME).allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
