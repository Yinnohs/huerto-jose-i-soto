package org.example.domain;

public class Client extends  Thread{
    private String name;
    private int quantityToConsume;

    private  Orchard orchard;




    @Override
    public  void  run(){
        for (int i = 0; i < quantityToConsume ; i++) {
            System.out.println("The Client: " + this.name + " is consuming: a crop");
            this.orchard.consumeVegetable(this.name);
        }
    }
}
