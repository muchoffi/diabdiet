package org.ai.diabdiet.es.data.patient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

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
	public boolean save(OutputStream os) {
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
	public boolean load(InputStream is) {
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

	public boolean validate(List<String> reasons) {
		boolean retval = true;
		if(laboratory.HDL < 35) { retval = false; reasons.add("HDL minimum value is 35!"); }
		if(laboratory.LDL < 60) { retval = false; reasons.add("LDL minimum value is 60!"); }
		if(laboratory.triglyceride < 40) { retval = false; reasons.add("Triglyceride minimum value is 40!"); }
		if(anthropometry.isGenderMale && laboratory.uricAcid < 3.5) { retval = false; reasons.add("Uric acid minimum value for male is 3.5!"); }
		else if(!anthropometry.isGenderMale && laboratory.uricAcid < 2.8) { retval = false; reasons.add("Uric acid minimum value for female is 2.8!"); }
		
		return retval;
	}
}
