package edu.lewisu.fieldformapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;

public class SQLDatabase {
    private String database="db";
    String table="user";
    private int version=1;


    // todo: add type of field as an entry in the database
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

    public void save(String fName, String mName, String ln, String add, String city,
                     String state, String zip, String county,
                     String DOB, String gender, String ethnicity, String SSN,
                     String phoneNum, String eMail, String contactPref, String highSchool,
                     String gradYear, String programOfInterest, String extraCurricularActivities,
                     String hobbies, String scholarships,
                     String finanAid, String medInfo, String consent) {
        ContentValues cv=new ContentValues();
        cv.put(FirstName, fName);
        cv.put(MiddleName, mName);
        cv.put(LastName, ln);
        cv.put(Address, add);
        cv.put(City, city);
        cv.put(State, state);
        cv.put(Zip, zip);
        cv.put(County,county);
        cv.put(DateOfBirth, DOB);
        cv.put(Gender, gender);
        cv.put(Ethnicity, ethnicity);
        cv.put(SSNum, SSN);
        cv.put(PhoneNum, phoneNum);
        cv.put(Email, eMail);
        cv.put(ContactPref, contactPref);
        cv.put(HighSchool, highSchool);
        cv.put(GradYear, gradYear);
        cv.put(ProgramOfInterest, programOfInterest);
        cv.put(ExtraCurricularActivities, extraCurricularActivities);
        cv.put(Hobbies, hobbies);
        cv.put(Scholarships, scholarships);
        cv.put(FinanAid, finanAid);
        cv.put(MedInfo, medInfo);
        cv.put(Consent, consent);
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


    // not used
    public void deleteRecord(int id) {
        h = new helper(c);
        s = h.getReadableDatabase();

        String Query = "DELETE FROM " + table + " WHERE id" + "=" + "'" + id + "';";
//        String Query = "DELETE FROM " + table + ";";
        s.execSQL(Query);

        Log.e("ERROR", "deleting record");
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
}

