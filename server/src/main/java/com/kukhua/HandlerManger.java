package com.kukhua;

import java.util.HashMap;
import com.kukhua.IHandler;

public class HandlerManger {
    private HashMap<String, IHandler> lookupHandle = new HashMap();
    private HandlerManger instance;

    private HandlerManger(){
        //all handlers go here:
        //this.lookupHandle.put("whatever", new Handler());
    }

    public HandlerManger get(){
        if(this.instance == null){
            this.instance = new HandlerManger();
        }

        return this.instance;
    }
    
}
