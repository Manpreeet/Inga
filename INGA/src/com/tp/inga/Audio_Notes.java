
package com.tp.inga;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tp.inga.database.DBHelper;
import com.tp.inga.database.DB_GetterSetter;

public class Audio_Notes extends Activity {
	ImageView back,save; 
	EditText title,note;
	DBHelper dbHelper;
	SQLiteDatabase liteDatabase;
	private final int REQ_CODE_SPEECH_INPUT = 100;
	DB_GetterSetter db_GetterSetter;
	
	TextView duration;
	//
	  private MediaRecorder myAudioRecorder;
	   private String outputFile = null;
	   
	// to check whether this is new note or not
		boolean edit_note = false;
		
		//update note
		String note_type ,note_id,note_title,note_content;
@Override
protected void onCreate(Bundle bundle) {
	// TODO Auto-generated method stub
	super.onCreate(bundle);
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	setContentView(R.layout.audio_notes);	
	
	title = (EditText)findViewById(R.id.editText1);
	note= (EditText)findViewById(R.id.editText2);
	save = (ImageView)findViewById(R.id.imageView3);
	back= (ImageView)findViewById(R.id.imageView1);
	duration = (TextView)findViewById(R.id.textView11);
	
	
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
	if(!edit_note)
	promptSpeechInput();
	else
	{
		title.setText(note_title);
		note.setText(note_content);
		outputFile = bundle.getString("audio_url");
		Log.e("audio file path", outputFile);
	}
	
	Button play = (Button)findViewById(R.id.button1);
	play.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			MediaPlayer mp = MediaPlayer.create(Audio_Notes.this, Uri.parse(outputFile));
			try {
				mp.prepare();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mp.start();
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
			dbHelper = new DBHelper(Audio_Notes.this);
//			liteDatabase = dbHelper.getWritableDatabase();
//			dbHelper.insertTextNote(new DB_GetterSetter());
			//dbHelper.insertTextNote(new DB_GetterSetter(2,title.getText().toString(), note.getText().toString(), "false", "false", "false", "false"));
			//dbHelper.insertTextNote(new DB_GetterSetter(2,title.getText().toString(), note.getText().toString(), "false", "false", "false", "false","#ffffff"));
			dbHelper.insertAudioNote(new DB_GetterSetter(2,title.getText().toString(), note.getText().toString(), outputFile, "false", "false", "false","#ffffff"));
			
			
			finish();
			
		}
	});
}


/**
 * Showing google speech input dialog
 * */
private void promptSpeechInput() {
    Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
    intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
            getString(R.string.speech_prompt));
    recordAudio();
    try {
        startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        startRecording();
    } catch (ActivityNotFoundException a) {
        Toast.makeText(getApplicationContext(),
                getString(R.string.speech_not_supported),
                Toast.LENGTH_SHORT).show();
    }
}

public void createDir()
{
	File notes_folder = new File("/sdcard/tp_notes");
	if(notes_folder.exists())
	{
		
	}
	else
	{
		notes_folder.mkdirs();	
	File nomediaFile = new File(notes_folder, ".nomedia");
	try {
		nomediaFile.createNewFile();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	outputFile = notes_folder+File.separator+System.currentTimeMillis()+ ".3gp";
}
/**
 * Receiving speech input
 * */
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    switch (requestCode) {
    case REQ_CODE_SPEECH_INPUT: {
        if (resultCode == RESULT_OK && null != data) {
        	stopRecording();
            ArrayList<String> result = data
                    .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            Log.e("result", result.size()+"  "+result.get(0));
            note.setText(result.get(0));
          
			
          //  txtSpeechInput.setText(result.get(0));
        }
        break;
    }

    }
}
public void startRecording(){
    try {
       myAudioRecorder.prepare();
       myAudioRecorder.start();
    } catch (IllegalStateException e) {
       // TODO Auto-generated catch block
       e.printStackTrace();
    } catch (IOException e) {
       // TODO Auto-generated catch block
       e.printStackTrace();
    }

    Toast.makeText(getApplicationContext(), "Recording started", Toast.LENGTH_LONG).show();

 }

 public void stopRecording(){
    myAudioRecorder.stop();
    myAudioRecorder.release();
    myAudioRecorder  = null;
    
    Toast.makeText(getApplicationContext(), "Audio recorded successfully",
    Toast.LENGTH_LONG).show();
    MediaPlayer mp = MediaPlayer.create(Audio_Notes.this, Uri.parse(outputFile));
	int duration = mp.getDuration()/10000;
	if(duration<60)
	this.duration.setText("0:"+duration);
	else
		this.duration.setText("0:"+duration);
 }
public void recordAudio()
{
			  createDir();
		      myAudioRecorder = new MediaRecorder();
		      myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		      myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		      myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
		      myAudioRecorder.setOutputFile(outputFile);
}
}
