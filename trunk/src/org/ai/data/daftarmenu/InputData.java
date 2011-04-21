/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ai.data.daftarmenu;


import java.io.File;

/**
 *
 * @author Erdiansyah
 */
public class InputData {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        File file = new File("E:/JavaApplication17/src/DaftarMenu/menudasar.txt");
/*
        ListMenu listmenu = new ListMenu();
        listmenu.getListmenu().add(new Menu(1100, "BNormal", new DetailMenu(60, 70, 70), new DetailMenu(25, 0, 0), new DetailMenu(0, 25, 25), new DetailMenu(100, 100, 100), new DetailMenu(25, 50, 50), new DetailMenu(5, 5, 5), new DetailMenu(100, 100, 100), null, null));
        listmenu.getListmenu().add(new Menu(1300, "B", new DetailMenu(70, 100, 100), new DetailMenu(25, 25, 25), new DetailMenu(10, 0, 0), new DetailMenu(100, 100, 100), new DetailMenu(25, 50, 50), new DetailMenu(5, 5, 5), new DetailMenu(125, 125, 125), null, null));
        listmenu.getListmenu().add(new Menu(1500, "B", new DetailMenu(75, 120, 120), new DetailMenu(25, 25, 25), new DetailMenu(25, 0, 0), new DetailMenu(100, 100, 100), new DetailMenu(25, 50, 50), new DetailMenu(5, (float) 7.5, (float) 7.5), null, null, null));
        listmenu.getListmenu().add(new Menu(1700, "B", new DetailMenu(90, 130, 130), new DetailMenu(25, 40, 25), new DetailMenu(25, 0, 0), new DetailMenu(100, 100, 100), new DetailMenu(25, 50, 50), new DetailMenu(5, (float) 7.5, (float) 7.5), new DetailMenu(150, 175, 175), null, null));
        listmenu.getListmenu().add(new Menu(1900, "B", new DetailMenu(100, 140, 140), new DetailMenu(25, 40, 25), new DetailMenu(25, 0, 0), new DetailMenu(100, 100, 100), new DetailMenu(25, 50, 50), new DetailMenu((float) 7.5, (float) 7.5, (float) 7.5), new DetailMenu(175, 200, 200), null, null));
        listmenu.getListmenu().add(new Menu(2100, "B", new DetailMenu(110, 150, 150), new DetailMenu(25, 40, 25), new DetailMenu(25, 25, 25), new DetailMenu(100, 100, 100), new DetailMenu(25, 50, 50), new DetailMenu(5, 10, 10), new DetailMenu(200, 200, 200), new DetailMenu(0, 100, 100), null));
        listmenu.getListmenu().add(new Menu(2300, "B", new DetailMenu(120, 160, 160), new DetailMenu(25, 40, 25), new DetailMenu(25, 25, 25), new DetailMenu(100, 100, 100), new DetailMenu(25, 50, 50), new DetailMenu(5, 10, 10), new DetailMenu(225, 200, 200), new DetailMenu(0, 100, 100), null));
        listmenu.getListmenu().add(new Menu(2500, "B", new DetailMenu(130, 170, 170), new DetailMenu(25, 40, 25), new DetailMenu(25, 40, 40), new DetailMenu(100, 100, 100), new DetailMenu(25, 50, 50), new DetailMenu((float) 12.5, (float) 12.5, (float) 12.5), new DetailMenu(225, 200, 200), new DetailMenu(0, 150, 150), null));
        listmenu.getListmenu().add(new Menu(2900, "B", new DetailMenu(160, 200, 200), new DetailMenu(40, 50, 50), new DetailMenu(25, 50, 50), new DetailMenu(100, 100, 100), new DetailMenu(25, 50, 50), new DetailMenu((float) 12.5, (float) 12.5, (float) 12.5), new DetailMenu(300, 200, 200), new DetailMenu(0, 200, 200), null));

        listmenu.getListmenu().add(new Menu(1100, "B1", new DetailMenu(35, 50, 50), new DetailMenu(25, 25, 25), new DetailMenu(25, 50, 50), new DetailMenu(100, 100, 100), new DetailMenu(25, 50, 50), new DetailMenu((float) 2.5, (float) 2.5, (float) 2.5), new DetailMenu(100, 100, 100), null, null));
        listmenu.getListmenu().add(new Menu(1300, "B1", new DetailMenu(40, 55, 55), new DetailMenu(25, 25, 25), new DetailMenu(25, 25, 25), new DetailMenu(100, 100, 100), new DetailMenu(25, 50, 50), new DetailMenu(5, 5, 5), null, new DetailMenu(125, 125, 125), new DetailMenu(20, 20, 20)));
        listmenu.getListmenu().add(new Menu(1500, "B1", new DetailMenu(60, 90, 90), new DetailMenu(25, 30, 30), new DetailMenu(25, 25, 25), new DetailMenu(100, 100, 100), new DetailMenu(25, 50, 50), new DetailMenu(5, 5, 5), null, new DetailMenu(175, 175, 175), new DetailMenu(20, 20, 20)));
        listmenu.getListmenu().add(new Menu(1700, "B1", new DetailMenu(80, 110, 110), new DetailMenu(25, 40, 40), new DetailMenu(25, 25, 25), new DetailMenu(100, 100, 100), new DetailMenu(25, 50, 50), new DetailMenu(5, 5, 5), null, new DetailMenu(175, 175, 175), new DetailMenu(25, 25, 25)));
        listmenu.getListmenu().add(new Menu(1900, "B1", new DetailMenu(90, 110, 110), new DetailMenu(40, 50, 50), new DetailMenu(25, 25, 25), new DetailMenu(100, 100, 100), new DetailMenu(25, 50, 50), new DetailMenu(5, 5, 5), null, new DetailMenu(200, 200, 200), new DetailMenu(25, 25, 25)));
        listmenu.getListmenu().add(new Menu(2100, "B1", new DetailMenu(100, 150, 150), new DetailMenu(50, 50, 50), new DetailMenu(25, 40, 40), new DetailMenu(100, 100, 100), new DetailMenu(25, 50, 50), new DetailMenu(5, (float) 7.5, (float) 7.5), null, new DetailMenu(225, 225, 225), new DetailMenu(25, 25, 25)));
        listmenu.getListmenu().add(new Menu(2300, "B1", new DetailMenu(100, 170, 170), new DetailMenu(50, 50, 50), new DetailMenu(50, 50, 50), new DetailMenu(100, 100, 100), new DetailMenu(25, 50, 50), new DetailMenu(5, (float) 7.5, (float) 7.5), null, new DetailMenu(250, 250, 250), new DetailMenu(25, 25, 25)));
        listmenu.getListmenu().add(new Menu(2500, "B1", new DetailMenu(120, 170, 170), new DetailMenu(50, 75, 70), new DetailMenu(50, 50, 50), new DetailMenu(100, 100, 100), new DetailMenu(25, 50, 50), new DetailMenu(5, 5, 5), null, new DetailMenu(325, 325, 325), new DetailMenu(30, 30, 30)));
        listmenu.getListmenu().add(new Menu(2700, "B1", new DetailMenu(120, 180, 180), new DetailMenu(75, 90, 90), new DetailMenu(50, 50, 50), new DetailMenu(100, 100, 100), new DetailMenu(25, 50, 50), new DetailMenu(5, 5, 5), null, new DetailMenu(350, 350, 350), new DetailMenu(30, 30, 30)));
        listmenu.getListmenu().add(new Menu(2900, "B1", new DetailMenu(130, 190, 190), new DetailMenu(75, 100, 100), new DetailMenu(50, 50, 50), new DetailMenu(100, 100, 100), new DetailMenu(25, 50, 50), new DetailMenu((float) 7.5, (float) 7.5, 5), null, new DetailMenu(400, 400, 400), new DetailMenu(30, 30, 30)));


        //Gson gson = new Gson();
        
        FileOperation.WriteFileListMenu(file, listmenu);
         */
        /*
        //create file external
        String Stringgson = gson.toJson(listmenu);
        try {
        FileOutputStream out = new FileOutputStream("menudasar.txt");
        out.write(Stringgson.getBytes());
        out.close();
        } catch (IOException ex) {
        Logger.getLogger(InputData.class.getName()).log(Level.SEVERE, null, ex);
        }

         */
        System.out.println();
        //make obj ListMenu
        ListMenu listMenuHasil = new ListMenu();
        listMenuHasil = FileOperation.OpenFileListMenu(file);
        for (Menu menu : listMenuHasil.getListmenu()) {
            System.out.println(menu.printMenu());
        }
    }
}
