package com.company;

public class Vessel {

    private int length;
    private Coordinate head;
    private String type;
    private int hits;

    public Vessel(int length, Coordinate head, String type, int hits) {
        this.length = length;
        this.head = head;
        this.type = type;
        this.hits = hits;
    }

    public Vessel() {
        this.length = -11;
        this.head = new Coordinate();
        this.type = "Unknown";
        this.hits = 0;
    }

    public int getLength() {return this.length;}
    public Coordinate getHead() {return this.head;}
    public String getType() {return this.type;}
    public int isHit() {return this.hits;}
    public void setLength(int length) {this.length = length;}
    public void setHead(Coordinate head) {this.head = head;}
    public void setType(String type) {this.type = type;}
    public void setHits(int hits) {this.hits = hits;}
    public String toString() {return length + " " + head + " " + type + " " + hits;}

}
