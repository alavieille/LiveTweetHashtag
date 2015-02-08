package dnr2i.antoine.amaury.livetweethashtag.modelDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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

    public static final String COL_HASHTAG = "hashtaId";




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
     * Add tweet
     * @param tweet tweet's content
     */
    public void addTweet(Tweet tweet){
        ContentValues valeurs = new ContentValues();
        valeurs.put(COL_CONTENT,tweet.getContent());
        valeurs.put(COL_PSEUDO,tweet.getPseudo());
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


    public void removeTweets(String id){
        SQLiteDatabase db = tweetDBOpen.getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_NAME+" WHERE "+COL_HASHTAG+"="+id);
    }

    /**
     * Find all tweet according hashtag id
     * @param id
     */
    public ArrayList<Tweet> findByHashtagId(String id){

        String[] col = new String[] {COL_ID, COL_CONTENT,COL_HASHTAG,COL_PSEUDO};
        SQLiteDatabase db = tweetDBOpen.getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME,col,COL_HASHTAG+"=?",new String[]{id},null,null,null);
        ArrayList<Tweet> tweets = new ArrayList<Tweet>();
        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            String pseudo = cursor.getString(cursor.getColumnIndex(COL_PSEUDO));
            String content = cursor.getString(cursor.getColumnIndex(COL_CONTENT));
            String hashtagId = cursor.getString(cursor.getColumnIndex(COL_HASHTAG));
            String idTweet = cursor.getString(cursor.getColumnIndex(COL_ID));
            tweets.add(new Tweet(pseudo,content,hashtagId,idTweet));
        }
        return tweets;
    }
}
