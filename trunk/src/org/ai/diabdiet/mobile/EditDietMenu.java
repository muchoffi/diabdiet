package org.ai.diabdiet.mobile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.ai.diabdiet.es.data.daftarmenu.DetailMenu;
import org.ai.diabdiet.es.data.daftarmenu.FileOperation;
import org.ai.diabdiet.es.data.daftarmenu.ListMenu;
import org.ai.diabdiet.es.data.daftarmenu.Menu;
import org.ai.mobile.diabdiet.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class EditDietMenu extends Activity {
	
	ListMenu 	_lm;
	Menu		_dietMenu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.editdietmenu);
        
        //Load list menu
		InputStream is = null;
		try {
			is = openFileInput(Utils.MENU);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			try {
				is = getAssets().open(Utils.MENU);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		_lm = FileOperation.OpenFileListMenu(is);

        //Get "save" button
        Button b = (Button) findViewById(R.id.edit_save);
        b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Save the diet plan
				saveDietPlan();
				
				//Update view
				updateView(_dietMenu);
			}
		});
		
		//Show details for selected menu (if any)
        if(getIntent().getExtras() != null) {
        	//Update view
        	_dietMenu = _lm.getListmenu().get(getIntent().getExtras().getInt("data"));
        	updateView(_dietMenu);
        }
	}
	
	private void saveDietPlan() {
		if(_dietMenu == null) {
			_dietMenu = new Menu();
			_lm.getListmenu().add(_dietMenu);
		}
		
		//Update menu
		DetailMenu m = null;
		m = getDetail(R.id.edit_dagingpagi, R.id.edit_dagingsiang, R.id.edit_dagingmalam);
		_dietMenu.setDaging(m);
		m = getDetail(R.id.edit_nasipagi, R.id.edit_nasisiang, R.id.edit_nasimalam);
		_dietMenu.nasi = m;
		m = getDetail(R.id.edit_minyakpagi, R.id.edit_minyaksiang, R.id.edit_minyakmalam);
		_dietMenu.minyak = m;
		m = getDetail(R.id.edit_pepayapagi, R.id.edit_pepayasiang, R.id.edit_pepayamalam);
		_dietMenu.pepaya = m;
		m = getDetail(R.id.edit_pisangpagi, R.id.edit_pisangpagi, R.id.edit_pisangmalam);
		_dietMenu.pisang = m;
		m = getDetail(R.id.edit_sayuranapagi, R.id.edit_sayuranasiang, R.id.edit_sayuranamalam);
		_dietMenu.sayuranA = m;
		m = getDetail(R.id.edit_sayuranbpagi, R.id.edit_sayuranbsiang, R.id.edit_sayuranbmalam);
		_dietMenu.sayuranB = m;
		m = getDetail(R.id.edit_sususkimpagi, R.id.edit_sususkimsiang, R.id.edit_sususkimmalam);
		_dietMenu.sususkim = m;
		m = getDetail(R.id.edit_tempepagi, R.id.edit_tempesiang, R.id.edit_tempemalam);
		_dietMenu.tempe = m;
		
		_dietMenu.kalori = Float.parseFloat(((EditText)findViewById(R.id.edit_kalori)).getText().toString());
		if((((RadioButton)findViewById(R.id.edit_jenisdiet_b)).isChecked())) _dietMenu.tipediet = "B";
		else _dietMenu.tipediet = "B1";

		((EditText)findViewById(R.id.edit_kalori)).setText("" + _dietMenu.getTotalCalories());
		
		//Save!
		try {
			FileOperation.WriteFileListMenu(openFileOutput(Utils.MENU, MODE_PRIVATE), _lm);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Toast.makeText(this, "Saving diet menu failed!", Toast.LENGTH_SHORT).show();
		}
		
		//Notify user
		Toast.makeText(this, "Diet menu saved successfully", Toast.LENGTH_SHORT).show();
	}
	
	private DetailMenu getDetail(int id0600, int id1200, int id1800) {
		float k0600, k1200, k1800;

		if(((EditText)findViewById(id0600)).getText().toString().length() <= 0) return null;
		else k0600 = Float.parseFloat(((EditText)findViewById(id0600)).getText().toString());

		if(((EditText)findViewById(id1200)).getText().toString().length() <= 0) return null;
		else k1200 = Float.parseFloat(((EditText)findViewById(id1200)).getText().toString());

		if(((EditText)findViewById(id1800)).getText().toString().length() <= 0) return null;
		else k1800 = Float.parseFloat(((EditText)findViewById(id1800)).getText().toString());
		
		return new DetailMenu(k0600, k1200, k1800);
	}
	
	private void updateView(Menu m) {
		//Headers
		((EditText)findViewById(R.id.edit_kalori)).setText("" + m.getKalori());
		if(m.getTipediet().equalsIgnoreCase("B")) ((RadioButton)findViewById(R.id.edit_jenisdiet_b)).toggle();
		else ((RadioButton)findViewById(R.id.edit_jenisdiet_b1)).toggle();
		
		//Details
		if(m.getDaging() != null) 	updateDetail(m.getDaging(), R.id.edit_dagingpagi, R.id.edit_dagingsiang, R.id.edit_dagingmalam);
		if(m.getNasi() != null) 	updateDetail(m.getNasi(), R.id.edit_nasipagi, R.id.edit_nasisiang, R.id.edit_nasimalam);
		if(m.getMinyak() != null) 	updateDetail(m.getMinyak(), R.id.edit_minyakpagi, R.id.edit_minyaksiang, R.id.edit_minyakmalam);
		if(m.getPepaya() != null) 	updateDetail(m.getPepaya(), R.id.edit_pepayapagi, R.id.edit_pepayasiang, R.id.edit_pepayamalam);
		if(m.getPisang() != null) 	updateDetail(m.getPisang(), R.id.edit_pisangpagi, R.id.edit_pisangsiang, R.id.edit_pisangmalam);
		if(m.getSayuranA() != null) updateDetail(m.getSayuranA(), R.id.edit_sayuranapagi, R.id.edit_sayuranasiang, R.id.edit_sayuranamalam);
		if(m.getSayuranB() != null) updateDetail(m.getSayuranB(), R.id.edit_sayuranbpagi, R.id.edit_sayuranbsiang, R.id.edit_sayuranbmalam);
		if(m.getSususkim() != null) updateDetail(m.getSususkim(), R.id.edit_sususkimpagi, R.id.edit_sususkimsiang, R.id.edit_sususkimmalam);
		if(m.getTempe() != null) 	updateDetail(m.getTempe(), R.id.edit_tempepagi, R.id.edit_tempesiang, R.id.edit_tempemalam);
	}
	
	private void updateDetail(DetailMenu m, int id1, int id2, int id3) {
		((EditText)findViewById(id1)).setText("" + m.getFirst());
		((EditText)findViewById(id2)).setText("" + m.getSecond());
		((EditText)findViewById(id3)).setText("" + m.getThird());
	}
}
