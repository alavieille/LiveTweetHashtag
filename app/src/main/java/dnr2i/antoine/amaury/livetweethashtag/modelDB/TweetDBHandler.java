package dnr2i.antoine.amaury.livetweethashtag.modelDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import dnr2i.antoine.amaury.livetweethashtag.model.Tweet;

/**
 * Class for Manage Tweet into SQLite Database
 * Created by amaury on 30/01/15.
 */
public class TweetDBHandler {



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


    public static final String COL_PSEUDO = "pseudo";

    public static final String COL_HASHTAG = "hashtag";




    /**
     * Instance of TweetDBOpen
     */
    private HashtagDBOpen tweetDBOpen;

    /**
     * Build TweetDBHandler
     * @param context
     */
    public TweetDBHandler(Context context) {
        tweetDBOpen = new HashtagDBOpen(context,null,DB_VERSION);
    }

    /**
     * Find all tweet
     * @return Cursor
     */
   /* public Cursor findAll(){
        String[] col = new String[] {COL_ID, COL_CONTENT};
        SQLiteDatabase db = tweetDBOpen.getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME,col,null,null,null,null,null);
        return cursor;
    }*/

    /**
     * Add tweet
     * @param tweet tweet's content
     */
    public void addTweet(Tweet tweet){
        ContentValues valeurs = new ContentValues();
        valeurs.put(COL_CONTENT,tweet.getContent());
        valeurs.put(COL_PSEUDO,tweet.getContent());
        valeurs.put(COL_HASHTAG,tweet.getHashtag());
        SQLiteDatabase db = tweetDBOpen.getWritableDatabase();
        db.insert(TABLE_NAME,null,valeurs);
    }

    /**
     * Add twwets
     * @param tweets array of tweets
     */
    public void addTweets(ArrayList<Tweet> tweets) {
        for (Tweet tweet : tweets){
            this.addTweet(tweet);
        }
    }
}
