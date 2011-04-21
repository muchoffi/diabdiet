/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ai.diabdiet.es.data.daftarmenu;

/**
 *
 * @author Erdiansyah
 */
public class Menu {

    private int kalori;
    private String tipediet;
    private DetailMenu nasi;
    private DetailMenu daging;
    private DetailMenu tempe;
    private DetailMenu sayuranA;
    private DetailMenu sayuranB;
    private DetailMenu minyak;
    private DetailMenu pisang;
    private DetailMenu pepaya;
    private DetailMenu sususkim;
    /*
    private ArrayList<Float> nasi = new ArrayList<Float>();
    private ArrayList<Float> daging = new ArrayList<Float>();
    private ArrayList<Float> tempe = new ArrayList<Float>();
    private ArrayList<Float> sayuranA = new ArrayList<Float>();
    private ArrayList<Float> sayuranB = new ArrayList<Float>();
    private ArrayList<Float> minyak = new ArrayList<Float>();
    private ArrayList<Float> pisang = new ArrayList<Float>();
    private ArrayList<Float> pepaya = new ArrayList<Float>();
    private ArrayList<Float> sususkim = new ArrayList<Float>();
*/
    /*
    public Menu(int kalori, String tipediet, ArrayList<Float> nasi, ArrayList<Float> daging, ArrayList<Float> tempe, ArrayList<Float> sayuranA, ArrayList<Float> sayuranB, ArrayList<Float> minyak, ArrayList<Float> pisang, ArrayList<Float> pepaya, ArrayList<Float> sususkim) {
        this.kalori = kalori;
        this.tipediet = tipediet;
        
        this.nasi = nasi;
        this.daging = daging;
        this.tempe = tempe;
        this.sayuranA = sayuranA;
        this.sayuranB = sayuranB;
        this.minyak = minyak;
        this.pisang = pisang;
        this.pepaya = pepaya;
        this.sususkim = sususkim;
         
    }
*/
    public Menu(int kalori, String tipediet, DetailMenu nasi, DetailMenu daging, DetailMenu tempe, DetailMenu sayuranA, DetailMenu sayuranB, DetailMenu minyak, DetailMenu pisang, DetailMenu pepaya, DetailMenu sususkim) {
        this.kalori = kalori;
        this.tipediet = tipediet;
        this.nasi = nasi;
        this.daging = daging;
        this.tempe = tempe;
        this.sayuranA = sayuranA;
        this.sayuranB = sayuranB;
        this.minyak = minyak;
        this.pisang = pisang;
        this.pepaya = pepaya;
        this.sususkim = sususkim;
    }

    public DetailMenu getDaging() {
        return daging;
    }

    public int getKalori() {
        return kalori;
    }

    public DetailMenu getMinyak() {
        return minyak;
    }

    public DetailMenu getNasi() {
        return nasi;
    }

    public DetailMenu getPepaya() {
        return pepaya;
    }

    public DetailMenu getPisang() {
        return pisang;
    }

    public DetailMenu getSayuranA() {
        return sayuranA;
    }

    public DetailMenu getSayuranB() {
        return sayuranB;
    }

    public DetailMenu getSususkim() {
        return sususkim;
    }

    public DetailMenu getTempe() {
        return tempe;
    }

    public String getTipediet() {
        return tipediet;
    }

    public void setDaging(DetailMenu daging) {
        this.daging = daging;
    }

    public void setKalori(int kalori) {
        this.kalori = kalori;
    }


  /*

    public static ArrayList<Float> makeArrayList(float x, float y, float z) {
        ArrayList<Float> temp = new ArrayList<Float>();
        temp.add(x);
        temp.add(y);
        temp.add(z);
        return temp;
    }
*/

    public String printMenu() {
        StringBuilder sb = new StringBuilder();
        sb.append("menu-dasar\n");
        sb.append("kalori : ").append(this.kalori).append("\n");
        sb.append("type-diet : ").append(this.tipediet).append("\n");
        
        if (this.nasi != null) {
            sb.append("nasi : ").append((int)this.nasi.getFirst()).append(" ").append((int)this.nasi.getSecond()).append(" ").append((int)this.nasi.getThird()).append("\n");
        }
        if (this.daging != null) {
            sb.append("daging : ").append((int)this.daging.getFirst()).append(" ").append((int)this.daging.getSecond()).append(" ").append((int)this.daging.getThird()).append("\n");
        }
        if (this.tempe != null) {
            sb.append("tempe : ").append((int)this.tempe.getFirst()).append(" ").append((int)this.tempe.getSecond()).append(" ").append((int)this.tempe.getThird()).append("\n");
        }
        if (this.sayuranA != null) {
            sb.append("sayuran-A : ").append((int)this.sayuranA.getFirst()).append(" ").append((int)this.sayuranA.getSecond()).append(" ").append((int)this.sayuranA.getThird()).append("\n");
        }
        if (this.sayuranB != null) {
            sb.append("sayuran-B : ").append((int)this.sayuranB.getFirst()).append(" ").append((int)this.sayuranB.getSecond()).append(" ").append((int)this.sayuranB.getThird()).append("\n");
        }
        if (this.minyak != null) {
            sb.append("minyak : ").append((float)this.minyak.getFirst()).append(" ").append((float)this.minyak.getSecond()).append(" ").append((float)this.minyak.getThird()).append("\n");
        }
        if (this.pisang != null) {
            sb.append("pisang : ").append((int)this.pisang.getFirst()).append(" ").append((int)this.pisang.getSecond()).append(" ").append((int)this.pisang.getThird()).append("\n");
        }
        if (this.pepaya != null) {
            sb.append("pepaya : ").append((int)this.pepaya.getFirst()).append(" ").append((int)this.pepaya.getSecond()).append(" ").append((int)this.pepaya.getThird()).append("\n");
        }
        if (this.sususkim != null) {
            sb.append("susu-skim : ").append((int)this.sususkim.getFirst()).append(" ").append((int)this.sususkim.getSecond()).append(" ").append((int)this.sususkim.getThird()).append("\n");
        }
        

        return sb.toString();
    }

    
}
