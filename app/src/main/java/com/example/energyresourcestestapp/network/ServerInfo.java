package com.example.energyresourcestestapp.network;
import android.os.Parcel;
import android.os.Parcelable;

public class ServerInfo implements Parcelable {
    private static final String DEFAULT_VALUE = "нет данных";
    public String organization;
    public String version;
    public String versionUpdatePack;
    public ServerInfo(){
        organization = DEFAULT_VALUE;
        version = DEFAULT_VALUE;
        versionUpdatePack = DEFAULT_VALUE;
    }
    public ServerInfo(Parcel in) {
        String[] data = new String[3];
        in.readStringArray(data);
        organization = data[0];
        version = data[1];
        versionUpdatePack = data[2];
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] { organization, version, versionUpdatePack });
    }

    public static final Parcelable.Creator<ServerInfo> CREATOR = new Parcelable.Creator<ServerInfo>() {

        @Override
        public ServerInfo createFromParcel(Parcel source) {
            return new ServerInfo(source);
        }

        @Override
        public ServerInfo[] newArray(int size) {
            return new ServerInfo[size];
        }
    };

}
