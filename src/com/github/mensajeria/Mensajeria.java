package com.github.mensajeria;

import java.util.ArrayList;
import java.util.Arrays;

public class Mensajeria {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList l = new ArrayList();
        System.out.println(l.size());
        l.add(null);
        l.add(null);
        System.err.println(l.get(0));
        System.out.println(l.size());
    }

}
