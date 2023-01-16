package org.example;

import org.example.domain.Client;
import org.example.domain.Farmer;
import org.example.domain.Orchard;

public class Main {
    public static void main(String[] args) {
        Orchard myOrd = new Orchard(10);

        Client jose = new Client("Jose", 5,myOrd);
        Client matuzalen = new Client("Matuzalen", 5, myOrd);
        Farmer producer = new Farmer("Productora 1", myOrd, 10);


        producer.start();
        matuzalen.start();
        jose.start();

    }

}