package com.example.bookscave;

public class UserData {

    private String Fullname;
    private String Emailadress;
    // an empty constructor is
    // required when using
    // Firebase Realtime Database.
    public UserData() {

    }
    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String Fullname) {
        this.Fullname = Fullname;
    }

    public String getEmailadress() {
        return Emailadress;
    }

    public void setEmailadress(String Emailadress) {
        this.Emailadress = Emailadress;
    }


}
