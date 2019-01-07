package nl.gijspost.individualassignment.models;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface AppDao {

    @Query("SELECT * FROM apps")
    LiveData<List<App>> getApps();

    @Insert
    void insertApp(App app);

    @Delete
    void deleteApp(App app);

    @Update
    void updateApp(App app);

}