package com.example.collectiveproject.Utility;

import com.example.collectiveproject.Model.Category;
import com.example.collectiveproject.Model.Status;


import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TaskValidator {

    public static boolean isValidName(String name){
        if(name == null || name.isEmpty())
            return false;
        return true;
    }

    public static boolean isValidDescription(String description){
        if(description == null || description.isEmpty())
            return false;
        if(description.length() < 100)
            return false;
        return true;
    }

    public static boolean isValidCategory(String category){
        if(category == null || category.isEmpty())
            return false;

        for(Category categoryValue: Category.values()){
            if(categoryValue.toString().equals(category))
                return true;
        }

        return false;
    }

    public static boolean isValidStatus(String status){
        if(status == null || status.isEmpty())
            return false;

        for(Status statusValue: Status.values()){
            if(statusValue.toString().equals(status))
                return true;
        }

        return false;
    }

    final static DateTimeFormatter dtfymd =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");
    final static DateTimeFormatter dtfym =
            DateTimeFormatter.ofPattern("yyyy-MM");

    public static boolean isValidDate(String dateToVerify) {
        LocalDate today = LocalDate.now();
        LocalDate date;
        if (dateToVerify.length() < 10) {
            date = YearMonth.parse(dateToVerify, dtfym).atDay(1);
            today = YearMonth.from(today).atDay(1);
        } else {
            date = LocalDate.parse(dateToVerify, dtfymd);
        }
        try {
            return date.isAfter(today);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }
        return false;
    }

}
