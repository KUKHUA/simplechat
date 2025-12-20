package com.kukhua;

import io.fusionauth.http.server.HTTPResponse;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class ClientStore {
    private static ClientStore instance;
    private ConcurrentHashMap<String, CopyOnWriteArrayList<HTTPResponse>> clients = new ConcurrentHashMap<>();

    private ClientStore(){}

    public CopyOnWriteArrayList<HTTPResponse> getClients(String channel){
        return clients.get(channel);
    }

    public boolean addClient(String channel, HTTPResponse res){
        CopyOnWriteArrayList<HTTPResponse> list = clients.get(channel);
        if(list == null) { 
            clients.put(channel, new CopyOnWriteArrayList<HTTPResponse>());
        }
        
        return clients.get(channel).add(res);
    }

    public static ClientStore get() {
        if(instance == null) { 
            instance = new ClientStore();
        }

        return instance;
    }
}