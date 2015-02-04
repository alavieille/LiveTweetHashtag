package dnr2i.antoine.amaury.livetweethashtag.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by amaury on 04/02/15.
 */
public class HashtagsAdapter extends ArrayAdapter<Hashtag> {

    public HashtagsAdapter(Context context, int idLayout, ArrayList<Hashtag> hashtags){
        super(context,idLayout,hashtags);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Hashtag hashtag = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        TextView text = (TextView) convertView.findViewById(android.R.id.text1);
        text.setText(hashtag.getLabel());

        return convertView;
    }


}
