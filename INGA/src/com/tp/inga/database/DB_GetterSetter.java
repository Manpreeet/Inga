package com.tp.inga.database;

import java.io.ByteArrayOutputStream;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;


public class DB_GetterSetter {
	int KEY_ID ;
     String KEY_CONTENT ;
     String KEY_COLOR ;
     String NOTE_TITLE;
     int NOTE_TYPE;
    
	public int getNOTE_TYPE() {
		return NOTE_TYPE;
	}
	public void setNOTE_TYPE(int nOTE_TYPE) {
		NOTE_TYPE = nOTE_TYPE;
	}
	public String getKEY_ARECHECKITEMS() {
		return KEY_ARECHECKITEMS;
	}
	public void setKEY_ARECHECKITEMS(String kEY_ARECHECKITEMS) {
		KEY_ARECHECKITEMS = kEY_ARECHECKITEMS;
	}
	String KEY_ARCHIEVEDNOTE ;
     String KEY_SHAREDWITH ;
     String KEY_ARECHECKITEMS ;
     String KEY_CHECKEDITEMS;
     String KEY_ISVOICENOTE ;
     String KEY_VOICERECORDING;
     String KEY_RECORDINGDURATION ;
     String KEY_HASPHOTO ;
      byte[] KEY_PICBLOB ;
     String KEY_NOTEMAKINGTIME ;
     String KEY_LASTEDITEDTIME ;
     String KEY_HASREMINDER ;
     String KEY_REMINDERTIME ;
     public String getNOTE_TITLE() {
 		return NOTE_TITLE;
 	}
 	public void setNOTE_TITLE(String nOTE_TITLE) {
 		NOTE_TITLE = nOTE_TITLE;
 	}
	public int getKEY_ID() {
		return KEY_ID;
	}
	public void setKEY_ID(int kEY_ID) {
		KEY_ID = kEY_ID;
	}
	public String getKEY_CONTENT() {
		return KEY_CONTENT;
	}
	public void setKEY_CONTENT(String kEY_CONTENT) {
		KEY_CONTENT = kEY_CONTENT;
	}
	public String getKEY_COLOR() {
		return KEY_COLOR;
	}
	public void setKEY_COLOR(String kEY_COLOR) {
		KEY_COLOR = kEY_COLOR;
	}
	public String getKEY_ARCHIEVEDNOTE() {
		return KEY_ARCHIEVEDNOTE;
	}
	public void setKEY_ARCHIEVEDNOTE(String kEY_ARCHIEVEDNOTE) {
		KEY_ARCHIEVEDNOTE = kEY_ARCHIEVEDNOTE;
	}
	public String getKEY_SHAREDWITH() {
		return KEY_SHAREDWITH;
	}
	public void setKEY_SHAREDWITH(String kEY_SHAREDWITH) {
		KEY_SHAREDWITH = kEY_SHAREDWITH;
	}
	public String getKEY_CHECKITEMS() {
		return KEY_ARECHECKITEMS;
	}
	public void setKEY_CHECKITEMS(String kEY_CHECKITEMS) {
		KEY_ARECHECKITEMS = kEY_CHECKITEMS;
	}
	public String getKEY_CHECKEDITEMS() {
		return KEY_CHECKEDITEMS;
	}
	public void setKEY_CHECKEDITEMS(String kEY_CHECKEDITEMS) {
		KEY_CHECKEDITEMS = kEY_CHECKEDITEMS;
	}
	public String getKEY_ISVOICENOTE() {
		return KEY_ISVOICENOTE;
	}
	public void setKEY_ISVOICENOTE(String kEY_ISVOICENOTE) {
		KEY_ISVOICENOTE = kEY_ISVOICENOTE;
	}
	public String getKEY_VOICERECORDING() {
		return KEY_VOICERECORDING;
	}
	public void setKEY_VOICERECORDING(String kEY_VOICERECORDING) {
		KEY_VOICERECORDING = kEY_VOICERECORDING;
	}
	public String getKEY_RECORDINGDURATION() {
		return KEY_RECORDINGDURATION;
	}
	public void setKEY_RECORDINGDURATION(String kEY_RECORDINGDURATION) {
		KEY_RECORDINGDURATION = kEY_RECORDINGDURATION;
	}
	public String getKEY_HASPHOTO() {
		return KEY_HASPHOTO;
	}
	public void setKEY_HASPHOTO(String kEY_HASPHOTO) {
		KEY_HASPHOTO = kEY_HASPHOTO;
	}
	public byte[] getKEY_PICBLOB() {
		return KEY_PICBLOB;
	}
	public void setKEY_PICBLOB(byte[] kEY_PICBLOB) {
		KEY_PICBLOB = kEY_PICBLOB;
	}
	public String getKEY_NOTEMAKINGTIME() {
		return KEY_NOTEMAKINGTIME;
	}
	public void setKEY_NOTEMAKINGTIME(String kEY_NOTEMAKINGTIME) {
		KEY_NOTEMAKINGTIME = kEY_NOTEMAKINGTIME;
	}
	public String getKEY_LASTEDITEDTIME() {
		return KEY_LASTEDITEDTIME;
	}
	public void setKEY_LASTEDITEDTIME(String kEY_LASTEDITEDTIME) {
		KEY_LASTEDITEDTIME = kEY_LASTEDITEDTIME;
	}
	public String getKEY_HASREMINDER() {
		return KEY_HASREMINDER;
	}
	public void setKEY_HASREMINDER(String kEY_HASREMINDER) {
		KEY_HASREMINDER = kEY_HASREMINDER;
	}
	public String getKEY_REMINDERTIME() {
		return KEY_REMINDERTIME;
	}
	public void setKEY_REMINDERTIME(String kEY_REMINDERTIME) {
		KEY_REMINDERTIME = kEY_REMINDERTIME;
	}
  public DB_GetterSetter(int note_type,String title_note,String content,String isvoicenote,String haspic,String has_reminder,String has_cb,String color)
{
	NOTE_TYPE  =note_type;
	NOTE_TITLE=title_note;
	KEY_CONTENT=content;
    KEY_ISVOICENOTE=isvoicenote;
    KEY_HASPHOTO=haspic;
    KEY_HASREMINDER=has_reminder;
    KEY_ARECHECKITEMS=has_cb;
   KEY_COLOR = color;
    
	}
public DB_GetterSetter(int note_type,String title_note,String content,String isvoicenote,String haspic,String has_reminder,String has_cb,String color,byte[] pic)
{
	NOTE_TYPE  =note_type;
	NOTE_TITLE=title_note;
	KEY_CONTENT=content;
    KEY_ISVOICENOTE=isvoicenote;
    KEY_HASPHOTO=haspic;
    KEY_HASREMINDER=has_reminder;
    KEY_ARECHECKITEMS=has_cb;
   KEY_PICBLOB=pic;
   KEY_COLOR = color;
   
	}
public DB_GetterSetter() {
	// TODO Auto-generated constructor stub
}

public DB_GetterSetter(int id,int notetype,String content,String title,String color)// update text note
{
	KEY_ID = id;
	NOTE_TYPE  =notetype;
	NOTE_TITLE=title;
	KEY_CONTENT=content;
	KEY_COLOR = color;
}

public DB_GetterSetter(int id,int notetype,String content,String title,String color,byte[] pic)// update picture note
{
	KEY_ID = id;
	NOTE_TYPE  =notetype;
	NOTE_TITLE=title;
	KEY_CONTENT=content;
	KEY_COLOR = color;
}

public static byte[] getBytes(Bitmap bitmap)
{
     ByteArrayOutputStream stream=new ByteArrayOutputStream();
     bitmap.compress(CompressFormat.JPEG,0, stream);
     return stream.toByteArray();
}
public static Bitmap getImage(byte[] image)
{
     return BitmapFactory.decodeByteArray(image, 0, image.length);
}
}

