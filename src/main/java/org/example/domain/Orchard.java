package org.example.domain;

import org.example.constants.CropsConstant;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Orchard {
    private int maxNumberOfVegetables;
    private List<String>currentVegetables;

    public List<String> getCurrentVegetables() {
        return currentVegetables;
    }

    public int getMaxNumberOfVegetables() {
        return maxNumberOfVegetables;
    }

    public  Orchard(int maxNumberOfVegetables){
        this.maxNumberOfVegetables = maxNumberOfVegetables;
        this.currentVegetables = new ArrayList<>();
    }

    public synchronized void produceVegetable(String vegetable, String farmerName){
        vegetable = vegetable.toLowerCase();

        while (this.currentVegetables.size() == this.maxNumberOfVegetables) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


        this.currentVegetables.add(vegetable);

        try {
            wait(getRandomNumber());
            System.out.println(
                    "the crop : "+
                    vegetable +
                    "\nhas been planted at : " +
                    LocalTime.now() +
                    "\nby : " + farmerName
                    );
        } catch (InterruptedException e) {
            System.err.println("Something weird occurred trying to plant the crop");
        }
    }

    public synchronized void consumeVegetable (String consumerName){
        String errMsg = "Something occurred during crop consumtion";
        boolean itPop = false;
        String vegetable = "";

        while (this.currentVegetables.size() == 0){
            try {
                System.out.println(" Esperando para consumir ");
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        vegetable = this.currentVegetables.remove(0);

        try {
            wait(getRandomNumber());
            System.out.println(
                    "the crop : "+
                            vegetable +
                            " has been consumed at : " +
                            LocalTime.now()
            );
        } catch (InterruptedException e) {
            System.err.println(errMsg);
        }
    }


    private long getRandomNumber (){
        int max = 4000;
        int min = 1000;
        double randomNumber = Math.random()*(max-min+1)+min;
        return (long) randomNumber;
    }

    private boolean anyMatch (String[] array, String matcher){
        for(String string : array){
            if (string.equals(matcher)) return true;
        }
        return  false;
    }

    private  String getOneByString(List<String> array ,String matcher){
        for (String string : array){
            if (string.equals(matcher)) return string;
        }
        return "No Possible Crop" ;
    }

}
