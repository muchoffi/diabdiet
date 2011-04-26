/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ai.diabdiet.es.data.core;

import java.util.ArrayList;
import java.util.List;
import org.ai.diabdiet.es.data.core.core.BloodPressure;
import org.ai.diabdiet.es.data.core.core.BodySize;
import org.ai.diabdiet.es.data.core.core.BodyTemperature;
import org.ai.diabdiet.es.data.daftarmenu.ListMenu;
import org.ai.diabdiet.es.data.knowledge.Knowledge;
import org.ai.diabdiet.es.data.patient.Anthropometry.Status;
import org.ai.diabdiet.es.data.patient.Patient;
import org.ai.diabdiet.es.data.plan.Plan;

/**
 *
 * @author Robert Gunawan
 */
public class Action {
    public static Patient P;
    public static float B;
    public static float B1;
    public static void aksi(int i)
    {
        int itr; //buat iterator
        if(i == 0) //aksi pertama
        {
            //aksi Menu Diet Diabetes Tipe 2

            //add anak
            AddAnak(i);
        }
        else if(i == 1)
        {
            //aksi Milih anak apakah hamil/menyusui atau tidak + tambah anak
            if(P.anthropometry.status.equals(Status.PREGNANT) || P.anthropometry.status.equals(Status.BREASTFEEDING)) //hamil atau menyusui
            {
                core.GetInstance().listP.add(1,core.GetInstance().treePlan.Tree.get(i).Child.get(0)); //masukin yang hamil
                core.GetInstance().isHamilMenyusui = true;
            }
            else //cowok atau cewek normal
            {
                core.GetInstance().isHamilMenyusui = false;
                core.GetInstance().listP.add(1,core.GetInstance().treePlan.Tree.get(i).Child.get(1)); //masukin yang perhitungan normal saja
            }
        }
        else if(i == 2)
        {
            //aksi Perhitungan data laboratorium

            //add anak
            AddAnak(i);
        }
        else if(i == 3)
        {
            //aksi Penentuan Bahan makanan

            //add anak
            AddAnak(i);
        }
        else if(i == 4)
        {
            //aksiPenentuan Tips Diet

            //add anak
            AddAnak(i);
        }
        else if(i == 5)
        {
            //aksi Perhitungan Kalori Hamil atau Menyusui

            //add anak
            AddAnak(i);
        }
        else if(i == 6)
        {
            //aksi Perhitungan Kebutuhan Normal

            //add anak
            AddAnak(i);
        }
        else if(i == 7)
        {
            //aksi Penentuan Data Suhu Badan
            //panggil temperatur
            if(P.laboratory.temperature<35) //suhu badan rendah
            {
                core.GetInstance().suhuBadan = BodyTemperature.DINGIN;
            }
            else if(P.laboratory.temperature>=35 || P.laboratory.temperature<=36)
            {
                core.GetInstance().suhuBadan = BodyTemperature.NORMAL;
            }
            else
            {
                core.GetInstance().suhuBadan = BodyTemperature.PANAS;
            }

            //add anak
            AddAnak(i);
        }
        else if(i == 8)
        {
            //aksi Penentuan Data Kolesterol

            //add anak
            AddAnak(i);
        }
        else if(i == 9)
        {
            //aksi Penentuan Data Tekanan Darah
            if(P.laboratory.systole<90 && P.laboratory.diastole<60) //hipotensi
            {
                core.GetInstance().tekananDarah = BloodPressure.RENDAH;
            }
            else if(P.laboratory.systole>120 && P.laboratory.diastole>80) //hipertensi
            {
                core.GetInstance().tekananDarah = BloodPressure.TINGGI;
            }
            else
            {
                core.GetInstance().tekananDarah = BloodPressure.NORMAL;
            }
            //add anak
            AddAnak(i);
        }
        else if(i == 10)
        {
            //aksi asam urat
            if(P.anthropometry.isGenderMale) //cowok
            {
                if(P.laboratory.uricAcid < 3.5 || P.laboratory.uricAcid > 7) //tidak normal
                {
                    core.GetInstance().isAsamUratNormal = false;
                }
                else
                {
                    core.GetInstance().isAsamUratNormal = true;
                }
            }
            else //cewek
            {
                if(P.laboratory.uricAcid < 2.8 || P.laboratory.uricAcid > 6.8) //tidak normal
                {
                    core.GetInstance().isAsamUratNormal = false;
                }
                else
                {
                    core.GetInstance().isAsamUratNormal = true;
                }
            }
            //add anak
            AddAnak(i);
        }
        else if(i == 11)
        {
            //aksi
            B1 = 0;
            B = 0;
            CekDiet(P.clinicalAnamnesa.hungerControl,true,(float)0.5);
            CekDiet(P.clinicalAnamnesa.bloodVeinProb,true,1);
            CekDiet(P.clinicalAnamnesa.fracture,false,1);
            CekDiet(P.clinicalAnamnesa.cellulitis,false,1);
            CekDiet(P.clinicalAnamnesa.wound,false,1);
            CekDiet(P.clinicalAnamnesa.diabMoreThan2Years,true,1);
            CekDiet(!core.GetInstance().isAsamUratNormal,true,1);
            //yang bawah ini belum pasti
            boolean temp = core.GetInstance().isHDLNormal && core.GetInstance().isLDLNormal && core.GetInstance().isTriNormal && core.GetInstance().isCholesterolNormal;
            CekDiet(!temp,true,1);
            if(core.GetInstance().isHamilMenyusui)
            {
            CekDiet(core.GetInstance().isHamilMenyusui,false,1);
            }
            else
            {
            CekDiet(core.GetInstance().ukuranBadan.equals(BodySize.KURUS),false,(float)0.5);
            }
            CekDiet((P.anthropometry.age < 25), false,(float) 0.5);

            if(B >= B1)
            {
                core.GetInstance().isDietB = true;
            }
            else
            {
                core.GetInstance().isDietB = false;
            }

            //tidak add anak (dihapus!)
        }
        else if(i == 12) //ini semifinal :D
        {
            //aksi Penentuan bahan makanan
            ListMenu a = core.GetInstance().listMenu;
            if(core.GetInstance().isDietB) //yang B maka yang B1 dibuang semua
            {
                for(int j =0;j<a.getListmenu().size();j++)
                {
                    if(a.getListmenu().get(j).getTipediet().equals("B1"))
                    {
                        a.getListmenu().remove(j);
                        j--;
                    }
                }
            }
            else
            {
                for(int j =0;j<a.getListmenu().size();j++)
                {
                    if(a.getListmenu().get(j).getTipediet().equals("B"))
                    {
                        a.getListmenu().remove(j);
                        j--;
                    }
                }
            }
            //di tahap ini udah diseleksi B atau B1
            //lihat kalori
            float selisihKal;
            float min = 99999999;
            int idMin = 0;
            float Kal;
            if(core.GetInstance().isHamilMenyusui) //hamil menyusui
            {
                Kal = core.GetInstance().kaloriTotalHamilMenyusui;
                //System.out.println("hamil / menyusui");
            }
            else //normal
            {
                Kal = core.GetInstance().kaloriTotalNormal;
            }
            //get selisih semuanya
            for(int j=0;j<a.getListmenu().size();j++)
            {
                selisihKal = Math.abs(a.getListmenu().get(j).getKalori() - Kal);
                if(min > selisihKal) // jika min kalah kecil
                {
                    min = selisihKal;
                    idMin = j;
                }
            }
            core.GetInstance().menuHasilInferensi = a.getListmenu().get(idMin);
            //add anak
            AddAnak(i);
        }
        else if(i == 13)
        {
            //aksi

            //add anak
            AddAnak(i);
        }
        else if(i == 14)
        {
            //aksi

            //add anak
            AddAnak(i);
        }
        else if(i == 15)
        {
            //aksi

            //add anak
            AddAnak(i);
        }
        else if(i == 16)
        {
            //aksi

            //add anak
            AddAnak(i);
        }
        else if(i == 17)
        {
            //aksi

            //add anak
            AddAnak(i);
        }
        else if(i == 18)
        {
            //aksi Perhitungan jumlah kalori dasar hamil
            core.GetInstance().kaloriDasarHamilMenyusui = (P.anthropometry.bodyHeight - 100) * 30;
            //add anak
            AddAnak(i);
        }
        else if(i == 19)
        {
            //aksi Penentuan kalori trisemester
            if(P.anthropometry.status == Status.PREGNANT) //hamil
            {
                core.GetInstance().kaloriHamilMenyusui = Knowledge.S_Kehamilan.get(0).GetNeedKalor();
            }
            else //menyusui
            {
                core.GetInstance().kaloriHamilMenyusui = Knowledge.S_Kehamilan.get(1).GetNeedKalor();
            }
            //add anak
            AddAnak(i);
        }
        else if(i == 20)
        {
            //aksi Perhitungan Kalori Hamil total per hari
            core.GetInstance().kaloriTotalHamilMenyusui = core.GetInstance().kaloriHamilMenyusui + core.GetInstance().kaloriDasarHamilMenyusui;
            //add anak
            AddAnak(i);
        }
        else if(i == 21)
        {
            //aksi Perhitungan Nilai Index RBW
            core.GetInstance().RBW = P.anthropometry.bodyWeight / (P.anthropometry.bodyHeight-100);
            //add anak
            AddAnak(i);
        }
        else if(i == 22)
        {
            //aksi Penentuan Status Gizi
            if(core.GetInstance().RBW <= Knowledge.S_Gizi.get(0).GetMaxGizi()) //kurus
            {
                core.GetInstance().ukuranBadan = BodySize.KURUS;
            }
            else if(core.GetInstance().RBW > Knowledge.S_Gizi.get(1).GetMinGizi() && core.GetInstance().RBW <=Knowledge.S_Gizi.get(1).GetMaxGizi()) //normal
            {
                core.GetInstance().ukuranBadan = BodySize.NORMAL;
            }
            else if(core.GetInstance().RBW > Knowledge.S_Gizi.get(2).GetMinGizi() && core.GetInstance().RBW <=Knowledge.S_Gizi.get(2).GetMaxGizi()) //gemuk
            {
                core.GetInstance().ukuranBadan = BodySize.GEMUK;
            }
            else
            {
                core.GetInstance().ukuranBadan = BodySize.OBESITAS;
            }
            //add anak
            AddAnak(i);
        }
        else if(i == 23)
        {
            //aksi Perhitungan kebutuhan kalori normal total
            if(core.GetInstance().ukuranBadan.equals(BodySize.KURUS))
            {
                core.GetInstance().kaloriTotalNormal = P.anthropometry.bodyWeight * 50;
            }
            else if(core.GetInstance().ukuranBadan.equals(BodySize.NORMAL))
            {
                core.GetInstance().kaloriTotalNormal = P.anthropometry.bodyWeight * 30;
            }
            else if(core.GetInstance().ukuranBadan.equals(BodySize.GEMUK))
            {
                core.GetInstance().kaloriTotalNormal = P.anthropometry.bodyWeight * 20;
            }
            else //obesitas
            {
                core.GetInstance().kaloriTotalNormal = (float) (P.anthropometry.bodyWeight * 12.5);
            }
            //add anak
            AddAnak(i);
        }
        else if(i == 24)
        {
            //aksi Penentuan data HDL
            if(P.laboratory.HDL < 35 || P.laboratory.HDL > 55) //tidak normal
            {
                core.GetInstance().isHDLNormal = false;
            }
            else
            {
                core.GetInstance().isHDLNormal = true;
            }
            //add anak
            AddAnak(i);
        }
        else if(i == 25)
        {
            //aksi Penentuan data LDL
            if(P.laboratory.LDL < 60 || P.laboratory.LDL > 185) //tidak normal
            {
                core.GetInstance().isLDLNormal = false;
            }
            else
            {
                core.GetInstance().isLDLNormal = true;
            }
            //add anak
            AddAnak(i);
        }
        else if(i == 26)
        {
            //aksi Penentuan data Trigliceride
            if(P.laboratory.triglyceride < 40 || P.laboratory.triglyceride > 150) //tidak normal
            {
                core.GetInstance().isTriNormal = false;
            }
            else
            {
                core.GetInstance().isTriNormal = true;
            }
            //add anak
            AddAnak(i);
        }
        else if(i == 27)
        {
            //aksi Penentuan data Kolesterol total
            if(P.laboratory.cholesterol < 120 || P.laboratory.cholesterol > 200) //tidak normal
            {
                core.GetInstance().isCholesterolNormal = false;
            }
            else
            {
                core.GetInstance().isCholesterolNormal = true;
            }
            //add anak
            AddAnak(i);
        }
        else if(i == 28)
        {
            //aksi

            //add anak
            AddAnak(i);
        }
        else if(i == 29)
        {
            //aksi

            //add anak
            AddAnak(i);
        }
        else if(i == 30)
        {
            //aksi

            //add anak
            AddAnak(i);
        }
        else if(i == 31)
        {
            //aksi

            //add anak
            AddAnak(i);
        }
        else if(i == 32)
        {
            //aksi

            //add anak
            AddAnak(i);
        }
        else if(i == 33)
        {
            //aksi

            //add anak
            AddAnak(i);
        }
    }

    public static void AddAnak(int i)
    {
        int itr;
        List<Plan> temp = new ArrayList<Plan>();
        for(itr =0;itr<core.GetInstance().treePlan.Tree.get(i).Child.size();itr++)
            {
                temp.add(core.GetInstance().treePlan.Tree.get(i).Child.get(itr));
            }
        //masukin ke listP tepat dibelakang elemen pertama
        core.GetInstance().listP.addAll(1, temp);
    }

    public static void CekDiet(boolean masukan, boolean isBPriority, float i)
    {
        if(masukan) //hasil true
        {
            if(isBPriority) //dan nambah di B
            {
                B = B + i*1;
            }
            else //nambah di B1
            {
                B1 = B1 + i*1;
            }
        }
        else //hasil false
        {
            if(isBPriority) //dan nambah di B1
            {
                B1 = B1 + i * 1;
            }
            else //nambah di B
            {
                B = B + i * 1;
            }
        }
    }
}
