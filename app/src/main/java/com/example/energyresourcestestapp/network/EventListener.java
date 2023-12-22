package com.example.energyresourcestestapp.network;
import com.example.energyresourcestestapp.connectionscreen.Error;

public interface EventListener {
    void onError(Error error);
    void onData(ServerInfo serverInfo);
}
