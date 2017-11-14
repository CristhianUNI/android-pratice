package com.example.cristhian.practiceretrofit.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by cristhian on 11/12/17.
 */

public class ContactResponse {

    @SerializedName("cellPhone")
    @Expose
    private String cellPhone;

    @SerializedName("contact")
    @Expose
    private String contact;

    @SerializedName("calculatorFormLink")
    @Expose
    private String calculatorFormLink;

    @SerializedName("contactFormLink")
    @Expose
    private String contactFormLink;

    @SerializedName("appStoreLink")
    @Expose
    private String appStoreLink;

    @SerializedName("playStoreLink")
    @Expose
    private String playStoreLink;

    public ContactResponse(String cellPhone, String contact, String calculatorFormLink, String contactFormLink, String appStoreLink, String playStoreLink) {
        this.cellPhone = cellPhone;
        this.contact = contact;
        this.calculatorFormLink = calculatorFormLink;
        this.contactFormLink = contactFormLink;
        this.appStoreLink = appStoreLink;
        this.playStoreLink = playStoreLink;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getCalculatorFormLink() {
        return calculatorFormLink;
    }

    public void setCalculatorFormLink(String calculatorFormLink) {
        this.calculatorFormLink = calculatorFormLink;
    }

    public String getContactFormLink() {
        return contactFormLink;
    }

    public void setContactFormLink(String contactFormLink) {
        this.contactFormLink = contactFormLink;
    }

    public String getAppStoreLink() {
        return appStoreLink;
    }

    public void setAppStoreLink(String appStoreLink) {
        this.appStoreLink = appStoreLink;
    }

    public String getPlayStoreLink() {
        return playStoreLink;
    }

    public void setPlayStoreLink(String playStoreLink) {
        this.playStoreLink = playStoreLink;
    }
}
