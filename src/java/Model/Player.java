/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

/**
 *
 * @author saljack
 */
public interface Player {

    public void addPoints(int point);
    public int getPoints();
    public void setPoints(int num);
    public void resetPoints();
    public boolean isHuman();
    public void setName(String name);
    public String getName();
    public void play();
    public void setActual(boolean actual);
    public String toHTML();
}
