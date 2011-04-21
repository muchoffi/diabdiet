/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ai.diabdiet.es.data.daftarmenu;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;

/**
 *
 * @author Erdiansyah
 */
public class FileOperation {
    public static ListMenu OpenFileListMenu(InputStream in){
        //mage obj Gson
        Gson gson = new Gson();

        //read from file external
        StringBuilder temp = new StringBuilder();
        int retval;
        try {
            while((retval = in.read())!=-1)
                temp.append((char)retval);
            in.close();

        } catch (IOException ex) {
            Logger.getLogger(FileOperation.class.getName()).log(Level.SEVERE, null, ex);
        }
        ListMenu listMenuHasil = new ListMenu();
        listMenuHasil = gson.fromJson(temp.toString(), ListMenu.class);

        return listMenuHasil;
    }

    public static void WriteFileListMenu(OutputStream out, ListMenu listmenu){
        //mage obj Gson
        Gson gson = new Gson();
        //create file external
        String Stringgson = gson.toJson(listmenu);
        try {
            out.write(Stringgson.getBytes());
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(FileOperation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
