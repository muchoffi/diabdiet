/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ai.diabdiet.es.data.plan;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author adytzs
 */
public class FileAction {

    public static List<String> ReadFile(InputStream is) {
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
}
