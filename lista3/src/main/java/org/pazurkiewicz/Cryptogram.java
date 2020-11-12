package org.pazurkiewicz;

import java.util.ArrayList;

public class Cryptogram {
    ArrayList<Integer> bytes = new ArrayList<>();
    public Cryptogram(String message){
        for (String a : message.split(" ")) {
            int b = Integer.parseInt(a,2);
            bytes.add(b);
        }
    }
}
