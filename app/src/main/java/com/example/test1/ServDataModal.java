package com.example.test1;

public class ServDataModal {
    String systeM_NO;
    String servS_TYPE;
    String agreemenT_ID;

    String quarter_id;
    String block_id;
    String parcel_id;

    public ServDataModal(String systeM_NO, String servS_TYPE, String agreemenT_ID, String quarter_id, String block_id, String parcel_id) {
        this.systeM_NO = systeM_NO;
        this.servS_TYPE = servS_TYPE;
        this.agreemenT_ID = agreemenT_ID;
        this.quarter_id = quarter_id;
        this.block_id = block_id;
        this.parcel_id = parcel_id;
    }

    public String getSysteM_NO() {
        return systeM_NO;
    }

    public void setSysteM_NO(String systeM_NO) {
        this.systeM_NO = systeM_NO;
    }

    public String getServS_TYPE() {
        return servS_TYPE;
    }

    public void setServS_TYPE(String servS_TYPE) {
        this.servS_TYPE = servS_TYPE;
    }

    public String getAgreemenT_ID() {
        return agreemenT_ID;
    }

    public void setAgreemenT_ID(String agreemenT_ID) {
        this.agreemenT_ID = agreemenT_ID;
    }

    public String getQuarter_id() {
        return quarter_id;
    }

    public void setQuarter_id(String quarter_id) {
        this.quarter_id = quarter_id;
    }

    public String getBlock_id() {
        return block_id;
    }

    public void setBlock_id(String block_id) {
        this.block_id = block_id;
    }

    public String getParcel_id() {
        return parcel_id;
    }

    public void setParcel_id(String parcel_id) {
        this.parcel_id = parcel_id;
    }
}
