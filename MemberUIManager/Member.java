package com.example.exam;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Member {
    String name;
    int birthYear;
    String job;
    int imageResId;

    public Member(String name, int birthYear, String job, int imageResId) {
        this.name = name;
        this.birthYear = birthYear;
        this.job = job;
        this.imageResId = imageResId;
    }

    public int getAge() {
        Calendar calendar = new GregorianCalendar(Locale.KOREA);
        int currentYear = calendar.get(Calendar.YEAR);
        return currentYear - birthYear + 1;
    }
}