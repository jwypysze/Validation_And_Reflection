package com.kodilla.reflection;

import java.util.Random;

public class Student {

    private String indexNumber = "";

    public void initializeIndexNumber(int z) {
        String[] array = new String[z];
        String word = "";
        for (int i = 0; i < z; i++) {
            int i1 = new Random().nextInt(65,90);
            char c = (char) i1;
            array[i] = String.valueOf(c);
            word = word.concat(array[i]);
        }
        this.indexNumber = word;
    }
}
