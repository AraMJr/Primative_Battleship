package com.company;

import java.util.Scanner;

public class Main {

    private static Scanner input(){
        return new Scanner(System.in);
    }

    protected static boolean binary_choice(String message) {
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

    public static void main(String[] args) {
        boolean cont = true;
        System.out.println();
        while (cont) {
            new Game();
            cont = binary_choice("Play again?");
        }
    }

}
