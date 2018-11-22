package edu.lewisu.fieldformapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class SQLDatabase {
    private String database="db";
    String table="user";
    private int version=1;

    // TODO - Prep code for form type, does not need to be enabled yet


    // todo: add type of field as an entry in the database ---- This may not be required if we implement below
    // todo: add required fields as an entry in database (based on order of fields on form)
    //          This would allow for greater flexibility in forms as they can be customized more easily
    //          EXAMPLE Required="YYYYYYYYYNYYNNNYYYYYN" OR "YYYYYYYYYYYYYYYYYYYYY"
    //
    //          Could implement types such as "TTTTTTTTRTTTTCTTTTNNNN"
    //              T = EditText, R = Radio Button, C = Checkbox, N = Not Applicable
    //          These could also be implemented as a secondary table to save space
    // TODO: Add form type as an entry in database
    //          EXAMPLE Type="R" (College recruiter) OR "H" (Health Professional)
    //
    private String FirstName="FirstName";
    private String MiddleName="MiddleName";
    private String LastName="LastName";
    private String Address="Address";
    private String City="City";
    private String State="State";
    private String Zip="Zip";
    private String County="County";
    private String DateOfBirth="DateOfBirth";
    private String Gender="Gender";
    private String Ethnicity="Ethnicity";
    private String SSNum="SSNum";
    private String PhoneNum="PhoneNum";
    private String Email="Email";
    private String ContactPref="ContactPref";
    private String HighSchool="HighSchool";
    private String GradYear="GradYear";
    private String ProgramOfInterest="ProgramOfInterest";
    private String ExtraCurricularActivities="ExtraCurricularActivities";
    private String Hobbies="Hobbies";
    private String Scholarships="Scholarships";
    private String FinanAid="FinanAid";
    private String MedInfo="MedInfo";
    private String Consent ="Consent";
    String id="id";


    helper h;
    Context c;

    SQLiteDatabase s;

    // constructor
    SQLDatabase(SQLHandler mainActivity)
    {
        c = mainActivity;
    }
    SQLDatabase(MainActivity mainActivity)
    {
        c = mainActivity;
    }

    // Database Handling
    void open()
    {
        h=new helper(c);
        s=h.getWritableDatabase();
    }

    void close()
    {
        s.close();
    }

    // TODO Modify to accept a String array rather than 24 strings
    public void save(String[] newRecord) {

        ContentValues cv=new ContentValues();
        String[] colNames = { id, FirstName, MiddleName, LastName, Address, City, State, Zip,
                County, DateOfBirth, Gender, Ethnicity, SSNum, PhoneNum, Email,
                ContactPref, HighSchool, GradYear, ProgramOfInterest,
                ExtraCurricularActivities, Hobbies, Scholarships, FinanAid, MedInfo, Consent};

        for (int i = 1; i < 25; i++) {
            cv.put(colNames[i], newRecord[i]);
        }

        s.insert(table, null, cv);
    }

    public void editRecord(String[] modifiedRecord) {
        ContentValues cv = new ContentValues();
        String[] colNames = { id, FirstName, MiddleName, LastName, Address, City, State, Zip,
                County, DateOfBirth, Gender, Ethnicity, SSNum, PhoneNum, Email,
                ContactPref, HighSchool, GradYear, ProgramOfInterest,
                ExtraCurricularActivities, Hobbies, Scholarships, FinanAid, MedInfo, Consent};

        for (int i = 1; i < 25; i++) {
            cv.put(colNames[i], modifiedRecord[i]);
        }

        s.update(table, cv, String.format("%s = ?", "id"), new String[] {modifiedRecord[0]});
//        s.update(table, cv, "id="+modifiedRecord[0], null);
    }

    // TODO Some of these field types within the query could be changed to other variables
    public class helper extends SQLiteOpenHelper {
        public helper(Context context) {
            super(context, database, null, version);
        }

        @Override
        public void onCreate(SQLiteDatabase arg0) {
            String query = "CREATE TABLE " + table + " ( " + id
                    + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FirstName
                    + " TEXT NOT NULL, " + MiddleName + " TEXT NOT NULL," + LastName + " TEXT NOT NULL," + Address + " TEXT NOT NULL,"
                    + City + " TEXT NOT NULL," + State + " TEXT NOT NULL," + Zip + " TEXT NOT NULL,"
                    + County + " TEXT NOT NULL," + DateOfBirth + " TEXT NOT NULL,"
                    + Gender + " TEXT NOT NULL," + Ethnicity + " TEXT NOT NULL,"
                    + SSNum + " TEXT NOT NULL," + PhoneNum + " TEXT NOT NULL,"
                    + Email + " TEXT NOT NULL," + ContactPref + " TEXT NOT NULL,"
                    + HighSchool + " EXT NOT NULL," + GradYear + " TEXT NOT NULL,"
                    + ProgramOfInterest + " TEXT NOT NULL,"
                    + ExtraCurricularActivities + " TEXT NOT NULL,"
                    + Hobbies + " TEXT NOT NULL," + Scholarships + " TEXT NOT NULL,"
                    + FinanAid + " TEXT NOT NULL,"
                    + MedInfo + " TEXT NOT NULL," + Consent + " TEXT NOT NULL );";
            arg0.execSQL(query);

        }

        @Override
        public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        }

    }

//   Does not work
    public boolean doesIdExist(int IDVal) {
        h = new helper(c);
        s = h.getReadableDatabase();

        Boolean recordExists;
//        String[] tempArr = {"id"};

        // TODO- If using this command, the query can be tightened up since we only care about the ID value
        // Alternative queries are commented out
//        Cursor tempC = s.rawQuery("SELECT * FROM " + table + " WHERE id" + "=" + "'" + IDVal + "';", null);
//        Cursor tempC = s.rawQuery("select null where not exists (select *  from user where id=" + , null);
//        Cursor c = s.query(table, tempArr, "id=" + IDVal, null, null, null, null);
        Cursor c = s.rawQuery("SELECT " + "id" + " FROM " + table + " where id=" +IDVal, null);

        recordExists = c != null && c.moveToFirst();

        c.close();

        if (recordExists) {
            Log.v("RETURN:", "TRUE");
            return true;
        } else {
            Log.v("RETURN:", "FALSE");
            return false;
        }
    }

    public void deleteRecord(int idVal) {
        h = new helper(c);
        s = h.getReadableDatabase();

        String Query = "DELETE FROM " + table + " WHERE id" + "=" + "'" + idVal + "';";
//        String Query = "DELETE FROM " + table + ";";
        s.execSQL(Query);

        Log.e("ERROR", "deleting record " + idVal);
    }




// TODO Should we have getters for specific fields?
    // Tester method that returns all current records
    public String get()
    {
        h = new helper(c);
        s = h.getReadableDatabase();
        String txt = "";
        String[] col = { id, FirstName, MiddleName, LastName, Address, City, State, Zip,
                County, DateOfBirth, Gender, Ethnicity, SSNum, PhoneNum, Email,
                ContactPref, HighSchool, GradYear, ProgramOfInterest,
                ExtraCurricularActivities, Hobbies, Scholarships, FinanAid, MedInfo, Consent};

        // Testing code that looks to see if the table is empty before attempting to return values
        boolean empty = true;
        Cursor tempC = s.rawQuery("SELECT id FROM user", null);
        if (tempC != null && tempC.moveToFirst()) {
            empty = (tempC.getInt (0) == 0);
        }
        tempC.close();
        if (empty)
            return "No data available";

        Cursor c = s.query(table, col, null, null, null, null, null); //fetching data from database
        c.moveToFirst();
        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {

            txt = txt + c.getString(0) + " " + c.getString(1) + " " +
                    c.getString(2) + " " + c.getString(3) + " " +
                    c.getString(4) + " " + c.getString(5) + " " +
                    c.getString(6) + " " + c.getString(7) + " " +
                    c.getString(8) + " " + c.getString(9) + " " +
                    c.getString(10) + " " + c.getString(11) + " " +
                    c.getString(12) + " " + c.getString(13) + " " +
                    c.getString(14) + " " + c.getString(15) + " " +
                    c.getString(16) + " " + c.getString(17) + " " +
                    c.getString(18) + " " + c.getString(19) + " " +
                    c.getString(20) + " " + c.getString(21) + "\n";
        }

        c.close();

        return txt;
    }

    // Test method to pull only certain fields from the table, used when using the Reports tab
    String[] getRowData() {
        h = new helper(c);
        s = h.getReadableDatabase();

        String[] txt = {"","","",""};

        boolean empty = true;
        Cursor tempC = s.rawQuery("SELECT id FROM user", null);
        if (tempC != null && tempC.moveToFirst()) {
            empty = (tempC.getInt (0) == 0);
        }
        tempC.close();
        if (empty) {
            txt[0] = "null";
            txt[1] = "null";
            txt[2] = "null";
            txt[3] = "null";
            return txt;
        }

        String[] col = {id, FirstName, MiddleName, LastName};
        Cursor c = s.query(table, col, null, null, null, null, null);
        c.moveToFirst();

        // Remove statements as needed to return only required data
        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            for (int i = 0; i < 4; i++){
                txt[i] = txt[i] + "," + c.getString(i);
            }
        }

        c.close();

        return txt;
    }

    // Returns all data for single record
    String[] getSingleRecord(int tempIndex) {
        h = new helper(c);
        s = h.getReadableDatabase();

        String[]txt = new String[25];

        String[] col = { id, FirstName, MiddleName, LastName, Address, City, State, Zip,
                County, DateOfBirth, Gender, Ethnicity, SSNum, PhoneNum, Email,
                ContactPref, HighSchool, GradYear, ProgramOfInterest,
                ExtraCurricularActivities, Hobbies, Scholarships, FinanAid, MedInfo, Consent};

        Cursor c = s.query(table, col, "id=" + tempIndex, null, null, null, null);
//        c = s.rawQuery("SELECT " + col + " FROM " + table + " where id=" +tempIndex, null);
        c.moveToFirst();

        // Returns all values within the table for the selected row
//        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
        for (int i = 0; i < 25; i++){
            txt[i] = c.getString(i);
        }

        c.close();

        return txt;
    }

    int getHighestIndex() {
        int tempIndex;
        Cursor tempC = s.rawQuery("SELECT id FROM user", null);
        tempC.moveToLast();
        tempIndex = Integer.parseInt(tempC.getString(0));
        tempC.close();
        return tempIndex;
    }
}

