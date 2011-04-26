package org.ai.diabdiet.es.data.patient;

public class ClinicalAnamnesa {
        public boolean digestionProb  = false; //gangguan pencernaan, default: false
	public boolean swallowProb 			= false; //gangguan menelan / pencernaan
	public boolean hungerControl		= true; //tahan lapar
	public boolean bloodVeinProb		= false; //pembuluh darah
	public boolean fracture				= true; //patah kaki
	public boolean wound				= true; //luka
	public boolean diabMoreThan2Years	= false; //punya diabetes lebih dari 2 tahun
	public boolean cellulitis			= false; //punya selulitis?
}
