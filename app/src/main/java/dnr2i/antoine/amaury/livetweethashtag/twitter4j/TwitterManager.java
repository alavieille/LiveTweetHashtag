package dnr2i.antoine.amaury.livetweethashtag.twitter4j;

import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import dnr2i.antoine.amaury.livetweethashtag.model.Tweet;
import dnr2i.antoine.amaury.livetweethashtag.modelDB.TweetDBHandler;
import dnr2i.antoine.amaury.livetweethashtag.utils.TwitterConfiguration;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

/**
 * Created by amaury on 04/02/15.
 */
public class TwitterManager extends AsyncTask<String,Void,Void> {


    private Twitter twitter;
    private Context context;
    private TweetDBHandler db;

    public TwitterManager(Context context) {
        twitter = new TwitterFactory(TwitterConfiguration.getConfiguration()).getInstance();
        db = new TweetDBHandler(context);

    }

    @Override
    protected Void doInBackground(String... hashtags) {
        for (String hashtag : hashtags) {
            try {
                ArrayList<Tweet> tweets = searchTweetHashtag(hashtag);
                db.addTweets(tweets);
            } catch (TwitterException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    private ArrayList<Tweet> searchTweetHashtag(String hashtag) throws TwitterException {

        ArrayList<Tweet> listTweets = new ArrayList<Tweet>();
        Query query = new Query(hashtag);
        QueryResult result;

        result = twitter.search(query);
        List<twitter4j.Status> tweets = result.getTweets();

        for (twitter4j.Status tweet : tweets) {
            listTweets.add(new Tweet(tweet.getUser().getScreenName(),tweet.getText(),hashtag));
            System.out.printf(tweet.getUser().getScreenName());
        }

        return listTweets;
    }

}
