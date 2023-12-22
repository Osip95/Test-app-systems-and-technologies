package com.example.energyresourcestestapp.network;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonParser {
    private static final String ORGANIZATION_KEY = "organization";
    private static final String VERSION_KEY = "version";
    private static final String VERSION_UPDATE_PACK_KEY = "versionUpdatePack";
    private static final String RESULT_CODE_KEY = "ResultCode";
    private static final int RESULT_ERROR = 3;
    private static final String SERVER_INFO_KEY = "serverInfo";

    public ServerInfo getServerInfoFromJson(JSONObject jsonServerInfo) throws JSONException {
        ServerInfo serverInfo = new ServerInfo();
        if (jsonServerInfo.getInt(RESULT_CODE_KEY) == RESULT_ERROR) {
            return null;
        }
        try {
            serverInfo.organization = jsonServerInfo.getJSONObject(SERVER_INFO_KEY).getString(ORGANIZATION_KEY);
        } catch (JSONException ignored) {}
        try {
            serverInfo.version = jsonServerInfo.getJSONObject(SERVER_INFO_KEY).getString(VERSION_KEY);
        } catch (JSONException ignored) {}
        try {
            serverInfo.versionUpdatePack = jsonServerInfo.getJSONObject(SERVER_INFO_KEY).getString(VERSION_UPDATE_PACK_KEY);
        } catch (JSONException ignored) {}

        return serverInfo;
    }
}
