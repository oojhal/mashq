/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hacker;

import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author ssiddiqu
 */
public class Util {
    static PrintStream st;
    static PrintStream console= System.out;
    public static Scanner getInputFromFile(String fileName) {
        File input = null;
        Scanner in = null;
        try {
            System.out.println("Current working directory is " + System.getProperty("user.dir"));
            input = new File("input" + File.separator + fileName);
            in = new Scanner(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return in;
    }
    public static void outputtoFile(String fileName) {
        try {
            File fl = new File("output" + File.separator + fileName);
            fl.createNewFile();
            st = new PrintStream(fl);
            System.setOut(st);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static void outputtoconsole() {
        if(st!=null) {
            st.close();
        }
        System.setOut(console);
    }
}
