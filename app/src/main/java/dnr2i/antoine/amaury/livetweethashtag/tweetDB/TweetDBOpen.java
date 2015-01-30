package dnr2i.antoine.amaury.livetweethashtag.tweetDB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by amaury on 30/01/15.
 */
public class TweetDBOpen extends SQLiteOpenHelper{

    /**
     * Name database
     */
    private String nameDB;

    /**
     * Request for create database
     */
    private final String DB_CREATE = "create table "+TweetDBHandler.TABLE_NAME
            + "( "+TweetDBHandler.COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT ,"
            +  TweetDBHandler.COL_CONTENT+" string);";


    public TweetDBOpen(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.nameDB = name;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + this.nameDB);
        onCreate(db);
    }
}
