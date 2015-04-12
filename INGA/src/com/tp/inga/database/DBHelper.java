package com.tp.inga.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
	
	// All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "notesManager";
 
    // Contacts table name
    private static final String TABLE_NOTES = "notes";
 
    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NOTETYPEID = "notetype_id";
    private static final String KEY_NOTETITLE = "notetitle";
    private static final String KEY_CONTENT = "content";
    private static final String KEY_COLOR = "color";
    private static final String KEY_ARCHIEVEDNOTE = "archieved_note";
    private static final String KEY_SHAREDWITH = "shared_with";
    private static final String KEY_ARECHECKITEMS = "check_items";
    private static final String KEY_CHECKEDITEMS = "checked_items";
    private static final String KEY_ISVOICENOTE = "isVoiceNote";
    private static final String KEY_VOICERECORDING = "voicerecording";
    private static final String KEY_RECORDINGDURATION = "recording_duration";
    private static final String KEY_HASPHOTO = "hasphoto";
    private static final String KEY_PICBLOB = "picblob";
    private static final String KEY_NOTEMAKINGTIME = "note_created";
    private static final String KEY_LASTEDITEDTIME = "note_lastedited";
    private static final String KEY_HASREMINDER = "hasreminder";
    private static final String KEY_REMINDERTIME = "remindertime";

	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub
		String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NOTES + "("
                + KEY_ID + " INTEGER PRIMARY KEY," 
                +KEY_NOTETYPEID+" INTEGER,"
				+ KEY_CONTENT + " TEXT,"
				+KEY_NOTETITLE+" TEXT,"
                + KEY_ARCHIEVEDNOTE + " TEXT,"
				+ KEY_SHAREDWITH + " TEXT,"
                + KEY_ARECHECKITEMS + " TEXT,"
                + KEY_CHECKEDITEMS + " TEXT,"
                + KEY_ISVOICENOTE + " TEXT,"
                + KEY_VOICERECORDING + " TEXT,"
                + KEY_RECORDINGDURATION + " TEXT,"
                + KEY_HASPHOTO + " TEXT,"
                + KEY_PICBLOB + " BLOB,"
                + KEY_NOTEMAKINGTIME + " TEXT,"
                + KEY_HASREMINDER + " TEXT,"
                + KEY_REMINDERTIME + " TEXT,"
                + KEY_LASTEDITEDTIME + " TEXT,"
                + KEY_COLOR + " TEXT" + ")";
        arg0.execSQL(CREATE_CONTACTS_TABLE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
        arg0.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES);
        
        // Create tables again
        onCreate(arg0);

	}
	   // Insertion
	public void insertTextNote(DB_GetterSetter contact) {
	    SQLiteDatabase db = this.getWritableDatabase();
	 
	    ContentValues values = new ContentValues();
	    values.put(KEY_NOTETYPEID, contact.getNOTE_TYPE());
	    values.put(KEY_NOTETITLE, contact.getNOTE_TITLE());
	    values.put(KEY_COLOR, contact.getKEY_COLOR());
	    values.put(KEY_CONTENT, contact.getKEY_CONTENT()); // DB_GetterSetter Name
	    values.put(KEY_ISVOICENOTE, contact.getKEY_ISVOICENOTE()); // Contact Phone Number
	    values.put(KEY_HASPHOTO, contact.getKEY_CONTENT()); // DB_GetterSetter Name
	    values.put(KEY_HASREMINDER, contact.getKEY_ISVOICENOTE()); // Contact Phone Number
	    values.put(KEY_ARECHECKITEMS, contact.getKEY_CHECKITEMS()); // DB_GetterSetter Name
	    values.put(KEY_ISVOICENOTE, contact.getKEY_ISVOICENOTE()); // Contact Phone Number
	   // DB_GetterSetter Name
//	    values.put(KEY_ISVOICENOTE, contact.getKEY_ISVOICENOTE()); // Contact Phone Number
//	    values.put(KEY_CONTENT, contact.getKEY_CONTENT()); // DB_GetterSetter Name
//	    values.put(KEY_ISVOICENOTE, contact.getKEY_ISVOICENOTE()); // Contact Phone Number
	 
	    // Inserting Row
	    db.insert(TABLE_NOTES, null, values);
	    Log.e("value inserted", "@@");
	    db.close(); // Closing database connection
	}
	 // Insertion
		public void insertAudioNote(DB_GetterSetter contact) {
		    SQLiteDatabase db = this.getWritableDatabase();
		 
		    ContentValues values = new ContentValues();
		    values.put(KEY_NOTETYPEID, contact.getNOTE_TYPE());
		    values.put(KEY_NOTETITLE, contact.getNOTE_TITLE());
		    values.put(KEY_CONTENT, contact.getKEY_CONTENT()); // Content Name
		    values.put(KEY_ISVOICENOTE, contact.getKEY_ISVOICENOTE()); // check if voice note
		    values.put(KEY_HASPHOTO, contact.getKEY_CONTENT()); //check if has photo
		    values.put(KEY_HASREMINDER, contact.getKEY_ISVOICENOTE()); // check if has reminder
		    values.put(KEY_ARECHECKITEMS, contact.getKEY_CHECKITEMS()); // check are check items
		    values.put(KEY_ISVOICENOTE, contact.getKEY_ISVOICENOTE()); // check is voice notes
		    values.put(KEY_COLOR, contact.getKEY_COLOR());
		 
		    // Inserting Row
		    db.insert(TABLE_NOTES, null, values);
		    db.close(); // Closing database connection
		}
		
		 // Insertion
		public void insertPicNote(DB_GetterSetter contact) {
		    SQLiteDatabase db = this.getWritableDatabase();
		 
		    ContentValues values = new ContentValues();
		    values.put(KEY_NOTETYPEID, contact.getNOTE_TYPE());
		    values.put(KEY_NOTETITLE, contact.getNOTE_TITLE());
		    values.put(KEY_CONTENT, contact.getKEY_CONTENT()); // DB_GetterSetter Name
		    values.put(KEY_ISVOICENOTE, contact.getKEY_ISVOICENOTE()); // Contact Phone Number
		    values.put(KEY_HASPHOTO, contact.getKEY_CONTENT()); // DB_GetterSetter Name
		    values.put(KEY_HASREMINDER, contact.getKEY_ISVOICENOTE()); // Contact Phone Number
		    values.put(KEY_ARECHECKITEMS, contact.getKEY_CHECKITEMS()); // DB_GetterSetter Name
		    values.put(KEY_PICBLOB, contact.getKEY_PICBLOB()); // Contact Phone Number
		    values.put(KEY_COLOR, contact.getKEY_COLOR());
		    Log.e("value inserted", "@@"+contact.getNOTE_TYPE());
		 //   values.put(KEY_CONTENT, contact.getKEY_CONTENT()); // DB_GetterSetter Name
//		    values.put(KEY_ISVOICENOTE, contact.getKEY_ISVOICENOTE()); // Contact Phone Number
//		    values.put(KEY_CONTENT, contact.getKEY_CONTENT()); // DB_GetterSetter Name
//		    values.put(KEY_ISVOICENOTE, contact.getKEY_ISVOICENOTE()); // Contact Phone Number
		 
		    // Inserting Row
		    db.insert(TABLE_NOTES, null, values);
		    db.close(); // Closing database connection
		}
		
//    // Getting single DB_GetterSetter
//public DB_GetterSetter getContact(int id) {
//    SQLiteDatabase db = this.getReadableDatabase();
// 
//    Cursor cursor = db.query(TABLE_NOTES, new String[] { KEY_ID,
//            KEY_NAME, KEY_PH_NO }, KEY_ID + "=?",
//            new String[] { String.valueOf(id) }, null, null, null, null);
//    if (cursor != null)
//        cursor.moveToFirst();
//// 
////    DB_GetterSetter contact = new DB_GetterSetter(Integer.parseInt(cursor.getString(0)),
////            cursor.getString(1), cursor.getString(2));
//    // return contact
//    return contact;
//}
//// Getting All Contacts
//public List<DB_GetterSetter> getAllContacts() {
//List<DB_GetterSetter> contactList = new ArrayList<DB_GetterSetter>();
//// Select All Query
//String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;
//
//SQLiteDatabase db = this.getWritableDatabase();
//Cursor cursor = db.rawQuery(selectQuery, null);
//
//// looping through all rows and adding to list
//if (cursor.moveToFirst()) {
//    do {
//        DB_GetterSetter contact = new DB_GetterSetter();
//        contact.setID(Integer.parseInt(cursor.getString(0)));
//        contact.setName(cursor.getString(1));
//        contact.setPhoneNumber(cursor.getString(2));
//        // Adding contact to list
//        contactList.add(contact);
//    } while (cursor.moveToNext());
//}
//
//// return contact list
//return contactList;
//}
		
 //Getting All Contacts
public List<DB_GetterSetter> getAllContacts() {
List<DB_GetterSetter> contactList = new ArrayList<DB_GetterSetter>();
// Select All Query
String selectQuery = "SELECT  * FROM " + TABLE_NOTES;

SQLiteDatabase db = this.getWritableDatabase();
Cursor cursor = db.rawQuery(selectQuery, null);

// looping through all rows and adding to list
if (cursor.moveToFirst()) {
    do {
        DB_GetterSetter contact = new DB_GetterSetter();
        contact.setKEY_ID(Integer.parseInt((cursor.getString(0))));
        contact.setNOTE_TYPE(Integer.parseInt(cursor.getString(1)));
        contact.setKEY_CONTENT(cursor.getString(2));
        String noteTitle = cursor.getString(3);
        if( null==noteTitle || noteTitle.equals(""))
        Log.e("@@@", "null");
        else
        	 Log.e("@@@", noteTitle);
        if( null==noteTitle || noteTitle.equals(""))
        	 contact.setNOTE_TITLE("Title");
        
        else
        	contact.setNOTE_TITLE(cursor.getString(3));	
        contact.setKEY_ARCHIEVEDNOTE(cursor.getString(4));
        contact.setKEY_SHAREDWITH(cursor.getString(5));
        contact.setKEY_ARECHECKITEMS(cursor.getString(6));
        contact.setKEY_CHECKEDITEMS(cursor.getString(7));
        contact.setKEY_ISVOICENOTE(cursor.getString(8));
        contact.setKEY_VOICERECORDING(cursor.getString(9));
        contact.setKEY_RECORDINGDURATION(cursor.getString(10));
        contact.setKEY_HASPHOTO(cursor.getString(11));
        contact.setKEY_PICBLOB(cursor.getBlob(12));
        contact.setKEY_NOTEMAKINGTIME(cursor.getString(13));
        contact.setKEY_HASREMINDER(cursor.getString(14));
        contact.setKEY_REMINDERTIME(cursor.getString(15));
        contact.setKEY_LASTEDITEDTIME(cursor.getString(16));
        contact.setKEY_COLOR(cursor.getString(17));
        // Adding contact to list
        contactList.add(contact);
    } while (cursor.moveToNext());
}


// return contact list
return contactList;
}
// Updating single contact
public int updateTextNote(DB_GetterSetter contact) {
 SQLiteDatabase db = this.getWritableDatabase();

 ContentValues values = new ContentValues();
// values.put(KEY_ID, contact.getKEY_ID());
 values.put(KEY_NOTETITLE, contact.getNOTE_TITLE());
 values.put(KEY_COLOR, contact.getKEY_COLOR());
 values.put(KEY_CONTENT, contact.getKEY_CONTENT());

 // updating row
 return db.update(TABLE_NOTES, values, KEY_ID + " = ?",
         new String[] { String.valueOf(contact.getKEY_ID()) });
}

}
