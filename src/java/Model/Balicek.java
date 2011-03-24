/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;

/**
 *
 * @author saljack
 */
public class Balicek implements Serializable {

    private int vel = 64;
    private Card[] cards;
    private String path;
    private String turnURL = "Turn";
    private int remains = 64;

    public String getTurnURL() {
        return turnURL;
    }

    public void setTurnURL(String turnURL) {
        this.turnURL = turnURL;
    }
    

    /**
     * Constructor
     */
    public Balicek() {
//        DBGStart();
    }
    
    public Balicek(String turnURL){
        this.turnURL = turnURL;
    }

    public String getPath() {
        return path;
    }

    public Card[] getCards() {
        return cards;
    }

    public void setCards(Card[] cards) {
        this.cards = cards;
    }

    public int getVel() {
        return vel;
    }

    public void setVel(int vel) {
        
        this.vel = vel;
        remains = vel;
    }

    private void DBGStart() {
        //DBG
        int[] bal = new int[64];
        for (int i = 0; i < 32; ++i) {
            bal[i] = i;
            bal[i + 32] = i;
        }
        setPath("/img/");
        newGame(bal);
        //END DBG
    }

    public void removeCards(int a, int b) {
        cards[a].removeState();
        cards[b].removeState();
        remains = remains -2 ;
    }
    
    public int remainCards(){
        return remains;
    }

    public void uncoverCard(int a) {
        cards[a].uncoverState();
    }

    /**
     * generate package of cards from field
     * @param karty
     */
    public void newGame(int[] karty) {
        setVel(karty.length);
        cards = new Card[karty.length];
        for (int i = 0; i < karty.length; ++i) {
            cards[i] = new Card(karty[i], i);
        }
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void turnBackCards(int a, int b) {
        cards[a].normalState();
        cards[b].normalState();
    }

    /**
     * Return table with card
     * @return
     */
    public String toHTML() {
        
        
        int sqr = (int) Math.sqrt(vel);
        int tmp = sqr - 1;
        String output = "<table><tr>";
        for (int i = 0; i < cards.length; ++i) {
            if (tmp > 0) {
                output += "<td>" + cards[i].toHTML(path, turnURL) + "</td>";
                --tmp;
            } else {
                output += "<td>" + cards[i].toHTML(path, turnURL) + "</td></tr>\n<tr>";
                tmp = sqr - 1;
            }
        }
        output += "</tr></table>";
        return output;
    }

    /**
     * if cards are same return true else false
     * @param firstCard
     * @param secondCard
     * @return 
     */
    public boolean checkCards(int firstCard, int secondCard) {
        if (cards[firstCard].getNum() == cards[secondCard].getNum()) {
            return true;
        }
        return false;
    }
    
//    public void setHTML(String HTML){
//        
//    }
//    
    public String getHTML(){
        return toHTML();
    }

}
