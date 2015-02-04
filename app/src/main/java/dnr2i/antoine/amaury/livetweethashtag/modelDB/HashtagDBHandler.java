package dnr2i.antoine.amaury.livetweethashtag.modelDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import dnr2i.antoine.amaury.livetweethashtag.model.Hashtag;

/**
 * Class for Manage Hashtag into SQLite Database
 * Created by amaury on 30/01/15.
 */
public class HashtagDBHandler {



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
        hashtagDBOpen = new HashtagDBOpen(context,null,DB_VERSION);
    }

    /**
     * Find all hashtag
     * @return ArrayList<Hashtag>
     */
    public ArrayList<Hashtag> findAll(){
        String[] col = new String[] {COL_ID, COL_HASHTAG};
        SQLiteDatabase db = hashtagDBOpen.getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME,col,null,null,null,null,null);
        ArrayList<Hashtag> hashtags = new ArrayList<Hashtag>();
        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            // The Cursor is now set to the right position
            Hashtag hashtag = new Hashtag(cursor.getInt(cursor.getColumnIndex(COL_ID)),cursor.getString(cursor.getColumnIndex(COL_HASHTAG)));
            hashtags.add(hashtag);
        }


        return hashtags;
    }

    public void deleteMessage(int id){
        SQLiteDatabase db = hashtagDBOpen.getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_NAME+" WHERE "+COL_ID+"="+id);
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
