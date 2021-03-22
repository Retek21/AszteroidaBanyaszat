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
            Skeleton.WriteName("[7] RobotMovesThroughTeleport()");
            Skeleton.WriteName("[8] RobotMovesToAsteroid()");
            Skeleton.WriteName("[9] RobotTriesToMoveTroughTeleport()");
            Skeleton.WriteName("[10] SettlerMovesThroughTeleport()");
            Skeleton.WriteName("[11] SettlerMovesToAsteroid()");
            Skeleton.WriteName("[12] SettlerTriesToMoveTroughTeleport()");
            Skeleton.WriteName("[13] AsteroidfieldMoves()");
            Skeleton.WriteName("[14] SunstormHitsAsteroidfield()");
            Skeleton.WriteName("[15] LonelyAsteroidExplodesWithRobotAndSettler()");
            Skeleton.WriteName("[16] AsteroidExplodesWithRobotAndSettler()");
            Skeleton.WriteName("[17] ControlRobot()");
            Skeleton.WriteName("[18] AsteroidExplodesWithTeleportTheOtherPairIsNearbyAnAsteroid()");
            Skeleton.WriteName("[19] AsteroidExplodesWithTeleportTheOtherPairIsInAnInventory()");
            Skeleton.WriteName("[20] SettlerDiesWithTeleportInInventory()");
            Skeleton.WriteName("[21] SettlerPlacesTeleport()");
            Skeleton.WriteName("[22] SettlerTriesToPlaceMaterialIntoFullAsteroid()");
            Skeleton.WriteName("[23] SettlerPlacesCoal()");
            Skeleton.WriteName("[24] SettlerPlacesUranium()");
            Skeleton.WriteName("[25] SettlerPlacesIce()");
            Skeleton.WriteName("[26] SettlerTriesToCraftRobot()");
            Skeleton.WriteName("[27] SettlerCraftsRobot()");
            Skeleton.WriteName("[28] SettlerTriesToCraftTeleports()");
            Skeleton.WriteName("[29] SettlerTriesToCraftTeleportWithoutFreeSlot()");
            Skeleton.WriteName("[30] SettlerCraftsTeleports()");
            Skeleton.WriteName("[31] SettlerTriesToMineEmptyAsteroid()");
            Skeleton.WriteName("[32] SettlerTriesToMineWithInventoryFull()");
            Skeleton.WriteName("[33] SettlerMines()");

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
                case 8:s.RobotMovesToAsteroid(); break;
                case 9:s.RobotTriesToMoveTroughTeleport(); break;
                case 10:s.SettlerMovesThroughTeleport(); break;
                case 11:s.SettlerMovesToAsteroid(); break;
                case 12:s.SettlerTriesToMoveTroughTeleport();break;
                case 13:s.AsteroidfieldMoves(); break;
                case 14:s.SunstormHitsAsteroidfield(); break;
                case 15:s.LonelyAsteroidExplodesWithRobotAndSettler(); break;
                case 16:s.AsteroidExplodesWithRobotAndSettler(); break;
                case 17:s.ControlRobot(); break;
                case 18:s.AsteroidExplodesWithTeleportTheOtherPairIsNearbyAnAsteroid(); break;
                case 19:s.AsteroidExplodesWithTeleportTheOtherPairIsInAnInventory(); break;
                case 20:s.SettlerDiesWithTeleportInInventory(); break;
                case 21:s.SettlerPlacesTeleport(); break;
                case 22:s.SettlerTriesToPlaceMaterialIntoFullAsteroid(); break;
                case 23:s.SettlerPlacesCoal(); break;
                case 24:s.SettlerPlacesUranium(); break;
                case 25:s.SettlerPlacesIce(); break;
                case 26:s.SettlerTriesToCraftRobot(); break;
                case 27:s.SettlerCraftsRobot(); break;
                case 28:s.SettlerTriesToCraftTeleports(); break;
                case 29:s.SettlerTriesToCraftTeleportWithoutFreeSlot(); break;
                case 30:s.SettlerCraftsTeleports(); break;
                case 31:s.SettlerTriesToMineEmptyAsteroid(); break;
                case 32:s.SettlerTriesToMineWithInventoryFull(); break;
                case 33:s.SettlerMines(); break;
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
