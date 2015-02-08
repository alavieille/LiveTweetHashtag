package dnr2i.antoine.amaury.livetweethashtag.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import dnr2i.antoine.amaury.livetweethashtag.HashtagDetailFragment;
import dnr2i.antoine.amaury.livetweethashtag.R;

/**
 * Boite de dialogue pour mettre à jour la liste des tweets d'un hashtag
 * Created by amaury and antoine on 06/02/15.
 */
public class UpdateTweetDialog extends DialogFragment {

    /**
     * Retourne un nouveau fragment contenant la boite de dialogue
     * @param title reference de la string du titre
     * @return retourne le frament contenant la boite de dialogue
     */
    public static UpdateTweetDialog newInstance(int title) {
        UpdateTweetDialog frag = new UpdateTweetDialog();
        Bundle args = new Bundle();
        args.putInt("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int title = getArguments().getInt("title");
        return new AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setPositiveButton(R.string.button_ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                HashtagDetailFragment fragment =  ((HashtagDetailFragment) getFragmentManager().findFragmentById(R.id.hashtag_detail_container));
                                fragment.updateTweet();
                            }
                        }
                )
                .setNegativeButton(R.string.button_dismiss,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.dismiss();
                            }
                        })
                .create();
    }

}
