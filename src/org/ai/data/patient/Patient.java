package org.ai.data.patient;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.google.gson.Gson;

public class Patient {
	public Anthropometry 	anthropometry;
	public Laboratory 		laboratory;
	public ClinicalAnamnesa clinicalAnamnesa;
	
	public Patient() {
		anthropometry 		= new Anthropometry();
		laboratory			= new Laboratory();
		clinicalAnamnesa 	= new ClinicalAnamnesa();
	}
	
	/**
	 * Returns true if save succeed
	 * You are the one who responsibles to close the stream
	 * @param os
	 * @return
	 */
	public boolean save(FileOutputStream os) {
		String json = new Gson().toJson(this);
		try {
			os.write(json.getBytes());
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Returns trus if load succeed
	 * @param is
	 * @return
	 */
	public boolean load(FileInputStream is) {
		try {
			StringBuilder b = new StringBuilder();
			int retval;
			while((retval = is.read()) != -1) {
				b.append((char)retval);
			}
			Patient p = new Gson().fromJson(b.toString(), Patient.class);
			anthropometry 	= p.anthropometry;
			laboratory		= p.laboratory;
			clinicalAnamnesa= p.clinicalAnamnesa;
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}
