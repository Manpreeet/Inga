package com.tp.inga;

import android.app.Activity;
import android.app.Dialog;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.tp.inga.database.DBHelper;
import com.tp.inga.database.DB_GetterSetter;

public class Add_TextNotes extends Activity {
	ImageView back,save,changecolor; 
	EditText title,note;
	DBHelper dbHelper;
	SQLiteDatabase liteDatabase;
	DB_GetterSetter db_GetterSetter;
	RelativeLayout parent_layout;
	String current_color="#ffffff";
	
	// to check whether this is new note or not
	boolean edit_note = false;
	
	//update note
	String note_type ,note_id,note_title,note_content;
	
	
@Override
protected void onCreate(Bundle bundle) {
	// TODO Auto-generated method stub
	super.onCreate(bundle);
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	setContentView(R.layout.text_notes);
	parent_layout = (RelativeLayout)findViewById(R.id.parent);
	
	bundle = getIntent().getExtras();
	if(null!= bundle)
	{
		 note_type = bundle.getString("note_type");
		 note_id = bundle.getString("note_id");
		 note_title = bundle.getString("note_title");
		 note_content = bundle.getString("note_content");
		 edit_note= true;
		 Log.e("note_type", note_type+"  "+note_id);
		
	}
	
	
	title = (EditText)findViewById(R.id.editText1);
	note= (EditText)findViewById(R.id.edit_notes);
	save = (ImageView)findViewById(R.id.imageView3);
	back= (ImageView)findViewById(R.id.imageView1);
	
	if(edit_note)
	{
		title.setText(note_title);
		note.setText(note_content);
	}
	
	changecolor= (ImageView)findViewById(R.id.changecolor);
	changecolor.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			colorPickerMethod();
		}
	});
	back.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			finish();
			
		}
	});
	save.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
//			dbHelper.o 
			dbHelper = new DBHelper(Add_TextNotes.this);

			Log.e("current color", current_color);
			if(!edit_note)
			dbHelper.insertTextNote(new DB_GetterSetter(1,title.getText().toString(), note.getText().toString(), "false", "false", "false", "false",current_color));
			else
				updateTextEvent(note_id, note_type);
			finish();
			
		}
	});
}

// ****************************method for open dialog of background
// color*****************//
private void colorPickerMethod() {
	final Dialog dialog = new Dialog(Add_TextNotes.this);
	dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
	dialog.setContentView(R.layout.custom_dialog);
	dialog.show();
	dialog.setCanceledOnTouchOutside(false);
	ImageView imv_black, imv_blue, imv_yellow, imv_green, imv_red, imv_pink;
	imv_black = (ImageView) dialog.findViewById(R.id.imv_black);
	imv_blue = (ImageView) dialog.findViewById(R.id.imv_blue);
	imv_yellow = (ImageView) dialog.findViewById(R.id.imv_yellow);
	imv_green = (ImageView) dialog.findViewById(R.id.imv_green);
	imv_pink = (ImageView) dialog.findViewById(R.id.imv_pink);
	imv_red = (ImageView) dialog.findViewById(R.id.imv_red);
	imv_black.setOnClickListener(new View.OnClickListener() {

		@Override
		public void onClick(View arg0) {
			parent_layout.setBackgroundColor(Color
					.parseColor("#000000"));
			current_color = "#000000";
			dialog.dismiss();

		}
	});
	imv_yellow.setOnClickListener(new View.OnClickListener() {

		@Override
		public void onClick(View arg0) {
			parent_layout.setBackgroundColor(Color
					.parseColor("#fff600"));
			current_color = "#fff600";
			dialog.dismiss();
		}
	});
	imv_red.setOnClickListener(new View.OnClickListener() {

		@Override
		public void onClick(View arg0) {
			parent_layout.setBackgroundColor(Color
					.parseColor("#ff0000"));
			current_color = "#ff0000";
			dialog.dismiss();

		}
	});
	imv_pink.setOnClickListener(new View.OnClickListener() {

		@Override
		public void onClick(View arg0) {
			parent_layout.setBackgroundColor(Color
					.parseColor("#ff00fc"));
			current_color = "#ff00fc";
			dialog.dismiss();
		}
	});
	imv_green.setOnClickListener(new View.OnClickListener() {

		@Override
		public void onClick(View arg0) {
			parent_layout.setBackgroundColor(Color
					.parseColor("#00ff00"));
			current_color = "#00ff00";
			dialog.dismiss();

		}
	});
	imv_blue.setOnClickListener(new View.OnClickListener() {

		@Override
		public void onClick(View arg0) {
			parent_layout.setBackgroundColor(Color
					.parseColor("#00fff6"));
			current_color = "#00fff6";
			dialog.dismiss();

		}
	});
}

public void updateTextEvent(String noteid,String notetype)
{
	dbHelper.updateTextNote(new DB_GetterSetter(Integer.parseInt(noteid),Integer.parseInt(notetype),note.getText().toString(),title.getText().toString(),current_color));
}


}
