/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ai.diabdiet.es.data.plan;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author adytzs
 */
public class TreePlan {
    public List<Plan> Tree;
    
    public TreePlan(){
        Tree = new ArrayList<Plan>();
    }

    public TreePlan(InputStream is){
        Tree = new ArrayList<Plan>();
        List<String> Data = FileAction.ReadFile(is);

        //read Data and Create List of Plan
        for(int i = 0; i < Data.size();i++){
            Tree.add(new Plan(Data.get(i)));
        }

        //Connect parrent to Child and Set Link Parrent
        for(int i = 0; i < Tree.size();i++){
            Plan temp = Tree.get(i);
            temp.CreateChiled(Tree);
            temp.CreateParentLink(Tree);
            Tree.set(i, temp);
        }

        //rebuild Child for coreection 
        for(int i = 0; i < Tree.size();i++){
            Plan temp = Tree.get(i);
            temp.CreateChiled(Tree);
            Tree.set(i, temp);
        }

        
    }

    public int GetParentIndex(){
        int result = 0;
        for(int i = 0; i < Tree.size(); i++){
            Plan x = Tree.get(i);
            if(x.ID_Parent == -1){
                result = x.ID;
                break;
            }
        }
        
        return result;
    }

}
