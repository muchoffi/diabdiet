/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ai.diabdiet.es.data.knowledge;

/**
 *
 * @author adytzs
 */
public class StatusGizi {

    private String Jenis;
    private float MinGizi;
    private float MaxGizi;

    public StatusGizi() {
        Jenis = "";
        MinGizi = 0;
        MaxGizi = 0;
    }

    public StatusGizi(String jenis, float maxval, float minval) {
        Jenis = jenis;
        MinGizi = minval;
        MaxGizi = maxval;
    }

    public StatusGizi(String Data) {
        String[] SplitData = Data.split(" ");
        if (SplitData.length >= 4) {
            try {
                Jenis = SplitData[1];
                MinGizi = Float.parseFloat(SplitData[2]);
                MaxGizi = Float.parseFloat(SplitData[3]);
            } catch (Exception e) {
                System.out.println("Read Wrong Format for StatusGizi");
            }

        } else {
            //System.out.println("Read Not Complite Data");
            Jenis = "";
            MinGizi = 0;
            MaxGizi = 0;
        }
    }

    public String GetJenis() {
        return Jenis;
    }

    public float GetMinGizi() {
        return MinGizi;
    }

    public float GetMaxGizi() {
        return MaxGizi;
    }

    public void SetJenis(String jenis) {
        Jenis = jenis;
    }

    public void SetMinGizi(float minval) {
        MinGizi = minval;
    }

    public void SetMaxGizi(float maxval) {
        MaxGizi = maxval;
    }
}
