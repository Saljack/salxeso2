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
public class Human implements Player, Serializable{
    private int points = 0;
    private String name = "plr";
    private boolean actual = false;
    
    @Override
    public void addPoints(int point) {
        points+=point;
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
        return true;
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
        //DO NOTHING
    }

    @Override
    public void setActual(boolean actual) {
        this.actual = actual;
    }

    @Override
    public String toHTML() {
        String output = "";
        if(actual){
            output += "<div class=\"actual\">";
        }else{
            output += "<div class=\"wait\">";
        }
        
        //TODO dodelat zobrazeni
        
        output += "</div>";
        return output;
    }

}
