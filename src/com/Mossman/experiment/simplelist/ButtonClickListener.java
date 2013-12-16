package com.Mossman.experiment.simplelist;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;


public class ButtonClickListener implements OnClickListener{
	
	MainActivity mainActivity;
	
	public ButtonClickListener(MainActivity act){
		mainActivity = act;
	}
	
	@Override
	public void onClick(View v) {
		//add the textbox string to the list of items (string)
		String textBoxString = mainActivity.textBox.getText().toString();
		mainActivity.items.add(textBoxString);
		//add an element to linelay with the same string
		CheckBox toAdd = new CheckBox(mainActivity);
		toAdd.setText(textBoxString);
		mainActivity.lineLay.addView(toAdd);
		//TODO: save list
	}
}
