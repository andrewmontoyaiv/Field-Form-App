package edu.lewisu.fieldformapp;

public class RowItemData {

    private String rowID;
    private String rowFN;
    private String rowMN;
    private String rowLN;

    public RowItemData(String tempID, String tempFN, String tempMN, String tempLN) {
        rowID = tempID;
        rowFN = tempFN;
        rowMN = tempMN;
        rowLN = tempLN;
    }

    // TODO Getters & Setters
    public String getID(){
        return rowID;
    }
    public String getFName(){
        return rowFN;
    }
    public String getMName(){
        return rowMN;
    }
    public String getLName(){
        return rowLN;
    }

    public void setFName(String tempFN) {
        rowFN = tempFN;
    }

    public void setLName(String tempLN) {
        rowLN = tempLN;
    }
}

