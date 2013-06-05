import java.awt.Dimension;
import java.awt.image.BufferedImage;

//UIUC CS125 SPRING 2013 MP. File: PixelEffects.java, CS125 Project: Challenge4-Photoscoop, Version: 2013-02-22T16:29:58-0600.413735000

/* A class to implement the various pixel effects.
 *
 * Todo: Put your netid (i.e. username) in the line below
 * 
 * @author svdesai2
 */
public class PixelEffects {

	/** Copies the source image to a new 2D integer image */
	public static int[][] copy(int[][] source) {
		// Create a NEW 2D integer array and copy the colors across
		// See redeye code below
		int trgtW=source.length;
		int trgtH=source[0].length;
		int[][] trgtPic= new int[trgtW][trgtH];
		
		for(int trgtX=0; trgtX< trgtW; trgtX++)	{
			for(int trgtY=0; trgtY< trgtH; trgtY++)	{
				trgtPic[trgtX][trgtY]=source[trgtX][trgtY];
				
			}
		}
		return trgtPic; // Fixed
	}
	/**
	 * Resize the array image to the new width and height
	 * You are going to need to figure out how to map between a pixel
	 * in the destination image to a pixel in the source image
	 * @param source
	 * @param newWidth
	 * @param newHeight
	 * @return
	 */
	public static int[][] resize(int[][] source, int newWidth, int newHeight) {
		int srcW=source.length;
		int srcH=source[0].length;
		//double shrinkFactorSquaredHeight= newHeight/srcH;
		//double shrinkFactorSquaredWidth= newWidth/srcW;
		int[][] trgtPic= new int[newWidth][newHeight];
		
		for(int trgtX=0; trgtX< newWidth; trgtX++)	{
			for(int trgtY=0; trgtY< newHeight; trgtY++)	{
				int srcY=(int) ((trgtY/(double) newHeight)*srcH);//Math.sqrt(shrinkFactorSquaredHeight));
				int srcX=(int) ((trgtX/(double) newWidth)*srcW);//Math.sqrt(shrinkFactorSquaredWidth)));
				trgtPic[trgtX][trgtY]=source[srcX][srcY];
				
			}
		}
		return trgtPic; // Fix Me
		// Hints: Use two nested for loops between 0... newWidth-1 and 0.. newHeight-1 inclusive.
		// Hint: You can just use relative proportion to calculate the x (or y coordinate)  in the original image.
		// For example if you're setting a pixel halfway across the image, you should be reading half way across the original image too.
	}

	/**
	 * Half the size of the image. This method should be just one line! Just
	 * delegate the work to resize()!
	 */
	public static int[][] half(int[][] source) {
		return resize(source,(int) Math.floor(source.length*0.5),(int) Math.floor(source[0].length*0.5)); // Fix Me
	}
	
	/**
	 * Create a new image array that is the same dimesions of the reference
	 * array. The array may be larger or smaller than the source. Hint -
	 * this methods should be just one line - delegate the work to resize()!
	 * 
	 * @param source
	 *            the source image
	 * @param reference
	 * @return the resized image
	 */
	public static int[][] resize(int[][] source, int[][] reference) {
		return resize(source, reference.length, reference[0].length); // Fix Me
	}

	/** Flip the image vertically */
	public static int[][] flip(int[][] source) {
		int[][] flippedPic= new int[source.length][source[0].length];
		for (int tgtX=0; tgtX<flippedPic.length; tgtX++)	{
			for (int tgtY=0; tgtY< flippedPic[0].length; tgtY++)	{
				flippedPic[tgtX][tgtY]=source[tgtX][(flippedPic[0].length-1-tgtY)]; //Y
				//flippedPic[tgtX][tgtY]=source[(flippedPic.length-tgtX)][tgtY]; //X
			}
		}
		return flippedPic;// Fixed
	}

	/** Reverse the image horizontally */
	public static int[][] mirror(int[][] source) {
		int[][] flippedPic= new int[source.length][source[0].length];
		for (int tgtX=0; tgtX<flippedPic.length; tgtX++)	{
			for (int tgtY=0; tgtY< flippedPic[0].length; tgtY++)	{
				//flippedPic[tgtX][tgtY]=source[tgtX][(flippedPic[0].length-tgtY)]; //Y
				flippedPic[tgtX][tgtY]=source[(flippedPic.length-1-tgtX)][tgtY]; //X
			}
		}
		return flippedPic;// Fix Me
	}

	/** Rotate the image */
	public static int[][] rotateLeft(int[][] source) {
		int[][] flippedPic= new int[source[0].length][source.length];
		for (int tgtX=0; tgtX<flippedPic.length; tgtX++)	{
			for (int tgtY=0; tgtY< flippedPic[0].length; tgtY++)	{
				//flippedPic[tgtX][tgtY]=source[tgtX][(flippedPic[0].length-tgtY)]; //Y
				//flippedPic[tgtY][tgtX]=source[tgtX][tgtY]; //
				
				int srcX=flippedPic[0].length-1-tgtY;
			    int srcY=tgtX;
			    flippedPic[tgtX][tgtY]=source[srcX][srcY];	    
			}
		}
		return flippedPic;// Fix Me
	}
	
	/** Merge the red,blue,green components from two images */
	public static int[][] merge(int[][] sourceA, int[][] sourceB) {
		// The output should be the same size as the input. Scale (x,y) values
		// when reading the background
		// (e.g. so the far right pixel of the source is merged with the
		// far-right pixel of the background).
		sourceB=resize(sourceB, sourceA.length, sourceA[0].length);
		int[][] newPic= new int[sourceA.length][sourceA[0].length];
		for(int bX=0; bX<sourceB.length; bX++){
			for(int bY=0; bY<sourceB[0].length; bY++){
				int pixAR=RGBUtilities.toRed(sourceA[bX][bY]);
				int pixAG=RGBUtilities.toGreen(sourceA[bX][bY]);
				int pixAB=RGBUtilities.toBlue(sourceA[bX][bY]);
				
				int pixBR=RGBUtilities.toRed(sourceB[bX][bY]);
				int pixBG=RGBUtilities.toGreen(sourceB[bX][bY]);
				int pixBB=RGBUtilities.toBlue(sourceB[bX][bY]);
				int newPixR=(int)(((1.5*pixAR)+(0.5*pixBR))/2);
				int newPixG=(int)(((1.5*pixAG)+(0.5*pixBG))/2);
				int newPixB=(int)(((1.5*pixAB)+(0.5*pixBB))/2);
				
				newPic[bX][bY]= RGBUtilities.toRGB(newPixR, newPixG, newPixB);
				
				
			}
		}
		
		return newPic;
	}

	/**
	 * Replace the green areas of the foreground image with parts of the back
	 * image
	 */
	public static int[][] chromaKey(int[][] foreImage, int[][] backImage) {
		// If the image has a different size than the background use the
		// resize() method
		// create an image the same as the background size.
		
		backImage=resize(backImage, foreImage.length, foreImage[0].length);
		int[][] newPic= new int[foreImage.length][foreImage[0].length];
		for(int foreX=0; foreX<foreImage.length; foreX++){
			for(int foreY=0; foreY<foreImage[0].length; foreY++){
				int foreRed=RGBUtilities.toRed(foreImage[foreX][foreY]);
				int foreGreen=RGBUtilities.toGreen(foreImage[foreX][foreY]);
				int foreBlue=RGBUtilities.toBlue(foreImage[foreX][foreY]);
				if ((foreGreen>foreRed) && (foreGreen>foreBlue))	{
					newPic[foreX][foreY]=backImage[foreX][foreY];
				}
				else	{
					newPic[foreX][foreY]=foreImage[foreX][foreY];
				}
			}
		}
		
		return newPic;
	}

	/** Removes "redeye" caused by a camera flash. sourceB is not used */
	public static int[][] redeye(int[][] source, int[][] sourceB) {
//	  public static int[][] redeye(int[][] source) {
		int width = source.length, height = source[0].length;
		int[][] result = new int[width][height];
		for (int i = 0; i < width; i++)	{
			for (int j = 0; j < height; j++) {
				int rgb = source[i][j];
				int red = RGBUtilities.toRed(rgb);
				int green = RGBUtilities.toGreen(rgb);
				int blue = RGBUtilities.toBlue(rgb);
				float redIntensity= (float) red/((blue+green)/2);
//				if (red > 4 * Math.max(green, blue) && red > 64)
//					red= 0;
				if (redIntensity> 1.5f)	{
					red=(green+blue)/2;
				}
				result[i][j] = RGBUtilities.toRGB(red, green, blue);
			}
		}

		return result;
	}

	/* Upto you! do something fun to the image */
	public static int[][] funky(int[][] source, int[][] sourceB) {
//	public static int[][] funky(int[][] source) {
		// You need to invent your own image effect
		// Minimum boring requirements to pass autograder:
		
		// Does not ask for any user input and returns a new 2D array
		// Todo: remove this return null
//		sourceB=resize(sourceB, source.length, source[0].length);
		sourceB=resize(sourceB,1920,1080);
		return psychedelicCalc(sourceB);
	}


	public static int[][] psychedelicCalc(int[][] source)	{
		int[][] newPic=new int[source.length][source[0].length];
		for(int x=0; x<source.length; x++)	{
			for(int Y=source[0].length-1; Y>=0; Y--)	{					
				int y=source[0].length-1-Y;
				//ENTER MAIN FUNCTION BELOW
				int f_xy=(int)  Math.floor(2*Math.tan(x^2+y^2)+0.5*Math.tan((x^2+y^2))); //Try Messing around with Math.tan  :)
				//ENTER MAIN FUNCTION ABOVE
//				int r=f_xy; //To Black and White
//				int g=f_xy; //To Black And White
				int r=(int)Math.ceil(map(x,0,newPic.length,0,255));
				int g=(int)Math.ceil(map(y,0,newPic[0].length,0,255));
				int b=f_xy;
				if(r>255) r=255;
				if(g>255) g=255;
				if(b>255) b=255;
				int pixel=RGBUtilities.toRGB(r,g,b);
				newPic[x][Y]=pixel;
			}
		}
		return newPic;
}
		
	public static double map(int x, int in_min, int in_max, int out_min, int out_max)	{
		return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
	}
	
}
