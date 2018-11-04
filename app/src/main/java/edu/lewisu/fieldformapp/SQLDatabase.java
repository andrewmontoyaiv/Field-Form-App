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

    String firstName="name";
    String Address="address";
    String id="id";


//    String middleName="Middle Name";
//    String lastName="Last Name";
//    String dateOfBirth="Date Of Birth";
//    String SSN="Social Security Number";
//    String email="Email Address";
//    String phoneNum="Phone Number";
//    String contactPref="Contact Preference";
//    String City="City";
//    String State="State";
//    String Zip="Zip Code";
//    String County="County";
//    String Gender="Gender";
//    String Ethnicity="Ethnicity";
//    String Consent ="Consent";
//    String highSchool="High School";
//    String programOfInterest="Program of Interest";
//    String gradYear="High School Graduation Year";
//    String extraCurricularActivity="Extra Curricular Activities";
//    String Hobbies="Hobbies";
//    String Scholarships="Scholarships";
//    String finanAid="Financial Aid";
//    String medInfo="Medical Information";

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

    public void save(String na, String ad)
    {
        ContentValues cv=new ContentValues();
        cv.put(firstName, na);
        cv.put(Address, ad);
        s.insert(table, null, cv);
    }

    public void close()
    {
        s.close();
    }


    public class helper extends SQLiteOpenHelper {
        public helper(Context context) {
            super(context, database, null, version);
        }

        @Override
        public void onCreate(SQLiteDatabase arg0) {
            String query = "CREATE TABLE " + table + " ( " + id
                    + " INTEGER PRIMARY KEY AUTOINCREMENT, " + firstName
                    + " TEXT NOT NULL, " + Address + " TEXT NOT NULL );";
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
        String[] col = { id, firstName, Address };
        Cursor c = s.query(table, col, null, null, null, null, null); //fetching data from database
        c.moveToFirst();
        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {

            txt = txt + c.getString(0) + " " + c.getString(1) + " "
                    + c.getString(2) + "\n";

        }

        return txt;
    }
}

