package ex5.logic;

import ex5.logic.exceptioon.InvalidTypeVariable;
import ex5.logic.exceptioon.LineEror;

import javax.naming.InvalidNameException;
import java.io.BufferedReader;

public abstract class Objects {


    public Objects(BufferedReader in, String line) {

    }

    public abstract boolean check() throws LineEror, InvalidNameException, InvalidTypeVariable;

}
