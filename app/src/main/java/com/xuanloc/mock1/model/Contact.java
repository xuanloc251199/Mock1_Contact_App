package com.xuanloc.mock1.model;

public class Contact {

    private String mName;
    private String mPhoneNumber;
    private String mAvatar;

    public Contact() {
    }

    public Contact(String mName, String mPhoneNumber, String mAvatar) {
        this.mName = mName;
        this.mPhoneNumber = mPhoneNumber;
        this.mAvatar = mAvatar;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmPhoneNumber() {
        return mPhoneNumber;
    }

    public void setmPhoneNumber(String mPhoneNumber) {
        this.mPhoneNumber = mPhoneNumber;
    }

    public String getmAvatar() {
        return mAvatar;
    }

    public void setmAvatar(String mAvatar) {
        this.mAvatar = mAvatar;
    }
}
