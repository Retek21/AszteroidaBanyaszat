package game.main;

import game.logic.Skeleton;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //Created by:Bendegúz Dengyel 2021.03.21
        //Testing menu:

        //Initialization of the needed objects(scanner, skeleton)
        Skeleton s=new Skeleton();
        boolean closeMenu=false;
        Scanner sc=new Scanner(System.in);

        //the menu runs until exit is chosen (0):
        while(!closeMenu){
            Skeleton.WriteName("[MENU] TEST CASES:");
            Skeleton.tab++;
            Skeleton.WriteName("[0] EXIT MENU");
            Skeleton.WriteName("[1] SettlerDrillsAsteroidWithUranium()");
            Skeleton.WriteName("[2] SettlerDrillsAsteroidWithIce()");
            Skeleton.WriteName("[3] SettlerDrillsAsteroidWithCoal()");
            Skeleton.WriteName("[4] RobotDrillsAsteroidWithUranium()");
            Skeleton.WriteName("[5] RobotDrillsAsteroidWithIce()");
            Skeleton.WriteName("[6] RobotDrillsAsteroidWithCoal()");

            Skeleton.tab--;
            Skeleton.WriteName("[MENU] CHOOSE A TEST NUMBER:");
            Skeleton.tab++;
            int menuNumber=sc.nextInt();

            //structure to call the chosen method:
            switch (menuNumber){
                case 0: closeMenu=true; break;
                case 1:s.SettlerDrillsAsteroidWithUranium(); break;
                case 2:s.SettlerDrillsAsteroidWithIce(); break;
                case 3:s.SettlerDrillsAsteroidWithCoal(); break;
                case 4:s.RobotDrillsAsteroidWithUranium(); break;
                case 5:s.RobotDrillsAsteroidWithIce(); break;
                case 6:s.RobotDrillsAsteroidWithCoal(); break;
                case 7:s.RobotMovesThroughTeleport(); break;
                case 8:s.RobotTriesToMoveTroughTeleport();break;
                case 9:s.RobotMovesToAsteroid();break;
                case 10:s.SettlerMovesThroughTeleport();break;
                case 11:s.SettlerTriesToMoveTroughTeleport();break;
                case 12:s.SettlerMovesToAsteroid();break;
            }
            Skeleton.tab--;
        }

        //dzsászt kidding lol :D
        Skeleton.WriteName("So you have chosen...EXIT!");
        Skeleton.WriteName("Launch warheads...");
        Skeleton.WriteName("Cleaning up...");
        Skeleton.WriteName("Formatting drive: C:\\... ");
    }
}
