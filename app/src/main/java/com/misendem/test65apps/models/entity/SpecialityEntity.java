package com.misendem.test65apps.models.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity
public class SpecialityEntity implements Serializable {

    @PrimaryKey
    @SerializedName("specialty_id")
//    @ColumnInfo(name = "speciality_id")
    public int id;

    public String name;

    public SpecialityEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
