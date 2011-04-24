package org.ai.diabdiet.mobile;

import org.ai.diabdiet.es.data.daftarmenu.DetailMenu;
import org.ai.diabdiet.es.data.daftarmenu.Menu;
import org.ai.mobile.diabdiet.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class SolveProblem extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.solve_problem);
        
        //Get say view
        TextView say = (TextView) findViewById(R.id.solve_say);
        say.setText("Hello there!");
        
        //Invoke solve operation
        Menu m = null;
        
        //Display solved problem
        ((TextView)findViewById(R.id.solved_dietname)).setText(m.getTipediet());
        ((TextView)findViewById(R.id.solved_dietcal)).setText("(" + m.getKalori() + " Kalori)");
        TableLayout t = (TableLayout) findViewById(R.id.solve_table);
        t.addView(getUIRow(m.getDaging(), "Meat"));
        t.addView(getUIRow(m.getMinyak(), "Oil"));
        t.addView(getUIRow(m.getNasi(), "Rice"));
        t.addView(getUIRow(m.getPepaya(), "Papaya"));
        t.addView(getUIRow(m.getPisang(), "Banana"));
        t.addView(getUIRow(m.getSayuranA(), "Vegetables A"));
        t.addView(getUIRow(m.getSayuranB(), "Vegetables B"));
        t.addView(getUIRow(m.getSususkim(), "Skimmed milk"));
        t.addView(getUIRow(m.getTempe(), "Tempe"));
	}
	
	private TableRow getUIRow(DetailMenu menu, String type) {
		TableRow tr = new TableRow(this);
		
		TextView tv;
		LayoutParams lp = new LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT);

		//Type
		tv = new TextView(this);
		tv.setLayoutParams(lp);
		tv.setText(type);
		tr.addView(tv);

		//6.00
		tv = new TextView(this);
		tv.setLayoutParams(lp);
		tv.setText(type);
		tr.addView(tv);

		//12.00
		tv = new TextView(this);
		tv.setLayoutParams(lp);
		tv.setText(type);
		tr.addView(tv);

		//18.00
		tv = new TextView(this);
		tv.setLayoutParams(lp);
		tv.setText(type);
		tr.addView(tv);
		
		return tr;
	}
}
