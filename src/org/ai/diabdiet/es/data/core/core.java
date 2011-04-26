package org.ai.diabdiet.es.data.core;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.ai.diabdiet.es.data.daftarmenu.FileOperation;
import org.ai.diabdiet.es.data.daftarmenu.ListMenu;
import org.ai.diabdiet.es.data.daftarmenu.Menu;
import org.ai.diabdiet.es.data.knowledge.Knowledge;
import org.ai.diabdiet.es.data.patient.Anthropometry.Status;
import org.ai.diabdiet.es.data.patient.Patient;
import org.ai.diabdiet.es.data.plan.Plan;
import org.ai.diabdiet.es.data.plan.TreePlan;

public class core{
	//attribut singleton
	private static core Instance;
	
	//attribut dasar
	public ListMenu listMenu;
	public TreePlan treePlan;
        public boolean hasInitiated = false;
        public List<Plan> listP = new ArrayList<Plan>();

        //ini tipe enum harus pake bahasa inggris
        public enum BodyTemperature {
		DINGIN, NORMAL, PANAS
	}
        public enum BloodPressure {
		RENDAH, NORMAL, TINGGI
	}
        public enum BodySize{
            KURUS,NORMAL,GEMUK,OBESITAS
        }
        //working memory =====================================================
        public boolean isHamilMenyusui;
        public BodyTemperature suhuBadan;
        public BloodPressure tekananDarah;
        public boolean isAsamUratNormal;
        //bagian hamil
            public float kaloriDasarHamilMenyusui;
            public float kaloriHamilMenyusui;
            public float kaloriTotalHamilMenyusui;
        //bagian normal
            public float RBW;
            public BodySize ukuranBadan;
            public float kaloriTotalNormal;
        public boolean isHDLNormal;
        public boolean isLDLNormal;
        public boolean isTriNormal;
        public boolean isCholesterolNormal;
        //bagian diet
        public boolean isDietB;
        public Menu menuHasilInferensi;
        //=====================================================================

	//method
	//singleton handler ================================================
        public void init(InputStream ListMenu, InputStream TreePlan, InputStream inKnowledge)
        {
            if(this.hasInitiated)
            {
                return;
            }
            this.listMenu = FileOperation.OpenFileListMenu(ListMenu);// dapet listMenu
            this.treePlan = new TreePlan((FileInputStream) (TreePlan)); //dapet masukan tree plan
            Knowledge.ConvertToKnowledge(inKnowledge);
            this.hasInitiated = true;

        }

	public static core GetInstance()
	{
		if(Instance == null)
		{
			Instance = new core();
		}
		return Instance;
	}
	
	private core(){ //constructor
	}


        public Menu solve(Patient P)
        {
           //seluruh working memory dijadikan kosong!
           isHamilMenyusui = false;
           suhuBadan = BodyTemperature.NORMAL;
           tekananDarah = BloodPressure.NORMAL;
           isAsamUratNormal = true;
            //bagian hamil
           kaloriDasarHamilMenyusui = 0;
           kaloriHamilMenyusui = 0;
           kaloriTotalHamilMenyusui = 0;
            //bagian normal
           RBW = 0;
           ukuranBadan = BodySize.NORMAL;
           kaloriTotalNormal = 0;
           isHDLNormal = true;
           isLDLNormal = true;
           isTriNormal = true;
           isCholesterolNormal = true;
            //bagian diet
           isDietB = true; 

            //=======================================
            int i;
            listP.add(this.GetInstance().treePlan.Tree.get(0));
            Action.P = P;
            while(listP.isEmpty() == false) //masih ada isi, maka lakukan looping
            {
                Action.aksi(listP.get(0).Action);
                //System.out.println(listP.get(0).Action);
                listP.remove(0);
            }
            return menuHasilInferensi;
        }
	//============================================================================
	
	public static void main(String[] args) throws FileNotFoundException//ini buat mainnya doank
	{
		System.out.println("Robert Ganteng");
                Patient P = new Patient();

                File fileListMenu = new File("menudasar.txt");
		InputStream inMenu = (new FileInputStream(fileListMenu));

                //baca plan
		File fileTreePlan = new File("plan.txt");
		InputStream inTree = new FileInputStream(fileTreePlan); //dapet masukan tree plan

                //baca plan
		File fileKnow = new File("knowledge.txt");
		InputStream inKnow = new FileInputStream(fileKnow); //dapet masukan tree plan

                //P.anthropometry.status = Status.PREGNANT;
                core.GetInstance().init(inMenu, inTree, inKnow);
                //System.out.println(core.GetInstance().listMenu.getListmenu().get(0).getDaging().getFirst());
                //System.out.println(core.GetInstance().listMenu.getListmenu().get(0).getDaging().getSecond());
                //P.anthropometry.bodyWeight = 999;
                P.anthropometry.status = Status.PREGNANT;
                core.GetInstance().solve(P);
                System.out.println("kalori "+core.GetInstance().menuHasilInferensi.getKalori());
                System.out.println("tipe diet "+core.GetInstance().menuHasilInferensi.getTipediet());
                //System.out.println(core.GetInstance().kaloriTotalNormal);
	}	
}