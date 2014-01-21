package com.Mossman.experiment.simplelist;

import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;

public class CheckBoxListener implements OnClickListener {
	MainActivity act;
	public CheckBoxListener(MainActivity act){
		this.act=act;
	}
	@Override
	public void onClick(View v) {
		((CheckBox)v).setTextColor(Color.LTGRAY);
		act.saveItems();
	}

}
