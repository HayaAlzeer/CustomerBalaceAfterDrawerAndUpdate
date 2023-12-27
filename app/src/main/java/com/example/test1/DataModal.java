package com.example.test1;

public class DataModal {
    String f_name_a;
    String s_name_a;
    String l_name_a;

    String systeM_NO;
    String m_name_a;

    public DataModal(String f_name_a, String s_name_a, String m_name_a, String l_name_a, String systeM_NO ) {
        this.f_name_a = f_name_a;
        this.s_name_a = s_name_a;
        this.m_name_a = m_name_a;
        this.l_name_a = l_name_a;
        this.systeM_NO = systeM_NO;

    }

    public DataModal(String f_name_a, String s_name_a, String l_name_a, String m_name_a) {
        this.f_name_a = f_name_a;
        this.s_name_a = s_name_a;
        this.l_name_a = l_name_a;
        this.m_name_a = m_name_a;
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

    public String getSysteM_NO() {
        return systeM_NO;
    }

    public void setSysteM_NO(String systeM_NO) {
        this.systeM_NO = systeM_NO;
    }




}
