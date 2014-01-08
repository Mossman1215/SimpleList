package com.Mossman.experiment.simplelist;

import java.util.List;

import android.widget.CheckBox;

public class LoadExistingList implements Runnable {
	
	MainActivity mainActivity;
	List<String> items;
	public LoadExistingList(MainActivity activity,List<String> items){
		
	}
	@Override
	public void run() {
		for(String s: items){
			CheckBox box = new CheckBox(mainActivity);
			box.setText(s);
			box.setOnLongClickListener(mainActivity);
			mainActivity.elementsView.addView(box);
		}
	}

}
