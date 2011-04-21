/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ai.knowledge;

/**
 *
 * @author adytzs
 */
public class StatusKomplikasi {

    private String Jenis;
    private int MaxDaging;
    private int MaxTempe;

    public StatusKomplikasi() {
        Jenis = "";
        MaxDaging = 0;
        MaxTempe = 0;
    }

    public StatusKomplikasi(String jenis, int maxval, int minval) {
        Jenis = jenis;
        MaxDaging = minval;
        MaxTempe = maxval;
    }

    public StatusKomplikasi(String Data) {
        String[] SplitData = Data.split(" ");
        if (SplitData.length >= 4) {
            try {
                Jenis = SplitData[1];
                MaxDaging = Integer.parseInt(SplitData[2]);
                MaxTempe = Integer.parseInt(SplitData[3]);
            } catch (Exception e) {
                System.out.println("Read Wrong Format for StatusKomplikasi");
            }

        } else {
            //System.out.println("Read Not Complite Data");
            Jenis = "";
            MaxDaging = 0;
            MaxTempe = 0;
        }
    }

    public String GetJenis() {
        return Jenis;
    }

    public int GetMaxDaging() {
        return MaxDaging;
    }

    public int GetMaxTempe() {
        return MaxTempe;
    }

    public void SetJenis(String jenis) {
        Jenis = jenis;
    }

    public void SetMaxDaging(int val) {
        MaxDaging = val;
    }

    public void SetMaxTemp(int val) {
        MaxTempe = val;
    }
}
