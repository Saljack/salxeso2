/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author saljack
 */
public class ImageCreator {

    final static String MIME = "jpg";

    public ImageCreator() {
    }

    /**
     *  WARNING file must be image (jpeg)
     * @param pathtoWEB - path to WEB directory
     * @return - path to directory with thema
     */
    public static String createImage(File file) {
        //COntroll jestli file je image
        try {
            BufferedImage main = ImageIO.read(file);

            if (main != null) {
                double ver = main.getHeight() / 6.0;
                double hor = main.getWidth() / 6.0;
                int num = 0;
                for (int rad = 0; rad < 6; ++rad) { //radky
                    for (int sloup = 0; sloup < 6; ++sloup) {
                        if (rad < 5 || sloup < 2) {
                            File subfile = new File(file.getParent() + "/" + num + "." + MIME);
                            subfile.createNewFile();
                            BufferedImage subimage = null;

                            subimage = main.getSubimage((int) (sloup * hor), (int) (rad * ver), (int) hor, (int) ver);

                            ImageIO.write(subimage, MIME, subfile);
                        } else if (rad == 5) { //posledni radek
                            if (sloup == 2) { //cover
                                File subfile = new File(file.getParent() + "/cover." + MIME);
                                subfile.createNewFile();
                                BufferedImage subimage = main.getSubimage((int) (sloup * hor), (int) (rad * ver), (int) hor, (int) ver);
                                ImageIO.write(subimage, MIME, subfile);
                            } else {//about
                                File subfile = new File(file.getParent() + "/about." + MIME);
                                subfile.createNewFile();
                                BufferedImage subimage = main.getSubimage((int) (4 * hor), (int) (5 * ver), (int) (2 * hor), (int) ver);
                                ImageIO.write(subimage, MIME, subfile);
                                break;
                            }
                        }
                        ++num;
                    }
                }
            }else{ //file is not jpeg
               
                return null;
            }
        } catch (IOException ioex) {
            System.err.println(ioex.toString());
            return null;
        }

        return file.getParent();
    }
}
