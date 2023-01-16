package org.example;

import org.example.domain.Client;
import org.example.domain.Farmer;
import org.example.domain.Orchard;

public class Main {
    public static void main(String[] args) {
        Orchard myOrd = new Orchard(5);

        Client jose = new Client("Jose", 5,myOrd);
        Farmer producer = new Farmer("Productora 1", 5, myOrd);


        producer.start();
        jose.start();

    }

}