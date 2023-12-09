package com.nickz.entity;

import java.util.Date;

public class IntervalOfEmployee {
    private int id;
    private Date dateStart;
    private Date dateEnd;

    public IntervalOfEmployee() {
    }

    public IntervalOfEmployee(int id, Date dateStart, Date dateEnd) {
        this.id = id;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public int getId() {
        return id;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }
}