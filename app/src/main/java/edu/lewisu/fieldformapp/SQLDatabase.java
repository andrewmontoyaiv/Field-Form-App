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

    String firstName="First Name";
    String middleName="Middle Name";
    String lastName="Last Name";
    String Address="Address";
    String City="City";
    String State="State";
    String Zip="Zip Code";
    String County="County";
    String dateOfBirth="Date Of Birth";
    String Gender="Gender";
    String Ethnicity="Ethnicity";
    String SSNum="Social Security Number";
    String phoneNum="Phone Number";
    String email="Email Address";
    String contactPref="Contact Preference";
    String highSchool="High School";
    String gradYear="High School Graduation Year";
    String programOfInterest="Program of Interest";
    String extraCurricularActivities="Extra Curricular Activities";
    String Hobbies="Hobbies";
    String Scholarships="Scholarships";
    String finanAid="Financial Aid";
    String medInfo="Medical Information";
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
                     String PhoneNum, String eMail, String ContactPref, String HighSchool,
                     String GradYear, String ProgramOfInterest, String ExtraCurricularActivities,
                     String hobbies, String scholarships,
                     String FinanAid, String MedInfo, String consent) {
        ContentValues cv=new ContentValues();
        cv.put(firstName, fName);
        cv.put(middleName, mName);
        cv.put(lastName, ln);
        cv.put(Address, add);
        cv.put(City, city);
        cv.put(State, state);
        cv.put(Zip, zip);
        cv.put(County,county);
        cv.put(dateOfBirth, DOB);
        cv.put(Gender, gender);
        cv.put(Ethnicity, ethnicity);
        cv.put(SSNum, SSN);
        cv.put(phoneNum, PhoneNum);
        cv.put(email, eMail);
        cv.put(contactPref, ContactPref);
        cv.put(highSchool, HighSchool);
        cv.put(gradYear, GradYear);
        cv.put(programOfInterest, ProgramOfInterest);
        cv.put(extraCurricularActivities, ExtraCurricularActivities);
        cv.put(Hobbies, hobbies);
        cv.put(Scholarships, scholarships);
        cv.put(finanAid, FinanAid);
        cv.put(medInfo, MedInfo);
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
                    + " INTEGER PRIMARY KEY AUTOINCREMENT, " + firstName
                    + "TEXT NOT NULL, " + middleName + "TEXT NOT NULL," + lastName + "TEXT NOT NULL," + Address + "TEXT NOT NULL,"
                    + City + "TEXT NOT NULL," + State + "TEXT NOT NULL," + Zip + "TEXT NOT NULL,"
                    + County + "TEXT NOT NULL," + dateOfBirth + "TEXT NOT NULL,"
                    + Gender + "TEXT NOT NULL," + Ethnicity + "TEXT NOT NULL,"
                    + SSNum + "TEXT NOT NULL," + phoneNum + "TEXT NOT NULL,"
                    + email + "TEXT NOT NULL," + contactPref + "TEXT NOT NULL,"
                    + highSchool + "TEXT NOT NULL," + gradYear + "TEXT NOT NULL,"
                    + programOfInterest + "TEXT NOT NULL,"
                    + extraCurricularActivities + "TEXT NOT NULL,"
                    + Hobbies + "TEXT NOT NULL," + Scholarships + "TEXT NOT NULL,"
                    + finanAid + "TEXT NOT NULL,"
                    + medInfo + "TEXT NOT NULL," + Consent + "TEXT NOT NULL,);";
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
        String[] col = { id, firstName, middleName, lastName, Address, City, State, Zip,
                County, dateOfBirth, Gender, Ethnicity, SSNum, phoneNum, email,
                contactPref, highSchool, gradYear, programOfInterest,
                extraCurricularActivities, Hobbies, Scholarships, finanAid, medInfo, Consent};
        Cursor c = s.query(table, col, null, null, null, null, null); //fetching data from database
        c.moveToFirst();
        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {

            txt = txt + c.getString(0) + " " + c.getString(1) + " "
                    + c.getString(2) + "\n";

        }

        return txt;
    }
}

