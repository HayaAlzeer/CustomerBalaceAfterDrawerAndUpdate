package com.example.test1;

public class SerLocation {

    String zone_id ;

    String block_id;

    String quarter_id;
    String parcel_id;

    public SerLocation(String zone_id, String block_id, String quarter_id, String parcel_id) {
        this.zone_id = zone_id;
        this.block_id = block_id;
        this.quarter_id = quarter_id;
        this.parcel_id = parcel_id;
    }


    public String getZone_id() {
        return zone_id;
    }

    public void setZone_id(String zone_id) {
        this.zone_id = zone_id;
    }

    public String getBlock_id() {
        return block_id;
    }

    public void setBlock_id(String block_id) {
        this.block_id = block_id;
    }

    public String getQuarter_id() {
        return quarter_id;
    }

    public void setQuarter_id(String quarter_id) {
        this.quarter_id = quarter_id;
    }

    public String getParcel_id() {
        return parcel_id;
    }

    public void setParcel_id(String parcel_id) {
        this.parcel_id = parcel_id;
    }
}
