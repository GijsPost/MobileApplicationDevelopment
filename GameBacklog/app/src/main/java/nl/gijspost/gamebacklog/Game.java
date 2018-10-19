package nl.gijspost.gamebacklog;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "game")
public class Game implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo (name = "gameName")
    private String gameName;
    @ColumnInfo (name = "gamePlatform")
    private String gamePlatform;
    @ColumnInfo (name = "gameStatus")
    private String gameStatus;
    @ColumnInfo (name = "gameNotes")
    private String gameNotes;
    @ColumnInfo (name = "gameDate")
    private String gameDate;

    public String getGameDate() {
        return gameDate;
    }

    public void setGameDate(String gameDate) {
        this.gameDate = gameDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGamePlatform() {
        return gamePlatform;
    }

    public void setGamePlatform(String gamePlatform) {
        this.gamePlatform = gamePlatform;
    }

    public String getGameNotes() {
        return gameNotes;
    }

    public void setGameNotes(String gameNotes) {
        this.gameNotes = gameNotes;
    }

    public String getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(String gameStatus) {
        this.gameStatus = gameStatus;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public Game(String gameName, String gamePlatform, String gameNotes, String gameStatus, String gameDate) {
        this.gameName = gameName;
        this.gamePlatform = gamePlatform;
        this.gameNotes = gameNotes;
        this.gameStatus = gameStatus;
        this.gameDate = gameDate;
    }

    @Override
    public String toString() {
        return gameName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.gameName);
        dest.writeString(this.gamePlatform);
        dest.writeString(this.gameNotes);
        dest.writeString(this.gameStatus);
    }

    protected Game(Parcel in) {
        this.id = in.readLong();
        this.gameName = in.readString();
        this.gamePlatform = in.readString();
        this.gameNotes = in.readString();
        this.gameStatus = in.readString();
    }

    public static final Creator<Game> CREATOR = new Creator<Game>() {
        @Override
        public Game createFromParcel(Parcel source) {
            return new Game(source);
        }

        @Override
        public Game[] newArray(int size) {
            return new Game[size];
        }
    };
}