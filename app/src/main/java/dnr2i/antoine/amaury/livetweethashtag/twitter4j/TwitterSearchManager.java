package dnr2i.antoine.amaury.livetweethashtag.twitter4j;

import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dnr2i.antoine.amaury.livetweethashtag.HashtagDetailFragment;
import dnr2i.antoine.amaury.livetweethashtag.model.Tweet;
import dnr2i.antoine.amaury.livetweethashtag.modelDB.HashtagDBHandler;
import dnr2i.antoine.amaury.livetweethashtag.modelDB.TweetDBHandler;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

/**
 * Created by amaury on 04/02/15.
 */
public class TwitterSearchManager extends AsyncTask<Void,Void,Void> {


    private Twitter twitter;
    private String queryHashtag;
    private String idHashtag;
    private TweetDBHandler dbTweet;
    private HashtagDBHandler dbHashtag;
    private Fragment fragment;


    public TwitterSearchManager(Context context, Fragment fragment,String queryHashtag, String idHashtag) {
        twitter = new TwitterFactory(TwitterConfiguration.getConfiguration()).getInstance();
        dbTweet = new TweetDBHandler(context);
        dbHashtag = new HashtagDBHandler(context);
        this.fragment = fragment;
        this.queryHashtag = queryHashtag;
        this.idHashtag = idHashtag;


    }

    @Override
    protected Void doInBackground(Void... params) {

        try {

            ArrayList<Tweet> tweets = searchTweetHashtag(this.queryHashtag);
            dbTweet.addTweets(tweets);
        } catch (TwitterException e) {
            e.printStackTrace();
        }

        return null;
    }


    private ArrayList<Tweet> searchTweetHashtag(String hashtagLabel) throws TwitterException {

        ArrayList<Tweet> listTweets = new ArrayList<Tweet>();

        Query query = new Query(hashtagLabel);
        QueryResult result;

        result = twitter.search(query);

        List<twitter4j.Status> tweets = result.getTweets();

        for (twitter4j.Status tweet : tweets) {
            Date date = tweet.getCreatedAt();
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String reportDate = df.format(date);
            String picture = tweet.getUser().getBiggerProfileImageURL();
            listTweets.add(new Tweet(tweet.getUser().getName(),tweet.getText(),reportDate,picture,this.idHashtag));

        }
        return listTweets;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        ((HashtagDetailFragment)this.fragment).loadListView(this.idHashtag);

    }
}
