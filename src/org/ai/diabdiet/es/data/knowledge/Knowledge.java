/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ai.diabdiet.es.data.knowledge;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author adytzs
 */
public class Knowledge {

    public static List<StatusGizi> S_Gizi = new ArrayList<StatusGizi>();      //List of StatusGizi
    public static List<StatusKehamilan> S_Kehamilan = new ArrayList<StatusKehamilan>(); //List of Status Kehamilan
    public static List<StatusKomplikasi> S_Komplikasi = new ArrayList<StatusKomplikasi>();//list of StatusKomplikasi

    private static List<String> ReadFile(InputStream is) {
        List<String> result = new ArrayList<String>();
        try {
            InputStream fstream = is;
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            while ((strLine = br.readLine()) != null) {
                result.add(strLine);
            }
            in.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return result;
    }

    public static void WriteFile(OutputStream os) {
        byte[] file = null;;
        String temp = "";
        BufferedOutputStream bos = null;
        try {
            //create an object of FileOutputStream
            OutputStream fos = os;

            //create an object of BufferedOutputStream
            bos = new BufferedOutputStream(fos);
            temp = "# status\n";
            file = temp.getBytes();
            bos.write(file);
            for (int i = 0; i < Knowledge.S_Gizi.size(); i++) {
                StatusGizi x = (StatusGizi) Knowledge.S_Gizi.get(i);
                temp = "+ " + x.GetJenis() + " " + x.GetMinGizi() + " " + x.GetMaxGizi()+"\n";
                file = temp.getBytes();
                bos.write(file);
            }

            temp = "# kehamilan\n";
            file = temp.getBytes();
            bos.write(file);
            for (int i = 0; i < Knowledge.S_Kehamilan.size(); i++) {
                StatusKehamilan y = (StatusKehamilan) Knowledge.S_Kehamilan.get(i);
                temp = "+ " + y.GetJenis() + " " + y.GetNeedKalor()+"\n";
                file = temp.getBytes();
                bos.write(file);
            }

            temp = "# komplikasi\n";
            file = temp.getBytes();
            bos.write(file);
            for (int i = 0; i < Knowledge.S_Komplikasi.size(); i++) {
                StatusKomplikasi z = (StatusKomplikasi) Knowledge.S_Komplikasi.get(i);
                temp = "+ " + z.GetJenis() + " " + z.GetMaxDaging() + " " + z.GetMaxTempe()+"\n";
                file = temp.getBytes();
                bos.write(file);
            }



        } catch (FileNotFoundException fnfe) {
            System.out.println("Specified file not found" + fnfe);
        } catch (IOException ioe) {
            System.out.println("Error while writing file" + ioe);
        } finally {
            if (bos != null) {
                try {
                    //flush the BufferedOutputStream
                    bos.flush();
                    //close the BufferedOutputStream
                    bos.close();
                } catch (Exception e) {
                }
            }
        }


    }

    public static void ConvertToKnowledge(InputStream is) {
        List<String> LoS_File = ReadFile(is);
        int S_Reader = 0; // 0 = readStatusGizi, 1 = readStatusKehamilan, 2 = readStatusKomplikasi
        String Data = "";
        for (int i = 0; i < LoS_File.size(); i++) {
            Data = (String) LoS_File.get(i);
            S_Reader = CheckStatus(Data, S_Reader);
            InsertData(Data, S_Reader);
        }
    }

    // Check Data ;0 = readStatusGizi, 1 = readStatusKehamilan, 2 = readStatusKomplikasi
    private static int CheckStatus(String Data, int S_Reader) {
        if (Data.equals("# status")) {
            return 0;
        } else if (Data.equals("# kehamilan")) {
            return 1;
        } else if (Data.equals("# komplikasi")) {
            return 2;
        } else {
            return S_Reader;
        }
    }

    private static void InsertData(String Data, int S_Reader) {
        if (S_Reader == 0) {
            StatusGizi sg = new StatusGizi(Data);
            if (!sg.GetJenis().equals("")) {
                S_Gizi.add(sg);
            }
        } else if (S_Reader == 1) {
            StatusKehamilan skh = new StatusKehamilan(Data);
            if (!skh.GetJenis().equals("")) {
                S_Kehamilan.add(skh);
            }

        } else if (S_Reader == 2) {
            StatusKomplikasi skp = new StatusKomplikasi(Data);
            if (!skp.GetJenis().equals("")) {
                S_Komplikasi.add(skp);
            }

        }
    }

    public static void ChangeElementS_Gizi(int indeks, String jns, float min, float max){
        StatusGizi temp = (StatusGizi)S_Gizi.get(indeks);
        if(!jns.equals(""))
        {
            temp.SetJenis(jns);
        }

        if(min != 0){
            temp.SetMinGizi(min);
        }
        if(max != 0 ){
            temp.SetMaxGizi(max);
        }
        S_Gizi.set(indeks, temp);
    }

    public static void ChangeElementS_Kehamilan(int indeks, String jns, int n_kalor){
        StatusKehamilan temp = (StatusKehamilan)S_Kehamilan.get(indeks);
        if(!jns.equals(""))
        {
            temp.SetJenis(jns);
        }

        if(n_kalor != 0){
            temp.SetNeedKalor(n_kalor);
        }
        S_Kehamilan.set(indeks, temp);
    }

    public static void ChangeElememtS_Komplikasi(int indeks, String jns, int m_daging, int m_tempe){
        StatusKomplikasi temp = (StatusKomplikasi) S_Komplikasi.get(indeks);
        if(!jns.equals(""))
        {
            temp.SetJenis(jns);
        }

        if(m_daging != 0){
            temp.SetMaxDaging(m_daging);
        }

        if(m_tempe != 0){
            temp.SetMaxTemp(m_tempe);
        }
        
        S_Komplikasi.set(indeks,temp);
    }
}
