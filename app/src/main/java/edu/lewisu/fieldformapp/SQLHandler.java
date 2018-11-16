package edu.lewisu.fieldformapp;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SQLHandler extends AppCompatActivity {

    // TODO: Factor out existing sql login into here


    ItemOneFragment itemOneFrag;
    ItemTwoFragment itemTwoFrag;
    ItemThreeFragment itemThreeFrag;

    RecyclerView rvFrag2;

    EditText firstName, middleName, lastName, Address, City, State, Zip, County, dateOfBirth,
            Gender, Ethnicity, SSNum, phoneNum, email, contactPref, highSchool,
            gradYear, programOfInterest, extraCurricularActivities, Hobbies,
            Scholarships, finanAid, medInfo, Consent;
    SQLDatabase sql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sql = new SQLDatabase(this);


    }

    // defined in the xml
    public void saveData(View v)
    {
        String fName = firstName.getText().toString();
        String mName = middleName.getText().toString();
        String lName = lastName.getText().toString();
        String add = Address.getText().toString();
        String city = City.getText().toString();
        String state = State.getText().toString();
        String zip = Zip.getText().toString();
        String county = County.getText().toString();
        String DOB = dateOfBirth.getText().toString();
        String gender = Gender.getText().toString();
        String ethnicity = Ethnicity.getText().toString();
        String SSN = SSNum.getText().toString();
        String PhoneNum = phoneNum.getText().toString();
        String eMail = email.getText().toString();
        String ContactPref = contactPref.getText().toString();
        String HighSchool = highSchool.getText().toString();
        String GradYear = gradYear.getText().toString();
        String ProgramOfInterest = programOfInterest.getText().toString();
        String ExtraCurricularActivities = extraCurricularActivities.getText().toString();
        String hobbies = Hobbies.getText().toString();
        String scholarships = Scholarships.getText().toString();
        String FinanAid = finanAid.getText().toString();
        String MedInfo = medInfo.getText().toString();
        String consent = Consent.getText().toString();
        sql.open();
        sql.save(fName,mName, lName, add, city, state, zip, county, DOB, gender, ethnicity, SSN,
                PhoneNum, eMail, ContactPref, HighSchool, GradYear,
                ProgramOfInterest, ExtraCurricularActivities, hobbies,
                scholarships, FinanAid, MedInfo, consent);
        sql.close();
        Toast.makeText(this, "Saved Successfully", Toast.LENGTH_SHORT).show();

        finish();

    }



}
