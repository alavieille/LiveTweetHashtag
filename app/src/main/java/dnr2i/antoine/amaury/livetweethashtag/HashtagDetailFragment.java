package dnr2i.antoine.amaury.livetweethashtag;

import android.app.ListFragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
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
        // Fragement update @TODO faire le test de connexion
        if(isOnline()) {
            UpdateTweetDialog newFragment = UpdateTweetDialog.newInstance(R.string.title_update_dialog);
            newFragment.show(getFragmentManager(), "update");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    /**
     * Check is user is online
     * @return status of connection
     */
    public boolean isOnline() {

        ConnectivityManager cm =(ConnectivityManager)this.getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        return isConnected;
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
            Toast.makeText(this.getActivity(), "Impossible de mettre à jour", Toast.LENGTH_SHORT).show();
        }

    }


    /**
     * Load list of tweet
     * @param idHashtag hastag's id
     */
    public void loadListView(String idHashtag){
        ArrayList<Tweet> tweets = db.findByHashtagId(idHashtag);
        TweetsAdapter adapter = new TweetsAdapter(this.getActivity(),android.R.layout.simple_list_item_2,tweets);
        setListAdapter(adapter);
    }
}
