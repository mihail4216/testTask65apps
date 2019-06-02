package com.misendem.test65apps.logic.network;

import com.google.gson.annotations.SerializedName;
import com.misendem.test65apps.models.Employee;

import java.util.ArrayList;

public class Response {
    @SerializedName("response")
    public ArrayList<Employee> employees;

    public Response(ArrayList<Employee> employees) {
        this.employees = employees;
    }
}
