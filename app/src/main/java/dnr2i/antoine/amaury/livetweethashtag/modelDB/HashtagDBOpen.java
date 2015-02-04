package dnr2i.antoine.amaury.livetweethashtag.modelDB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by amaury on 30/01/15.
 */
public class HashtagDBOpen extends SQLiteOpenHelper{

    public static final String DB_NAME = "twitter3.db";
    /**
     * Name database
     */
    private String nameDB;



    /**
     * Request for create database
     */
    private final String DB_CREATE_HASHTAG = "create table "+HashtagDBHandler.TABLE_NAME
            + "( "+HashtagDBHandler.COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT ,"
            +  HashtagDBHandler.COL_HASHTAG+" string not null);";


    /**
     * Request for create database
     */
    private final String DB_CREATE_TWEET = "create table "+TweetDBHandler.TABLE_NAME
            + "( "+TweetDBHandler.COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT ,"
            +  TweetDBHandler.COL_CONTENT+" string, "
            +  TweetDBHandler.COL_HASHTAG+" string, "
            +  TweetDBHandler.COL_PSEUDO+" string);";


    public HashtagDBOpen(Context context, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_CREATE_HASHTAG);
        db.execSQL(DB_CREATE_TWEET);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + this.nameDB);
        onCreate(db);
    }
}