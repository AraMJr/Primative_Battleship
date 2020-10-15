package com.company;

public class Coordinate {

    private char rows;
    private int columns;
    private String symbol;
    private boolean occupied;

    public Coordinate(char r, int c, String sym, boolean occ) {
        this.rows = r;
        this.columns = c;
        this.symbol = sym;
        this.occupied = occ;
    }

    public Coordinate() {
        this.rows = '?';
        this.columns = -1;
        this.symbol = " ? ";
        this.occupied = false;
    }

    public char getRows() {return this.rows;}
    public int getColumns() {return this.columns;}
    public String getSymbol() {return this.symbol;}
    public boolean isOccupied() {return this.occupied;}
    public String getCoordinate() {return new StringBuilder().append(rows).append(columns).toString();}
    public void setRows(char r) {rows = r;}
    public void setColumns(int c) {columns = c;}
    public void setSymbol(String sym) {symbol = sym;}
    public void setOccupied(boolean occ) {occupied = occ;}
    public String toString() {return symbol;}

}
