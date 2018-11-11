package edu.lewisu.fieldformapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLDatabase {
    String database="db";
    String table="user";
    int version=1;

    String FirstName="FirstName";
    String MiddleName="MiddleName";
    String LastName="LastName";
    String Address="Address";
    String City="City";
    String State="State";
    String Zip="Zip";
    String County="County";
    String DateOfBirth="DateOfBirth";
    String Gender="Gender";
    String Ethnicity="Ethnicity";
    String SSNum="SSNum";
    String PhoneNum="PhoneNum";
    String Email="Email";
    String ContactPref="ContactPref";
    String HighSchool="HighSchool";
    String GradYear="GradYear";
    String ProgramOfInterest="ProgramOfInterest";
    String ExtraCurricularActivities="ExtraCurricularActivities";
    String Hobbies="Hobbies";
    String Scholarships="Scholarships";
    String FinanAid="FinanAid";
    String MedInfo="MedInfo";
    String Consent ="Consent";
    String id="id";


    helper h;
    Context c;

    SQLiteDatabase s;

    // constructor
    public SQLDatabase(BaseActivity baseActivity)
    {
        c=baseActivity;
    }

    // Database Handling
    public void open()
    {
        h=new helper(c);
        s=h.getWritableDatabase();
    }

    public void close()
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






    public String get()
    {
        h = new helper(c);
        s = h.getReadableDatabase();
        String txt = "";
        String[] col = { id, FirstName, MiddleName, LastName, Address, City, State, Zip,
                County, DateOfBirth, Gender, Ethnicity, SSNum, PhoneNum, Email,
                ContactPref, HighSchool, GradYear, ProgramOfInterest,
                ExtraCurricularActivities, Hobbies, Scholarships, FinanAid, MedInfo, Consent};
        Cursor c = s.query(table, col, null, null, null, null, null); //fetching data from database
        c.moveToFirst();
        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {

            txt = txt + c.getString(0) + " " + c.getString(1) + " "
                    + c.getString(2) + " " + c.getString(3) + " " + c.getString(2) + " " + c.getString(2) + " " +
            c.getString(4) + " " + c.getString(5) + " " + c.getString(6) + " " + c.getString(7) + " " + c.getString(8) + " " +
            c.getString(9) + " " + c.getString(10) + " " + c.getString(11) + " " + c.getString(12) + " " + c.getString(13) + " " +
            c.getString(14) + " " + c.getString(15) + " " + c.getString(16) + " " + c.getString(17) + " " + c.getString(18) + " " + c.getString(19) + " " +
            c.getString(20) + " " + c.getString(21) + "\n";

        }

        return txt;
    }
}

