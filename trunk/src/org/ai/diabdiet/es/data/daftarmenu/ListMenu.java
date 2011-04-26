/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ai.diabdiet.es.data.daftarmenu;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Erdiansyah
 */
public class ListMenu {
    private List<Menu> listmenu = new ArrayList<Menu>();


    public ListMenu() {
    }

    public void setListmenu(ArrayList<Menu> listmenu) {
        this.listmenu = listmenu;
    }

    public List<Menu> getListmenu() {
        return listmenu;
    }
    
}
