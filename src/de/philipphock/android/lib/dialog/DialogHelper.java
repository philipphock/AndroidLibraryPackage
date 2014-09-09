package de.philipphock.android.lib.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class DialogHelper {
	
	public static AlertDialog createSingleChoiceDialog(Context c, String title, String[] choices,DialogInterface.OnClickListener listener){
		AlertDialog ret;
        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle(title);
        builder.setSingleChoiceItems(choices, -1, listener);
        ret = builder.create();
        return ret;
	
	}
	
	
	public interface SelectedCallback<E>{
		public void onSelected(E d);
		public void onCancel();
	}
	

}
