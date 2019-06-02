package com.misendem.test65apps.models;

import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;
import com.misendem.test65apps.models.entity.EmployeeEntity;
import com.misendem.test65apps.models.entity.SpecialityEntity;

import java.util.ArrayList;

public class Employee {

    @PrimaryKey
    public int id;

    @SerializedName("l_name")
    public String lastName;

    @SerializedName("f_name")
    public String firstName;

    @SerializedName("avatr_url")
    public String avatarUrl;

    public String birthday;

    @SerializedName("specialty")
    public ArrayList<SpecialityEntity> specialityEntities;


    public Employee(int id, String lastName, String firstName, String avatarUrl, String birthday, ArrayList<SpecialityEntity> specialityEntities) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.avatarUrl = avatarUrl;
        this.birthday = birthday;
        this.specialityEntities = specialityEntities;
    }

    public static EmployeeEntity parceEmployeeToEntity(Employee employee) {
        return new EmployeeEntity( employee.lastName, employee.firstName, employee.avatarUrl, employee.birthday);
    }
}

