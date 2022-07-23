package models.diplomacy;

public class Trade {
    String hostName;
    String guestName;
    String type;
    int suggestedGold;
    String resourceName;

    public Trade(String hostName, String guestName, String type, int suggestedGold, String resourceName) {
        this.hostName = hostName;
        this.guestName = guestName;
        this.type = type;
        this.suggestedGold = suggestedGold;
        this.resourceName = resourceName;
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

    public int getSuggestedGold() {
        return suggestedGold;
    }

    public void setSuggestedGold(int suggestedGold) {
        this.suggestedGold = suggestedGold;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }
}


