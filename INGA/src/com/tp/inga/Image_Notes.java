package com.tp.inga;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.MediaColumns;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.tp.inga.database.DBHelper;
import com.tp.inga.database.DB_GetterSetter;

public class Image_Notes extends Activity {
	EditText title ,notes;
	ImageView save;
	DBHelper dbHelper;
	ImageView imageView;
	byte[] pic;
	int REQUEST_CAMERA =200, SELECT_FILE=500;
	@Override
	protected void onCreate(Bundle bundle) {
		// TODO Auto-generated method stub
		super.onCreate(bundle);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.image_notes);
		imageView = (ImageView)findViewById(R.id.img);
		bundle = getIntent().getExtras();		
		selectImage();
		
		dbHelper = new DBHelper(Image_Notes.this);
		
		title = (EditText)findViewById(R.id.editText1);
		notes = (EditText)findViewById(R.id.editText2);
		save = (ImageView)findViewById(R.id.imageView3);
		save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
//				dbHelper.o 
				dbHelper = new DBHelper(Image_Notes.this);
//				liteDatabase = dbHelper.getWritableDatabase();
//				dbHelper.insertTextNote(new DB_GetterSetter());
				//Log.e("current color", current_color);
				if(null!= pic)
				dbHelper.insertPicNote(new DB_GetterSetter(3,title.getText().toString(), notes.getText().toString(), "false", "false", "false","false","#ffffff",pic));
				else {
					Toast.makeText(Image_Notes.this, "null blob", Toast.LENGTH_SHORT).show();
				}
				
				
				finish();
				
			}
		});
		
	}
	private void selectImage() {
		final CharSequence[] items = { "Choose from Library",
		"Cancel" };
		 
		AlertDialog.Builder builder = new AlertDialog.Builder(Image_Notes.this);
		builder.setTitle("Add Photo!");
		builder.setItems(items, new DialogInterface.OnClickListener() {
		@Override
		public void onClick(DialogInterface dialog, int item) {
//		if (items[item].equals("Take Photo")) {
//		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//		File f = new File(android.os.Environment
//		.getExternalStorageDirectory(), "temp.jpg");
//		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
//		startActivityForResult(intent, REQUEST_CAMERA);
//		} else
			if (items[item].equals("Choose from Library")) {
		Intent intent = new Intent(
		Intent.ACTION_PICK,
		android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		intent.setType("image/*");
		startActivityForResult(
		Intent.createChooser(intent, "Select File"),
		SELECT_FILE);
		} else if (items[item].equals("Cancel")) {
		dialog.dismiss();
		finish();
		}
		}
		});
		builder.show();
		}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	super.onActivityResult(requestCode, resultCode, data);
	if (resultCode == RESULT_OK) {
	if (requestCode == REQUEST_CAMERA) {
	File f = new File(Environment.getExternalStorageDirectory()
	.toString());
	for (File temp : f.listFiles()) {
	if (temp.getName().equals("temp.jpg")) {
	f = temp;
	break;
	}
	}
	try {
	Bitmap bm;
	BitmapFactory.Options btmapOptions = new BitmapFactory.Options();
	 
	bm = BitmapFactory.decodeFile(f.getAbsolutePath(),
	btmapOptions);
	 
	// convert to byte array
	ByteArrayOutputStream baos = new ByteArrayOutputStream();  
	bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
	 bm = Bitmap.createScaledBitmap(bm, 100, 100, true);
	//this will convert image to byte[] 
	pic= baos.toByteArray(); 
	// bm = Bitmap.createScaledBitmap(bm, 70, 70, true);
	imageView.setImageBitmap(bm);
	 
	String path = android.os.Environment
	.getExternalStorageDirectory()
	+ File.separator
	+ "Phoenix" + File.separator + "default";
	f.delete();
	OutputStream fOut = null;
	File file = new File(path, String.valueOf(System
	.currentTimeMillis()) + ".jpg");
	try {
	fOut = new FileOutputStream(file);
	bm.compress(Bitmap.CompressFormat.JPEG, 85, fOut);
	fOut.flush();
	fOut.close();
	} catch (FileNotFoundException e) {
	e.printStackTrace();
	} catch (IOException e) {
	e.printStackTrace();
	} catch (Exception e) {
	e.printStackTrace();
	}
	} catch (Exception e) {
	e.printStackTrace();
	}
	} else if (requestCode == SELECT_FILE) {
	Uri selectedImageUri = data.getData();
	 
	String tempPath = getPath(selectedImageUri, Image_Notes.this);
	
	Bitmap bm;
	BitmapFactory.Options btmapOptions = new BitmapFactory.Options();
	bm = BitmapFactory.decodeFile(tempPath, btmapOptions);
	
	// convert to byte array
		ByteArrayOutputStream baos = new ByteArrayOutputStream();  
		bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
		//this will convert image to byte[] 
		pic= baos.toByteArray(); 
	imageView.setImageBitmap(bm);
	}
	}
	}
	public String getPath(Uri uri, Activity activity) {
		String[] projection = { MediaColumns.DATA };
		Cursor cursor = activity
		.managedQuery(uri, projection, null, null, null);
		int column_index = cursor.getColumnIndexOrThrow(MediaColumns.DATA);
		cursor.moveToFirst();
		return cursor.getString(column_index);
		}
}
