package com.Mossman.experiment.simplelist;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Point;
import android.view.Display;
import android.view.Menu;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class MainActivity extends Activity {
	
	ArrayList<String> items;
	LinearLayout elementsView;
	ScrollView scrollView;
	LinearLayout textBoxView;
	LinearLayout mainContainer;
	Button button;
	EditText textBox;
	CheckBox toAdd;
	
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
		//TODO: load from file and add to linearlayout
		items = new ArrayList<String>();
		
		String textBoxString = textBox.getText().toString()+" Hello";
		items.add(textBoxString);
		//add an element to linelay with the same string
		toAdd = new CheckBox(this);
		toAdd.setText("hello");
		toAdd.setId(2);
		elementsView.addView(toAdd);
		
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
		File todoFile = new File(filesDir,"todo.txt");
		try{
			items = new ArrayList<String>(FileUtils.readLines(todoFile));
		}catch(IOException e){
			items = new ArrayList<String>();
			e.printStackTrace();
		}
	}
	
	private void saveItems(){
		File filesDir = getFilesDir();
		File todoFile = new File(filesDir,"todo.txt");
		try{
			FileUtils.writeLines(todoFile, items);
		}catch(IOException e){
			e.printStackTrace();
		}
	}

}
