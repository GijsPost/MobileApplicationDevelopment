package nl.gijspost.gamebacklog;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;


@Database(entities = {Game.class}, version = 5)

public abstract class GameDatabase extends RoomDatabase {

    public abstract GameDao gameDao();

    private static GameDatabase database;

    public static GameDatabase getInstance(Context context) {

        if (database == null) {
            database = Room.databaseBuilder(context, GameDatabase.class, "game_db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return database;
    }

}