package dnr2i.antoine.amaury.livetweethashtag.tweetDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Class for Manage Tweet into SQLite Database
 * Created by amaury on 30/01/15.
 */
public class TweetDBHandler {

    /**
     * Database name
     */
    private static final String DB_NAME = "tweet.db";

    /**
     * Database version
     */
    private static final int DB_VERSION = 1;

    /**
     * Table name
     */
    public static final String TABLE_NAME = "tweet";

    /**
     * column id name
     */
    public static final String COL_ID = "_id";

    /**
     * Colum twwet name
     */
    public static final String COL_CONTENT = "content";


    /**
     * Instance of TweetDBOpen
     */
    private TweetDBOpen tweetDBOpen;

    /**
     * Build TweetDBHandler
     * @param context
     */
    public TweetDBHandler(Context context) {
        tweetDBOpen = new TweetDBOpen(context,DB_NAME,null,DB_VERSION);
    }

    /**
     * Find all tweet
     * @return Cursor
     */
    public Cursor findAll(){
        String[] col = new String[] {COL_ID, COL_CONTENT};
        SQLiteDatabase db = tweetDBOpen.getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME,col,null,null,null,null,null);
        return cursor;
    }

    /**
     * Add tweet
     * @param content tweet's content
     */
    public void addMessage(String content){
        ContentValues valeurs = new ContentValues();
        valeurs.put(COL_CONTENT,content);
        SQLiteDatabase db = tweetDBOpen.getWritableDatabase();
        db.insert(TABLE_NAME,null,valeurs);
    }




}
