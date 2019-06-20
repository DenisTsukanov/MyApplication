package com.example.den4i.myapplication;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;

public class Car1 implements Comparable<Car1>
{
    String model;
    String owner;
    String license_plate;
    String idCar;
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

    public void setModel(String model) {
        this.model = model;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setLicense_plate(String license_plate) {
        this.license_plate = license_plate;
    }

    public void setIdCar(String idCar) {
        this.idCar = idCar;
    }

    Car1(String model, String owner, String license_plate, String id)
    {
        this.license_plate = license_plate;
        this.model = model;
        this.owner = owner;
        this.idCar = id;
    }

    public String getModel() {
        return model;
    }

    public String getOwner() {
        return owner;
    }

    public String getLicense_plate() {
        return license_plate;
    }

    public String getIdCar() {
        return idCar;
    }

    public String getParkingName() {
        return parkingName;
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

