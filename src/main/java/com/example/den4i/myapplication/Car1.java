package com.example.den4i.myapplication;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.TreeMap;

public class Car1 implements Comparable<Car1>
{
    String model;
    String owner;
    String license_plate;
    String parkingName;

    LinkedHashMap<LocalDate,LocalDate> dates = new LinkedHashMap<>();

    Car1() {

    }

    protected Map<LocalDate, LocalDate> getDates() {
        return dates;
    }

    protected LocalDate getArrivalDate() {
        LocalDate result = null;
        for(NavigableMap.Entry<LocalDate, LocalDate> item : dates.entrySet()){
            result = item.getKey();
        }
        return result;
    }

    protected LocalDate getLeavingDate() {
        LocalDate result = null;
        for(NavigableMap.Entry<LocalDate, LocalDate> item : dates.entrySet()){
            if(item.getValue()!=null){
                result = item.getValue();
            }
        }
        return result;
    }

    protected LocalDate getLeavingDateTestVariant() {
        LocalDate result = null;
        for(NavigableMap.Entry<LocalDate, LocalDate> item : dates.entrySet()){
            result = item.getValue();
        }
        return result;
    }

    protected Entry lstEnter(){
        List<Entry<LocalDate,LocalDate>> entryList =
                new ArrayList<Map.Entry<LocalDate, LocalDate>>(dates.entrySet());
        Entry<LocalDate, LocalDate> lastEntry =
                entryList.get(entryList.size()-1);
        return lastEntry;
    }

    Car1(String model, String owner, String license_plate)
    {
        this.license_plate = license_plate;
        this.model = model;
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Car model: \t" + this.model + "\n" + "Owner: \t" + this.owner + "\n"+
                "License plate: \t" +this.license_plate +
                "\n---------------------------------------------------------";
    }

    @Override
    public int compareTo(Car1 o) {
        return this.license_plate.compareTo(o.license_plate);
        //Commit
    }
}

