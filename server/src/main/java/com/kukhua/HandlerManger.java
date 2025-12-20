package com.kukhua;

import java.util.HashMap;

import com.kukhua.Handlers.IHandler;
import com.kukhua.Handlers.StreamMessages;

public class HandlerManger {
    private HashMap<String, IHandler> lookupHandle = new HashMap<String,IHandler>();
    private static HandlerManger instance;

    private HandlerManger(){
        //all handlers go here:
        this.lookupHandle.put("StreamMessages", new StreamMessages());
    }

    public IHandler findHandler(String handerName){
        return this.lookupHandle.get(handerName);
    }

    public static HandlerManger get(){
        if(instance == null){
            instance = new HandlerManger();
        }

        return instance;
    }
    
}
