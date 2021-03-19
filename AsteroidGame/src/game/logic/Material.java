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
    public void Deploy(Asteroid a){
        System.out.println("\t" + name + ": Deploy(a)");
        if(a.AddMaterial(this)) {
                ///coming soon
        }
    }
    /*
    absztrakt interact, mindegyik osztaly overriderolja
     */
    public void Interact(Asteroid a){
        System.out.println("\t" + name + ": Interact(a)");
    }
    /*
    Egy masik anyaggal hasonlitja ossze magat
    @return Boolea - osszehasonlitas eredmenye
     */
    public Boolean CompareMaterial(Material m){
        if(m.name.compareTo(name) == 0) {
            System.out.println("\t" + name + ": Comparematerial(" + m.name +") return: true");
            return true;
        }
        System.out.println("\t" + name + ": Comparematerial(" + m.name +") return: false");c
        return false;
    }
    /*
    Jelez a conditionmanagernek hogy kikerult a jatekbol
     */
    public void Disintegrate(){
        System.out.println("\t" + name + ": Disintegrate");
    }
}