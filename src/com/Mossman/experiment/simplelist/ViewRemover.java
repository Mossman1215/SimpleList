package com.Mossman.experiment.simplelist;

import android.view.View;
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
	public ViewRemover(View toRemove,LinearLayout list){
		this.toRemove=toRemove;
		this.list = list;
	}
	@Override
	public void run() {
		list.removeView(toRemove);
	}

}
