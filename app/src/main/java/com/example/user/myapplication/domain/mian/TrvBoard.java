package com.example.user.myapplication.domain.mian;

/**
 * Created by user on 2017-02-16.
 */

public class TrvBoard extends Announce {

    private int trv_board_id;
    private String company_name;
    private String city_name;
    private String trv_board_title;
    private String trv_board_content;
    private String trv_board_regdate;
    private int trv_board_hit;

    public int getTrv_board_id() {
        return trv_board_id;
    }

    public void setTrv_board_id(int trv_board_id) {
        this.trv_board_id = trv_board_id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getTrv_board_title() {
        return trv_board_title;
    }

    public void setTrv_board_title(String trv_board_title) {
        this.trv_board_title = trv_board_title;
    }

    public String getTrv_board_content() {
        return trv_board_content;
    }

    public void setTrv_board_content(String trv_board_content) {
        this.trv_board_content = trv_board_content;
    }

    public String getTrv_board_regdate() {
        return trv_board_regdate;
    }

    public void setTrv_board_regdate(String trv_board_regdate) {
        this.trv_board_regdate = trv_board_regdate;
    }

    public int getTrv_board_hit() {
        return trv_board_hit;
    }

    public void setTrv_board_hit(int trv_board_hit) {
        this.trv_board_hit = trv_board_hit;
    }
}
