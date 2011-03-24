/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author saljack
 */
public class Card implements Serializable{
    /**
     * Card value
     */
    private int num;
    private int position;
    private int state = 0;

    public void uncoverState(){
        state = 1;
    }
    public void removeState(){
        state = -1;
    }

    public void normalState(){
        state = 0;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
    
    /**
     * 
     * @return // -1 - removed 0-normal 1 - uncover
     */
    public int getState(){
        return state;
    }

    public Card() {
    }
    /**
     * 
     * @param num - value of card
     * @param pos - position 0-64;
     */
    public Card(int num, int pos){
        this.num = num;
        this.position = pos;

    }

    @Override
    public String toString() {
        return super.toString();
    }

    //Encode URL 
    public String toHTML(String path, String url){

        switch(state){
            case 0:
                return "<a href=\""+url+"?card="+position+"\"><img src=\"img/"+path+"/cover.jpg\" alt=\""+num+"\"></a>";           
            case 1:
                return "<img src=\"img/"+path+"/"+num+".jpg\" alt=\""+num+"\">";
        }
        return "";
    }



}
