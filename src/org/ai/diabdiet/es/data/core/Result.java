/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ai.diabdiet.es.data.core;

import java.util.ArrayList;
import org.ai.diabdiet.es.data.daftarmenu.Menu;


/**
 *
 * @author Robert Gunawan
 */
public class Result {
    public Result(){
        
    }
    public Menu menuHasilInferensi;
    public ArrayList<String> Alasan = new ArrayList<String>();
    public ArrayList<ArrayList<String>> Tips = new ArrayList<ArrayList<String>>();
}
