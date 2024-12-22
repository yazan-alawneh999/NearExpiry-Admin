package com.big0soft.nearexpireadmin.data.requests;

import com.big0soft.resource.data.FromJson;
import com.big0soft.resource.data.ToJson;
import com.big0soft.resource.gson.FromJsonImpl;
import com.big0soft.resource.gson.ToJsonImpl;

public class ContactDetailsRequest implements FromJson, ToJson {
    private String phone ;
    private String whatsapp;
    private String email;

    public ContactDetailsRequest(String phone, String whatsapp, String email) {
        this.phone = phone;
        this.whatsapp = whatsapp;
        this.email = email;
    }

    public ContactDetailsRequest() {
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public FromJson fromJson(String data) {
        return new FromJsonImpl<>(getClass()).fromJson(data);
    }

    @Override
    public String toJson() {
        return new ToJsonImpl<>(this).toJson();
    }
}
