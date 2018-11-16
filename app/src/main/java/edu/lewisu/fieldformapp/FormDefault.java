package edu.lewisu.fieldformapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class FormDefault extends SQLHandler {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.default_form);

        firstName=(EditText)findViewById(R.id.fname);
        middleName=(EditText)findViewById(R.id.mname);
        lastName=(EditText)findViewById(R.id.lname);
        Address=(EditText)findViewById(R.id.fname);
        City=(EditText)findViewById(R.id.city);
        State=(EditText)findViewById(R.id.state);
        Zip=(EditText)findViewById(R.id.zip);
        County=(EditText)findViewById(R.id.county);
        dateOfBirth=(EditText)findViewById(R.id.dob);
        Gender=(EditText)findViewById(R.id.gender);
        Ethnicity=(EditText)findViewById(R.id.ethnicity);
        SSNum=(EditText)findViewById(R.id.ssnum);
        phoneNum=(EditText)findViewById(R.id.phoneNum);
        email=(EditText)findViewById(R.id.email);
        contactPref=(EditText)findViewById(R.id.contactPref);
        highSchool=(EditText)findViewById(R.id.highschool);
        gradYear=(EditText)findViewById(R.id.gradYear);
        programOfInterest=(EditText)findViewById(R.id.poi);
        extraCurricularActivities=(EditText)findViewById(R.id.eca);
        Hobbies=(EditText)findViewById(R.id.hobbies);
        Scholarships=(EditText)findViewById(R.id.scholarship);
        finanAid=(EditText)findViewById(R.id.financialAid);
        medInfo=(EditText)findViewById(R.id.medInfo);
        Consent=(EditText)findViewById(R.id.consent);
    }



}
