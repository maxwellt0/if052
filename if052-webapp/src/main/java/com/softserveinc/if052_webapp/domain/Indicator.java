package com.softserveinc.if052_webapp.domain;

import java.util.Date;

/**
 * Created by Maksym Vynnyk on 02.02.2015.
 */
public class Indicator {
    private int indicatorId;
    private Date date;
    private double tariffPerDate;
    private int value;
    private boolean paid;
    private boolean published;
    private WaterMeter waterMeter;

    public int getIndicatorId() {
        return indicatorId;
    }

    public void setIndicatorId(int indicatorId) {
        this.indicatorId = indicatorId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTariffPerDate() { return tariffPerDate; }

    public void setTariffPerDate(double tariffPerDate) { this.tariffPerDate = tariffPerDate; }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public WaterMeter getWaterMeter() {
        return waterMeter;
    }

    public void setWaterMeter(WaterMeter waterMeter) {
        this.waterMeter = waterMeter;
    }

    @Override
    public String toString() {
        return "Indicator{" +
                "indicatorId=" + indicatorId +
                ", date=" + date +
                ", tariffPerDate=" + tariffPerDate +
                ", value=" + value +
                ", paid=" + paid +
                ", published=" + published +
                '}';
    }
}