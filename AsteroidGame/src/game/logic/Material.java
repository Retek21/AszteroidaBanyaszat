package game.logic;
/*
Absztrakt Material class
 */
public abstract class Material {
    /*
    @String name - Material neve
    */
    protected String name;

    /*
    A metódus valósítja meg, hogy mi történik a nyersanyaggal, ha nap éri.
    A leszármazottak felüldefiniálhatják egyéni viselkedést megvalósítva.
     */
    public void Interact(Asteroid a){

    }

    /*
    A nyersanyag összehasonlítja magát egy másik nyersanyaggal. Egyezés esetén true-val, egyébként
    false-szal tér vissza.
     */
    public Boolean CompareMaterial(Material m){
        if(m.name.compareTo(name) == 0) {
            return true;
        }
        return false;
    }

    /*
    Jelez a controllernek, hogy kikerült a játékból (Megj.: A controller a skeletonban még nincs megvalósítva
    szóval a metódus nem csinál semmit a neve kiírásán kívül.)
     */
    public void Disintegrate(){

    }


}
