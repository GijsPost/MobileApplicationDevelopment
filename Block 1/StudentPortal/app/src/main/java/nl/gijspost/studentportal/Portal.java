package nl.gijspost.studentportal;

public class Portal {

    private String portalLabel;
    private String portalUrl;

    //constructor
    public Portal(String portalLabel, String portalUrl) {
        this.portalLabel = portalLabel;
        this.portalUrl = portalUrl;
    }

    public String getPortalLabel() {
        return portalLabel;
    }

    public String getPortalUrl() {
        return portalUrl;
    }
}
