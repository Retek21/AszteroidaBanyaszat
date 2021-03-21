package game.logic;

import java.util.Scanner;

//class for testing basic business logic of the game
public class Skeleton {

    //STATIC:

    //attribute storing the number of tabulators:
    public static int tab = 0;

    //method for the indented console output:
    public static void WriteName(String s) {
        //whenever the method is called, itt writes
        //the number of tabulators before the text it receives
        //in the header of the method:
        for (int i = 0; i < tab; i++)
            System.out.print("\t");
        //finally breaks the line
        System.out.print(s + "\n");
    }



    //TEST CASES:

    //test case for settler drills asteroid with uranium
    public void SettlerDrillsAsteroidWithUranium(){
        WriteName("[START] SETTLERS DRILLS ASTEROID WITH URANIUM");

        //initialization of the required objects:
        WriteName("[INITIALIZATION] Initialization of the required objects:");
        Uranium u=new Uranium();
        Asteroid a=new Asteroid();
        a.AddMaterial(u);
        Asteroidfield af=new Asteroidfield();
        af.AddAsteroid(a);
        Settler s=new Settler();
        Inventory i=new Inventory();
        s.SetInventory(i);
        a.AddEntity(s);

        //launching the test:
        WriteName("[LAUNCHING] Launching the test:");
        Scanner sc=new Scanner(System.in);

        WriteName("[INPUT] How many layers are there (integer, greater than or equal to 1)?");
        int layers=sc.nextInt();//reading the input (layers)
        a.SetLayer(layers);

        WriteName("[INPUT] Is the asteroid near to the sun (true/false)?");
        boolean sunnear=sc.nextBoolean();//reading the input (sunneraness)
        a.SetSunnearness(sunnear);

        tab++;
        //the point where the use-case diagram starts:
        s.Drill();
        tab--;
        WriteName("[END]");//end of the test, returning to the menu
    }

    //test case for settler drills asteroid with ice
    public void SettlerDrillsAsteroidWithIce(){
        WriteName("[START] SETTLERS DRILLS ASTEROID WITH ICE");

        //initialization of the required objects:
        WriteName("[INITIALIZATION] Initialization of the required objects:");
        Ice i=new Ice();
        Asteroid a=new Asteroid();
        a.AddMaterial(i);
        Settler s=new Settler();
        a.AddEntity(s);

        //launching the test:
        WriteName("[LAUNCHING] Launching the test:");
        Scanner sc=new Scanner(System.in);

        WriteName("[INPUT] How many layers are there (integer, greater than or equal to 1)?");
        int layers=sc.nextInt();//reading the input (layers)
        a.SetLayer(layers);

        WriteName("[INPUT] Is the asteroid near to the sun (true/false)?");
        boolean sunnear=sc.nextBoolean();//reading the input (sunneraness)
        a.SetSunnearness(sunnear);

        tab++;
        //the point where the use-case diagram starts:
        s.Drill();
        tab--;
        WriteName("[END]");//end of the test, returning to the menu

    }

    //test case for settler drills asteroid with coal
    public void SettlerDrillsAsteroidWithCoal(){
        WriteName("[START] SETTLERS DRILLS ASTEROID WITH COAL");

        //initialization of the required objects:
        WriteName("[INITIALIZATION] Initialization of the required objects:");
        Coal c=new Coal();
        Asteroid a=new Asteroid();
        a.AddMaterial(c);
        Settler s=new Settler();
        a.AddEntity(s);

        //launching the test:
        WriteName("[LAUNCHING] Launching the test:");
        Scanner sc=new Scanner(System.in);

        WriteName("[INPUT] How many layers are there (integer, greater than or equal to 1)?");
        int layers=sc.nextInt();//reading the input (layers)
        a.SetLayer(layers);

        WriteName("[INPUT] Is the asteroid near to the sun (true/false)?");
        boolean sunnear=sc.nextBoolean();//reading the input (sunneraness)
        a.SetSunnearness(sunnear);

        tab++;
        //the point where the use-case diagram starts:
        s.Drill();
        tab--;
        WriteName("[END]");//end of the test, returning to the menu


    }

    //test case for robot drills asteroid with uranium
    public void RobotDrillsAsteroidWithUranium(){
        WriteName("[START] ROBOT DRILLS ASTEROID WITH URANIUM");

        //initialization of the required objects:
        WriteName("[INITIALIZATION] Initialization of the required objects:");
        Uranium u=new Uranium();
        Asteroid a=new Asteroid();
        a.AddMaterial(u);
        Asteroidfield af=new Asteroidfield();
        af.AddAsteroid(a);
        Robot r=new Robot();
        r.Deploy(a);

        //launching the test:
        WriteName("[LAUNCHING] Launching the test:");
        Scanner sc=new Scanner(System.in);

        WriteName("[INPUT] How many layers are there (integer, greater than or equal to 1)?");
        int layers=sc.nextInt();//reading the input (layers)
        a.SetLayer(layers);

        WriteName("[INPUT] Is the asteroid near to the sun (true/false)?");
        boolean sunnear=sc.nextBoolean();//reading the input (sunneraness)
        a.SetSunnearness(sunnear);

        tab++;
        //the point where the use-case diagram starts:
        r.Drill();
        tab--;
        WriteName("[END]");//end of the test, returning to the menu
    }

    //test case for robot drills asteroid with ice
    public void RobotDrillsAsteroidWithIce(){
        WriteName("[START] ROBOT DRILLS ASTEROID WITH ICE");

        //initialization of the required objects:
        WriteName("[INITIALIZATION] Initialization of the required objects:");
        Ice i=new Ice();
        Asteroid a=new Asteroid();
        a.AddMaterial(i);
        Robot r=new Robot();
        r.Deploy(a);

        //launching the test:
        WriteName("[LAUNCHING] Launching the test:");
        Scanner sc=new Scanner(System.in);

        WriteName("[INPUT] How many layers are there (integer, greater than or equal to 1)?");
        int layers=sc.nextInt();//reading the input (layers)
        a.SetLayer(layers);

        WriteName("[INPUT] Is the asteroid near to the sun (true/false)?");
        boolean sunnear=sc.nextBoolean();//reading the input (sunneraness)
        a.SetSunnearness(sunnear);

        tab++;
        //the point where the use-case diagram starts:
        r.Drill();
        tab--;
        WriteName("[END]");//end of the test, returning to the menu
    }

    //test case for robot drills asteroid with coal
    public void RobotDrillsAsteroidWithCoal(){
        WriteName("[START] ROBOT DRILLS ASTEROID WITH COAL");

        //initialization of the required objects:
        WriteName("[INITIALIZATION] Initialization of the required objects:");
        Coal c=new Coal();
        Asteroid a=new Asteroid();
        a.AddMaterial(c);
        Robot r=new Robot();
        r.Deploy(a);

        //launching the test:
        WriteName("[LAUNCHING] Launching the test:");
        Scanner sc=new Scanner(System.in);

        WriteName("[INPUT] How many layers are there (integer, greater than or equal to 1)?");
        int layers=sc.nextInt();//reading the input (layers)
        a.SetLayer(layers);

        WriteName("[INPUT] Is the asteroid near to the sun (true/false)?");
        boolean sunnear=sc.nextBoolean();//reading the input (sunneraness)
        a.SetSunnearness(sunnear);

        tab++;
        //the point where the use-case diagram starts:
        r.Drill();
        tab--;
        WriteName("[END]");//end of the test, returning to the menu
    }

}
