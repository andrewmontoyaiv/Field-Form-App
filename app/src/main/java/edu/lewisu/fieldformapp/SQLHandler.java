package edu.lewisu.fieldformapp;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class SQLHandler extends AppCompatActivity {
    // TODO: Factor out existing sql login into here
    String[] fieldNames = {"currID", "formType", "fName", "mName",
            "lName", "address", "city", "state",
            "zip", "county", "dob", "gender",
            "ethnicity", "ssnum", "phoneNum", "email",
            "contactPref", "highSchool", "gradYear", "poi",
            "eca", "hobbies", "scholarship", "financialAid",
            "medInfo", "consent", "medication", "allergy",
            "immunization", "dietaryRestriction", "illnessHistory", "drugHistory"};

    // T - EditText, R - Radio, C - Checkbox
    char[] fieldTypes = {'T','T','T','T',
                        'T','T','T','T',
                        'T','T','R','C',
                        'T','T','T','T',
                        'T','T','T','T',
                        'T','T','T','T',
                        'T','C','T','T',
                        'T','T','T','T'};
    // B - Both, R - Recruiter, H - Healthcare
    char[] requiredFields ={'B','B','B','B',
                            'B','B','B','B',
                            'B','B','B','B',
                            'B','B','B','B',
                            'B','R','R','R',
                            'R','B','R','R',
                            'B','B','H','H',
                            'H','H','H','H'};


    ItemOneFragment itemOneFrag;
    ItemTwoFragment itemTwoFrag;
    ItemThreeFragment itemThreeFrag;

    EditText firstName, middleName, lastName, address, city, state, zip, county, dateOfBirth,
            ssnum, phoneNum, email, contactPref, highSchool, gradYear, programOfInterest,
            extraCurricularActivities, hobbies, scholarship, finanAid, medInfo, medication,
            allergy, immunization, dietaryRestriction, illnessHistory, drugHistory;
    RadioGroup genderRadioGroup;
    RadioButton rdbtn_gender_male, rdbtn_gender_female;
    CheckBox chk_ethnicity_nativeAmerican, chk_ethnicity_asian, chk_ethnicity_black,
            chk_ethnicity_pacific, chk_ethnicity_caucasian, chk_ethnicity_hispanic,
            chk_consent;
    TextView currID, formType;

    SQLDatabase sql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sql = new SQLDatabase(this);
    }



    // defined in the xml
    public void saveData(View v)
    {
        String[] newRecord = getAllFields();

        if (! validateFields(newRecord))
            return;

        sql.open();
        sql.save(newRecord);
        sql.close();
        Toast.makeText(this, "Saved Successfully", Toast.LENGTH_SHORT).show();

        finish();
    }

    String[] getAllFields() {
        String[] currRecord = new String [32];

        currRecord[0] = currID.getText().toString();
        currRecord[1] = formType.getText().toString();
        currRecord[2] = firstName.getText().toString();
        currRecord[3] = middleName.getText().toString();
        currRecord[4] = lastName.getText().toString();
        currRecord[5] = address.getText().toString();
        currRecord[6] = city.getText().toString();
        currRecord[7] = state.getText().toString();
        currRecord[8] = zip.getText().toString();
        currRecord[9] = county.getText().toString();
        currRecord[10] = dateOfBirth.getText().toString();

        // Check Gender Radio Buttons
        if (rdbtn_gender_male.isChecked())
            currRecord[11] = "M"; // Male
        else if (rdbtn_gender_female.isChecked())
            currRecord[11] = "F"; // Female
        else
            currRecord[11] = "N"; // Not Selected


        // Build Ethnicity String
        if (chk_ethnicity_nativeAmerican.isChecked())
            currRecord[12] = "1";
        else
            currRecord[12] = "0";
        if (chk_ethnicity_asian.isChecked())
            currRecord[12] += "1";
        else
            currRecord[12] += "0";
        if (chk_ethnicity_black.isChecked())
            currRecord[12] += "1";
        else
            currRecord[12] += "0";
        if (chk_ethnicity_pacific.isChecked())
            currRecord[12] += "1";
        else
            currRecord[12] += "0";
        if (chk_ethnicity_caucasian.isChecked())
            currRecord[12] += "1";
        else
            currRecord[12] += "0";
        if (chk_ethnicity_hispanic.isChecked())
            currRecord[12] += "1";
        else
            currRecord[12] += "0";

        currRecord[13] = ssnum.getText().toString();
        currRecord[14] = phoneNum.getText().toString();
        currRecord[15] = email.getText().toString();
        currRecord[16] = contactPref.getText().toString();
        currRecord[17] = highSchool.getText().toString();
        currRecord[18] = gradYear.getText().toString();
        currRecord[19] = programOfInterest.getText().toString();
        currRecord[20] = extraCurricularActivities.getText().toString();
        currRecord[21] = hobbies.getText().toString();
        currRecord[22] = scholarship.getText().toString();
        currRecord[23] = finanAid.getText().toString();
        currRecord[24] = medInfo.getText().toString();
        currRecord[25] = String.valueOf(chk_consent.isChecked());
        currRecord[26] = medication.getText().toString();
        currRecord[27] = allergy.getText().toString();
        currRecord[28] = immunization.getText().toString();
        currRecord[29] = dietaryRestriction.getText().toString();
        currRecord[30] = illnessHistory.getText().toString();
        currRecord[31] = drugHistory.getText().toString();

        return currRecord;
    }

    // Validation method that will highlight any and all invalid fields.
    Boolean validateFields(String[] recordFields) { //, String[] fieldNames) {
        Boolean allPass = true;
        TextInputLayout tempTextInputLayout;

        // Variables to hold the name, type, and ID of a particular field.  Parsed from the fieldNames array
        int tempID;
        String tempName, tempType;

        // Starts at 1 since the CurrID field cannot be modified by user
        for (int i = 1; i < recordFields.length; i++) {
            // Is field blank, AND is field used in this form type
            if (recordFields[i].equals("") &&
                    ((requiredFields[i] == 'B') || (requiredFields[i] == recordFields[1].charAt(0)))) {
                tempType = String.valueOf(fieldTypes[i]);
                tempName = fieldNames[i];

//                tempID = getResources().getIdentifier(tempName,"id",getPackageName());

                // Validation for Text Boxes
                if ("T".equals(tempType)) {
                    // Error must be set on the TextInputLayout, not the nested EditText
                    tempName += "Layout";
                    tempID = getResources().getIdentifier(tempName,"id",getPackageName());
                    tempTextInputLayout = (TextInputLayout) findViewById(tempID);
                    tempTextInputLayout.setError("This field cannot be blank");
                }

                allPass = false;
            }
        }

        // Validation for Gender Radio Buttons
        if (recordFields[11].equals("N")) {
            rdbtn_gender_male.setError("Must select a gender option");

            allPass = false;
        }

        // Validation specifically for the Consent checkbox
        if (recordFields[25].equals("false")) {
            chk_consent.setError("Must approve to submit form");

            allPass = false;
        }

        return allPass;
    }

    public void updateData(View v) {
        String[] modifiedRecord = getAllFields();

        if (! validateFields(modifiedRecord))
            return;


        sql.open();
        sql.editRecord(modifiedRecord);
        sql.close();
        Toast.makeText(this, "Updated Successfully", Toast.LENGTH_SHORT).show();

        finish();
    }

    public void deleteData(View v) {
        sql.deleteRecord(Integer.parseInt(currID.getText().toString()));
        finish();
    }
}
