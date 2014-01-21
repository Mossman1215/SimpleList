package com.Mossman.experiment.simplelist;

import java.util.List;

import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
/**
 * Class to remove checkbox from a list (linear layout)
 * could not use a annonymous inner class as it needs a reference to the checkbox to remove
 * @author moss
 *
 */
public class ViewRemover implements Runnable {
	View toRemove;
	LinearLayout list;
	List<CheckBox> checkboxes;
	List<String> items;
	public ViewRemover(View toRemove,LinearLayout list,List<CheckBox> checkboxes,List<String>items){
		this.toRemove=toRemove;
		this.list = list;
		this.checkboxes = checkboxes;
		this.items = items;
	}
	@Override
	public void run() {
		list.removeView(toRemove);
		checkboxes.remove((CheckBox)toRemove);
	}

}
