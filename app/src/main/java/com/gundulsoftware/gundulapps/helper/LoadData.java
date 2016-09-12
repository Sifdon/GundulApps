package com.gundulsoftware.gundulapps.helper;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ardika Bagus on 17-May-16.
 */
public class LoadData implements Parcelable {

    // User info
    private String provider;
    private String fullName;
    private String userEmail;
    private String connection;
    private String createdAt;
    private int avatarId;
    private String FriendUserUid;

    //Player info
    private String pfullname;
    private String puserEmail;
    private String pcreatedAt;
    private String puserUid;
    public LoadData(){

    }

    private LoadData(Parcel parcelIn){
        provider = parcelIn.readString();
        fullName = parcelIn.readString();
        userEmail = parcelIn.readString();
        connection = parcelIn.readString();
        createdAt = parcelIn.readString();
        avatarId = parcelIn.readInt();
        FriendUserUid = parcelIn.readString();
        pfullname = parcelIn.readString();
        puserEmail = parcelIn.readString();
        pcreatedAt = parcelIn.readString();
        puserUid = parcelIn.readString();
    }

    //Player info
    public String getFullName(){
        return fullName;
    }
    public String getProvider(){
        return provider;
    }
    public String getUserEmail(){
        return userEmail;
    }
    public String getCreatedAt(){
        return createdAt;
    }
    public String getConnection(){
        return connection;
    }
    public int getAvatarId(){
        return avatarId;
    }
    public void setFriendUserUid(String givenUserUid){ FriendUserUid = givenUserUid ;}


    //Player Now info
    public void setPlayerFullname(String fullname){pfullname=fullname;}
    public void setPlayerUserEmail(String userEmail){puserEmail=userEmail;}
    public void setPlayerCreatedAt(String createdAt){pcreatedAt=createdAt;}
    public void setPlayerUserUid(String useruid){puserUid = useruid;}

    public String getPlayerFullname(){
        return pfullname;
    }
    public String getPlayerUserEmail(){
        return puserEmail;
    }
    public String getPlayerCreatedAt(){
        return pcreatedAt;
    }
    public String getPlayerUserUid(){return puserUid;}



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(provider);
        dest.writeString(fullName);
        dest.writeString(userEmail);
        dest.writeString(connection);
        dest.writeString(createdAt);
        dest.writeInt(avatarId);
        dest.writeString(FriendUserUid);
        dest.writeString(pfullname);
        dest.writeString(puserEmail);
        dest.writeString(pcreatedAt);
        dest.writeString(puserUid);
    }

    public static final Creator<LoadData> CREATOR = new Creator<LoadData>() {
        @Override
        public LoadData createFromParcel(Parcel in) {
            return new LoadData(in);
        }

        @Override
        public LoadData[] newArray(int size) {
            return new LoadData[size];
        }
    };

}
