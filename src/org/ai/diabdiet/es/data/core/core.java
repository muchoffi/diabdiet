package org.ai.diabdiet.es.data.core;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import org.ai.diabdiet.es.data.daftarmenu.FileOperation;
import org.ai.diabdiet.es.data.daftarmenu.ListMenu;
import org.ai.diabdiet.es.data.patient.Patient;
import org.ai.diabdiet.es.data.plan.TreePlan;

public class core{
	//attribut singleton
	private static core Instance;
	
	//attribut dasar
	public ListMenu listMenu;
	public TreePlan treePlan;
	public Patient patient;
	
	
	//method
	//singleton handler ================================================
	public static core GetInstance(Patient P) throws FileNotFoundException
	{
		if(Instance == null)
		{
			Instance = new core(P);
		}
		return Instance;
	}
	
	private core(Patient p) throws FileNotFoundException{
		//assign patient
		patient = p;
		
		//bagian baca menu
		File fileListMenu = new File("../../../../../../assets/menudasar.txt");
		InputStream in = new BufferedInputStream(new FileInputStream(fileListMenu)) {
			
			@Override
			public int read() throws IOException {
				// TODO Auto-generated method stub
				return 0;
			}
		};;;
		
		listMenu = FileOperation.OpenFileListMenu(in);// dapet listMenu
		
		//baca plan
		File fileTreePlan = new File("../../../../../../assets/plan.txt");
		treePlan = new TreePlan(new FileInputStream(fileTreePlan)); //dapet masukan tree plan	
	}
	//============================================================================
}