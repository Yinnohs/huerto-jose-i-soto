package org.example.builder;

import org.example.domain.CropWorker;
import org.example.domain.Orchard;
import org.example.enums.CropWorkerType;

/**
 * <h2>This is a builder pattern class for  CropWorkers</h2>
 * this class is not needed but make the code elegant for me.
 * @attr name, this is the name for the worker
 * @attr type, the type of worker used.
 * @attr crops, crops to plant or consume
 * @attr orchard, the orchard to plant or consume the crops.
 * */
public class CropWorkerBuilder {
    String name = "";
    CropWorkerType type = null;
    String[] crops = null;
    Orchard orchard = null;



    public CropWorkerBuilder setName (String name){
        this.name = name;
        return  this;
    }

    public CropWorkerBuilder setType (CropWorkerType type){
        this.type = type;
        return  this;
    }

    public CropWorkerBuilder setCrops (String[] crops){
        this.crops = crops;
        return  this;
    }

    public  CropWorkerBuilder setOrchard(Orchard orchard){
        this.orchard = orchard;
        return this;
    }

    private void reset (){
        this.name = "";
        this.type = null;
        this.crops = null;
        this.orchard = null;
    }

    public CropWorker build(){
        CropWorker workerToReturn =   new CropWorker(
                this.name,
                this.type,
                this.crops,
                this.orchard
        );
        reset();
        return workerToReturn;
    }
}


