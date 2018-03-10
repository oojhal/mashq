/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hacker;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ssiddiqu
 */
public class TowHanoi {
    

    /**
     * Moves disks from sourceTower to destinationTower using tmpTower tower
     * @sourceTower tower that has disks smallest to largest with smallest on top
     * @tmpTower temp tower
     * @destTower destination tower
     * @smallestDisk smallest disk size. topmost
     * @largestDisk largest disk size. bottommost
    */
    public static void move(int sourceTower, int tmpTower, int destTower, int smallestDisk, int largestDisk)
    {
        // only 1 disk to move
        if(smallestDisk==largestDisk)
        {
            System.out.println(printMove(sourceTower,destTower,smallestDisk));
            return;
        }
        move(sourceTower,destTower,tmpTower,smallestDisk,largestDisk-1);
        System.out.println(printMove(sourceTower,destTower,largestDisk));
        move(tmpTower,sourceTower,destTower,smallestDisk,largestDisk-1);    
    }
    public static String printMove(int source, int dest, int diskSize)
    {
        return "Move disk "+diskSize+" from Tower"+ source+ "==>Tower"+dest;
    }
    public static void main(String[] args)
    {
        int tower1=1;
        int tower2=2;
        int tower3=3;
        int largestDisk=1;
        int smallestDisk=1;
        System.out.println("*******Move "+(largestDisk-smallestDisk+1)+" disks ***********");
        move(tower1,tower2,tower3,smallestDisk,largestDisk);
        largestDisk=3;
        smallestDisk=1;
        System.out.println("*******Move "+(largestDisk-smallestDisk+1)+" disks ***********");
        move(tower1,tower2,tower3,smallestDisk,largestDisk);
        largestDisk=10;
        smallestDisk=1;
        System.out.println("*******Move "+(largestDisk-smallestDisk+1)+" disks ***********");
        move(tower1,tower2,tower3,smallestDisk,largestDisk);

    }
}
