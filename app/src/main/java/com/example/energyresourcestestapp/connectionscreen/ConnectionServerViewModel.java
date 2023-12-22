package com.example.energyresourcestestapp.connectionscreen;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.energyresourcestestapp.network.EventListener;
import com.example.energyresourcestestapp.network.ApiServerInfo;
import com.example.energyresourcestestapp.network.ServerInfo;
import com.example.energyresourcestestapp.shared.AsyncRequester;
import java.net.MalformedURLException;
import java.net.URL;

public class ConnectionServerViewModel extends ViewModel implements EventListener {
    private static final String DELIMITER = "://";
    private final MutableLiveData<ServerInfo> _liveDataServerInfo = new MutableLiveData<ServerInfo>();
    public LiveData<ServerInfo> liveDataServerInfo = _liveDataServerInfo;
    private final MutableLiveData<Error> _liveDataError = new MutableLiveData<>();
    public LiveData<Error> liveDataError = _liveDataError;
    ApiServerInfo apiServerInfo = new ApiServerInfo();
    private final AsyncRequester asyncRequester = new AsyncRequester();

    public ConnectionServerViewModel() {
        apiServerInfo.setListenerEvent(this);
    }

    public void connectButtonPressed(String url) {
        asyncRequester.stopAsyncTask();
        String protocolType = url.split(DELIMITER)[0];
        URL urlServer = createURL(url);
        if (urlServer == null) return;
        asyncRequester.startAsyncTask(() ->
                apiServerInfo.getInfoFromServer(urlServer, protocolType));
    }

    private URL createURL(String url) {
        URL urlServer = null;
        try {
            urlServer = new URL(url);
        } catch (MalformedURLException e) {
            onError(Error.ERROR_URL);
        }
        return urlServer;
    }

    @Override
    public void onError(Error error) {
        _liveDataError.postValue(error);
    }

    @Override
    public void onData(ServerInfo serverInfo) {
        if (serverInfo == null) {
            _liveDataError.postValue(Error.ERROR_METHOD);
            return;
        }
        _liveDataServerInfo.postValue(serverInfo);
    }
}
