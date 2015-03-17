package com.softserveinc.if052_restful.domain;

/**
 * This class is a model of the datatable "METER_TYPE"
 *
 * @author Bogdan Pastushkevych
 * @version 1.0
 */
public class MeterType {
    private int meterTypeId;
    private String type;

    public int getMeterTypeId() { return meterTypeId; }

    public void setMeterTypeId(int meterTypeId) { this.meterTypeId = meterTypeId; }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    @Override
    public String toString() {
        return "MeterType{" +
                "meterTypeId=" + meterTypeId +
                ", type='" + type + '\'' +
                '}';
    }
}
