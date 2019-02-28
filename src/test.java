import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class test {
    public static void main(String[] args) {
        /*
        general variables
         */
        String[] toWrite = new String[5];
        //String[] toWrite = new String[1];
       // toWrite[0] = "test.txt";
        toWrite[0] = "a_example.txt";
        toWrite[1] = "b_lovely_landscapes.txt";
        toWrite[2] = "c_memorable_moments.txt";
        toWrite[3] = "d_pet_pictures.txt";
        toWrite[4] = "e_shiny_selfies.txt";
        for (String file : toWrite) {
            int imageAmount;
            int insertedImageCount = 0;
            ArrayList<Photo> photos = new ArrayList<>();
            HashSet<String> existingTags = new HashSet<>();
            LinkedList<Slide> toFillSlides = new LinkedList<>();
            /*Read input*/
            String testFilepath = "";
            File test = new File("resources/" + file);

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


                Collections.sort(photos);

                List<Slide> theSlider = new ArrayList<>();

                Iterator myIterator = photos.iterator();
                Slide currentSlide;
                //= new Slide((Photo) myIterator.next());
                while (myIterator.hasNext()) {
                    Photo currentPhoto = (Photo) myIterator.next();
                    switch (currentPhoto.getOrientation()) {
                        case HORIZONTAL:
                            currentSlide = new Slide(currentPhoto);
                            theSlider.add(currentSlide);
                            break;
                        case VERTICAL:
                            if (toFillSlides.isEmpty()) {
                                currentSlide = new Slide(currentPhoto);
                                toFillSlides.add(currentSlide);
                                theSlider.add(currentSlide);
                            } else {
                                try {
                                    currentSlide = toFillSlides.getFirst();
                                    currentSlide.addPhoto(currentPhoto);
                                    toFillSlides.removeFirst();
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                }
                            }
                            break;
                    }
                }
                /*Write output*/

                File outputFile = new File("Output/" + file);
                try (FileOutputStream myFile = new FileOutputStream(outputFile.getAbsoluteFile());
                     PrintWriter writer = new PrintWriter(myFile);) {
                    writer.write(theSlider.size() + "\r\n");
                    theSlider.forEach(testSlide -> testSlide.printSlideInfo(writer));
                } catch (Exception e) {
                    e.printStackTrace();
               }
                /*Output myself out*/
            }
        }
    }

}
