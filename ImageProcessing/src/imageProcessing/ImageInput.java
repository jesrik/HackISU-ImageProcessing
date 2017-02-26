package imageProcessing;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageInput {
	
	private BufferedImage img;
	private int pixels[][];
	
	public ImageInput(String filename) throws IOException {
	try {
	    img = ImageIO.read(new File(filename));
	    createPixelArray(img.getWidth(), img.getHeight());
	} catch (IOException e) { throw new IOException("File not found."); }
	}
	
	public int[][] createPixelArray(int width, int height) {
		if(width == 0 && height == 0)
			return null;
		pixels = new int[width][height];
		for(int i = 0; i < width; i ++) {
			for(int j = 0; j < height; j++) {
				if(getColor(img, i, j) == 0)
					pixels[i][j] = 1;
				else
					pixels[i][j] = 0;
			}
		}
		System.out.println("Image dimensions: " + img.getWidth() + "x" + img.getHeight());
		return pixels;
	}
	
	public int[][] getPixelValueArray() { return pixels; }
	
	public BufferedImage getImage() { return img; }
	
	public int getColor(BufferedImage image, int width, int height) {
		int[] colorCode = new int[3];
		Color pixelColor = new Color(img.getRGB(width, height));
		int colorSum = 0;
		colorCode[0] = pixelColor.getRed();
		colorCode[1] = pixelColor.getGreen();
		colorCode[2] = pixelColor.getBlue();
		for(int i = 0; i < colorCode.length; i++) {
			colorSum += colorCode[i];
			if(i == colorCode.length-1)
				if(colorSum == 0 || (colorSum > 0 && colorSum < 340)) {
					return 0;
				}
		}
		return -1;
	}

	public void printImageArr() {
		for(int y = 0; y < img.getHeight(); y++) { // height
			System.out.println();
			for(int x = 0; x < img.getWidth(); x++) { // width
				System.out.print(pixels[x][y]);
			}
		}	
	}
}
