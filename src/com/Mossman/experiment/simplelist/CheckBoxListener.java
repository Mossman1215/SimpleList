package com.Mossman.experiment.simplelist;

import android.view.View;
import android.view.View.OnClickListener;

public class CheckBoxListener implements OnClickListener {
	MainActivity act;
	public CheckBoxListener(MainActivity act){
		this.act=act;
	}
	@Override
	public void onClick(View v) {
		act.saveItems();
	}

}
