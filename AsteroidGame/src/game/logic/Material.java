package game.logic;
/*
Absztrakt Material class
 */
public abstract class Material implements Placeable{
    /*
    @String name - Material neve
     //coming soon//@ConditionManager cm - referencia a ConditionManager osztalyra
    */
    protected String name;


   /*
   Deploy ezen meg kellene gondolkodni
    */
    @Override
    public boolean Deploy(Asteroid a){
        Skeleton.WriteName( name + ": Deploy(a)");
        Skeleton.tab++;
        if(a.AddMaterial(this)) {
            return true;
        }
        Skeleton.tab--;
        return false;
    }
    /*
    absztrakt interact, mindegyik osztaly overriderolja
     */
    public void Interact(Asteroid a){
        Skeleton.WriteName( name + ": Interact(a)");
    }

    /*
    Egy masik anyaggal hasonlitja ossze magat
    @return Boolean - osszehasonlitas eredmenye
     */
    public Boolean CompareMaterial(Material m){
        if(m.name.compareTo(name) == 0) {
            Skeleton.WriteName( name + ": Comparematerial(" + m.name +") return: true");
            return true;
        }
        Skeleton.WriteName( name + ": Comparematerial(" + m.name +") return: false");
        return false;
    }
    /*
    Jelez a controllernek, hogy kikerult a jatekbol
     */
    public void Disintegrate(){
        Skeleton.WriteName( name + ": Disintegrate()");
    }


}
