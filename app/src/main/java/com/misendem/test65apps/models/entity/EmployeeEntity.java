package com.misendem.test65apps.models.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Calendar;

@Entity
public class EmployeeEntity implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String lastName;

    public String firstName;

    public String avatarUrl;

    public String birthday;

    public EmployeeEntity(String lastName, String firstName, String avatarUrl, String birthday) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.avatarUrl = avatarUrl;
        this.birthday = birthday;
    }

    public static String formatName(String name) {
        StringBuilder newName = new StringBuilder(name.toLowerCase());
        newName.setCharAt(0, name.toUpperCase().charAt(0));
        return newName.toString();
    }

    public static String getYearsEmployee(String date) {
        int lengthStringForYears = 4;
        if (date != null) {

            String[] arr = date.split("-");
            for (String year : arr) {
                if (year.length() == lengthStringForYears) {
                    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
                    return String.format("%d лет", currentYear - Integer.parseInt(year));
                }
            }

        }
        return "-";
    }

    @NotNull
    public static String formatDate(String date) {
        if (date == null)return "-";
        String[] arr = date.split("-");
        String day = "";
        String years = "";
        String month = "";
        if ( !date.isEmpty()) {
            for (String number : arr) {
                if (number.length() == 4) {
                    years = number;
                } else if (!number.isEmpty() && Integer.parseInt(number) > 12 && day.isEmpty()) {
                    day = number;
                } else {
                    month = number;
                }
            }
            return String.format("%s.%s.%s", day, month, years);
        } else return "-";
    }


}
