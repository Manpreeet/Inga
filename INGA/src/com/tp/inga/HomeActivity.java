package com.tp.inga;

import java.util.ArrayList;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.ParentActivity;
import com.tp.inga.database.DBHelper;
import com.tp.inga.database.DB_GetterSetter;

//
public class HomeActivity extends ParentActivity implements OnClickListener {
	/*
	 * Text notes id=1,audio notes id=2,picture id=3 listnotes id =4
	 */
	DBHelper dbHelper;
	ImageView audio, imv_menu_right;
	GridView gridView;
	ArrayList<DB_GetterSetter> arrayList = new ArrayList<DB_GetterSetter>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.home);
		dbHelper = new DBHelper(this);
		gridView = (GridView) findViewById(R.id.gridView1);
		// gridView.setNumColumns(2);
		findId();
		ImageView add_notes = (ImageView) findViewById(R.id.add_notes);
		ImageView image_notes = (ImageView) findViewById(R.id.imageView6);
		image_notes.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(HomeActivity.this, Image_Notes.class);
				startActivity(intent);

			}
		});
		audio = (ImageView) findViewById(R.id.imageView5);
		fetchNotesFromDB();

		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				if (arrayList.get(arg2).getNOTE_TYPE() == 1)// text
				{
					Intent intent = new Intent(HomeActivity.this,
							Add_TextNotes.class);
					intent.putExtra("note_type", "1");
					intent.putExtra("note_id", arrayList.get(arg2).getKEY_ID()
							+ "");
					Log.e("kjhh", arrayList.get(arg2).getKEY_ID() + "");
					intent.putExtra("note_title", arrayList.get(arg2)
							.getNOTE_TITLE());
					intent.putExtra("note_content", arrayList.get(arg2)
							.getKEY_CONTENT());
					startActivity(intent);
				} else if (arrayList.get(arg2).getNOTE_TYPE() == 2)// audio
				{
					Intent intent = new Intent(HomeActivity.this,
							Audio_Notes.class);
					intent.putExtra("note_type", "2");
					intent.putExtra("note_id", arrayList.get(arg2).getKEY_ID()
							+ "");
					Log.e("kjhh", arrayList.get(arg2).getKEY_ID() + "");
					intent.putExtra("note_title", arrayList.get(arg2)
							.getNOTE_TITLE());
					intent.putExtra("note_content", arrayList.get(arg2)
							.getKEY_CONTENT());
					intent.putExtra("audio_url", arrayList.get(arg2)
							.getKEY_ISVOICENOTE());
					startActivity(intent);
				}
			}
		});

		audio.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(HomeActivity.this, Audio_Notes.class);
				startActivity(intent);
			}
		});
		add_notes.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(HomeActivity.this,
						Add_TextNotes.class);
				startActivity(intent);
			}
		});
	}

	/**
	 * Name-:Manpreet Singh Date-:8-4-15 return-:void description -:method used
	 * for find all attributesid's
	 **/
	public void findId() {
		imv_menu_right = (ImageView) findViewById(R.id.menu_right);
	}

	public void fetchNotesFromDB() {
		arrayList.clear();
		arrayList = (ArrayList<DB_GetterSetter>) dbHelper.getAllContacts();
		// DB_GetterSetter db_GetterSetter = new DB_GetterSetter();

		if (arrayList.size() > 0) {
			System.out.println(">> " + arrayList.size() + ""
					+ arrayList.get(0).getKEY_CONTENT());

			gridView.setAdapter(new GridAdapter());
		}

	}

	public class GridAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return arrayList.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			// TODO Auto-generated method stub
			Log.e("###", "#@!");
			LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
			View rowview = layoutInflater.inflate(R.layout.add_layout, null);
			LinearLayout layout = (LinearLayout) rowview
					.findViewById(R.id.linear);
			Log.e("@!!", arrayList.get(arg0).getNOTE_TYPE() + "");

			if (arrayList.get(arg0).getNOTE_TYPE() == 1) // text note
			{

				LayoutInflater layoutInflater1 = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
				View vv = layoutInflater1.inflate(R.layout.grid_simpletext,
						null);
				Log.e("color get from db", arrayList.get(arg0).getKEY_COLOR());
				vv.setBackgroundColor(Color.parseColor(arrayList.get(arg0)
						.getKEY_COLOR()));
				layout.setBackgroundColor(Color.parseColor(arrayList.get(arg0)
						.getKEY_COLOR()));
				// rowview.setBackgroundColor(Color.parseColor(arrayList.get(arg0).getKEY_COLOR()));
				TextView title = (TextView) vv.findViewById(R.id.textView1);
				TextView notes = (TextView) vv.findViewById(R.id.textView2);
				title.setText(arrayList.get(arg0).getNOTE_TITLE());
				notes.setText(arrayList.get(arg0).getKEY_CONTENT());
				layout.addView(vv);
			} else if (arrayList.get(arg0).getNOTE_TYPE() == 2) // audio note
			{

				LayoutInflater layoutInflater1 = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
				View vv = layoutInflater1.inflate(R.layout.grid_audio_notes,
						null);
				// Log.e("color get from db",
				// arrayList.get(arg0).getKEY_COLOR());
				vv.setBackgroundColor(Color.parseColor(arrayList.get(arg0)
						.getKEY_COLOR()));
				layout.setBackgroundColor(Color.parseColor(arrayList.get(arg0)
						.getKEY_COLOR()));
				// rowview.setBackgroundColor(Color.parseColor(arrayList.get(arg0).getKEY_COLOR()));
				TextView title = (TextView) vv.findViewById(R.id.textView1);
				TextView notes = (TextView) vv.findViewById(R.id.textView2);
				title.setText(arrayList.get(arg0).getNOTE_TITLE());
				notes.setText(arrayList.get(arg0).getKEY_CONTENT());
				layout.addView(vv);
			} else if (arrayList.get(arg0).getNOTE_TYPE() == 3) // image note
			{

				LayoutInflater layoutInflater1 = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
				View vv = layoutInflater1.inflate(R.layout.grid_image, null);

				ImageView imageView = (ImageView) vv
						.findViewById(R.id.imageView1);
				byte[] imm = arrayList.get(arg0).getKEY_PICBLOB();

				// Convert image into string to save path in local DB
				Bitmap bmp = BitmapFactory.decodeByteArray(imm, 0, imm.length);
				imageView.setImageBitmap(bmp);

				TextView title = (TextView) vv.findViewById(R.id.textView1);
				TextView notes = (TextView) vv.findViewById(R.id.textView2);
				title.setText(arrayList.get(arg0).getNOTE_TITLE());
				notes.setText(arrayList.get(arg0).getKEY_CONTENT());
				layout.addView(vv);
			}

			return rowview;
		}

		public View addGridItem() {
			return audio;

		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		fetchNotesFromDB();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}
}
