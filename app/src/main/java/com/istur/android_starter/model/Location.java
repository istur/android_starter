package com.istur.android_starter.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Objects;

@Entity(tableName = "location_table")
public final class Location implements Serializable {

    @PrimaryKey
    @NonNull
    private int id;

    private String name;
    @SerializedName("code")
    private String myCode;

    private boolean favourite;
    private String nickname;

    public Location(){};

    @Ignore
    public Location(@NonNull int id, String name, String myCode) {
        this.id = id;
        this.name = name;
        this.myCode = myCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMyCode() {
        return myCode;
    }

    public void setMyCode(String myCode) {
        this.myCode = myCode;
    }

    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return id == location.id && favourite == location.favourite && Objects.equals(name, location.name) && Objects.equals(myCode, location.myCode) && Objects.equals(nickname, location.nickname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, myCode, favourite, nickname);
    }
}
