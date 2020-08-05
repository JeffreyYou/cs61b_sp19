package creatures;

import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;

import java.awt.Color;
import java.util.*;

import static huglife.HugLifeUtils.*;
/**
 * An implementation of a motile pacifist photosynthesizer.
 *
 * @author Josh Hug
 */
public class Plip extends Creature {

    /**
     * red color.
     */
    private int r;
    /**
     * green color.
     */
    private int g;
    /**
     * blue color.
     */
    private int b;

    /**
     * creates plip with energy equal to E.
     */
    public Plip(double e) {
        super("plip");
        r = 0;
        g = 0;
        b = 0;
        if (e<0){
            energy = 0;
        }else if (e > 2){
            energy = 2;
        }else energy = e;
    }

    public String name() {
        return super.name();
    }
    /**
     * creates a plip with energy equal to 1.
     */
    public Plip() {
        this(1);
    }

    /**
     * Should return a color with red = 99, blue = 76, and green that varies
     * linearly based on the energy of the Plip. If the plip has zero energy,
     * it should have a green value of 63. If it has max energy, it should
     * have a green value of 255. The green value should vary with energy
     * linearly in between these two extremes. It's not absolutely vital
     * that you get this exactly correct.
     */
    public Color color() {
        g = 63 + (int)(96*energy);
        r = 99;
        b = 76;
        return color(r, g, b);

    }

    /**
     * Do nothing with C, Plips are pacifists.
     */
    public void attack(Creature c) {
        // do nothing.
    }

    /**
     * Plips should lose 0.15 units of energy when moving. If you want to
     * to avoid the magic number warning, you'll need to make a
     * private static final variable. This is not required for this lab.
     */
    public void move() {
        // TODO
        if (energy>0.15){
            energy -= 0.15;
        }else energy = 0;
    }


    /**
     * Plips gain 0.2 energy when staying due to photosynthesis.
     */
    public void stay() {
        // TODO
        if (energy <=1.8){
            energy += 0.2;
        }else energy = 2;
    }

    /**
     * Plips and their offspring each get 50% of the energy, with none
     * lost to the process. Now that's efficiency! Returns a baby
     * Plip.
     */
    public Plip replicate() {
        this.energy = energy*0.5;
        return new Plip(this.energy);
    }

    /**
     * Plips take exactly the following actions based on NEIGHBORS:
     * 1. If no empty adjacent spaces, STAY.
     * 2. Otherwise, if energy >= 1, REPLICATE towards an empty direction
     * chosen at random.
     * 3. Otherwise, if any Cloruses, MOVE with 50% probability,
     * towards an empty direction chosen at random.
     * 4. Otherwise, if nothing else, STAY
     * <p>
     * Returns an object of type Action. See Action.java for the
     * scoop on how Actions work. See SampleCreature.chooseAction()
     * for an example to follow.
     */
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        // Rule 1
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        boolean anyClorus = false;
        // TODO
        // (Google: Enhanced for-loop over keys of NEIGHBORS?)
        // for () {...}


        //dequeEmpty is used to store the key of empty
        Deque dequeEmpty = new ArrayDeque();
        // mySet is the Set view of the mappings contained in this map.
        Set<Map.Entry<Direction,Occupant>> mySet = neighbors.entrySet();
        // using the iterator() Method to creat my own iterator mySetIterator
        Iterator<Map.Entry<Direction,Occupant>> mySetIterator= mySet.iterator();
        //Traverse the neighbors
        while (mySetIterator.hasNext()){
            Map.Entry<Direction,Occupant> setTemp = mySetIterator.next();
            Direction dir = setTemp.getKey();
            Occupant occ = setTemp.getValue();

            if (occ.name().equals("empty")){
                dequeEmpty.addLast(dir);
            }
            if (occ.name().equals("clorus")){
                anyClorus = true;
            }
        }

        //Rule 1 : If there are no empty spaces, the Plip should STAY.
        if (dequeEmpty.size() == 0){
            return new Action(Action.ActionType.STAY);
        }
        //Rule 2 : if the Plip has energy greater than or equal to 1.0, it should replicate to an available space.
        // HINT: randomEntry(emptyNeighbors)
        else if (energy >= 1){
            return new Action(Action.ActionType.REPLICATE, randomEntry(dequeEmpty));
        }
        //Rule 3 : run away
        else if (anyClorus && Math.random()>0.5){
            return new Action(Action.ActionType.MOVE, randomEntry(dequeEmpty));
        }
        //Rule 4 Stay
        else return new Action(Action.ActionType.STAY);






    }
}
