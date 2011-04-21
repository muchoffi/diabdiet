package org.ai.data.patient;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.google.gson.Gson;

public class Patient {
	public Anthropometry 	anthropometry;
	public Laboratory 		laboratory;
	public ClinicalAnamnesa clinicalAnamnesa;
	
	public void save(FileOutputStream os) {
		String json = new Gson().toJson(this);
		try {
			os.write(json.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void load(FileInputStream is) {
		try {
			StringBuilder b = new StringBuilder();
			int retval;
			while((retval = is.read()) != 1) {
				b.append((char)retval);
			}
			Patient p = new Gson().fromJson(b.toString(), Patient.class);
			anthropometry 	= p.anthropometry;
			laboratory		= p.laboratory;
			clinicalAnamnesa= p.clinicalAnamnesa;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
