/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author saljack
 */
public class ComputerV1 extends Computer implements Serializable {

    @Override
    public void play() {
        //TODO udelat pristup k baliku
        Random rnd = new Random();
        int nah = rnd.nextInt(balik.getVel());
        Card[] cards = balik.getCards();
        while (cards[nah].getState() != 0) {
            nah = (nah + 1) % balik.getVel();
        }


        int nah2 = rnd.nextInt(balik.getVel());
        while (cards[nah].getState() != 0 && nah != nah2) {
            nah = (nah + 1) % balik.getVel();
        }
        gm.computerTurnCard(nah, nah2);

    }
}
