package org.example.domain;

import org.example.constants.CropsConstant;

import java.util.ArrayList;

public class Farmer extends Thread {
    private String name;
    private Orchard orchard;
    private int maxCropsToPlant = 0;

    private ArrayList<String> cropsToplant;
    private String[] planteableCrops = CropsConstant.CROPS;


    public  Farmer(String name, int maxCropsToPlant, Orchard orchard ){
        this.name = name;
        this.orchard = orchard;
        this.maxCropsToPlant = maxCropsToPlant;
        this.cropsToplant = getRandomCrops(maxCropsToPlant);
    }

    public  void run(){

        for (String crop : cropsToplant){
            this.orchard.produceVegetable(crop, this.name);
        }
    }

    private ArrayList<String> getRandomCrops(int max){
        ArrayList<String> cropsToReturn = new ArrayList<>();
        int randomNumber = 0;
        for (int i = 0; i < max; i++) {
            cropsToReturn.add(this.planteableCrops[randomNumber]);
        }
        return  cropsToReturn;
    }

    private long getRandomNumberFromArray (){
        int max = this.planteableCrops.length;
        int min = 1;
        double randomNumber = Math.random()*(max-min+1)+min;
        return (long) randomNumber;
    }
}
