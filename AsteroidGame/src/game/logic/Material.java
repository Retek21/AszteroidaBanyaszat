package game.logic;

public abstract class Material implements Placeable{
   protected String name;
   protected Inventory i;
   protected ConditionManager cm;

    @Override
    public void Deploy(Asteroid a){
        System.out.println("\t" + name + ": Deploy(a)");
        if(a.AddMaterial(this)){
            i.RemoveMaterial(this);
        }
    }
    public void Interact(Asteroid a){
        System.out.println("\t" + name + ": Interact(a)");
    }
    public Boolean CompareMaterial(Material m){
        if(m.name.compareTo(name) == 0) {
            System.out.println("\t" + name + ": Comparematerial(" + m.name +") return: true");
            return true;
        }
        System.out.println("\t" + name + ": Comparematerial(" + m.name +") return: false");c
        return false;
    }
    public void Disintegrate(){
        System.out.println("\t" + name + ": Disintegrate");
    }
}
