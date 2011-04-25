package org.ai.diabdiet.mobile;

import java.io.FileNotFoundException;

import org.ai.diabdiet.es.data.patient.*;
import org.ai.diabdiet.es.data.patient.Anthropometry.Status;
import org.ai.mobile.diabdiet.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;

public class InputProblem extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_problem);
        
        //Add listener to gender male radio button
        ((RadioButton)findViewById(R.id.in_antro_gender_male)).setOnCheckedChangeListener(new OnCheckedChangeListener() {			
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				((LinearLayout)findViewById(R.id.form_antro_status)).setVisibility(isChecked ? View.GONE : View.VISIBLE);
			}
		});
        
        //Load patient
        loadPatientData();
        
        //Get button
        Button b = (Button) findViewById(R.id.form_btn_next);
        b.setOnClickListener(new OnClickListener() {			
			public void onClick(View v) {
				//Save input to external file
				updatePatient();
				
				//Go to solve screen
				Intent i = new Intent(InputProblem.this, SolveProblem.class);
				startActivity(i);
			}
		});
    }
    
    protected void updatePatient() {	
    	//Init patient
        Patient p = new Patient();
        
		//Assign settings
		p.anthropometry.age = Integer.parseInt(((EditText)findViewById(R.id.in_antro_age)).getText().toString());
		p.anthropometry.bodyWeight = Float.parseFloat(((EditText)findViewById(R.id.in_antro_bodyheight)).getText().toString());
		p.anthropometry.bodyWeight = Float.parseFloat(((EditText)findViewById(R.id.in_antro_bodyweight)).getText().toString());
		p.anthropometry.isGenderMale = ((RadioButton)findViewById(R.id.in_antro_gender_male)).isChecked();
		if(p.anthropometry.isGenderMale) p.anthropometry.status = Status.NORMAL;
		else {
			if(((RadioButton)findViewById(R.id.in_antro_status_preg)).isChecked()) p.anthropometry.status = Status.PREGNANT;
			else if(((RadioButton)findViewById(R.id.in_antro_status_breastfeed)).isChecked()) p.anthropometry.status = Status.BREASTFEEDING;
		}

		p.laboratory.cholesterol = Float.parseFloat(((EditText)findViewById(R.id.in_lab_chol)).getText().toString());
		p.laboratory.diastole = Float.parseFloat(((EditText)findViewById(R.id.in_lab_diastole)).getText().toString());
		p.laboratory.systole = Float.parseFloat(((EditText)findViewById(R.id.in_lab_systole)).getText().toString());
		p.laboratory.HDL = Float.parseFloat(((EditText)findViewById(R.id.in_lab_HDL)).getText().toString());
		p.laboratory.LDL = Float.parseFloat(((EditText)findViewById(R.id.in_lab_LDL)).getText().toString());
		p.laboratory.temperature = Float.parseFloat(((EditText)findViewById(R.id.in_lab_temp)).getText().toString());
		p.laboratory.triglyceride = Float.parseFloat(((EditText)findViewById(R.id.in_lab_trig)).getText().toString());
		p.anthropometry.bodyWeight = Float.parseFloat(((EditText)findViewById(R.id.in_lab_uricacid)).getText().toString());

		p.clinicalAnamnesa.swallowProb = ((CheckBox)findViewById(R.id.in_anam_swallow)).isChecked();
		p.clinicalAnamnesa.bloodVeinProb = ((CheckBox)findViewById(R.id.in_clinic_bloodvein)).isChecked();
		p.clinicalAnamnesa.cellulitis = ((CheckBox)findViewById(R.id.in_clinic_cellulitis)).isChecked();
		p.clinicalAnamnesa.diabMoreThan2Years = ((CheckBox)findViewById(R.id.in_clinic_diablong)).isChecked();
		p.clinicalAnamnesa.fracture = ((CheckBox)findViewById(R.id.in_clinic_fracture)).isChecked();
		p.clinicalAnamnesa.hungerControl = ((CheckBox)findViewById(R.id.in_clinic_standhunger)).isChecked();
		p.clinicalAnamnesa.wound = ((CheckBox)findViewById(R.id.in_clinic_wound)).isChecked();
		
		//Save patient
		try {
			p.save(openFileOutput(FilePaths.PATIENT, Context.MODE_PRIVATE));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void loadPatientData() {    
        //Load file
        Patient p = new Patient();
        try {
			p.load(openFileInput(FilePaths.PATIENT));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        
        //Assign
		((EditText)findViewById(R.id.in_antro_age)).setText("" + p.anthropometry.age);
		((EditText)findViewById(R.id.in_antro_bodyheight)).setText("" + p.anthropometry.bodyHeight);
		((EditText)findViewById(R.id.in_antro_bodyweight)).setText("" + p.anthropometry.bodyWeight);
		if(p.anthropometry.isGenderMale) ((RadioButton)findViewById(R.id.in_antro_gender_male)).toggle();
		else ((RadioButton)findViewById(R.id.in_antro_gender_female)).toggle();
		if(p.anthropometry.status == Status.PREGNANT) ((RadioButton)findViewById(R.id.in_antro_status_preg)).toggle();
		else if(p.anthropometry.status == Status.BREASTFEEDING) ((RadioButton)findViewById(R.id.in_antro_status_breastfeed)).toggle();
		else ((RadioButton)findViewById(R.id.in_antro_status_normal)).toggle();

		((EditText)findViewById(R.id.in_lab_chol)).setText("" + p.laboratory.cholesterol);
		((EditText)findViewById(R.id.in_lab_diastole)).setText("" + p.laboratory.diastole);
		((EditText)findViewById(R.id.in_lab_systole)).setText("" + p.laboratory.systole);
		((EditText)findViewById(R.id.in_lab_HDL)).setText("" + p.laboratory.HDL);
		((EditText)findViewById(R.id.in_lab_LDL)).setText("" + p.laboratory.LDL);
		((EditText)findViewById(R.id.in_lab_temp)).setText("" + p.laboratory.temperature);
		((EditText)findViewById(R.id.in_lab_trig)).setText("" + p.laboratory.triglyceride);
		((EditText)findViewById(R.id.in_lab_uricacid)).setText("" + p.anthropometry.bodyWeight);

		((CheckBox)findViewById(R.id.in_anam_swallow)).setChecked(p.clinicalAnamnesa.swallowProb);
		((CheckBox)findViewById(R.id.in_clinic_bloodvein)).setChecked(p.clinicalAnamnesa.bloodVeinProb);
		((CheckBox)findViewById(R.id.in_clinic_cellulitis)).setChecked(p.clinicalAnamnesa.cellulitis);
		((CheckBox)findViewById(R.id.in_clinic_diablong)).setChecked(p.clinicalAnamnesa.diabMoreThan2Years);
		((CheckBox)findViewById(R.id.in_clinic_fracture)).setChecked(p.clinicalAnamnesa.fracture);
		((CheckBox)findViewById(R.id.in_clinic_standhunger)).setChecked(p.clinicalAnamnesa.hungerControl);
		((CheckBox)findViewById(R.id.in_clinic_wound)).setChecked(p.clinicalAnamnesa.wound);
    }
}
