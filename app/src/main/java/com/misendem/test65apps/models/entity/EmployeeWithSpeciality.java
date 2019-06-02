package com.misendem.test65apps.models.entity;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import com.misendem.test65apps.models.Employee;

import java.util.List;

public class EmployeeWithSpeciality {

    @Embedded
    public Employee employee;

    @Relation(parentColumn = "id", entityColumn = "specialityId", entity = EmployeeSpecialityEntity.class, projection = "employeeId")
    public List<Integer> employeeId;

    public EmployeeWithSpeciality(Employee employee, List<Integer> employeeId) {
        this.employee = employee;
        this.employeeId = employeeId;
    }
}
