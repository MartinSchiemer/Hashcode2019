import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

public class Slide {

    private ArrayList<Photo> photosInSlide = new ArrayList<>();
    private int freeSpaces = 2;

    public int getFreeSpaces() {
        return freeSpaces;
    }

    public Slide(Photo photo2Add) {
        try {
            addPhoto(photo2Add);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void addPhoto(Photo photo2Add) throws IllegalAccessException {
        switch (photo2Add.getOrientation()) {
            case VERTICAL:
                if (freeSpaces > 0) {
                    photosInSlide.add(photo2Add);
                    freeSpaces--;
                } else {
                    throw new IllegalAccessException("Garbage 2");
                }
                break;
            case HORIZONTAL:
                photosInSlide.add(photo2Add);
                freeSpaces = 0;
                break;
            default:
                throw new IllegalAccessException("Garbage");
        }
    }

    public void printSlideInfo(PrintWriter myWriter) {
        Iterator test = photosInSlide.iterator();
        while (test.hasNext()) {
            Photo photoTest = (Photo) test.next();
            myWriter.write(photoTest.getPhotoID() + " ");
        }
        myWriter.write("\r\n");
    }
}
