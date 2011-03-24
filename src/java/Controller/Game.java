/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Balicek;
import Model.Computer;
import Model.Player;
import java.io.Serializable;
import java.util.Random;
import javax.servlet.http.HttpServletResponse;

/**
 *  Obsluha hry se vsim vsudy
 *
 * @author saljack
 */
public class Game implements Serializable {

    private Player[] plrs;
    private Balicek balik = null;
    private int rozmery = 8;
    private String theme = "Default";
    private int firstCard = -1;
    private int secondCard = -1;
    private String turnURL = "Turn";
    private int actualPlr = 0;

    public Game() {
    }

    /*
     * Ulozit na zacatku do seassion
     *
     */
    public void setPlayers(Player[] plrs) {
        this.plrs = plrs;
        for (int i = 0; i < plrs.length; ++i) {
            System.err.println("Hrac" + i + ": " + plrs[i]);
        }
    }

    public Player[] getPlayers() {
        return plrs;
    }

    /**
     * Start new game
     */
    public void newGame(HttpServletResponse response) {
        //Set session if  cookies are disable
        turnURL = response.encodeURL("Turn");
        mixPackage();

        //Natavi balik pocitacum
        for (int i = 0; i < plrs.length; ++i) {
            if (plrs[i] != null) {
                if (!plrs[i].isHuman()) {
                    ((Computer) plrs[i]).setBalik(balik);
                    ((Computer) plrs[i]).setGame(this);
                }
            }
        }

    }

    /**
     * 
     * @param num rozmery NxN je to pouze N
     */
    public void setRozmery(int num) {
        rozmery = num;
    }

    public int getRozmery() {
        return rozmery;
    }

    private void mixPackage() {
        if (balik == null) {
            balik = new Balicek();
            balik.setTurnURL(turnURL);

        }
        balik.setPath(theme);
        balik.setVel(rozmery * rozmery);
        int[] karty = new int[rozmery * rozmery];
        int pul = karty.length / 2;

        for (int i = 0; i < pul; ++i) {
            karty[i] = i;
            karty[pul + i] = i;
        }

        Random rnd = new Random();
        for (int i = karty.length - 1; i > 0; --i) {
            int ran = rnd.nextInt(i);
            swap(karty, ran, i);
        }

        balik.newGame(karty);

    }

    private void swap(int[] pole, int x, int y) {
        int tmp = pole[x];
        pole[x] = pole[y];
        pole[y] = tmp;
    }

    public void setBalik(Balicek bal) {
        balik = bal;
    }

    public Balicek getBalicek() {
        return balik;
    }

    /**
     * 
     * @param path - name of dir in /img/XXXXXX
     */
    public void setTheme(String path) {
        theme = path;
    }

    public String getTheme() {
        return theme;
    }

    /**
     * USE ONLY FOR TURN SERVLET!!!!
     * @param position
     * @return 
     */
    public boolean turnCard(int position) {
        if (plrs[actualPlr].isHuman()) {
            return forceTurnCard(position);
        } else {
            turnBackCards();
        }
        return false;
    }
    
    public void computerTurnCard(int a, int b){
        forceTurnCard(a);
        forceTurnCard(b);
    }

    /**
     * Force turn card on position
     * @param position 
     */
    private boolean forceTurnCard(int position) {
        if (firstCard == -1) {
            balik.uncoverCard(position);
            firstCard = position;
        } else if (secondCard == -1) {
            if (position == firstCard) { //Kontrola aby se neposlala dvakrat stejna karta
                return false;
            }
            balik.uncoverCard(position);
            secondCard = position;
            if (balik.checkCards(firstCard, secondCard)) { //Karty jsou stejne

                //Prida hraci body
                plrs[actualPlr].addPoints(10);
                System.out.println(plrs[actualPlr].getName() + " ma " + plrs[actualPlr].getPoints());
                
                //Napad udelat observe na balik
                balik.removeCards(firstCard, secondCard);
                if(balik.remainCards() <1){
                    System.out.println("KONEC");
                }
                firstCard = -1;
                secondCard = -1;
            } else {
                return true;
            }
        } else {
            turnBackCards();
        }
        return false;
    }

    /**
     * Pozor nastavuje dalsiho hrace !!!!!!
     */
    public void turnBackCards() {
        if (firstCard != -1 && secondCard != -1) {
            balik.turnBackCards(firstCard, secondCard);
            firstCard = -1;
            secondCard = -1;
            nextPlr();
        }
    }

    private void nextPlr() {
        actualPlr = (actualPlr + 1) % 4;
        while (plrs[actualPlr] == null) {
            actualPlr = (actualPlr + 1) % 4;
        }
        System.out.println("Hraje: " + plrs[actualPlr].getName());
        if (!plrs[actualPlr].isHuman()) {
            plrs[actualPlr].play();
        }
    }
}
