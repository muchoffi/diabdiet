package org.ai.diabdiet.es.data.patient;

public class Anthropometry {
	/* all public because this is logically a struct */
	public boolean isGenderMale = true;
	public enum Status {
		PREGNANT, BREASTFEEDING, NORMAL
	}
	public Status status		= Status.NORMAL;		
	public int age				= 40;
	public float bodyHeight		= 170;
	public float bodyWeight		= 100;
}
