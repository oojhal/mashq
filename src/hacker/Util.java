/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hacker;

import java.io.File;
import java.util.Scanner;

/**
 *
 * @author ssiddiqu
 */
public class Util {
        public static Scanner getInputFromFile(String fileName)
    {
        File input = null;
        Scanner in = null;
        try
        {
        input = new File("input"+File.separator+fileName);
        in = new Scanner(input);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return in;
    }
}
