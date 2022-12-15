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

    @Override
    public  void run(){
        if(this.type == CropWorkerType.CLIENT){
            for (String crop : crops){
                System.out.println("The Client: " + this.name + " is consuming: " + crop);
                this.orchard.consumeVegetable(crop);
            }
        }
        else if (this.type == CropWorkerType.FARMER) {
            for (String crop : crops){
                System.out.println("The Farmer: " + this.name + " is planting: " + crop);
                this.orchard.produceVegetable(crop);
            }
        }
        else {
            System.err.println("This type of Perticipant does not exist");
        }
    }



}
