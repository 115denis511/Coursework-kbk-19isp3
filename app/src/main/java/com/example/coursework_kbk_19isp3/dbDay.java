package com.example.coursework_kbk_19isp3;

import java.io.Serializable;

public class dbDay implements Serializable {

    public dbLesson one;
    public dbLesson two;
    public dbLesson three;
    public dbLesson four;
    public dbLesson five;
    public dbLesson six;
    public dbLesson seven;

    dbDay(){
        one = new dbLesson();
        two = new dbLesson();
        three = new dbLesson();
        four = new dbLesson();
        five = new dbLesson();
        six = new dbLesson();
        seven = new dbLesson();
    }

    public dbLesson getOne() {
        return one;
    }

    public void setOne(dbLesson one) {
        this.one = one;
    }

    public dbLesson getTwo() {
        return two;
    }

    public void setTwo(dbLesson two) {
        this.two = two;
    }

    public dbLesson getThree() {
        return three;
    }

    public void setThree(dbLesson three) {
        this.three = three;
    }

    public dbLesson getFour() {
        return four;
    }

    public void setFour(dbLesson four) {
        this.four = four;
    }

    public dbLesson getFive() {
        return five;
    }

    public void setFive(dbLesson five) {
        this.five = five;
    }

    public dbLesson getSix() {
        return six;
    }

    public void setSix(dbLesson six) {
        this.six = six;
    }

    public dbLesson getSeven() {
        return seven;
    }

    public void setSeven(dbLesson seven) {
        this.seven = seven;
    }
}
