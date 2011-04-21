/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ai.knowledge;

/**
 *
 * @author adytzs
 */
public class StatusKehamilan {

    private String Jenis;
    private int NeedKalor;

    public StatusKehamilan() {
        Jenis = "";
        NeedKalor = 0;
    }

    public StatusKehamilan(String jenis, int maxval, int minval) {
        Jenis = jenis;
        NeedKalor = minval;
    }

    public StatusKehamilan(String Data) {
        String[] SplitData = Data.split(" ");
        if (SplitData.length >= 3) {
            try {
                Jenis = SplitData[1];
                NeedKalor = Integer.parseInt(SplitData[2]);
            } catch (Exception e) {
                System.out.println("Read Wrong Format for StatusKehamilan");
            }

        } else {
            //System.out.println("Read Not Complite Data");
            Jenis = "";
            NeedKalor = 0;
        }
    }

    public String GetJenis() {
        return Jenis;
    }

    public int GetNeedKalor() {
        return NeedKalor;
    }

    

    public void SetJenis(String jenis) {
        Jenis = jenis;
    }

    public void SetNeedKalor(int minval) {
        NeedKalor = minval;
    }
}
