package com.example.listviewwithsnackbar;

public class PersonalDetails {

    private String personName;
    private Boolean gender;

    public PersonalDetails(String nm, Boolean gender) {
        this.personName = nm;
        this.gender = gender;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public Boolean getGender() {
        return gender;
    }

}
