package com.example.test1;

public class UserModal {



   String f_name_a, s_name_a, m_name_a, l_name_a, idcard_type, idcard_no, zone_id, block_id, quarter_id, parcel_id, mobile , street_id , building_id, flat_id,email ;



    public UserModal(String f_name_a, String s_name_a, String m_name_a, String l_name_a, String idcard_type, String idcard_no, String zone_id, String block_id, String quarter_id, String parcel_id, String mobile, String street_id, String building_id, String flat_id, String email) {
        this.f_name_a = f_name_a;
        this.s_name_a = s_name_a;
        this.m_name_a = m_name_a;
        this.l_name_a = l_name_a;
        this.idcard_type = idcard_type;
        this.idcard_no = idcard_no;
        this.zone_id = zone_id;
        this.block_id = block_id;
        this.quarter_id = quarter_id;
        this.parcel_id = parcel_id;
        this.mobile = mobile;
        this.street_id = street_id;
        this.building_id = building_id;
        this.flat_id = flat_id;
        this.email = email;
    }

    public UserModal(String f_name_a, String s_name_a, String m_name_a, String l_name_a, String idcard_type, String idcard_no, String zone_id, String block_id, String quarter_id, String parcel_id, String mobile) {
        this.f_name_a = f_name_a;
        this.s_name_a = s_name_a;
        this.m_name_a = m_name_a;
        this.l_name_a = l_name_a;
        this.idcard_type = idcard_type;
        this.idcard_no = idcard_no;
        this.zone_id = zone_id;
        this.block_id = block_id;
        this.quarter_id = quarter_id;
        this.parcel_id = parcel_id;
        this.mobile = mobile;
    }

    public String getF_name_a() {
        return f_name_a;
    }

    public void setF_name_a(String f_name_a) {
        this.f_name_a = f_name_a;
    }

    public String getS_name_a() {
        return s_name_a;
    }

    public void setS_name_a(String s_name_a) {
        this.s_name_a = s_name_a;
    }

    public String getM_name_a() {
        return m_name_a;
    }

    public void setM_name_a(String m_name_a) {
        this.m_name_a = m_name_a;
    }

    public String getL_name_a() {
        return l_name_a;
    }

    public void setL_name_a(String l_name_a) {
        this.l_name_a = l_name_a;
    }

    public String getIdcard_type() {
        return idcard_type;
    }

    public void setIdcard_type(String idcard_type) {
        this.idcard_type = idcard_type;
    }

    public String getIdcard_no() {
        return idcard_no;
    }

    public void setIdcard_no(String idcard_no) {
        this.idcard_no = idcard_no;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getStreet_id() {
        return street_id;
    }

    public void setStreet_id(String street_id) {
        this.street_id = street_id;
    }

    public String getBuilding_id() {
        return building_id;
    }

    public void setBuilding_id(String building_id) {
        this.building_id = building_id;
    }

    public String getFlat_id() {
        return flat_id;
    }

    public void setFlat_id(String flat_id) {
        this.flat_id = flat_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
