package dnr2i.antoine.amaury.livetweethashtag.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.EditText;

import dnr2i.antoine.amaury.livetweethashtag.HashtagListActivity;
import dnr2i.antoine.amaury.livetweethashtag.R;

/**
 *
 * Boite de dialogue pour ajouter un novueau hashtag à la liste
 *
 */
public class AddHashtagDialog extends DialogFragment {

    /**
     * Retourne un nouveau fragment contenant la boite de dialogue
     * @param title reference de la string du titre
     * @return retourne le frament contenant la boite de dialogue
     */
    public static AddHashtagDialog newInstance(int title) {
        AddHashtagDialog frag = new AddHashtagDialog();
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
                .setPositiveButton(R.string.button_valid,
                        new DialogInterface.OnClickListener() {
                            // valid action
                            public void onClick(DialogInterface dialog, int whichButton) {
                                EditText editText = (EditText) Dialog.class.cast(dialog).findViewById(R.id.input_hashtag);
                                String hashtag = editText.getText().toString();
                                ((HashtagListActivity) getActivity()).addHashtag(hashtag);
                            }
                        }
                )
                .setNegativeButton(R.string.button_dismiss,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.dismiss();
                            }
                        })
                .setView(R.layout.dialog_add_hashtag)
                .create();
    }

}