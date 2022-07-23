package models.diplomacy;

public class Peace {
    String hostName;
    String guestName;
    String type;

    public Peace(String hostName, String guestName, String type) {
        this.hostName = hostName;
        this.guestName = guestName;
        this.type = type;
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
}
