import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class test {
    public static void main(String[] args) {
        /*
        general variables
         */
        int imageAmount;
        int insertedImageCount = 0;
        List<Photo> photos = new ArrayList<>();
        HashSet<String> existingTags = new HashSet<>();
        LinkedList<Slide> toFillSlides = new LinkedList<>();
        /*Read input*/
        String testFilepath = "";
        String filename = "a_example.txt";
        File test = new File("resources/" +filename);

        if (test != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(test.getAbsoluteFile()));) {
                int inputCounter = 0;
                String line;
                while ((line = reader.readLine()) != null) {
                    if (inputCounter == 0) {
                        imageAmount = Integer.parseInt(line);
                    } else {
                        photos.add(new Photo(inputCounter - 1, line));
                    }
                    inputCounter++;

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            List<Slide> theSlider = new ArrayList<>();

            Iterator myIterator = photos.iterator();
            Slide currentSlide = new Slide((Photo) myIterator.next());
            theSlider.add(currentSlide);
            toFillSlides.add(currentSlide);
            while (myIterator.hasNext()) {
                Photo currentPhoto = (Photo) myIterator.next();
                if (currentSlide.getFreeSpaces() == 0 || currentPhoto.getSpaceNeeded() > currentSlide.getFreeSpaces()) {
                    /*No space create new slide*/
                    currentSlide = new Slide(currentPhoto);
                    theSlider.add(currentSlide);
                    toFillSlides.remove(currentSlide);
                } else {
                    try {
                        currentSlide.addPhoto(currentPhoto);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                }
            }
            /*Write output*/

            File outputFile = new File("Output/" + filename);
            try(FileOutputStream myFile = new FileOutputStream(outputFile.getAbsoluteFile());
                PrintWriter writer = new PrintWriter(myFile);)
            {
                writer.write(theSlider.size() + "\r\n");
                theSlider.forEach(testSlide -> testSlide.printSlideInfo(writer));
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            /*Output myself out*/
        }


    }

}
