package com.example.energyresourcestestapp.infoserverscreen;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.energyresourcestestapp.network.ServerInfo;

public class InfoServerViewModel extends ViewModel {
    private final MutableLiveData<ServerInfo> _liveDataServerInfo = new MutableLiveData<ServerInfo>();
    public LiveData<ServerInfo> liveDataServerInfo = _liveDataServerInfo;
   public void onCreatedFragment(ServerInfo serverInfo){
        _liveDataServerInfo.postValue(serverInfo);
    }

}
