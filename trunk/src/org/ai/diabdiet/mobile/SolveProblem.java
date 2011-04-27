package org.ai.diabdiet.mobile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.ai.diabdiet.es.data.core.Result;
import org.ai.diabdiet.es.data.core.core;
import org.ai.diabdiet.es.data.daftarmenu.DetailMenu;
import org.ai.diabdiet.es.data.daftarmenu.Menu;
import org.ai.diabdiet.es.data.patient.Patient;
import org.ai.mobile.diabdiet.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

public class SolveProblem extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.solve_problem);
        
        ///Solving operation
        //Load Menu
        InputStream inList = null;
		try {
			inList = getAssets().open(Utils.MENU);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Load Plan
        InputStream inTree = null;
		try {
			inTree = getAssets().open(Utils.PLAN);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Load Knowledge
        InputStream inKnow = null; 
        try {
			inKnow = openFileInput(Utils.KNOWLEDGE);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			try {
				inKnow = getAssets().open(Utils.KNOWLEDGE);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		//Patient
        Patient p = new Patient();
        try {
			p.load(openFileInput(Utils.PATIENT));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//Solve it!
        core.GetInstance().init(inList, inTree, inKnow);
        Result res = core.GetInstance().solve(p);
        Menu m = res.menuHasilInferensi;
        
        //Display solved problem
        ((TextView)findViewById(R.id.solved_dietname)).setText("Diet type : " + m.getTipediet());
        ((TextView)findViewById(R.id.solved_dietcal)).setText("(" + m.getKalori() + " Calories)");
        TableLayout t = (TableLayout) findViewById(R.id.solve_table);
        if(m.getDaging() != null) 	t.addView(getUIRow(m.getDaging(), "Meat"));
        if(m.getMinyak() != null) 	t.addView(getUIRow(m.getMinyak(), "Oil"));
        if(m.getNasi() != null) 	t.addView(getUIRow(m.getNasi(), "Rice"));
        if(m.getPepaya() != null) 	t.addView(getUIRow(m.getPepaya(), "Papaya"));
        if(m.getPisang() != null) 	t.addView(getUIRow(m.getPisang(), "Banana"));
        if(m.getSayuranA() != null) t.addView(getUIRow(m.getSayuranA(), "Vegs from leaves"));
        if(m.getSayuranB() != null) t.addView(getUIRow(m.getSayuranB(), "Vegs from fruits"));
        if(m.getSususkim() != null) t.addView(getUIRow(m.getSususkim(), "Skimmed milk"));
        if(m.getTempe() != null) 	t.addView(getUIRow(m.getTempe(), "Tempe"));
        Log.i("aaa", "" + t.getChildCount());
        
        //Display explanations
        LinearLayout l = (LinearLayout)findViewById(R.id.solved_explanation_container);
        for(String s : res.Alasan) {
        	l.addView(new ListItem(this, s));
        }
        
        //Display tips
        l = (LinearLayout)findViewById(R.id.solved_tips_container);
        if(res.Tips.size() > 0) {
	        for(List<String> ls : res.Tips) {
	        	String title = ls.get(0);
	        	for(int i = 1; i < ls.size(); ++i) {
	            	l.addView(new ListItem(this, title + ": " + ls.get(i)));
	        	}
	        }
        } else {
        	l.addView(new ListItem(this, "No tips to display."));
        }
	}
	
	private TableRow getUIRow(DetailMenu menu, String type) {
		TableRow tr = new TableRow(this);
		
		TextView tv;
		LayoutParams lp = new LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
		lp.leftMargin = 5;
		lp.rightMargin = 5;
		
		//Table row
		tr.setLayoutParams(lp);

		//Type
		tv = new TextView(this);
		tv.setLayoutParams(lp);
		tv.setText(type);
		tv.setTextSize(11);
		tr.addView(tv);

		//6.00
		tv = new TextView(this);
		tv.setLayoutParams(lp);
		tv.setText("" + menu.getFirst());
		tv.setTextSize(11);
		tr.addView(tv);

		//12.00
		tv = new TextView(this);
		tv.setLayoutParams(lp);
		tv.setText("" + menu.getSecond());
		tv.setTextSize(11);
		tr.addView(tv);

		//18.00
		tv = new TextView(this);
		tv.setLayoutParams(lp);
		tv.setText("" + menu.getThird());
		tv.setTextSize(11);
		tr.addView(tv);
		
		return tr;
	}
	
	public class ListItem extends LinearLayout {
		public ListItem(Context ctx, String text) {
			super(ctx);
			
			//Text displayer
			TextView tv = new TextView(ctx);
			this.setOrientation(VERTICAL);
			LayoutParams lp = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
			lp.setMargins(5, 5, 5, 5);
			tv.setLayoutParams(lp);
			tv.setText(text);
			addView(tv);
			
			//Border
			/*
			View v = new View(ctx);
			LayoutParams lp = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
			lp.height = 1;
			lp.setMargins(5, 5, 5, 5);
			v.setLayoutParams(lp);
			v.setBackgroundColor(Color.WHITE);
			addView(v);
			*/
		}
	}
}
