package imageProcessing;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageResizer {
	
	public static void resize(BufferedImage image, String outputImagePath, int scaledWidth, int scaledHeight)
            throws IOException {
		
		System.out.println("Original image was scaled.");
 
        // creates output image
        BufferedImage outputImage = new BufferedImage(scaledWidth, scaledHeight, image.getType());
 
        // scales the input image to the output image
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(image, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();
 
        // extracts extension of output file
        String formatName = outputImagePath.substring(outputImagePath.lastIndexOf(".") + 1);
 
        // writes to output file
        ImageIO.write(outputImage, formatName, new File(outputImagePath));
    }

}
