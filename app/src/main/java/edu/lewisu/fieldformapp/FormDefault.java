package edu.lewisu.fieldformapp;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FormDefault extends SQLHandler {
    Button btnSubmit, btnEdit, btnUpdate, btnDelete;
    int ID;
    private static MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.default_form);

        firstName=(EditText)findViewById(R.id.fname);
        middleName=(EditText)findViewById(R.id.mname);
        lastName=(EditText)findViewById(R.id.lname);
        Address=(EditText)findViewById(R.id.address);
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

        currID = (TextView) findViewById(R.id.currID);

        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnEdit = (Button) findViewById(R.id.btnEdit);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);

        // Hides buttons and textview that should not be used by users at this time
        currID.setVisibility(View.GONE);
        btnEdit.setVisibility(View.GONE);
        btnUpdate.setVisibility(View.GONE);
        btnDelete.setVisibility(View.GONE);

        // If the form has been created with extras, the form will populate with the provided strings
        // This is used when opening a previous record for viewing/editing
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String[] tempStr = extras.getStringArray("Record Data");
            openRecord(tempStr);
            ID = extras.getInt("Record ID");
        }

    }

    public void deleteData(View view) {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            ID = extras.getInt("Record ID");
        }

        sql.deleteRecord(ID);
        finish();
    }



    public void openRecord(String[] recordData) {
        // Autofill all fields with record values
        currID.setText(recordData[0]);
        firstName.setText(recordData[1]);
        middleName.setText(recordData[2]);
        lastName.setText(recordData[3]);
        Address.setText(recordData[4]);
        City.setText(recordData[5]);
        State.setText(recordData[6]);
        Zip.setText(recordData[7]);
        County.setText(recordData[8]);
        dateOfBirth.setText(recordData[9]);
        Gender.setText(recordData[10]);
        Ethnicity.setText(recordData[11]);
        SSNum.setText(recordData[12]);
        phoneNum.setText(recordData[13]);
        email.setText(recordData[14]);
        contactPref.setText(recordData[15]);
        highSchool.setText(recordData[16]);
        gradYear.setText(recordData[17]);
        programOfInterest.setText(recordData[18]);
        extraCurricularActivities.setText(recordData[19]);
        Hobbies.setText(recordData[20]);
        Scholarships.setText(recordData[21]);
        finanAid.setText(recordData[22]);
        medInfo.setText(recordData[23]);
        Consent.setText(recordData[24]);

        // Disable selectable fields
        firstName.setKeyListener(null);
        middleName.setKeyListener(null);
        lastName.setKeyListener(null);
        Address.setKeyListener(null);
        City.setKeyListener(null);
        State.setKeyListener(null);
        Zip.setKeyListener(null);
        County.setKeyListener(null);
        dateOfBirth.setKeyListener(null);
        Gender.setKeyListener(null);
        Ethnicity.setKeyListener(null);
        SSNum.setKeyListener(null);
        phoneNum.setKeyListener(null);
        email.setKeyListener(null);
        contactPref.setKeyListener(null);
        highSchool.setKeyListener(null);
        gradYear.setKeyListener(null);
        programOfInterest.setKeyListener(null);
        extraCurricularActivities.setKeyListener(null);
        Hobbies.setKeyListener(null);
        Scholarships.setKeyListener(null);
        finanAid.setKeyListener(null);
        medInfo.setKeyListener(null);
        Consent.setKeyListener(null);

        // Hide Submit button
        btnSubmit.setVisibility(View.GONE);
        // Make Edit button visible
        btnEdit.setVisibility(View.VISIBLE);
    }

    public void editRecord(View v) {
        // Hide Edit button
        btnEdit.setVisibility(View.GONE);
        // Make Update and delete button visible
        btnUpdate.setVisibility(View.VISIBLE);
        btnDelete.setVisibility(View.VISIBLE);

        // Enable selectable fields
        firstName.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        middleName.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        lastName.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        Address.setInputType(InputType.TYPE_TEXT_VARIATION_POSTAL_ADDRESS);
        City.setInputType(InputType.TYPE_TEXT_VARIATION_POSTAL_ADDRESS);
        State.setInputType(InputType.TYPE_TEXT_VARIATION_POSTAL_ADDRESS);
        Zip.setInputType(InputType.TYPE_CLASS_NUMBER);
        County.setInputType(InputType.TYPE_TEXT_VARIATION_POSTAL_ADDRESS);
        dateOfBirth.setInputType(InputType.TYPE_CLASS_NUMBER);
        Gender.setInputType(InputType.TYPE_CLASS_TEXT);
        Ethnicity.setInputType(InputType.TYPE_CLASS_TEXT);
        SSNum.setInputType(InputType.TYPE_CLASS_NUMBER);
        phoneNum.setInputType(InputType.TYPE_CLASS_PHONE);
        email.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        contactPref.setInputType(InputType.TYPE_CLASS_TEXT);
        highSchool.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
        gradYear.setInputType(InputType.TYPE_CLASS_NUMBER);
        programOfInterest.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        extraCurricularActivities.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        Hobbies.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        Scholarships.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        finanAid.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        medInfo.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        Consent.setInputType(InputType.TYPE_CLASS_TEXT);
    }
}
