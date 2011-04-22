/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ai.diabdiet.es.data.plan;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author adytzs
 */
public class Plan {

    int ID;
    int Relation; // 0 Null, 1 And, 2 Or
    List<Plan> Child;
    List<Integer> LChild; // klo g punya anak -1
    int ID_Parent;
    int Action;

    public Plan() {
        ID = 0;
        Relation = 0;
        Child = new ArrayList<Plan>();
        LChild = new ArrayList<Integer>();
        ID_Parent = 0;
        Action = 0;
    }

    public Plan(int id, int relation, List<Plan> child, List<Integer> lchild,int id_parent, int act) {
        ID = id;
        Relation = relation;
        Child = child;
        LChild = lchild;
        ID_Parent = id_parent;
        Action = act;
    }

    public Plan(String Data) {
        String[] Temp_Data = Data.split(" ");
        ID = Integer.parseInt(Temp_Data[1]);
        if (Temp_Data[2].equals("AND")) {
            Relation = 1;
        } else if (Temp_Data[2].equals("OR")) {
            Relation = 2;
        } else {
            Relation = 0;
        }

        Child = new ArrayList<Plan>();

        LChild = new ArrayList<Integer>();
        String temp = Temp_Data[3];

        if ((temp.charAt(0) == '[')&&(temp.charAt(temp.length()-1)==']')) {
            temp = temp.substring(1, Temp_Data[3].length() - 1);
            String[] ListChild = temp.split(",");
            for (int i = 0; i < ListChild.length; i++) {
                int y = Integer.parseInt(ListChild[i]);
                LChild.add(y);
            }
        }
        ID_Parent = 0;
        Action = Integer.parseInt(Temp_Data[4]);
    }

    public void CreateChiled(List<Plan> PlanTree) {
        Child = new ArrayList<Plan>();
        for(int i =0 ;i< LChild.size();i++){
            int t = LChild.get(i);
            if(t != -1){
                Plan plan= PlanTree.get(t);
                Child.add(plan);
            }
        }
    }

    public void CreateParentLink(List<Plan> PlanTree){
        ID_Parent = -1;
        for(int i = 0; i < PlanTree.size(); i++){
            Plan Temp = PlanTree.get(i);
            List<Integer> TempChild = Temp.LChild;
            for(int j = 0; j < TempChild.size(); j++){
                int x = TempChild.get(j);
                if(x == ID){
                    ID_Parent = Temp.ID;
                    break;
                }
            }
        }
    }
    
    public void SetParent(int val){
        ID_Parent = val;
    }

    
}
