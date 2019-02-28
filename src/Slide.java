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
                    throw new IllegalAccessException("Free spaces " + freeSpaces);
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
        for (int index = 0; index < photosInSlide.size(); index++) {
            Photo photoTest = photosInSlide.get(index);
            String testWrite;
            if ((photoTest.getOrientation() == Photo.Photo_Orientation.VERTICAL) && (index == 0)) {
                testWrite = photoTest.getPhotoID() + " ";
            } else {
                testWrite = String.valueOf(photoTest.getPhotoID());
            }
            myWriter.write(testWrite);
        }
        myWriter.write("\r\n");
    }
}
