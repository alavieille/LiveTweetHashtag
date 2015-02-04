package dnr2i.antoine.amaury.livetweethashtag;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.EditText;


public class AddHashtagDialog extends DialogFragment {

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
                //.setIcon(R.drawable.alert_dialog_icon)
                .setTitle(title)
                .setPositiveButton(R.string.button_valid,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                EditText editText = (EditText) Dialog.class.cast(dialog).findViewById(R.id.input_hashtag);
                                String hashtag = editText.getText().toString();
                                ((HashtagListActivity) getActivity()).addHashtag(hashtag);
                            }
                        }
                )
                .setNegativeButton("Annuler",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.dismiss();
                            }
                        })
                .setView(R.layout.dialog_add_hashtag)
                .create();
    }

}