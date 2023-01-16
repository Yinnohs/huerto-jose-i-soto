package org.example.domain;

public class Client extends  Thread{
    private String name;
    private int quantityToConsume;

    private  Orchard orchard;

    public  Client (String name, int quantityToConsume, Orchard orchard){
        this.orchard = orchard;
        this.name = name;
        this.quantityToConsume = quantityToConsume;
    }

    @Override
    public  void  run(){
        for (int i = 0; i < quantityToConsume ; i++) {
            this.orchard.consumeVegetable(this.name);
        }
    }
}
