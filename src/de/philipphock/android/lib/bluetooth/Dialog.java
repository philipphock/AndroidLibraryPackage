package de.philipphock.android.lib.bluetooth;

import java.util.Set;

import android.app.AlertDialog;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.ListAdapter;
import de.philipphock.android.lib.dialog.DialogHelper.SelectedCallback;

public class Dialog {
	public static AlertDialog createSingleChoiceDialog(Context c, String title,Set<BluetoothDevice> devices,final SelectedCallback<BluetoothDevice> callback){
		AlertDialog ret;
        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        final BluetoothDeviceArrayAdapter daa = new BluetoothDeviceArrayAdapter(c);
        ListAdapter la = daa;
        daa.addAll(devices);
        builder.setTitle(title);
        builder.setSingleChoiceItems(la, -1, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				BluetoothDevice d = daa.getItem(which);
				dialog.dismiss();
				callback.onSelected(d);
			}
		});
       
        builder.setNegativeButton("Abort", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				callback.onCancel();
				
			}
		});
        ret = builder.create();
        return ret;
	}
	
	
}
