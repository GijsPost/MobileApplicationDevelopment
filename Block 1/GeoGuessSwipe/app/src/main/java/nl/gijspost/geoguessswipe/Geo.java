package nl.gijspost.geoguessswipe;

public class Geo {

    private boolean isInEurope;
    private int imageId;

    public Geo(boolean isInEurope, int imageId) {
        this.isInEurope = isInEurope;
        this.imageId = imageId;
    }

    public boolean isInEurope() {
        return isInEurope;
    }

    public int getImage() {
        return imageId;
    }

    public void setInEurope(boolean inEurope) {
        isInEurope = inEurope;
    }
}
