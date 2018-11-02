package edu.lewisu.fieldformapp;

public class ItemData {

    private String formName;
    private String formDate;

    public ItemData(String tempName, String tempDate) {
        formName = tempName;
        formDate = tempDate;
    }

    // TODO Getters & Setters
    public String getName(){
        return formName;
    }
    public String getDate() {
        return formDate;
    }

    public void setName(String tempName) {
        formName = tempName;
    }

    public void setDate(String tempDate) {
        formDate = tempDate;
    }
}

