package org.ai.diabdiet.mobile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.ai.diabdiet.es.data.knowledge.Knowledge;
import org.ai.diabdiet.es.data.knowledge.StatusGizi;
import org.ai.diabdiet.es.data.knowledge.StatusKehamilan;
import org.ai.diabdiet.es.data.knowledge.StatusKomplikasi;
import org.ai.mobile.diabdiet.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class EditKnowledge extends Activity {
	private List<ComplicationUIRow> _compUIRows;
	private List<GiziUIRow> _giziUIRows;
	private List<AdditionalCalUIRow> _addCalUIRows;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_knowledge);
        
        //Reset view
        resetView();
        
        //Load knowledge from data        
        InputStream is = null;
        try {
			is = openFileInput(Utils.KNOWLEDGE);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			try {
				is = getAssets().open(Utils.KNOWLEDGE);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		//Display the data
		loadView(is);
		
		//Click handler - save
		Button b = (Button) findViewById(R.id.editk_save_btn);
		b.setOnClickListener(new OnClickListener() {			
			public void onClick(View v) {
				save();
			}
		});
		
		//Click handler - load from server
		b = (Button) findViewById(R.id.editk_server_btn);
		b.setOnClickListener(new OnClickListener() {			
			public void onClick(View v) {
				//Connect to server
				InputStream in = null;
				boolean succ = true;
				try {
					in = Utils.OpenHttpConnection(Utils.URL_KNOWLEDGE);
					if(in == null) succ = false;
					else {
						resetView();
						loadView(in);
					}
				} catch (IOException e) {
					e.printStackTrace();
					succ = false;
				}
				
				//User feedback
				if(succ) {
					Toast.makeText(getThis(), "Knowledge loaded from server", Toast.LENGTH_SHORT).show();
					save();
				} else {
					Toast.makeText(getThis(), "Failed to connect server", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
	
	private void resetView() {
		TableLayout table = (TableLayout) findViewById(R.id.editk_complication_table);
		while(table.getChildCount() > 1) { table.removeViewAt(1); }
		table = (TableLayout) findViewById(R.id.editk_rbw_table);
		while(table.getChildCount() > 1) { table.removeViewAt(1); }
		table = (TableLayout) findViewById(R.id.editk_addcal_table);
		while(table.getChildCount() > 1) { table.removeViewAt(1); }
	}
	
	private void loadView(InputStream is) {
		Knowledge.reset();
		Knowledge.ConvertToKnowledge(is);
		
		//Prepare vars
		_compUIRows = new ArrayList<EditKnowledge.ComplicationUIRow>();
		_giziUIRows = new ArrayList<EditKnowledge.GiziUIRow>();
		_addCalUIRows = new ArrayList<EditKnowledge.AdditionalCalUIRow>();
		
		//Print complication
		TableLayout table = (TableLayout) findViewById(R.id.editk_complication_table);
		int i = 0;
		for(StatusKomplikasi sk : Knowledge.S_Komplikasi) {
			ComplicationUIRow c = new ComplicationUIRow(this, i, sk);
			table.addView(c);
			_compUIRows.add(c);
			++i;
		}
		
		//Print Gizi
		table = (TableLayout) findViewById(R.id.editk_rbw_table);
		i = 0;
		for(StatusGizi sk : Knowledge.S_Gizi) {
			GiziUIRow c = new GiziUIRow(this, i, sk);
			table.addView(c);
			_giziUIRows.add(c);
			++i;
		}
		
		//Print Additional calories
		table = (TableLayout) findViewById(R.id.editk_addcal_table);
		i = 0;
		for(StatusKehamilan sk : Knowledge.S_Kehamilan) {
			AdditionalCalUIRow c = new AdditionalCalUIRow(this, i, sk);
			table.addView(c);
			_addCalUIRows.add(c);
			++i;
		}
	}
	
	private void save() {
		//Update UIs
		for(AdditionalCalUIRow vv : _addCalUIRows) vv.updateAdditionalCals();
		for(ComplicationUIRow vv : _compUIRows) vv.updateStatusKomplikasi();
		for(GiziUIRow vv : _giziUIRows) vv.updateGizi();
		
		//Save
		try {
			Knowledge.WriteFile(openFileOutput(Utils.KNOWLEDGE, Context.MODE_PRIVATE));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//Notify users
		Toast.makeText(getThis(), R.string.editk_toast_save, Toast.LENGTH_SHORT).show();
	}
	
	private EditKnowledge getThis() { return this; }
	
	
	/************* UI CLASSES **************************/
	
	public class ComplicationUIRow extends TableRow {
		private int _id;
		private EditText _meat, _tempe;
		
		public ComplicationUIRow(Context ctx, int id, StatusKomplikasi sk) {
			super(ctx);
			_id = id;
			LayoutParams lp = new LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
			lp.leftMargin 	= 10;
			lp.rightMargin 	= 10;
			this.setLayoutParams(lp);
			
			TextView tv = new TextView(ctx);
			tv.setLayoutParams(lp);
			tv.setText(sk.GetJenis());
			this.addView(tv);
			
			_meat = new EditText(ctx);
			_meat.setLayoutParams(lp);
			_meat.setText("" + sk.GetMaxDaging());
			_meat.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_CLASS_NUMBER);
			this.addView(_meat);
			
			_tempe = new EditText(ctx);
			_tempe.setLayoutParams(lp);
			_tempe.setText("" + sk.GetMaxTempe());
			_tempe.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_CLASS_NUMBER);
			this.addView(_tempe);
		}
		
		public void updateStatusKomplikasi() {
			Knowledge.ChangeElementS_Komplikasi(_id, "", (int)Float.parseFloat(_meat.getText().toString()), (int)Float.parseFloat(_tempe.getText().toString()));
		}
	}
	
	public class GiziUIRow extends TableRow {
		private int _id;
		private EditText _min, _max;
	
		public GiziUIRow(Context ctx, int id, StatusGizi sg) {
			super(ctx);
			_id = id;
			LayoutParams lp = new LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
			lp.leftMargin 	= 10;
			lp.rightMargin 	= 10;
			this.setLayoutParams(lp);
			
			TextView tv = new TextView(ctx);
			tv.setLayoutParams(lp);
			tv.setText(sg.GetJenis());
			this.addView(tv);
			
			_min = new EditText(ctx);
			_min.setLayoutParams(lp);
			_min.setText("" + sg.GetMinGizi());
			_min.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_CLASS_NUMBER);
			this.addView(_min);
			
			_max = new EditText(ctx);
			_max.setLayoutParams(lp);
			_max.setText("" + sg.GetMaxGizi());
			_max.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_CLASS_NUMBER);
			this.addView(_max);
		}
		
		public void updateGizi() {
			Knowledge.ChangeElementS_Gizi(_id, "", Float.parseFloat(_min.getText().toString()), Float.parseFloat(_max.getText().toString()));
		}
	}
	
	public class AdditionalCalUIRow extends TableRow {
		private int _id;
		private EditText _calories;
	
		public AdditionalCalUIRow(Context ctx, int id, StatusKehamilan sg) {
			super(ctx);
			_id = id;
			LayoutParams lp = new LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
			lp.leftMargin 	= 10;
			lp.rightMargin 	= 10;
			this.setLayoutParams(lp);
			
			TextView tv = new TextView(ctx);
			tv.setLayoutParams(lp);
			tv.setText(sg.GetJenis());
			this.addView(tv);
			
			_calories = new EditText(ctx);
			_calories.setLayoutParams(lp);
			_calories.setText("" + sg.GetNeedKalor());
			_calories.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL  | InputType.TYPE_CLASS_NUMBER);
			this.addView(_calories);
		}
		
		public void updateAdditionalCals() {
			Knowledge.ChangeElementS_Kehamilan(_id, "", Integer.parseInt(_calories.getText().toString()));
		}
	}
}
