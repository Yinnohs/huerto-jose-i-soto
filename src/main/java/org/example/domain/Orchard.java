package org.example.domain;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Orchard {
    private int maxNumberOfVegetables;
    private List<String>currentVegetables;
    private final String[] possibleVegetables = new String[]{
            "lettuce",
            "cabbage",
            "onion",
            "spinach",
            "potato",
            "celery",
            "asparagus",
            "radish",
            "broccoli",
            "artichoke",
            "tomato",
            "cucumber",
            "eggplant",
            "carrot",
            "green bean"
    };

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

    public synchronized void produceVegetable(String vegetable){
        boolean vegetableExist= false;
        vegetable = vegetable.toLowerCase();

        if (this.currentVegetables.size() == this.maxNumberOfVegetables) {
            System.err.println("You can't plant any more vegetables in this orchard there are already: "
                    + this.maxNumberOfVegetables);
            return;
        }

        vegetableExist = this.anyMatch(this.possibleVegetables, vegetable);

        if (!vegetableExist){
            System.err.println("This type of vegetable cannot be plant int the orchard" + vegetable.toUpperCase());
            return;
        }

        this.currentVegetables.add(vegetable);

        try {
            wait(getRandomNumber());
            System.out.println(
                    "the crop : "+
                    vegetable +
                    " has been planted at : " +
                    LocalTime.now()
                    );
        } catch (InterruptedException e) {
            System.err.println("Something weird occurred trying to plant the crop");
        }
    }

    public synchronized void consumeVegetable (String vegetable){
        String errMsg = "Something occurred during crop consumtion";
        boolean vegetableExist = false;
        boolean itPop = false;
        String vegetableToPop= "";
        vegetable = vegetable.toLowerCase();

        if (this.currentVegetables.size() == 0){
            System.err.println("You cannot consume any vegetables, theres no vegetables in the Orchard");
            return;
        }

        vegetableToPop = this.getOneByString(this.currentVegetables, vegetable);
        vegetableExist = this.anyMatch(this.possibleVegetables, vegetableToPop);

        if (!vegetableExist){
            System.err.println("This type of vegetable cannot exist in  the orchard" + vegetable.toUpperCase());
            return;
        }

        itPop = this.currentVegetables.remove(vegetableToPop);

        if (!itPop){
            System.err.println(errMsg);
            return;
        }

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
        boolean itMatch = false;
        for(String string : array){
            if (string.equals(matcher)) itMatch = true;
        }
        return  itMatch;
    }

    private  String getOneByString(List<String> array ,String matcher){
        for (String string : array){
            if (string.equals(matcher)) return string;
        }
        return "No Possible Crop" ;
    }

}
