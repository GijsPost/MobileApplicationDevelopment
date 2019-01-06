package nl.gijspost.numbertrivia;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NumberTrivia {

    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("number")
    @Expose
    private int number;
    @SerializedName("found")
    @Expose
    private boolean found;
    @SerializedName("type")
    @Expose
    private String type;

    /**
     * No args constructor for use in serialization
     *
     */
    public NumberTrivia() {
    }

    /**
     *
     * @param text
     * @param number
     * @param type
     * @param found
     */
    public NumberTrivia(String text, int number, boolean found, String type) {
        super();
        this.text = text;
        this.number = number;
        this.found = found;
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public NumberTrivia withText(String text) {
        this.text = text;
        return this;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public NumberTrivia withNumber(int number) {
        this.number = number;
        return this;
    }

    public boolean isFound() {
        return found;
    }

    public void setFound(boolean found) {
        this.found = found;
    }

    public NumberTrivia withFound(boolean found) {
        this.found = found;
        return this;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public NumberTrivia withType(String type) {
        this.type = type;
        return this;
    }

    @Override
    public String toString() {
        return this.text;
    }

}