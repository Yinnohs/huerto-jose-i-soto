package org.example.domain;

import org.example.constants.CropsConstant;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
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
        this.currentVegetables = Collections.synchronizedList(new ArrayList<>());
    }

    public synchronized void produceVegetable(String vegetable, String farmerName){
        String errMsg = "Something weird occurred trying to plant the crop";

        if (this.currentVegetables.size() == this.maxNumberOfVegetables){
            System.out.println("The orchard do not have more room for Vegetables\n");
        }
        while (this.currentVegetables.size() == this.maxNumberOfVegetables) {
            try {
                wait(100);
            } catch (InterruptedException e) {
                System.err.println(errMsg);
            }
        }

        System.out.println("The Farmer: " + farmerName + " is planting " + vegetable + "\n");

        try {
            wait(getRandomNumber() - 1000);
            this.currentVegetables.add(vegetable);
            System.out.println(
                    "the crop : "+
                    vegetable +
                    "\nhas been planted at : " +
                    LocalTime.now() +
                    "\nby : " + farmerName +
                    "\n");
        } catch (InterruptedException e) {
            System.err.println(errMsg);
        }
    }

    public synchronized void consumeVegetable (String consumerName){
        String errMsg = "Something occurred during crop consumtion";
        boolean itPop = false;
        String vegetable = "";

        if (this.currentVegetables.size() == 0){
            System.out.println("the client : " + consumerName + " is waiting for a vegetable\n");
        }
        while (this.currentVegetables.size() == 0){
            try {
                wait(100);
            } catch (InterruptedException e) {
                System.err.println(errMsg);
            }
        }

        System.out.println("The Client: " + consumerName + " is consuming a vegetable\n");

        try {
            wait(getRandomNumber());
            vegetable = this.currentVegetables.remove(0);
            System.out.println(
                    "the Vegetable: "+
                            vegetable +
                            "\nhas been consumed at : " +
                            LocalTime.now() +
                            "\nby : " + consumerName + "\n"
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
