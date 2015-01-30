package dnr2i.antoine.amaury.livetweethashtag.tweetDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Class for Manage Hashtag into SQLite Database
 * Created by amaury on 30/01/15.
 */
public class HashtagDBHandler {

    /**
     * Database name
     */
    private static final String DB_NAME = "hashtag.db";

    /**
     * Database version
     */
    private static final int DB_VERSION = 1;

    /**
     * Table name
     */
    public static final String TABLE_NAME = "hashtag";

    /**
     * column id name
     */
    public static final String COL_ID = "_id";

    /**
     * Colum hashtag name
     */
    public static final String COL_HASHTAG = "hashtag";

    /**
     * Instance of HastagDBOpen
     */
    private HashtagDBOpen hashtagDBOpen;

    /**
     * Build HashtagDBHandler
     * @param context
     */
    public HashtagDBHandler(Context context) {
        hashtagDBOpen = new HashtagDBOpen(context,DB_NAME,null,DB_VERSION);
    }

    /**
     * Find all hashtag
     * @return Cursor
     */
    public Cursor findAll(){
        String[] col = new String[] {COL_ID, COL_HASHTAG};
        SQLiteDatabase db = hashtagDBOpen.getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME,col,null,null,null,null,null);
        return cursor;
    }

    /**
     * Add hashtag
     * @param hashtag hashtag's name
     */
    public void addMessage(String hashtag){
        ContentValues valeurs = new ContentValues();
        valeurs.put(COL_HASHTAG,hashtag);
        SQLiteDatabase db = hashtagDBOpen.getWritableDatabase();
        db.insert(TABLE_NAME,null,valeurs);
    }




}
