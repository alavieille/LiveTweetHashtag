package dnr2i.antoine.amaury.livetweethashtag;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import dnr2i.antoine.amaury.livetweethashtag.modelDB.TweetDBHandler;


/**
 * An activity representing a single Hashtag detail screen. This
 * activity is only used on handset devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link HashtagListActivity}.
 * <p/>
 * This activity is mostly just a 'shell' activity containing nothing
 * more than a {@link HashtagDetailFragment}.
 */
public class HashtagDetailActivity extends FragmentActivity {
    /**
     * Instance de tweetDbHandler
     */
    private TweetDBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new TweetDBHandler(this);

        setContentView(R.layout.activity_hashtag_detail);

        // Show the Up button in the action bar.
        getActionBar().setDisplayHomeAsUpEnabled(true);
        if (savedInstanceState == null) {

            setTitle(getIntent().getStringExtra(HashtagDetailFragment.HASHTAG_LABEL));

            Bundle arguments = new Bundle();
            arguments.putString(HashtagDetailFragment.HASHTAG_ID,getIntent().getStringExtra(HashtagDetailFragment.HASHTAG_ID));
            arguments.putString(HashtagDetailFragment.HASHTAG_LABEL,getIntent().getStringExtra(HashtagDetailFragment.HASHTAG_LABEL));
            HashtagDetailFragment fragment = new HashtagDetailFragment();
            fragment.setArguments(arguments);
            getFragmentManager().beginTransaction()
                    .add(R.id.hashtag_detail_container, fragment)
                    .commit();



        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_tweet_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: // return home
                navigateUpTo(new Intent(this, HashtagListActivity.class));
                return true;
            case R.id.setting:
                SettingsActivity activity = new SettingsActivity();
                //startActivity(activity);
                Intent intent = new Intent(this,SettingsActivity.class);
                startActivity(intent);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
