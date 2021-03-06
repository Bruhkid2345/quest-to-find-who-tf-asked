package net.questfor.thepersonwhoasked.Maingam;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UtilityTool {
    //scales the image, makes it much less resourceful making it much easier to render mass amounts of images
    public BufferedImage scaleimage(BufferedImage original, int width, int height){
        BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(original, 0, 0, width, height, null);
        g2.dispose();
        return scaledImage;
    }
}
