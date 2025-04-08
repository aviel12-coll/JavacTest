package ex5.main;

import ex5.logic.FileParser;

import java.io.IOException;

public class sjavac {

    public static void main (String[] args) {
        if (args.length !=1) {
            System.out.println("Incorrect input");
            System.out.println(2);
            return;
        }

        String filename = args[0];
        try {
            FileParser r = new FileParser(filename);
            r.read();
            //if the file is correct
            System.out.println(0);
        } catch (Exception e) {
            System.out.println(1);
            System.err.println(e.getMessage());

        }


    }
}
