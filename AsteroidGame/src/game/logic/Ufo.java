package game.logic;

import game.controller.Controller;

/**
 * Ufo osztaly, az Entity leszarmazotta,
 * feladata az aszteroidak kozti mozgas, azok banyaszata.
 * @author Dengyel Bendeguz 2021.04.13.
 */
public class Ufo extends Entity {
    /**
     * Az ufo konstruktora beallitja  a parmeterul kapott controllert.
     */
    public Ufo(Controller c){
        super(c);
    }

    /**
     *Metodus, meghivja az Ufo::Die() metodusat.
     * Az os metodusanak felulirasa.
     */
    @Override
    public void BlowUp() {
        this.Die();
    }

    /**
     * Metodus, mely az aszteroida nyersanyagt banyassza ki.
     * Az aszteroida Asteroid::RemoveMaterial() metodussal
     * kibanyassza a nyersanyagot annak magjabol.
     * Sikeres banyaszat eseten (ha a nyersanyag nem null), meghivja
     * annak a Material::Disintegrate() metodusat.
     * A banyaszat sikeressegevel ter vissza.
     * @return a metodus sikeressege
     */
    public boolean Mine(){
        Material m=asteroid.RemoveMaterial();
        if(m==null)
            return false;
        else{
            m.Disintegrate();
            return true;
        }
    }

    /**
     * Metodus, mely az ufo halalkor hivodik.
     * Az os felulirasa. Az os metodusat meghivja, majd a
     * controller Controller::UfoDie(Ufo u) metodusat is (mely eltavolítja
     * a tovabbi fazisokbol).
     */
    @Override
    public void Die(){
        super.Die();
        c.UfoDie(this);
    }
}
