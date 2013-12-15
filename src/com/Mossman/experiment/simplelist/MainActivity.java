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
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class MainActivity extends Activity {
	
	ArrayList<String> items;
	LinearLayout lineLay;
	ScrollView scrollView;
	Button button;
	EditText textBox;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		scrollView = new ScrollView(this);
		lineLay = new LinearLayout(this);
		scrollView.addView(lineLay);
		button = new Button(this);
		button.setText("Add to list");
		button.setOnClickListener(new ButtonClickListener(this));
		lineLay.addView(button);
		textBox = new EditText(this);
		textBox.setSingleLine(true);
		textBox.setMinimumWidth(getTextWidth()-button.getWidth());
		lineLay.addView(textBox);
		this.setContentView(scrollView);
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
