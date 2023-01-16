package org.example;

import org.example.domain.Orchard;

public class Main {
    public static void main(String[] args) {
        Orchard myOrd = new Orchard(10);

        // TODO Add new parameter quantity to produce and quantity to consume.
        CropWorker jose = new CropWorkerBuilder()
                .setCrops(new String[]{"lettuce", "cabbage","onion"}) // delete this option, get random option
                .setType(CropWorkerType.FARMER)
                .setName("jose")
                .setOrchard(myOrd)
                .build();

        CropWorker manuel  = new CropWorkerBuilder()
                .setCrops(new String[]{"lettuce", "cabbage","onion"}) // just consume the first (pop)
                .setType(CropWorkerType.CLIENT)
                .setName("adrian")
                .setOrchard(myOrd)
                .build();

        jose.run();
        manuel.run();
    }

}