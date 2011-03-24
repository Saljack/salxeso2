/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import Model.Balicek;
import Model.Player;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author saljack
 */
public class GameTest {

    public GameTest() {
        
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of setPlayers method, of class Game.
     */
    @Test
    public void testSetPlayers() {
//        System.out.println("setPlayers");
//        Player[] plrs = null;
//        Game instance = new Game();
//        instance.setPlayers(plrs);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getPlayers method, of class Game.
     */
    @Test
    public void testGetPlayers() {
//        System.out.println("getPlayers");
//        Game instance = new Game();
//        Player[] expResult = null;
//        Player[] result = instance.getPlayers();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of newGame method, of class Game.
     */
    @Test
    public void testNewGame() {
        System.out.println("newGame");
        Game instance = new Game();
        instance.setRozmery(8);
        System.out.println(instance.getRozmery());

    }

    /**
     * Test of setRozmery method, of class Game.
     */
    @Test
    public void testSetRozmery() {
//        System.out.println("setRozmery");
//        int num = 0;
//        Game instance = new Game();
//        instance.setRozmery(num);
    }

    /**
     * Test of getRozmery method, of class Game.
     */
    @Test
    public void testGetRozmery() {
//        System.out.println("getRozmery");
//        Game instance = new Game();
//        int expResult = 0;
//        int result = instance.getRozmery();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setBalik method, of class Game.
     */
    @Test
    public void testSetBalik() {
//        System.out.println("setBalik");
//        Balicek bal = null;
//        Game instance = new Game();
//        instance.setBalik(bal);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getBalicek method, of class Game.
     */
    @Test
    public void testGetBalicek() {
//        System.out.println("getBalicek");
//        Game instance = new Game();
//        Balicek expResult = null;
//        Balicek result = instance.getBalicek();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

}