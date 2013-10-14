package de.philipphock.android.lib.activities;

import de.philipphock.android.lib.R;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class DialogActivity extends Activity {

	public static String title=null;
	public static String text=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dialog);
	}

	  
	
	@Override
	protected void onResume() {
		super.onResume();
		
		TextView txt = (TextView) findViewById(R.id.dialogText); 
		txt.setText(text);
		setTitle(title);
		
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}
	
	
	
	public void onOkClick(View v){
		finish();
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
	}

	
	public static void showDialog(String title, String text, Context c){
		Intent i = new Intent(c,DialogActivity.class);
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		DialogActivity.title=title;
		DialogActivity.text=text;
		c.startActivity(i);
	}
}
