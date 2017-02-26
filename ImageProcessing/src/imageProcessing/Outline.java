package imageProcessing;

import java.awt.Point;
import java.util.ArrayList;

public class Outline {
	
	private ImageInput image;
	private ArrayList<Point> points = new ArrayList<Point>();
	private int[][] pixelsCopy;
	private int width;
	private int height;
	
	public Outline(ImageInput image) {
		this.image = image;
		width = image.getImage().getWidth();
		height = image.getImage().getHeight();
		pixelsCopy = new int[image.getImage().getWidth()][image.getImage().getHeight()];
		for(int i = 0; i < pixelsCopy.length; i++)
			for(int j = 0; j < pixelsCopy[0].length; j++)
				pixelsCopy[i][j] = 0;
		System.out.println();
		System.out.println("Populating Point array list...");
		createTopOutline();
		createRightOutline();
		createBottomOutline();
		createLeftOutline();
		cleanUpList(points);
	}
	
	public int getWidth() { return width; }
	
	public int getHeight() { return height; }
	
	public void createTopOutline() {
		System.out.println("TOP plotting points: ");
			for(int x = 0; x < image.getImage().getWidth(); x++) {
				for(int y = 0; y < image.getImage().getHeight(); y++) {
				if(image.getPixelValueArray()[x][y] == 1) {
					Point p = new Point(x, y);
					points.add(p);
					System.out.println("Point (" + p.getX() + "," + p.getY() + ")");
					pixelsCopy[x][y] = 1;
					break;
				}
			}
		}
	}
	
	public void createBottomOutline() {
		System.out.println();
		System.out.println("BOTTOM plotting points: ");
		for(int x = image.getImage().getWidth()-1; x > 0; x--) {
			for(int y = image.getImage().getHeight()-1; y > -1; y--) {
				if(image.getPixelValueArray()[x][y] == 1) {
					Point p = new Point(x, y);
					points.add(p);
					System.out.println("Point (" + p.getX() + "," + p.getY() + ")");
					pixelsCopy[x][y] = 1;
					break;
				}
			}
		}
	}
	
	public void createLeftOutline() {
		System.out.println();
		System.out.println("LEFT plotting points: ");
		for(int y = image.getImage().getHeight()-1; y > 0; y--) {
			for(int x = 0; x < image.getImage().getWidth(); x++) {
				if(image.getPixelValueArray()[x][y] == 1) {
					Point p = new Point(x, y);
					points.add(p);
					System.out.println("Point (" + p.getX() + "," + p.getY() + ")");
					pixelsCopy[x][y] = 1;
					break;
				}
			}
		}
	}
	
	public void createRightOutline() {
		System.out.println();
		System.out.println("RIGHT plotting points: ");
		for(int y = 0; y < image.getImage().getHeight(); y++) {
			for(int x = image.getImage().getWidth()-1; x > -1; x--) {
				if(image.getPixelValueArray()[x][y] == 1) {
					Point p = new Point(x, y);
					points.add(p);
					System.out.println("Point (" + p.getX() + "," + p.getY() + ")");
					pixelsCopy[x][y] = 1;
					break;
				}
			}
		}
	}
	
	public void printPictureOnConsole() {
		for(int i = 0; i <  image.getImage().getHeight(); i++) {
			System.out.println();
			for(int j = 0; j <  image.getImage().getWidth(); j++) {
				if(image.getPixelValueArray()[j][i] == 0)
					System.out.print(" ");
				else
					System.out.print(image.getPixelValueArray()[j][i]);
			}
		}
	}
	
	public void printOutlineOnConsole() {
		System.out.println();
		System.out.println("Processed image on 2D plotting plane:");
		for(int j = 0; j < image.getImage().getHeight(); j++) {
			System.out.println();
			for(int i = 0; i < image.getImage().getWidth(); i++) {
				if(pixelsCopy[i][j] == 0)
					System.out.print(" ");
				else
					System.out.print(pixelsCopy[i][j]);
			}
		}
		System.out.println();
	}
	
	public int[][] getOutlineArray() { return pixelsCopy; }

	public int getNumberOfPoints() { 
		int numberOfPoints = 0;
		for(int j = 0; j < image.getImage().getHeight(); j++) {  // height
			for(int i = 0; i < image.getImage().getWidth(); i++) { // width
				if(pixelsCopy[i][j] == 1)
					numberOfPoints++;
			}
		}
		return numberOfPoints;
	}
	
	public ArrayList<Point> getPoints() { return points; }
	
	public void cleanUpList(ArrayList<Point> points) {
		int repeats = 0;
		for(int i = 0; i < points.size()-1; i++) {
			for(int j = i+1; j < points.size(); j++) {
				if(points.get(i).getX() == points.get(j).getX() && points.get(i).getY() == points.get(j).getY()) {
					points.remove(j);
					repeats++;
				}
			}
		}
		System.out.println();
		System.out.println("Number of point repetitions found and removed: " + repeats);
	}
	
}
