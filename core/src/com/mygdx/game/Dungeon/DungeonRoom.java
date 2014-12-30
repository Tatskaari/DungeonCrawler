package com.mygdx.game.Dungeon;

public class DungeonRoom {
    private int roomNumber;
    private int x;
    private int y;
    private int width;
    private int height;

    public DungeonRoom(int x, int y, int width, int height, int roomNumber){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.roomNumber = roomNumber;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getRoomNumber(){
        return roomNumber;
    }
}
