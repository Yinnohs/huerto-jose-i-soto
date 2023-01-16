package org.example.domain;

import org.example.enums.CropWorkerType;

public class CropWorker extends Thread  {
    String name;
    CropWorkerType type;
    String[] crops;

    Orchard orchard;


    public CropWorker(String name, CropWorkerType type, String[] crops, Orchard orchard) {
        this.name = name;
        this.type = type;
        this.crops = crops;
        this.orchard = orchard;
    }

    // TODO: spread functionality to single class.
    // i don't need
    @Override
    public  void run(){
    }



}
