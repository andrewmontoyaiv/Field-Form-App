package edu.lewisu.fieldformapp;

public class RowItemData {

    private int rowID;
    private String rowFN;
    private String rowMN;
    private String rowLN;
    private char rowFT;

    public RowItemData(int tempID, String tempFN, String tempMN, String tempLN, char tempFT) {
        rowID = tempID;
        rowFN = tempFN;
        rowMN = tempMN;
        rowLN = tempLN;
        rowFT = tempFT;
    }

    // TODO Getters & Setters
    public int getID(){
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
    public char getFType(){
        return rowFT;
    }

    // No setter for ID as that cannot be changed manually
    public void setFName(String tempFN) {
        rowFN = tempFN;
    }
    public void setMName(String tempMN) { rowMN = tempMN; }
    public void setLName(String tempLN) {
        rowLN = tempLN;
    }
}

