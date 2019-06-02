package com.misendem.test65apps.models.entity;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class EmployeeSpecialityEntity {

    @PrimaryKey(autoGenerate = true)
    public Integer id;

    public Integer employeeId;
    public Integer specialityId;

    public EmployeeSpecialityEntity( Integer employeeId, Integer specialityId) {
//        this.id = id;
        this.employeeId = employeeId;
        this.specialityId = specialityId;
    }
}
