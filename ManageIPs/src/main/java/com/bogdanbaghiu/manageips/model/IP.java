/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bogdanbaghiu.manageips.model;

/**
 *
 * @author bogdan
 */
public class IP {

    private String IP;
    private String countryCode;
    private String countryName;
    private String regionCode;
    private String regionName;
    private String city;
    private int zipCode;
    private String timeZone;
    private double latitude;
    private double longitude;
    private int metroCode;

    public IP() {
    }

    public IP(String IP) {
        this.IP = IP;
    }

    public IP(String IP, String countryCode, String countryName, String regionCode, String regionName, String city, int zipCode, String timeZone, double latitude, double longitude, int metroCode) {
        this.IP = IP;
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.regionCode = regionCode;
        this.regionName = regionName;
        this.city = city;
        this.zipCode = zipCode;
        this.timeZone = timeZone;
        this.latitude = latitude;
        this.longitude = longitude;
        this.metroCode = metroCode;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getMetroCode() {
        return metroCode;
    }

    public void setMetroCode(int metroCode) {
        this.metroCode = metroCode;
    }

}
