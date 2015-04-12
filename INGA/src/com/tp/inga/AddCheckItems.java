package com.tp.inga;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.LinearLayout;

public class AddCheckItems extends Activity{
	LinearLayout layout_AddView;
	EditText editText;
	int id =0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.adcheckitems);
		layout_AddView =(LinearLayout)findViewById(R.id.ll);
		EditText editText1 = (EditText)findViewById(R.id.editText1);
		editText1.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				if(s.length()>0)
				{
					 editText = new EditText(AddCheckItems.this);
					layout_AddView.addView(editText);
					id=id+1;
					
				} else if(s.length()==0)
					
				{
					layout_AddView.removeViewAt(id);
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
	}

}
