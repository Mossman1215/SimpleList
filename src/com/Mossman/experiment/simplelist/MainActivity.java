package com.Mossman.experiment.simplelist;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Point;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class MainActivity extends Activity implements OnLongClickListener{
	
	ArrayList<String> items;
	LinearLayout elementsView;
	ScrollView scrollView;
	LinearLayout textBoxView;
	LinearLayout mainContainer;
	Button button;
	EditText textBox;
	CheckBox toAdd;
	List<CheckBox> checkboxes;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mainContainer = new LinearLayout(this);
		mainContainer.setOrientation(LinearLayout.VERTICAL);
		scrollView = new ScrollView(this);
		elementsView = new LinearLayout(this);
		elementsView.setOrientation(LinearLayout.VERTICAL);
		scrollView.addView(elementsView);
		textBoxView = new LinearLayout(this);
		textBoxView.setOrientation(LinearLayout.HORIZONTAL);
		button = new Button(this);
		button.setText("Add to list");
		button.setOnClickListener(new ButtonClickListener(this));
		textBoxView.addView(button);
		textBox = new EditText(this);
		textBox.setSingleLine(true);
		textBox.setMinimumWidth(getTextWidth()-button.getWidth());
		textBoxView.addView(textBox);
		//addeverything to main container
		mainContainer.addView(textBoxView);
		mainContainer.addView(scrollView);
		
		items = new ArrayList<String>();//just in case read items leads to a null list
		checkboxes = new ArrayList<CheckBox>();
		readItems();
		//if items has stuff in it populate the list with existing content
		
		if(!items.isEmpty()){
			for(String s: items){
				CheckBox box = new CheckBox(this);
				box.setOnClickListener(new CheckBoxListener(this));
				checkboxes.add(box);
				String[] values = s.split(" ");
				box.setText(values[0]);
				s = values[0];
				if(values[1].equals("true")){
					box.setChecked(true);
				}else{
					box.setChecked(false);
				}
				box.setOnLongClickListener(this);
				elementsView.addView(box);
			}
		}						
		this.setContentView(mainContainer);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@SuppressLint("NewApi")
	private int getTextWidth() {
		Display display = getWindowManager().getDefaultDisplay();
		if(android.os.Build.VERSION.SDK_INT<13){
			@SuppressWarnings("deprecation")
			int width = display.getWidth();
			return width;
		}else{
			Point point = new Point();
			display.getSize(point);
			return point.x;
		}
	}
	
	private void readItems(){
		File filesDir = getFilesDir();
		File todoFile = new File(filesDir,"SimpleList2.txt");
		try{
			items = new ArrayList<String>(FileUtils.readLines(todoFile));
		}catch(IOException e){
			items = new ArrayList<String>();
			e.printStackTrace();
		}
	}
	
	public void saveItems(){
		File filesDir = getFilesDir();
		File todoFile = new File(filesDir,"SimpleList2.txt");
		try{
			//TODO: write out checkbox status
			//iterate over list of checkboxes and list of string names
			int size = items.size();
			for(int i =0;i<size;i++){
				String item = items.get(i);
				if(checkboxes.get(i).isChecked()&&(!item.contains("true")||!item.contains("false"))){
					items.set(i, items.get(i).concat(" true"));
				}else{
					items.set(i, items.get(i).concat(" false"));
				}
			}
			FileUtils.writeLines(todoFile, items);
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	@Override
	public boolean onLongClick(View v) {
		if(v instanceof CheckBox){
			int id = v.getId();
			//find the other references to v (the check box that was selected and set them to null or some other way of removing them
			CheckBox toRemove = ((CheckBox)v);
			items.remove(toRemove.getText());
			//remove from elementsView
			runOnUiThread(new ViewRemover(v, elementsView,checkboxes));
			toRemove.setTextColor(Color.LTGRAY);		
			saveItems();
			return true;
		}else{
			throw new RuntimeException("Long press on an view that was not a checkbox" + v.toString());
		}
	}
}
