package com.example.energyresourcestestapp.network;
import static java.net.HttpURLConnection.HTTP_OK;
import com.example.energyresourcestestapp.connectionscreen.Error;
import com.example.energyresourcestestapp.shared.Utils;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

public class ApiServerInfo {
    private static final String HTTPS_TYPE = "https";
    private static final String HTTP_TYPE = "http";
    private static final String POST_REQUEST_TYPE = "POST";

    private EventListener eventListener;
    private final JsonParser jsonParser = new JsonParser();


    public void getInfoFromServer(URL urlServer,String protocolType) {
            try {
                HttpURLConnection connection = createConnection(protocolType, urlServer);
                if (connection.getResponseCode() == HTTP_OK) {
                    ServerInfo serverInfo = getServerInfoFromInputStream(connection.getInputStream());
                    eventListener.onData(serverInfo);
                } else {
                    eventListener.onError(Error.ERROR_SERVER);
                }
            }
            catch (InterruptedIOException ignored) {}
            catch (IOException e) {
                eventListener.onError(Error.ERROR_NETWORK);
            }
    }

    public void setListenerEvent(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    private HttpURLConnection createConnection(String protocolType, URL url) throws IOException {
        HttpURLConnection connection = null;
        switch (protocolType) {
            case HTTPS_TYPE:
                connection = (HttpsURLConnection) url.openConnection();
                break;
            case HTTP_TYPE:
                connection = (HttpURLConnection) url.openConnection();
                break;
        }
        if (connection != null) connection.setRequestMethod(POST_REQUEST_TYPE);
        return connection;
    }



    private ServerInfo getServerInfoFromInputStream(InputStream inputStream) throws IOException {
        ServerInfo serverInfo = null;
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(Utils.convertStreamToString(inputStream));
            serverInfo = jsonParser.getServerInfoFromJson(jsonObject);
        } catch (JSONException ignored) {}
        inputStream.close();
        return serverInfo;
    }
}
