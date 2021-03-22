package game.logic;
/*
Absztrakt Material class
 */
public abstract class Material implements Placeable{
    /*
    @String name - Material neve
    */
    protected String name;


   /*
   A nyersanyag lekerül egy aszteroidára. A függvény true-val tér vissza, ha sikeres volt
   a műveletem, false-szal, ha nem.
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
    A metódus valósítja meg, hogy mi történik a nyersanyaggal, ha nap éri.
    A leszármazottak felüldefiniálhatják egyéni viselkedést megvalósítva.
     */
    public void Interact(Asteroid a){
        Skeleton.WriteName( name + ": Interact(a)");
    }

    /*
    A nyersanyag összehasonlítja magát egy másik nyersanyaggal. Egyezés esetén true-val, egyébként
    false-szal tér vissza.
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
    Jelez a controllernek, hogy kikerült a játékból (Megj.: A controller a skeletonban még nincs megvalósítva
    szóval a metódus nem csinál semmit a neve kiírásán kívül.)
     */
    public void Disintegrate(){
        Skeleton.WriteName( name + ": Disintegrate()");
    }


}
