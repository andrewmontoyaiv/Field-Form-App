package edu.lewisu.fieldformapp;

public class ItemData {
    // TODO Implement this class.
    // Used for the different available forms that are visible in fragment one
    private String formName;
    private String formDate;

    public ItemData(String tempName, String tempDate) {
        formName = tempName;
        formDate = tempDate;
    }

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

