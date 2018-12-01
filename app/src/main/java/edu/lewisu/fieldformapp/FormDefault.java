package edu.lewisu.fieldformapp;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.database.Cursor;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import java.io.BufferedWriter;

public class FormDefault extends SQLHandler {
    Button btnSubmit, btnEdit, btnUpdate, btnDelete, btnExport;
    char newFormType;
    ArrayList<View> touchables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.default_form);

        // Name
        firstName = findViewById(R.id.fName);
        middleName = findViewById(R.id.mName);
        lastName = findViewById(R.id.lName);

        // Address
        address = findViewById(R.id.address);
        city = findViewById(R.id.city);
        state = findViewById(R.id.state);
        zip = findViewById(R.id.zip);
        county = findViewById(R.id.county);

        // Demographic
        dateOfBirth= findViewById(R.id.dob);
//        gender = findViewById(R.id.gender);
//        ethnicity = findViewById(R.id.ethnicity);
        ssnum = findViewById(R.id.ssnum);

        // Contact
        phoneNum= findViewById(R.id.phoneNum);
        email= findViewById(R.id.email);
        contactPref= findViewById(R.id.contactPref);

        // High School
        highSchool= findViewById(R.id.highSchool);
        gradYear= findViewById(R.id.gradYear);
        programOfInterest= findViewById(R.id.poi);
        extraCurricularActivities= findViewById(R.id.eca);
        hobbies = findViewById(R.id.hobbies);

        // College
        programOfInterest= findViewById(R.id.poi);
        scholarship = findViewById(R.id.scholarship); //
        finanAid= findViewById(R.id.financialAid); //

        // Medical
        medInfo= findViewById(R.id.medInfo);
        medication = findViewById(R.id.medication);
        allergy = findViewById(R.id.allergy);
        immunization = findViewById(R.id.immunization);
        dietaryRestriction = findViewById(R.id.dietaryRestriction);
        illnessHistory = findViewById(R.id.illnessHistory);
        drugHistory = findViewById(R.id.drugHistory);

//        consent = findViewById(R.id.consent);

        // Gender selection
        genderRadioGroup = findViewById(R.id.genderRadioGroup);
        rdbtn_gender_male = findViewById(R.id.rdbtn_gender_male);
        rdbtn_gender_female = findViewById(R.id.rdbtn_gender_female);

        // Ethnicity selection - These are based on the Illinois State Board of Educations
        // recognized Race/Ethnicity Standards
        chk_ethnicity_nativeAmerican = findViewById(R.id.chk_ethnicity_nativeAmerican);
        chk_ethnicity_asian = findViewById(R.id.chk_ethnicity_asian);
        chk_ethnicity_black = findViewById(R.id.chk_ethnicity_black);
        chk_ethnicity_pacific = findViewById(R.id.chk_ethnicity_pacific);
        chk_ethnicity_caucasian = findViewById(R.id.chk_ethnicity_caucasian);
        chk_ethnicity_hispanic = findViewById(R.id.chk_ethnicity_hispanic);

        chk_consent = findViewById(R.id.chk_consent);

        // TextView that is hidden from the user at all times
        currID = findViewById(R.id.currID);
        formType = findViewById(R.id.formType);

        // Buttons that appear as needed
        btnSubmit = findViewById(R.id.btnSubmit);
        btnEdit = findViewById(R.id.btnEdit);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnExport = findViewById(R.id.btnExport);

        // Hides buttons and textview that should not be used by users at this time
        currID.setVisibility(View.GONE);
        formType.setVisibility(View.GONE);
        btnEdit.setVisibility(View.GONE);
        btnUpdate.setVisibility(View.GONE);
        btnDelete.setVisibility(View.GONE);


        newFormType = 'A';
        // If the form has been created with extras, the form will populate with the provided strings
        // This is used when opening a previous record for viewing/editing
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
//            ID = extras.getInt("Record ID"); // Can be deleted
            newFormType =  extras.getChar("Form Type");

            String[] tempStr = extras.getStringArray("Record Data");
            openRecord(tempStr);
        }


        setFormType(newFormType);

        btnExport = (Button)findViewById(R.id.btnExport);
        btnExport.setOnClickListener(new View.OnClickListener() {
            SQLHandler sqlhandlr = controller.getReadableDatabase();
            Cursor c = null;

            @Override
            public void onClick(View v) {

                try {
                    c = sqlhandlr.rawQuery("select * from places", null);
                    int rowcount = 0;
                    int colmncount = 0;
                    File sdCardDir = Environment.getExternalStorageDirectory();
                    String filename = "exportedDatabase.csv";
                    File saveFile = new File(sdCardDir, filename);
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
                        infotext.setText("Exported Successfully!");
                    }
                } catch (Exception ex) {
                    if (sqlhandlr.isOpen()) {
                        sqlhandlr.close();
                        infotext.setText(ex.getMessage().toString());
                    }
                } finally {

                }
            }
        });
    }

    // Used to hide fields that are not used for the chosen form type
    void setFormType(char fType) {
        // For the textboxes, you need to hide the surrounding layouts in order to shrink the space
        if (fType == 'R') {
            // Hide Healthcare only fields
            findViewById(R.id.medicationLayout).setVisibility(View.GONE);
            findViewById(R.id.allergyLayout).setVisibility(View.GONE);
            findViewById(R.id.immunizationLayout).setVisibility(View.GONE);
            findViewById(R.id.dietaryRestrictionLayout).setVisibility(View.GONE);
            findViewById(R.id.illnessHistoryLayout).setVisibility(View.GONE);
            findViewById(R.id.drugHistoryLayout).setVisibility(View.GONE);
        } else if (fType == 'H') {
            // Hide Recruiter only fields
            findViewById(R.id.scholarshipLayout).setVisibility(View.GONE);
            findViewById(R.id.financialAidLayout).setVisibility(View.GONE);
            findViewById(R.id.highSchoolLayout).setVisibility(View.GONE);
            findViewById(R.id.gradYearLayout).setVisibility(View.GONE);
            findViewById(R.id.poiLayout).setVisibility(View.GONE);
            findViewById(R.id.ecaLayout).setVisibility(View.GONE);
            findViewById(R.id.collegeDivider).setVisibility(View.GONE);
        }

        // Sets formType for new forms
        formType.setText(String.valueOf(fType));
    }

    // Disables all fields so they cannot be modified
    void disableAllFields() {
        LinearLayout formLinearLayout = (LinearLayout) findViewById(R.id.formLinearLayout);

        // Save all touchable IDs to allow for simpler re-enabling
        touchables = formLinearLayout.getTouchables();

        for (View view : formLinearLayout.getTouchables()) {
            if (view instanceof EditText) {
                EditText editText = (EditText) view;
                editText.setEnabled(false);
                editText.setFocusable(false);
                editText.setFocusableInTouchMode(false);
            } else if (view instanceof CheckBox) {
                CheckBox tempCB = (CheckBox) view;
                tempCB.setEnabled(false);
            } else if (view instanceof RadioButton) {
                RadioButton tempRB = (RadioButton) view;
                tempRB.setEnabled(false);
            }
        }
    }

    // Enables all fields so they can be modified
    void enableAllFields() {
        for (View view : touchables) {
            if (view instanceof EditText) {
                EditText editText = (EditText) view;
                editText.setFocusableInTouchMode(true);
                editText.setFocusable(true);
                editText.setEnabled(true);
            } else if (view instanceof CheckBox) {
                CheckBox tempCB = (CheckBox) view;
                tempCB.setEnabled(true);
            } else if (view instanceof RadioButton) {
                RadioButton tempRB = (RadioButton) view;
                tempRB.setEnabled(true);
            }
        }
    }

    // Parses information from a record and displays it in the matching fields
    public void openRecord(String[] recordData) {
        // Checks to see if a previous record has been sent to be displayed
        if (recordData == null)
            return;

        // Autofill all fields with record values

        currID.setText(recordData[0]);
        formType.setText(recordData[1]);
        firstName.setText(recordData[2]);
        middleName.setText(recordData[3]);
        lastName.setText(recordData[4]);
        address.setText(recordData[5]);
        city.setText(recordData[6]);
        state.setText(recordData[7]);
        zip.setText(recordData[8]);
        county.setText(recordData[9]);
        dateOfBirth.setText(recordData[10]);

        if (recordData[11].equals("M"))
            rdbtn_gender_male.setChecked(true);
        else if (recordData[11].equals("F"))
            rdbtn_gender_female.setChecked(true);

        int[] tempEth = new int[recordData[12].length()];
        for (int i = 0; i < tempEth.length; i++) {
            tempEth[i] = Integer.parseInt(recordData[12].substring(i, i+1));
        }

        chk_ethnicity_nativeAmerican.setChecked(tempEth[0] == 1);
        chk_ethnicity_asian.setChecked(tempEth[1] == 1);
        chk_ethnicity_black.setChecked(tempEth[2] == 1);
        chk_ethnicity_pacific.setChecked(tempEth[3] == 1);
        chk_ethnicity_caucasian.setChecked(tempEth[4] == 1);
        chk_ethnicity_hispanic.setChecked(tempEth[5] == 1);

        ssnum.setText(recordData[13]);
        phoneNum.setText(recordData[14]);
        email.setText(recordData[15]);
        contactPref.setText(recordData[16]);
        highSchool.setText(recordData[17]);
        gradYear.setText(recordData[18]);
        programOfInterest.setText(recordData[19]);
        extraCurricularActivities.setText(recordData[20]);
        hobbies.setText(recordData[21]);
        scholarship.setText(recordData[22]);
        finanAid.setText(recordData[23]);
        medInfo.setText(recordData[24]);

        // Two different statements used since the SQL table will store the value as either 0/1 or false/true
        if (recordData[25].contains("1") || recordData[25].equals("true"))
            chk_consent.setChecked(true);

        medication.setText(recordData[26]);
        allergy.setText(recordData[27]);
        immunization.setText(recordData[28]);
        dietaryRestriction.setText(recordData[29]);
        illnessHistory.setText(recordData[30]);
        drugHistory.setText(recordData[31]);

        disableAllFields();

        // Hide Submit button
        btnSubmit.setVisibility(View.GONE);
        // Make Edit button visible
        btnEdit.setVisibility(View.VISIBLE);
    }

    // Changes the button layout and reenables all fields
    public void editRecord(View v) {
        // Hide Edit button
        btnEdit.setVisibility(View.GONE);
        // Make Update and delete button visible
        btnUpdate.setVisibility(View.VISIBLE);
        btnDelete.setVisibility(View.VISIBLE);

        enableAllFields();
    }
}
