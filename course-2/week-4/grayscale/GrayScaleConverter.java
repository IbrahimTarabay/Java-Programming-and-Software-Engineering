
/**
 * Write a description of GrayScaleConverter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;

public class GrayScaleConverter {
    //I started with the image I wanted (inImage)
    public ImageResource makeGray(ImageResource inImage) {
        //I made a blank image of the same size
           ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        //for each pixel in outImage
            for(Pixel pixel: outImage.pixels()){
            //look at the corresponding pixel in inImage
            Pixel inPixel= inImage.getPixel(pixel.getX(), pixel.getY());
            //compute inPixel's red + inPixel's blue + inPixel's green
            //divide that sum by 3 (call it average)
            int average = (inPixel.getRed() + inPixel.getGreen() + inPixel.getBlue()) / 3;
            //set pixel's red to average
            pixel.setRed(average);
            //set pixel's green to average
            pixel.setGreen(average);
            //set pixel's blue to average
            pixel.setBlue(average);
    }
            //outImage is your answer
            return outImage;
    }

    public void SelectAndConvert() {
        DirectoryResource dr = new DirectoryResource();
         for (File f : dr.selectedFiles()) {
	    ImageResource original = new ImageResource(f);
            ImageResource grayscale = makeGray(original);
            grayscale.setFileName("gray-" + original.getFileName());
            grayscale.draw();
            grayscale.save();
        }
        
    }
}
