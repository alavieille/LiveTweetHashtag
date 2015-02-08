package dnr2i.antoine.amaury.livetweethashtag.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Un adpater pour afficher la liste des tweets dans le layout
 * Created by amaury and antoine on 05/02/15.
 */
public class TweetsAdapter extends ArrayAdapter<Tweet> {

    public TweetsAdapter(Context context, int idLayout, ArrayList<Tweet> tweets){
        super(context,idLayout,tweets);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Tweet tweet = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
        }

        TextView text1 = (TextView) convertView.findViewById(android.R.id.text1);
        TextView text2 = (TextView) convertView.findViewById(android.R.id.text2);
        text1.setText(tweet.getPseudo());
        text2.setText(tweet.getContent());

        return convertView;
    }


}
