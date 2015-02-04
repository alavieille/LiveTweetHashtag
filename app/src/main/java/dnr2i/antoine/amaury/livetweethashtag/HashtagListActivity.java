package dnr2i.antoine.amaury.livetweethashtag;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import dnr2i.antoine.amaury.livetweethashtag.modelDB.HashtagDBHandler;


/**
 * An activity representing a list of Tweet. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link HashtagDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p/>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link HashtagListFragment} and the item details
 * (if present) is a {@link HashtagDetailFragment}.
 * <p/>
 * This activity also implements the required
 * {@link HashtagListFragment.Callbacks} interface
 * to listen for item selections.
 */
public class HashtagListActivity extends FragmentActivity
implements HashtagListFragment.Callbacks
{

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hashtag_list);
        //getActionBar().setDisplayHomeAsUpEnabled(true);
        if (findViewById(R.id.hashtag_detail_container) != null) {
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((HashtagListFragment) getFragmentManager()
                    .findFragmentById(R.id.hashtag_list))
                    .setActivateOnItemClick(true);
        }
    }

    /**
     * Callback method from {@link HashtagListFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(String id) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(HashtagDetailFragment.ARG_ITEM_ID, id);
            HashtagDetailFragment fragment = new HashtagDetailFragment();
            fragment.setArguments(arguments);
            getFragmentManager().beginTransaction()
                    .replace(R.id.hashtag_detail_container, fragment)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, HashtagDetailActivity.class);
            detailIntent.putExtra(HashtagDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_hashtag_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_add_hashtag:
                openAddHashtag();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void openAddHashtag(){

      // new TwitterManager(this).execute("DNR2I");


       AddHashtagDialog newFragment = AddHashtagDialog.newInstance(
       R.string.button_add_hashtag);
       newFragment.show(getSupportFragmentManager(), "dialog");
    }

    public void addHashtag(String hashtag){
        new HashtagDBHandler(this).addMessage(hashtag);
        Toast.makeText(this, "Hashtag ajouté", Toast.LENGTH_SHORT).show();
        ((HashtagListFragment) getFragmentManager().findFragmentById(R.id.hashtag_list)).loadListView();

    }

}
