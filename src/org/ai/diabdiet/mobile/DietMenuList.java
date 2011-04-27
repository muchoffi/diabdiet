package org.ai.diabdiet.mobile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.ai.diabdiet.es.data.daftarmenu.FileOperation;
import org.ai.diabdiet.es.data.daftarmenu.ListMenu;
import org.ai.diabdiet.es.data.daftarmenu.Menu;
import org.ai.mobile.diabdiet.R;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class DietMenuList extends ListActivity {
	private ListMenu _lm;
	private String[] _items;
	private int _lastSelectedPosition;

	private static final int DIALOG_EXIT 		= 0;
	private static final int DIALOG_LISTITEM 	= 1;
	
	protected void onCreate(android.os.Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		
		//Long clicked adapter
		OnItemLongClickListener lc = new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				return onListItemLongClick(arg1, arg2, arg3);
			}
		};
		getListView().setOnItemLongClickListener(lc);

		//Setup views
		onRestart();
	}
	
	@Override
	protected Dialog onCreateDialog(int id) {
	    Dialog dialog;
	    switch(id) {
	    case DIALOG_LISTITEM:
	    	//Create exit dialog
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("What do you want to do?")
		       .setCancelable(true)
		       .setPositiveButton("Edit", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		        	   //Open item in a new view
		        	   editItem(_lastSelectedPosition);
		           }
		       })
		       .setNegativeButton("Delete", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		               //Delete the item
		        	   delete(_lastSelectedPosition);
		        	   
		        	   //Save it
		        	   saveChanges();
		        	   
		        	   //Update views
		        	   updateViews();
		           }
		       });
			dialog = builder.create();
	        break;
	    case DIALOG_EXIT:
	        //Create list item dialog
			builder = new AlertDialog.Builder(this);
			builder.setMessage("Save before exit?")
		       .setCancelable(true)
		       .setPositiveButton("Save", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		        	   //Save it
		        	   saveChanges();
		        	   
		        	   //Exit
		        	   DietMenuList.this.finish();
		           }
		       })
		       .setNegativeButton("Just exit", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		        	   //Exit
		        	   DietMenuList.this.finish();
		           }
		       });
			dialog = builder.create();
	        break;
	    default:
	        dialog = null;
	    }
	    return dialog;
	}
	
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.dietlistmenu, menu);
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.dietlist_menu_loadfromserver:
			//Load from server
			loadFromServer();
			
			//Update view
			updateViews();
			
			break;
		case R.id.dietlist_menu_savechanges:
			//Save changes
			saveChanges();
			
			break;
		case R.id.dietlist_menu_new:
			//new menu
			editItem(-1);
			
			break;
		}
		return true;
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		//Update position
		_lastSelectedPosition = position;
		
		//Open dialogbox containing "Edit" and "Delete"
		showDialog(DIALOG_LISTITEM);
	}
	
	protected boolean onListItemLongClick(View v, int pos, long id) {
		return true;
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK) {
			onBackPressed();
		}
		return super.onKeyDown(keyCode, event);
	}
	
	protected void onBackPressed() {
		//Open dialogbox containing "Save & Quit" and "Quit"
		//showDialog(DIALOG_EXIT);
		this.finish();
	}
	
	private void updateViews() {
		_items = new String[_lm.getListmenu().size()];
		int i = 0;
		for(Menu m : _lm.getListmenu()) {
			_items[i] = m.getTipediet() + " (" + m.getKalori() + " Calories)";
			++i;
		}
		this.setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, _items));
	}
	
	private void loadFromServer() {
		try {
			_lm = FileOperation.OpenFileListMenu(Utils.OpenHttpConnection(Utils.URL_DIETMENU));
			Toast.makeText(this, "Successfully loaded from server", Toast.LENGTH_SHORT).show();
		} catch (IOException e) {
			e.printStackTrace();
			Toast.makeText(this, "Failed to connect server", Toast.LENGTH_SHORT).show();
		}
	}
	
	private void delete(int position) {
		String s = getListView().getItemAtPosition(position).toString();
		Toast.makeText(this, s + " deleted", Toast.LENGTH_SHORT).show();
		_lm.getListmenu().remove(position);
	}
	
	private void saveChanges() {
		try {
			FileOperation.WriteFileListMenu(openFileOutput(Utils.MENU, MODE_PRIVATE), _lm);
			Toast.makeText(this, "Changes saved", Toast.LENGTH_SHORT).show();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Toast.makeText(this, "Failed to save", Toast.LENGTH_SHORT).show();
		}
	}
	
	private void editItem(int position) {
		Intent i = new Intent(DietMenuList.this, EditDietMenu.class);
		if(position >= 0) i.putExtra("data", position);
		startActivity(i);
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		
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
		
		//Update views
		updateViews();
	}
}
