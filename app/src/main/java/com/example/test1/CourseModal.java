package com.example.test1;

public class CourseModal {

    // variables for our course
    // name and description.
    private String courseName;
    private String courseimg;
    private String courseMode;
    private String courseTracks;

    private String systeM_NO;

    private String arabiC_NAME;

    private String mobile;
    private String balance;
    private String servS_TYPE;
    private String agreemenT_ID;
    private String currencY_ID;
    private String quarter_id;

    private String block_id;

    private String parcel_id;

    private String id;

    private String idcard_no;
    private String f_name_a;


    private String s_name_a;
    private String m_name_a;

    public CourseModal(String systeM_NO, String arabiC_NAME, String mobile, String balance, String servS_TYPE, String agreemenT_ID, String currencY_ID, String quarter_id, String block_id, String parcel_id, String id, String idcard_no, String f_name_a, String s_name_a, String m_name_a, String l_name_a) {
        this.courseName = courseName;
        this.courseimg = courseimg;
        this.courseMode = courseMode;
        this.courseTracks = courseTracks;
        this.systeM_NO = systeM_NO;
        this.arabiC_NAME = arabiC_NAME;
        this.mobile = mobile;
        this.balance = balance;
        this.servS_TYPE = servS_TYPE;
        this.agreemenT_ID = agreemenT_ID;
        this.currencY_ID = currencY_ID;
        this.quarter_id = quarter_id;
        this.block_id = block_id;
        this.parcel_id = parcel_id;
        this.id = id;
        this.idcard_no = idcard_no;
        this.f_name_a = f_name_a;
        this.s_name_a = s_name_a;
        this.m_name_a = m_name_a;
        this.l_name_a = l_name_a;
    }

    private String l_name_a;


    public CourseModal(String systeM_NO, String arabiC_NAME, String mobile, String balance, String servS_TYPE, String agreemenT_ID, String currencY_ID, String quarter_id, String block_id, String parcel_id, String id) {
        this.systeM_NO = systeM_NO;
        this.arabiC_NAME = arabiC_NAME;
        this.mobile = mobile;
        this.balance = balance;
        this.servS_TYPE = servS_TYPE;
        this.agreemenT_ID = agreemenT_ID;
        this.currencY_ID = currencY_ID;
        this.quarter_id = quarter_id;
        this.block_id = block_id;
        this.parcel_id = parcel_id;
        this.id = id;
    }

    // creating constructor for our variables.
    public CourseModal(String courseName, String courseimg, String courseMode, String courseTracks) {
        this.courseName = courseName;
        this.courseimg = courseimg;
        this.courseMode = courseMode;
        this.courseTracks = courseTracks;
    }

    public CourseModal(String f_name_a, String s_name_a) {
        this.f_name_a = f_name_a;
        this.s_name_a = s_name_a;
    }


    public String getIdcard_no() {
        return idcard_no;
    }

    public void setIdcard_no(String idcard_no) {
        this.idcard_no = idcard_no;
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

    // creating getter and setter methods.
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseimg() {
        return courseimg;
    }

    public void setCourseimg(String courseimg) {
        this.courseimg = courseimg;
    }

    public String getCourseMode() {
        return courseMode;
    }

    public void setCourseMode(String courseMode) {
        this.courseMode = courseMode;
    }

    public String getCourseTracks() {
        return courseTracks;
    }

    public void setCourseTracks(String courseTracks) {
        this.courseTracks = courseTracks;
    }



    public String getSysteM_NO() {
        return systeM_NO;
    }

    public void setSysteM_NO(String systeM_NO) {
        this.systeM_NO = systeM_NO;
    }

    public String getArabiC_NAME() {
        return arabiC_NAME;
    }

    public void setArabiC_NAME(String arabiC_NAME) {
        this.arabiC_NAME = arabiC_NAME;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
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

    public String getCurrencY_ID() {
        return currencY_ID;
    }

    public void setCurrencY_ID(String currencY_ID) {
        this.currencY_ID = currencY_ID;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
