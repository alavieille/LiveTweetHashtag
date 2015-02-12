package dnr2i.antoine.amaury.livetweethashtag.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import dnr2i.antoine.amaury.livetweethashtag.R;

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
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_layout, parent, false);
        }

        TextView userText = (TextView) convertView.findViewById(R.id.tweet_user);
        TextView contentText = (TextView) convertView.findViewById(R.id.tweet_content);
        TextView dateText = (TextView) convertView.findViewById(R.id.tweet_date);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.tweet_picture);

        userText.setText(tweet.getPseudo());
        dateText.setText(tweet.getDate());
        contentText.setText(tweet.getContent());
        ImageLoadTask loadPicture = new ImageLoadTask(tweet.getPicture(),imageView);
        loadPicture.execute();
        return convertView;
    }



}
