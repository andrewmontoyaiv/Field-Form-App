package edu.lewisu.fieldformapp;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class SQLDatabase extends AppCompatActivity {
    private String database="db";
    String table="user";
    private int version=1;
    private int numOfColumns = 32;

    // TODO - Compound these into fewer statements
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
    private String FormType = "FormType";
    private String Medication = "Medication";
    private String Allergy = "Allergy";
    private String Immunization = "Immunization";
    private String DietaryRestriction = "DietaryRestriction";
    private String IllnessHistory = "IllnessHistory";
    private String DrugHistory = "DrugHistory";

    private String[] columnNames = {id, FormType, FirstName, MiddleName, LastName, Address, City,
            State, Zip, County, DateOfBirth, Gender, Ethnicity, SSNum, PhoneNum, Email, ContactPref,
            HighSchool, GradYear, ProgramOfInterest, ExtraCurricularActivities, Hobbies, Scholarships,
            FinanAid, MedInfo, Consent, Medication, Allergy, Immunization, DietaryRestriction, IllnessHistory, DrugHistory};


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

void export() {
            Log.i("Exporting","...");
            h = new helper(c);
            s = h.getReadableDatabase();
            Cursor c = null;

            File sdCardDir = null;



            try {

                c = s.rawQuery("select * from user", null);
                int rowcount = 0;
                int colmncount = 0;
                sdCardDir = Environment.getExternalStorageDirectory();
                String filename = "exportedDatabase.csv";
                File saveFile = new File(sdCardDir.getAbsolutePath(), filename);
                FileWriter fw = new FileWriter(saveFile);


                BufferedWriter bw = new BufferedWriter(fw);
                rowcount = c.getCount();
                colmncount = c.getColumnCount();
                if (rowcount > 0) {
                    c.moveToFirst();

                    for (int i = 0; i < colmncount; i++) {
                        if (i != colmncount - 1) {
                            bw.write(c.getColumnName(i) + ",");
                        } else {
                            bw.write(c.getColumnName(i));
                        }
                    }

                    bw.newLine();

                    for (int i = 0; i < rowcount; i++) {
                        c.moveToPosition(i);

                        for (int j = 0; j < colmncount; j++) {
                            if (j != colmncount - 1)
                                bw.write(c.getString(j) + ",");
                            else
                                bw.write(c.getString(j));
                        }
                        bw.newLine();
                    }
                    bw.flush();


                }
            } catch (Exception ex) {
                Log.e("ERROR", ex.getMessage());
                if (s.isOpen()) {
                    s.close();
                }
            } finally {
                s.close();
            }







    }

    // TODO Modify to accept a String array rather than 24 strings
    public void save(String[] newRecord) {
        ContentValues cv=new ContentValues();

        // TODO modify loop to account for boolean
        for (int i = 1; i < numOfColumns; i++) {
            if (i == 25)
                cv.put(columnNames[i], Boolean.parseBoolean(newRecord[i]));
            else
                cv.put(columnNames[i], newRecord[i]);
        }

        s.insert(table, null, cv);
    }

    public void editRecord(String[] modifiedRecord) {
        ContentValues cv = new ContentValues();

        for (int i = 1; i < numOfColumns; i++) {
            cv.put(columnNames[i], modifiedRecord[i]);
        }

        s.update(table, cv, String.format("%s = ?", "id"), new String[] {modifiedRecord[0]});
    }

    // TODO Some of these field types within the query could be changed to other variables
    public class helper extends SQLiteOpenHelper {
        public helper(Context context) {
            super(context, database, null, version);
        }

        @Override
        public void onCreate(SQLiteDatabase arg0) {
            // Original Query
//            String query = "CREATE TABLE " + table + " ( " + id
//                    + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FirstName
//                    + " TEXT NOT NULL, " + MiddleName + " TEXT NOT NULL," + LastName + " TEXT NOT NULL," + Address + " TEXT NOT NULL,"
//                    + City + " TEXT NOT NULL," + State + " TEXT NOT NULL," + Zip + " TEXT NOT NULL,"
//                    + County + " TEXT NOT NULL," + DateOfBirth + " TEXT NOT NULL,"
//                    + Gender + " TEXT NOT NULL," + Ethnicity + " TEXT NOT NULL,"
//                    + SSNum + " TEXT NOT NULL," + PhoneNum + " TEXT NOT NULL,"
//                    + Email + " TEXT NOT NULL," + ContactPref + " TEXT NOT NULL,"
//                    + HighSchool + " EXT NOT NULL," + GradYear + " TEXT NOT NULL,"
//                    + ProgramOfInterest + " TEXT NOT NULL,"
//                    + ExtraCurricularActivities + " TEXT NOT NULL,"
//                    + Hobbies + " TEXT NOT NULL," + Scholarships + " TEXT NOT NULL,"
//                    + FinanAid + " TEXT NOT NULL,"
//                    + MedInfo + " TEXT NOT NULL," + Consent + " TEXT NOT NULL );";

            // Expanded Query with new datatypes
            String query = "CREATE TABLE " + table + " ( " +id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    FormType + " CHAR(1) NOT NULL, " +
                    FirstName + " VARCHAR(30), " +
                    MiddleName + " VARCHAR(30), " +
                    LastName + " VARCHAR(30), " +
                    Address + " VARCHAR(50), " +
                    City + " VARCHAR(25), " +
                    State + " VARCHAR(15), " +
                    Zip + " DECIMAL(5), " +
                    County + " VARCHAR(25), " +
                    DateOfBirth + " DATE, " +
                    Gender + " CHAR(1), " +
                    Ethnicity + " VARCHAR(6), " +
                    SSNum + " DECIMAL(9), " +
                    PhoneNum + " DECIMAL(15), " +
                    Email + " VARCHAR(30), " +
                    ContactPref + " CHAR(1), " +
                    HighSchool + " VARCHAR(50), " +
                    GradYear + " DECIMAL(4), " +
                    ProgramOfInterest + " TEXT, " +
                    ExtraCurricularActivities + " TEXT, " +
                    Hobbies + " TEXT, " +
                    Scholarships + " TEXT, " +
                    FinanAid + " TEXT, " +
                    MedInfo + " TEXT, " +
                    Consent + " BOOLEAN NOT NULL, " +
                    Medication + " TEXT, " +
                    Allergy + " TEXT, " +
                    Immunization + " TEXT, " +
                    DietaryRestriction + " TEXT, " +
                    IllnessHistory + " TEXT, " +
                    DrugHistory + " TEXT " + " );";

            arg0.execSQL(query);

        }

        @Override
        public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        }

    }

    public boolean doesIdExist(int IDVal) {
        h = new helper(c);
        s = h.getReadableDatabase();

        Boolean recordExists;
        Cursor tempC = s.rawQuery("SELECT " + "id" + " FROM " + table + " where id=" +IDVal, null);

        recordExists = tempC != null && tempC.moveToFirst();
        tempC.close();

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

        // Testing code that looks to see if the table is empty before attempting to return values
        boolean empty = true;
        Cursor tempC = s.rawQuery("SELECT id FROM user", null);
        if (tempC != null && tempC.moveToFirst()) {
            empty = (tempC.getInt (0) == 0);
        }
        tempC.close();
        if (empty)
            return "No data available";

        Cursor c = s.query(table, columnNames, null, null, null, null, null); //fetching data from database
        c.moveToFirst();
        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {

            for (int i = 0; i < columnNames.length; i++) {
                txt += c.getString(i) + ", ";
            }
            txt += "\n\n";
        }

        c.close();

        return txt;
    }

    // TODO Modify what fields are returned based on final design of list_row_item
    String[] getRowData() {
        h = new helper(c);
        s = h.getReadableDatabase();

        String[] txt = {"","","","",""};

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
            txt[4] = "null";
            return txt;
        }

        // Enter whatever data you would like pulled here
        String[] col = {id, FirstName, MiddleName, LastName, FormType};
        tempC = s.query(table, col, null, null, null, null, null);
        tempC.moveToFirst();

        // Remove statements as needed to return only required data
        for (tempC.moveToFirst(); !tempC.isAfterLast(); tempC.moveToNext()) {
            for (int i = 0; i < 5; i++){
                txt[i] = txt[i] + "," + tempC.getString(i);
            }
        }

        tempC.close();

        return txt;
    }

    // Returns all data for single record
    String[] getSingleRecord(int tempIndex) {
        h = new helper(c);
        s = h.getReadableDatabase();

        String[]txt = new String[numOfColumns];

        Cursor c = s.query(table, columnNames, "id=" + tempIndex, null, null, null, null);
        c.moveToFirst();

        // Returns all values within the table for the selected row
        for (int i = 0; i < numOfColumns; i++){
            txt[i] = c.getString(i);
        }

        c.close();

        return txt;
    }
}

