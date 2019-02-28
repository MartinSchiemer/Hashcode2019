import java.util.Comparator;
import java.util.HashSet;
import java.util.IllegalFormatException;

public class Photo implements Comparable<Photo> {

    public Photo_Orientation getOrientation() {
        return orientation;
    }

    public int getSpaceNeeded() {
        return spaceNeeded;
    }

    public int getPhotoID() {
        return photoID;
    }

    @Override
    public int compareTo(Photo o) {
        return this.numberOfTags - o.numberOfTags;
    }

    public enum Photo_Orientation {
        VERTICAL("V"),
        HORIZONTAL("H");

        private String description;

        Photo_Orientation(String description) {
            this.description = description;
        }
    }

    private int photoID;
    private int spaceNeeded;
    private int numberOfTags;
    private Photo_Orientation orientation;
    private HashSet<String> tags = new HashSet<>();

    public Photo(int photoID, String input) {
        this.photoID = photoID;
        decomposePhoto(input);
    }

    public int getNumberOfTags()
    {
        return  this.numberOfTags;

    }

    private void decomposePhoto(String inputString) throws IllegalFormatException {
        String[] inputBreakdown = inputString.split(" ");
        if (inputBreakdown[0].equals("V")) {
            orientation = Photo_Orientation.VERTICAL;
            spaceNeeded = 1;
        } else {
            orientation = Photo_Orientation.HORIZONTAL;
            spaceNeeded = 2;
        }
        int numberTags = Integer.parseInt(inputBreakdown[1]);
        for (int index = 2; index < inputBreakdown.length; index++) {
            tags.add(inputBreakdown[index]);
        }
        numberOfTags = tags.size();
    }



}
