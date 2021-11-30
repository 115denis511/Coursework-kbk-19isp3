package com.example.coursework_kbk_19isp3;

import java.io.Serializable;

public class dbWeek implements Serializable {

    public String dayStart;
    public String dayEnd;
    public dbDay monday;
    public dbDay tuesday;
    public dbDay wednesday;
    public dbDay thursday;
    public dbDay friday;
    public dbDay sunday;
    public dbDay saturday;

    dbWeek(){
        dayStart = "01.01.20";
        dayEnd = "06.01.20";

        monday = new dbDay();
        tuesday = new dbDay();
        wednesday = new dbDay();
        thursday = new dbDay();
        friday = new dbDay();
        sunday = new dbDay();
        saturday = new dbDay();
    }

    public String getDayStart() {
        return dayStart;
    }

    public void setDayStart(String dayStart) {
        this.dayStart = dayStart;
    }

    public String getDayEnd() {
        return dayEnd;
    }

    public void setDayEnd(String dayEnd) {
        this.dayEnd = dayEnd;
    }

    public dbDay getMonday() {
        return monday;
    }

    public void setMonday(dbDay monday) {
        this.monday = monday;
    }

    public dbDay getTuesday() {
        return tuesday;
    }

    public void setTuesday(dbDay tuesday) {
        this.tuesday = tuesday;
    }

    public dbDay getWednesday() {
        return wednesday;
    }

    public void setWednesday(dbDay wednesday) {
        this.wednesday = wednesday;
    }

    public dbDay getThursday() {
        return thursday;
    }

    public void setThursday(dbDay thursday) {
        this.thursday = thursday;
    }

    public dbDay getFriday() {
        return friday;
    }

    public void setFriday(dbDay friday) {
        this.friday = friday;
    }

    public dbDay getSunday() {
        return sunday;
    }

    public void setSunday(dbDay sunday) {
        this.sunday = sunday;
    }

    public dbDay getSaturday() {
        return saturday;
    }

    public void setSaturday(dbDay saturday) {
        this.saturday = saturday;
    }
}
