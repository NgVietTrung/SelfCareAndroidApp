package org.o7planning.nhom3.model;

public class HealthInfo {
    private String bmi_and_bsa;
    private String blood_pressure;
    private String temprature;

    public HealthInfo(String bmi_and_bsa, String blood_pressure, String temprature) {
        this.bmi_and_bsa = bmi_and_bsa;
        this.blood_pressure = blood_pressure;
        this.temprature = temprature;
    }

    public String getBmi_and_bsa() {
        return bmi_and_bsa;
    }

    public void setBmi_and_bsa(String bmi_and_bsa) {
        this.bmi_and_bsa = bmi_and_bsa;
    }

    public String getBlood_pressure() {
        return blood_pressure;
    }

    public void setBlood_pressure(String blood_pressure) {
        this.blood_pressure = blood_pressure;
    }

    public String getTemprature() {
        return temprature;
    }

    public void setTemprature(String temprature) {
        this.temprature = temprature;
    }
}
