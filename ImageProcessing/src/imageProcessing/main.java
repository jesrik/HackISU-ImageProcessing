package imageProcessing;

import java.io.IOException;

public class main {

	public static void main (String[] args) throws IOException {
		
		ImageInput img = new ImageInput(args[0]);  // Open image from command line
		
		// Resize image
		ImageResizer.resize(img.getImage(), args[0].substring(0, args[0].length()-4) + "Scaled.jpg", 160, 90);  // 90x50 works well
		ImageInput imgScale = new ImageInput(args[0].substring(0, args[0].length()-4) + "Scaled.jpg");
		
		Outline outline = new Outline(imgScale);  // Produce outline of image
		
		outline.printOutlineOnConsole();
		outline.printPictureOnConsole();

	}

}
