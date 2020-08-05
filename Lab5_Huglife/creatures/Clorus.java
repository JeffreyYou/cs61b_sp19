package creatures;


import huglife.Action;
import huglife.Creature;
import huglife.Direction;
import huglife.Occupant;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.Set;
import static huglife.HugLifeUtils.*;


public class Clorus extends Creature {

    private int r;
    private int g;
    private int b;

    // creates plip with energy equal to E.
    public Clorus(double e) {
        super("clorus");
        r = 34;
        g = 0;
        b = 231;
        energy = e;
    }

    // creates a Clorus with energy equal to 1.
    public Clorus() {
        this(1);
    }

    //return the color of Clorus
    public Color color() {
        r = 34;
        g = 0;
        b = 231;
        return color(r, g, b);
    }

    public String name() {
        return super.name();
    }

    public void attack(Creature c) {
        energy += c.energy();

    }

    // lose 0.03 units of energy
    public void move() {
        // TODO
        energy -= 0.03;
        //ATTENTION!!
        // NEVER set energy = 0 ,since the requestAction Method in Grid.java make it Die depending on whether the energy is below zero

        /*if (energy <= 0) {
            energy = 0;
        }*/

    }

    // lose 0.01 units of energy
    public void stay() {
        // TODO
        energy -= 0.01;
        //ATTENTION!!
        // NEVER set energy = 0 ,since the requestAction Method in Grid.java make it Die depending on whether the energy is below zero
        /*if (energy <= 0) {
            energy = 0;
        }*/
    }

    //repilicate
    public Clorus replicate() {
        energy = energy * 0.5;
        return new Clorus(energy);
    }

    /*  1.If there are no empty squares, the Clorus will STAY (even if there are Plips nearby they could attack since plip squares do not count as empty squares).
        2.Otherwise, if any Plips are seen, the Clorus will ATTACK one of them randomly.
        3.Otherwise, if the Clorus has energy greater than or equal to one, it will REPLICATE to a random empty square.
        4.Otherwise, the Clorus will MOVE to a random empty square*/
    public Action chooseAction(Map<Direction, Occupant> neighbors) {

        Deque<Direction> emptyDeque = new ArrayDeque<>();
        Deque<Direction> plipDeque = new ArrayDeque<>();
        //Traverse the neighbors
        for (Map.Entry<Direction,Occupant> myEntry : neighbors.entrySet()){
            Direction dir = myEntry.getKey();
            Occupant occ = myEntry.getValue();

            if (occ.name().equals("empty")){
                emptyDeque.addLast(dir);
            }
            if (occ.name().equals("plip")){
                plipDeque.addLast(dir);
            }
        }

        //Rule 1 : If there are no empty squares, the Clorus will STAY
        if (emptyDeque.size() == 0){
            return new Action(Action.ActionType.STAY);
        }
        //Rule 2 : if any Plips are seen, the Clorus will ATTACK one of them randomly
        else if ( plipDeque.size() >= 1){
            return new Action(Action.ActionType.ATTACK, randomEntry(plipDeque));
        }
        //Rule 3 : if the Clorus has energy greater than or equal to one, it will REPLICATE to a random empty square
        else if (energy >= 1){
            return new Action(Action.ActionType.REPLICATE,randomEntry(emptyDeque));
        }
        //Rule 4 : the Clorus will MOVE to a random empty square
        else {
            return new Action(Action.ActionType.MOVE, randomEntry(emptyDeque));
        }



    }
}
