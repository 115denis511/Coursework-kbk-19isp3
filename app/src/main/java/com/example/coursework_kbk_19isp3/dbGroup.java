package com.example.coursework_kbk_19isp3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class dbGroup implements Serializable {

    public List<dbWeek> weeks;

    dbGroup(){
        weeks = new ArrayList<dbWeek>();
        weeks.add(new dbWeek());
    }

    public List<dbWeek> getWeeks() {
        return weeks;
    }

    public void setWeeks(List<dbWeek> weeks) {
        this.weeks = weeks;
    }
}
