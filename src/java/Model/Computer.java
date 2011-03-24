/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.Game;
import java.io.Serializable;

/**
 *
 * @author saljack
 */
public abstract class Computer implements Player, Serializable {

    private int points = 0;
    private boolean actual = false;
    private String name = "computer";
    protected Balicek balik = null;
    protected Game gm = null;

    public void setGame(Game gm) {
        this.gm = gm;
    }

    public void setBalik(Balicek balik) {
        this.balik = balik;
    }

    @Override
    public void addPoints(int point) {
        points += point;
    }
    
    
    @Override
    public int getPoints() {
        return points;
    }

    @Override
    public void setPoints(int num) {
        points =num;
    }

    @Override
    public void resetPoints() {
        points = 0;
    }

    @Override
    public boolean isHuman() {
        return false;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void play() {
        //TODO 
    }

    @Override
    public void setActual(boolean actual) {
        this.actual = actual;
    }

    @Override
    public String toHTML() {
        String output = "";
        if (actual) {
            output += "<div class=\"actual\">";
        } else {
            output += "<div class=\"wait\">";
        }
        //TODO dodelat zobrazeni


        output += "</div>";
        return output;
    }
}
