package creatures;

import huglife.*;
import org.junit.Test;

import java.awt.*;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestClorus {

    @Test
    public void testBasics(){
        Clorus p = new Clorus(2);
        assertEquals(2, p.energy(), 0.01);
        assertEquals(new Color(34, 0, 231), p.color());
        p.move();
        assertEquals(1.97, p.energy(), 0.01);
        p.move();
        assertEquals(1.94, p.energy(), 0.01);
        p.stay();
        assertEquals(1.93, p.energy(), 0.01);
        p.stay();
        assertEquals(1.92, p.energy(), 0.01);
    }

    @Test
    public void testReplicate(){
        Clorus p = new Clorus(2);
        Clorus offsrping = p.replicate();
        assertEquals(offsrping.energy(), p.energy(), 0.01);

    }

    @Test
    public void testAttack(){
        Plip p = new Plip(2);
        Clorus attacker = new Clorus(2);
        attacker.attack(p);
        double expect = 4;
        assertEquals(expect, attacker.energy(), 0.01);

    }

    @Test
    public void testChoose() {

        // No empty adjacent spaces; stay.
        Clorus p1 = new Clorus(1.2);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());

        Action actual = p1.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);

        //Attack adjacent Plip
        Clorus p2 = new Clorus(1.2);
        HashMap<Direction, Occupant> plipAround = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Plip(0.5));
        surrounded.put(Direction.RIGHT, new Empty());

        actual = p2.chooseAction(surrounded);
        expected = new Action(Action.ActionType.ATTACK,Direction.LEFT);

        assertEquals(expected, actual);

        // Energy >= 1; No adjacent Plip,replicate towards an empty space.
        Clorus p3 = new Clorus(1.2);
        HashMap<Direction, Occupant> noPlipAround = new HashMap<Direction, Occupant>();
        noPlipAround.put(Direction.TOP, new Empty());
        noPlipAround.put(Direction.BOTTOM, new Impassible());
        noPlipAround.put(Direction.LEFT, new Impassible());
        noPlipAround.put(Direction.RIGHT, new Impassible());

        actual = p3.chooseAction(noPlipAround);
        expected = new Action(Action.ActionType.REPLICATE, Direction.TOP);

        assertEquals(expected, actual);

        //Move random
        Clorus p4 = new Clorus(0.9);
        HashMap<Direction, Occupant> topEmpty = new HashMap<Direction, Occupant>();
        topEmpty.put(Direction.TOP, new Empty());
        topEmpty.put(Direction.BOTTOM, new Impassible());
        topEmpty.put(Direction.LEFT, new Impassible());
        topEmpty.put(Direction.RIGHT, new Impassible());

        actual = p4.chooseAction(topEmpty);
        expected = new Action(Action.ActionType.MOVE, Direction.TOP);

        assertEquals(expected, actual);
    }
}

