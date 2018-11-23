package edu.lewisu.fieldformapp;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;

public class FormDefault extends SQLHandler {
    Button btnSubmit, btnEdit, btnUpdate, btnDelete;
    int ID;
    String formType;
//    private static MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.default_form);

        // Adjust names to match camelCase
        firstName = findViewById(R.id.fName);
        middleName = findViewById(R.id.mName);
        lastName = findViewById(R.id.lName);
        address = findViewById(R.id.address);
        city = findViewById(R.id.city);
        state = findViewById(R.id.state);
        zip = findViewById(R.id.zip);
        County= findViewById(R.id.county);
        dateOfBirth= findViewById(R.id.dob);
        gender = findViewById(R.id.gender);
        ethnicity = findViewById(R.id.ethnicity);
        ssnum = findViewById(R.id.ssnum);
        phoneNum= findViewById(R.id.phoneNum);
        email= findViewById(R.id.email);
        contactPref= findViewById(R.id.contactPref);
        highSchool= findViewById(R.id.highSchool);
        gradYear= findViewById(R.id.gradYear);
        programOfInterest= findViewById(R.id.poi);
        extraCurricularActivities= findViewById(R.id.eca);
        hobbies = findViewById(R.id.hobbies);
        scholarship = findViewById(R.id.scholarship); //
        finanAid= findViewById(R.id.financialAid); //
        medInfo= findViewById(R.id.medInfo);
        Medication = findViewById(R.id.medication);
        Allergy = findViewById(R.id.allergy);
        Immunization = findViewById(R.id.immunization);
        dietaryRestriction = findViewById(R.id.DietRestriction);
        illnessHistory = findViewById(R.id.IllHistory);
        drugHistory = findViewById(R.id.drgHistory);
        consent = findViewById(R.id.consent);
        // TODO Add new fields here


        
        // TextView that is hidden from the user at all times
        currID = findViewById(R.id.currID);

        // Buttons that appear as needed
        btnSubmit = findViewById(R.id.btnSubmit);
        btnEdit = findViewById(R.id.btnEdit);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        // Hides buttons and textview that should not be used by users at this time
        currID.setVisibility(View.GONE);
        btnEdit.setVisibility(View.GONE);
        btnUpdate.setVisibility(View.GONE);
        btnDelete.setVisibility(View.GONE);



        formType = "R";
        // If the form has been created with extras, the form will populate with the provided strings
        // This is used when opening a previous record for viewing/editing
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String[] tempStr = extras.getStringArray("Record Data");
            openRecord(tempStr);
            ID = extras.getInt("Record ID");
            formType =  extras.getString("Form Type");
        }




        ////////////////////////////////////////////////////////////////////////////////////////////
        /// TODO For Hiren
        //////////////////////////////////////////
        if (formType.equals("R")) {
            // Hide Healthcare only fields
            Medication.setVisibility(View.GONE); /// Adam or Andy look over these fields and make
            Allergy.setVisibility(View.GONE); /// sure they make sense
            dietaryRestriction.setVisibility(View.GONE);
            illnessHistory.setVisibility(View.GONE);
            drugHistory.setVisibility(View.GONE);

        } else if (formType.equals("H")) {
            // Hide Recruiter only fields
            scholarship.setVisibility(View.GONE);
            finanAid.setVisibility(View.GONE);
            ssnum.setVisibility(View.GONE);
            highSchool.setVisibility(View.GONE);
            gradYear.setVisibility(View.GONE);
            programOfInterest.setVisibility(View.GONE);
            extraCurricularActivities.setVisibility(View.GONE);
            hobbies.setVisibility(View.GONE);
        }
    }

//    public void deleteData(View view) {
//        Bundle extras = getIntent().getExtras();
//        if (extras != null) {
//            ID = extras.getInt("Record ID");
//        }
//
//        super.itemTwoFrag.changeMade = 'D';
//
//        sql.deleteRecord(ID);
//        finish();
//    }



    public void openRecord(String[] recordData) {
        // Autofill all fields with record values
        currID.setText(recordData[0]);
        firstName.setText(recordData[1]);
        middleName.setText(recordData[2]);
        lastName.setText(recordData[3]);
        address.setText(recordData[4]);
        city.setText(recordData[5]);
        state.setText(recordData[6]);
        zip.setText(recordData[7]);
        County.setText(recordData[8]);
        dateOfBirth.setText(recordData[9]);
        gender.setText(recordData[10]);
        ethnicity.setText(recordData[11]);
        ssnum.setText(recordData[12]);
        phoneNum.setText(recordData[13]);
        email.setText(recordData[14]);
        contactPref.setText(recordData[15]);
        highSchool.setText(recordData[16]);
        gradYear.setText(recordData[17]);
        programOfInterest.setText(recordData[18]);
        extraCurricularActivities.setText(recordData[19]);
        hobbies.setText(recordData[20]);
        scholarship.setText(recordData[21]);
        finanAid.setText(recordData[22]);
        medInfo.setText(recordData[23]);
        Medication.setText(recordData[24]);
        Allergy.setText(recordData[25]);
        Immunization.setText(recordData[26]);
        dietaryRestriction.setText(recordData[27]);
        illnessHistory.setText(recordData[28]);
        drugHistory.setText(recordData[29]);
        consent.setText(recordData[30]);

        // if (recordData[25].equals("R") // College Recruiter


        // if (recordData[25].equals("H") // Healthcare Professional

        // Disable selectable fields
        firstName.setKeyListener(null);
        middleName.setKeyListener(null);
        lastName.setKeyListener(null);
        address.setKeyListener(null);
        city.setKeyListener(null);
        state.setKeyListener(null);
        zip.setKeyListener(null);
        County.setKeyListener(null);
        dateOfBirth.setKeyListener(null);
        gender.setKeyListener(null);
        ethnicity.setKeyListener(null);
        ssnum.setKeyListener(null);
        phoneNum.setKeyListener(null);
        email.setKeyListener(null);
        contactPref.setKeyListener(null);
        highSchool.setKeyListener(null);
        gradYear.setKeyListener(null);
        programOfInterest.setKeyListener(null);
        extraCurricularActivities.setKeyListener(null);
        hobbies.setKeyListener(null);
        scholarship.setKeyListener(null);
        finanAid.setKeyListener(null);
        medInfo.setKeyListener(null);
        Medication.setKeyListener(null);
        Allergy.setKeyListener(null);
        Immunization.setKeyListener(null);
        dietaryRestriction.setKeyListener(null);
        illnessHistory.setKeyListener(null);
        drugHistory.setKeyListener(null);
        consent.setKeyListener(null);

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
        address.setInputType(InputType.TYPE_TEXT_VARIATION_POSTAL_ADDRESS);
        city.setInputType(InputType.TYPE_TEXT_VARIATION_POSTAL_ADDRESS);
        state.setInputType(InputType.TYPE_TEXT_VARIATION_POSTAL_ADDRESS);
        zip.setInputType(InputType.TYPE_CLASS_NUMBER);
        County.setInputType(InputType.TYPE_TEXT_VARIATION_POSTAL_ADDRESS);
        dateOfBirth.setInputType(InputType.TYPE_CLASS_NUMBER);
        gender.setInputType(InputType.TYPE_CLASS_TEXT);
        ethnicity.setInputType(InputType.TYPE_CLASS_TEXT);
        ssnum.setInputType(InputType.TYPE_CLASS_NUMBER);
        phoneNum.setInputType(InputType.TYPE_CLASS_PHONE);
        email.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        contactPref.setInputType(InputType.TYPE_CLASS_TEXT);
        highSchool.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
        gradYear.setInputType(InputType.TYPE_CLASS_NUMBER);
        programOfInterest.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        extraCurricularActivities.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        hobbies.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        scholarship.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        finanAid.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        medInfo.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        Medication.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE); /// Adam or Andy look over
        Allergy.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE); /// these fields and make
        Immunization.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE); /// sure they make sense
        dietaryRestriction.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        illnessHistory.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        drugHistory.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        consent.setInputType(InputType.TYPE_CLASS_TEXT);
    }
}
