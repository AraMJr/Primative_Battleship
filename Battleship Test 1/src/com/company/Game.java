package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;

public class Game {

    Coordinate[][] map = new Coordinate[10][10];
    StringBuilder map_line = new StringBuilder();

    public void build_map() {
        char latitude = 'A';
        for(Coordinate[] line: map) {
            for(int longitude=0; longitude<line.length; longitude++) {
                Coordinate coordinate = new Coordinate();
                coordinate.setRows(latitude);
                coordinate.setColumns(longitude);
                coordinate.setSymbol(" * ");
                line[longitude] = coordinate;
            }
            latitude++;
        }
    }

    public void print_map() {
        map_line.delete(0, map_line.length());
        map_line.append("  ");
        for (int ct=0; ct < 10; ct++) {
            map_line.append(" " + ct + " ");
        }
        map_line.append("\n");
        char rt = 'A';
        for (Coordinate[] line: map) {
            map_line.append(rt + " ");
            for (Coordinate coordinate: line) {
                map_line.append(coordinate);
            }
            map_line.append("\n");
            rt++;
        }
        System.out.println("\n" + map_line);
    }

    public void set_vessels() {
        Vessel Carrier = new Vessel();
        Vessel Battleship = new Vessel();
        Vessel Missile_Cruiser = new Vessel();
        Vessel Submarine = new Vessel();
        Vessel Patrol_Boat = new Vessel();
        Carrier.setLength(5);
        Carrier.setType("Carrier");
        Battleship.setLength(4);
        Battleship.setType("Battleship");
        Missile_Cruiser.setLength(3);
        Missile_Cruiser.setType("Missile_Cruiser");
        Submarine.setLength(3);
        Submarine.setType("Submarine");
        Patrol_Boat.setLength(2);
        Patrol_Boat.setType("PatrolBoat");
        set_position(Carrier);
        set_position(Battleship);
        set_position(Missile_Cruiser);
        set_position(Submarine);
        set_position(Patrol_Boat);
    }

    public void set_position(Vessel vessel) {
        Random rand = rand();
        boolean valid = true;
        boolean not_fit = true;
        while (valid) {
            Coordinate head = new Coordinate();
            head.setRows((char)(rand.nextInt(10)+65));
            head.setColumns(rand.nextInt(10));
            vessel.setHead(head);
            while (not_fit) {
                int random = rand.nextInt(4);
                if (random == 0) {
                    if ((head.getRows() + vessel.getLength()) <= 10) {
                        not_fit = false;
                        for (int r=0; r<vessel.getLength(); r++) {
                            Coordinate coordinate = new Coordinate();
                            coordinate.setRows((char)(head.getRows() + r));
                            coordinate.setColumns(head.getColumns());
                            Coordinate space = search(coordinate);
                            space.setOccupied(true);
                        }
                    }
                } else if (random == 1) {
                    if ((head.getColumns() + vessel.getLength()) <= 10) {
                        not_fit = false;
                        for (int c=0; c<vessel.getLength(); c++) {
                            Coordinate coordinate = new Coordinate();
                            coordinate.setRows(head.getRows());
                            coordinate.setColumns(head.getColumns() + c);
                            Coordinate space = search(coordinate);
                            space.setOccupied(true);
                        }
                    }
                } else if (random == 2) {
                    if ((head.getRows() - vessel.getLength()) >= 0) {
                        not_fit = false;
                        for (int r=0; r<vessel.getLength(); r++) {
                            Coordinate coordinate = new Coordinate();
                            coordinate.setRows((char)(head.getRows() - r));
                            coordinate.setColumns(head.getColumns());
                            Coordinate space = search(coordinate);
                            space.setOccupied(true);
                        }
                    }
                } else {
                    if ((head.getColumns() - vessel.getLength()) >= 0) {
                        not_fit = false;
                        for (int c=0; c<vessel.getLength(); c++) {
                            Coordinate coordinate = new Coordinate();
                            coordinate.setRows(head.getRows());
                            coordinate.setColumns(head.getColumns() - c);
                            Coordinate space = search(coordinate);
                            space.setOccupied(true);
                        }
                    }
                }
            }
            valid = false;
        }
    }

    private Random rand() {
        return new Random();
    }

    private Scanner input() {
        return new Scanner(System.in);
    }

    protected boolean binary_choice(String message) {
        System.out.println("\n" + message + " y/n: ");
        String answer = input().nextLine().toLowerCase();
        boolean cont;
        if (answer.equals("y")) {
            cont = true;
        }
        else if (answer.equals("n")) {
            cont = false;
        }
        else {
            System.out.println("\nInvalid Input");
            cont = binary_choice(message);
        }
        return cont;
    }

    public Coordinate search(Coordinate target) {
        char row = target.getRows();
        int column = target.getColumns();
        return map[row-65][column];
    }

    public boolean turn() {
        boolean no_victor = true;
        Coordinate target = new Coordinate();
        boolean valid = true;
        while (valid) {
            System.out.println("Enter coordinates: ");
            String input = input().nextLine().toUpperCase();
            char input_row = input.charAt(0);
            int input_column = input.indexOf(1);
            if (input_row >= 'K' || input_row < 'A') {
                System.out.println("Invalid input. Input single character between A and J.");
                valid = true;
            } else {
                target.setRows(input_row);
                valid = false;
            }
            try {
                target.setColumns(input_column);
            } catch (InputMismatchException ime) {
                System.out.println("Invalid input. Input integers only.");
            }
        }

        Coordinate coordinate = search(target);
        if (coordinate.isOccupied()) {
            coordinate.setSymbol(" + ");
            System.out.println("Hit at " + coordinate.getCoordinate());
        } else {
            coordinate.setSymbol(" - ");
            System.out.println("Miss at " + coordinate.getCoordinate());
        }

        print_map();

        no_victor = binary_choice("Test again?");

        return no_victor;
    }

    public Game() {
        boolean in_session = true;
        build_map();
        set_vessels();
        print_map();
        while (in_session) {
            in_session = turn();
        }

    }

}
