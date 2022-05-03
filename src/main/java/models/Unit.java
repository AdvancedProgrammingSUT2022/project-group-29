package models;

public class Unit {
    private int x, y;
    private int cost;
    private String type;
    private int movement;
    private String combatType;
    private int xEnd, yEnd;
    private String state = "";
    public Unit(String type) {
        this.type = type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setMovement(int movement) {
        this.movement = movement;
    }

    public int getMovement() {
        return movement;
    }

    public int getxEnd() {
        return xEnd;
    }

    public void setxEnd(int xEnd) {
        this.xEnd = xEnd;
    }

    public int getyEnd() {
        return yEnd;
    }

    public void setyEnd(int yEnd) {
        this.yEnd = yEnd;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
