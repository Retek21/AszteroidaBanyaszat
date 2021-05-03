package game.controller;

import java.util.Comparator;

public class ActorComparator implements Comparator<Actor> {

    public int compare(Actor ac1, Actor ac2) {
        String a = ac1.GetID();
        String b = ac2.GetID();
        if (a.charAt(0) == 's') {
            return SettlerElementCompare(a, b);
        }
        else if (a.charAt(0) == 'r')
            return RobotElementCompare(a, b);
        else if (a.charAt(0) == 'u')
            return UfoElementCompare(a, b);
        else if (a.charAt(0) == 't')
            return TeleportElementCompare(a, b);
        else if (a.equals("_sun"))
            return SunCompare(a, b);
        else if (a.equals("_asteroidfield"))
            return AsteroidfieldCompare(a, b);
        else
            return 0;

    }

    private int SettlerElementCompare(String a, String b) {
        if (b.charAt(0) != 's')
            return -1;
        else if (b.charAt(0) == 's') {
            char ca = a.charAt(1);
            char cb = b.charAt(1);
            int a1 = Character.getNumericValue(ca);
            int b1 = Character.getNumericValue(cb);
            return a1 - b1;
        }
        else return 0;
    }

    private int RobotElementCompare(String a, String b) {
        if (b.charAt(0) == 's')
            return 1;
        else if (b.charAt(0) != 'r')
            return -1;
        else if (b.charAt(0) == 'r') {
            char ca = a.charAt(1);
            char cb = b.charAt(1);
            int a1 = Character.getNumericValue(ca);
            int b1 = Character.getNumericValue(cb);
            return a1 - b1;
        }
        else return 0;
    }

    private int UfoElementCompare(String a, String b) {
        if (b.charAt(0) == 's' || b.charAt(0) == 'r')
            return 1;
        else if (b.charAt(0) != 'u')
            return -1;
        else if (b.charAt(0) == 'u') {
            char ca = a.charAt(1);
            char cb = b.charAt(1);
            int a1 = Character.getNumericValue(ca);
            int b1 = Character.getNumericValue(cb);
            return a1 - b1;
        }
        else return 0;
    }

    private int TeleportElementCompare(String a, String b) {
        if (b.equals("_sun") || b.equals("_asteroidfield"))
            return -1;
        else if (b.charAt(0) != 't')
            return 1;
        else if (b.charAt(0) == 't') {
            char ca = a.charAt(1);
            char cb = b.charAt(1);
            int a1 = Character.getNumericValue(ca);
            int b1 = Character.getNumericValue(cb);
            return a1 - b1;
        }
        else return 0;
    }

    private int SunCompare(String a, String b) {
        if (b.equals("_asteroidfield"))
            return -1;
        else
            return 1;
    }

    private int AsteroidfieldCompare(String a, String b) {
        return 1;
    }
}
