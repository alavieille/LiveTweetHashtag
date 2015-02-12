package dnr2i.antoine.amaury.livetweethashtag;

import android.app.ListFragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import dnr2i.antoine.amaury.livetweethashtag.dialog.UpdateTweetDialog;
import dnr2i.antoine.amaury.livetweethashtag.model.Tweet;
import dnr2i.antoine.amaury.livetweethashtag.model.TweetsAdapter;
import dnr2i.antoine.amaury.livetweethashtag.modelDB.TweetDBHandler;
import dnr2i.antoine.amaury.livetweethashtag.twitter4j.TwitterSearchManager;

/**
 * A fragment representing a single Hashtag detail screen.
 * This fragment is either contained in a {@link HashtagListActivity}
 * in two-pane mode (on tablets) or a {@link HashtagDetailActivity}
 * on handsets.
 */
public class HashtagDetailFragment extends ListFragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String HASHTAG_ID = "hashtag_id";

    /**
     * The fragment argument representing the item LABEL that this fragment
     * represents.
     */
    public static final String HASHTAG_LABEL = "hashtag_label";

    /**
     * Instance of TweetDBHandler
     */
    private TweetDBHandler db;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public HashtagDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new TweetDBHandler(this.getActivity());
        if (getArguments().containsKey(HASHTAG_ID)) {
            String idHashtag = getArguments().getString(HASHTAG_ID);
            loadListView(idHashtag);
        }

        if(checkSettingUpdate()) {

            UpdateTweetDialog newFragment = UpdateTweetDialog.newInstance(R.string.title_update_dialog);
            newFragment.show(getFragmentManager(), "update");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    /**
     * Check if show dialog for update tweet according settings
     * @return booleans
     */
    public boolean checkSettingUpdate(){
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this.getActivity());
        String syncConnPref = sharedPref.getString("sync_tweet", "-1");
        if(syncConnPref.equals("-1")) // jamais
            return false;
        else if(syncConnPref.equals("2") && isOnline()) // Toujours demander
            return true;
        else if(syncConnPref.equals("1") && isOnline())// uniquement en wifi
            return true;
        else
            return false;
    }

    /**
     * Check if user is online
     * @return status of connection
     */
    public boolean isOnline() {

        ConnectivityManager cm =(ConnectivityManager)this.getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return  activeNetwork != null && activeNetwork.isConnected();

    }

    /**
     * Check if user is connect with wifi
     * @return boolean
     */
    public boolean isWifi(){
        ConnectivityManager cm = (ConnectivityManager) this.getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeWifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return activeWifi != null && activeWifi.isConnected();
    }

    /**
     * Refrest list of tweet
     */
    public void updateTweet(){
        if(HashtagDetailFragment.HASHTAG_LABEL != null && HashtagDetailFragment.HASHTAG_ID != null) {
            String labelHashtag = getArguments().getString(HASHTAG_LABEL);
            String idHashtag = getArguments().getString(HASHTAG_ID);
            //HashtagDetailFragment fragment =  ((HashtagDetailFragment) getFragmentManager().findFragmentById(R.id.hashtag_detail_container));

            TwitterSearchManager twitterSearchManager = new TwitterSearchManager(this.getActivity(),this,labelHashtag,idHashtag);

            db.removeTweets(idHashtag);

            twitterSearchManager.execute();
            Toast.makeText(this.getActivity(), R.string.load_update, Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this.getActivity(),R.string.error_update, Toast.LENGTH_SHORT).show();
        }

    }


    /**
     * Load list of tweet
     * @param idHashtag hastag's id
     */
    public void loadListView(String idHashtag){
        ArrayList<Tweet> tweets = db.findByHashtagId(idHashtag);
        if(tweets.size() == 0 ){
            Toast.makeText(this.getActivity(), R.string.none_tweet, Toast.LENGTH_SHORT).show();
        }
        else{
            TweetsAdapter adapter = new TweetsAdapter(this.getActivity(),R.layout.list_layout,tweets);
            setListAdapter(adapter);
        }
    }
}
