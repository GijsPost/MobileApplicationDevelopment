package nl.gijspost.gamebacklog;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface GameDao {

    @Query("SELECT * FROM Game")
    public List<Game> getGames();

    @Insert
    public void addGames(Game games);

    @Delete
    public void deleteGames(Game games);

    @Update
    public void updateGames(Game games);


}