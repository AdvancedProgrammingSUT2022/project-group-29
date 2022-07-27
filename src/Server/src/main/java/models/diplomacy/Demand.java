package models.diplomacy;

public class Demand {
    String hostName;
    String guestName;
    String type;
    String resourceName;
    int goldNeeded;

    public Demand(String hostName, String guestName, String type, String resourceName, int goldNeeded) {
        this.hostName = hostName;
        this.guestName = guestName;
        this.type = type;
        this.resourceName = resourceName;
        this.goldNeeded = goldNeeded;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public int getGoldNeeded() {
        return goldNeeded;
    }

    public void setGoldNeeded(int goldNeeded) {
        this.goldNeeded = goldNeeded;
    }
}
