package edu.lewisu.fieldformapp;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class SQLHandler extends AppCompatActivity {

    // TODO: Factor out existing sql login into here

    // Formula = Type + defaultForm.xml Name
    String[] fieldNames = {"TcurrID", "TfName", "TmName", "TlName", "Taddress", "Tcity", "Tstate",
            "Tzip", "Tcounty", "Tdob", "Tgender", "Tethnicity", "Tssnum", "TphoneNum", "Temail",
            "TcontactPref", "ThighSchool", "TgradYear", "Tpoi", "Teca",
            "Thobbies", "Tscholarship", "TfinancialAid", "TmedInfo", "Tconsent"};

    ItemOneFragment itemOneFrag;
    ItemTwoFragment itemTwoFrag;
    ItemThreeFragment itemThreeFrag;

//    char recordChange;

//    RecyclerView rvFrag2;

    EditText firstName, middleName, lastName, address, city, state, zip, County, dateOfBirth,
            gender, ethnicity, ssnum, phoneNum, email, contactPref, highSchool,
            gradYear, programOfInterest, extraCurricularActivities, hobbies,
            scholarship, finanAid, medInfo, consent;
    TextView currID;

    SQLDatabase sql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sql = new SQLDatabase(this);

//        recordChange = 'N';
    }



    // defined in the xml
    public void saveData(View v)
    {
//        String firstName = firstName.getText().toString();
//        String middleName = middleName.getText().toString();
//        String lName = lastName.getText().toString();
//        String add = address.getText().toString();
//        String city = city.getText().toString();
//        String state = state.getText().toString();
//        String zip = zip.getText().toString();
//        String county = County.getText().toString();
//        String DOB = dateOfBirth.getText().toString();
//        String gender = gender.getText().toString();
//        String ethnicity = ethnicity.getText().toString();
//        String SSN = ssnum.getText().toString();
//        String PhoneNum = phoneNum.getText().toString();
//        String eMail = email.getText().toString();
//        String ContactPref = contactPref.getText().toString();
//        String HighSchool = highSchool.getText().toString();
//        String GradYear = gradYear.getText().toString();
//        String ProgramOfInterest = programOfInterest.getText().toString();
//        String ExtraCurricularActivities = extraCurricularActivities.getText().toString();
//        String hobbies = hobbies.getText().toString();
//        String scholarships = scholarship.getText().toString();
//        String FinanAid = finanAid.getText().toString();
//        String MedInfo = medInfo.getText().toString();
//        String consent = consent.getText().toString();
//
//        String[] fieldNames = {"firstName", "middleName", "lastName", "address", "city", "state",
//                "zip", "County", "dateOfBirth", "gender", "ethnicity", "ssnum", "phoneNum", "email",
//                "contactPref", "highSchool", "gradYear", "programOfInterest", "extraCurricularActivities",
//                "hobbies", "scholarship", "finanAid", "medInfo", "consent"};

        String[] newRecord = new String [25];
        newRecord[0] = currID.getText().toString();
        newRecord[1] = firstName.getText().toString();
        newRecord[2] = middleName.getText().toString();
        newRecord[3] = lastName.getText().toString();
        newRecord[4] = address.getText().toString();
        newRecord[5] = city.getText().toString();
        newRecord[6] = state.getText().toString();
        newRecord[7] = zip.getText().toString();
        newRecord[8] = County.getText().toString();
        newRecord[9] = dateOfBirth.getText().toString();
        newRecord[10] = gender.getText().toString();
        newRecord[11] = ethnicity.getText().toString();
        newRecord[12] = ssnum.getText().toString();
        newRecord[13] = phoneNum.getText().toString();
        newRecord[14] = email.getText().toString();
        newRecord[15] = contactPref.getText().toString();
        newRecord[16] = highSchool.getText().toString();
        newRecord[17] = gradYear.getText().toString();
        newRecord[18] = programOfInterest.getText().toString();
        newRecord[19] = extraCurricularActivities.getText().toString();
        newRecord[20] = hobbies.getText().toString();
        newRecord[21] = scholarship.getText().toString();
        newRecord[22] = finanAid.getText().toString();
        newRecord[23] = medInfo.getText().toString();
        newRecord[24] = consent.getText().toString();

        if (! validateFields(newRecord)) //,fieldNames))
            return;

        sql.open();
//        sql.save(firstName,middleName, lName, add, city, state, zip, county, DOB, gender, ethnicity, SSN,
//                PhoneNum, eMail, ContactPref, HighSchool, GradYear,
//                ProgramOfInterest, ExtraCurricularActivities, hobbies,
//                scholarships, FinanAid, MedInfo, consent);
        sql.save(newRecord);
//        int tempIndex = sql.getHighestIndex();
        sql.close();
        Toast.makeText(this, "Saved Successfully", Toast.LENGTH_SHORT).show();


        // Create new RowItemData for ItemTwoFrag rowItemAdapter;
//        itemTwoFrag.rowItemAdapter.addRow(new RowItemData(String.valueOf(tempIndex), newRecord[1], newRecord[2], newRecord[3]));
//        itemTwoFrag.rowItemAdapter.notifyItemChanged(itemTwoFrag.rowItemAdapter.getItemCount()-1);

//        recordChange = 'N';

        finish();
    }

    //TODO The validation methods do work, but need to confirm field names are spelled correctly
    // Validation method that will highlight any and all invalid fields.
    Boolean validateFields(String[] recordFields) { //, String[] fieldNames) {
        Boolean[] isBlank = new Boolean[recordFields.length];
        Boolean allPass = true;
        EditText tempEditText;
        TextInputLayout tempTextInputLayout;
        RadioGroup tempRadio;
        RadioButton tempRButton;

        // Variables to hold the name, type, and ID of a particular field.  Parsed from the fieldNames array
        int tempID;
        String tempName, tempType;


        // Starts at 1 since the CurrID field cannot be modified by user
        for (int i = 1; i < recordFields.length; i++) {
            if ((recordFields[i].equals("")) || (recordFields[i].equals(null))) {
                    //isBlank[i] = true;  // TODO, nest the below code into this loop to remove secondary loop

                    tempType = fieldNames[i].substring(0,1);
                    tempName = fieldNames[i].substring(1);
                    tempID = getResources().getIdentifier(tempName,"id",getPackageName());

//                if (tempType.equals('T')) {
                if ("T".equals(tempType)) {
                    // Error must be set on the TextInputLayout, not the nested EditText
                    tempName += "Layout";
                    tempID = getResources().getIdentifier(tempName,"id",getPackageName());
                    tempTextInputLayout = (TextInputLayout) findViewById(tempID);
                    tempTextInputLayout.setError("This field cannot be blank");

//                    tempEditText = (EditText) findViewById(tempID);
//
//                    tempEditText.setError("This field cannot be blank");
                } else if (tempType.equals('R')) {
                    tempRadio = (RadioGroup) findViewById(tempID);

                    if (tempRadio.getCheckedRadioButtonId() == -1) {
                        // TODO Define what radio buttons are applicable and highlight them with an error
                        tempRButton = (RadioButton) findViewById(tempRadio.getChildAt(0).getId());

                        tempRButton.setError("Must choose one of the options");
                    }
                } else if (tempType.equals('C')) {
                    // TODO Define how validation will work for checkboxes
                }

                allPass = false;
            }
        }

        // Available Types: T - EditText, R - Radio Button, C - Checkbox
//        for (int j = 0; j < isBlank.length; j++) {
//            if (!isBlank[j]) {
//                tempType = fieldNames[j].substring(0,1);
//                tempName = fieldNames[j].substring(1);
//                tempID = getResources().getIdentifier(tempName,"id",getPackageName());
//                if (tempType.equals('T')) {
//                    tempEditText = (EditText) findViewById(tempID);
//
//                    tempEditText.setError("This field cannot be blank");
//                } else if (tempType.equals('R')) {
//                    tempRadio = (RadioGroup) findViewById(tempID);
//
//                    if (tempRadio.getCheckedRadioButtonId() == -1) {
//                        // TODO Define what radio buttons are applicable and highlight them with an error
//                        tempRButton = (RadioButton) findViewById(tempRadio.getChildAt(0).getId());
//
//                        tempRButton.setError("Must choose one of the options");
//                    }
//                } else if (tempType.equals('C')) {
//                    // TODO Define how validation will work for checkboxes
//                }
//            }
//        }

        return allPass;
    }

    public void updateData(View v)
    {
        String[] modifiedRecord = new String [25];
        modifiedRecord[0] = currID.getText().toString();
        modifiedRecord[1] = firstName.getText().toString();
        modifiedRecord[2] = middleName.getText().toString();
        modifiedRecord[3] = lastName.getText().toString();
        modifiedRecord[4] = address.getText().toString();
        modifiedRecord[5] = city.getText().toString();
        modifiedRecord[6] = state.getText().toString();
        modifiedRecord[7] = zip.getText().toString();
        modifiedRecord[8] = County.getText().toString();
        modifiedRecord[9] = dateOfBirth.getText().toString();
        modifiedRecord[10] = gender.getText().toString();
        modifiedRecord[11] = ethnicity.getText().toString();
        modifiedRecord[12] = ssnum.getText().toString();
        modifiedRecord[13] = phoneNum.getText().toString();
        modifiedRecord[14] = email.getText().toString();
        modifiedRecord[15] = contactPref.getText().toString();
        modifiedRecord[16] = highSchool.getText().toString();
        modifiedRecord[17] = gradYear.getText().toString();
        modifiedRecord[18] = programOfInterest.getText().toString();
        modifiedRecord[19] = extraCurricularActivities.getText().toString();
        modifiedRecord[20] = hobbies.getText().toString();
        modifiedRecord[21] = scholarship.getText().toString();
        modifiedRecord[22] = finanAid.getText().toString();
        modifiedRecord[23] = medInfo.getText().toString();
        modifiedRecord[24] = consent.getText().toString();

        if (! validateFields(modifiedRecord))
            return;

        // Old validation method, can be removed
//        for(int i=0; i<25; i++)
//        {
//            if ((modifiedRecord[i].equals ("") || (modifiedRecord [i].equals (null))))
//            {
//                Toast.makeText(this, "Please fix all blank fields!!", Toast.LENGTH_SHORT).show();
//                return;
//            }
//        }

        sql.open();
        sql.editRecord(modifiedRecord);
        sql.close();
        Toast.makeText(this, "Updated Successfully", Toast.LENGTH_SHORT).show();

//        recordChange = 'U';

        finish();
    }

    public void deleteData(View v) {
//        recordChange = 'D';
        sql.deleteRecord(Integer.parseInt(currID.getText().toString()));
        finish();
    }


    public void openRecord(String[] recordData) {
        // Alternative method to restore data by passing in the fieldNames along with the record data
        int tempID;
        EditText tempEdit;

        // TODO - Implement this shorthand elsewhere
        for (int i = 0; i < fieldNames.length; i++) {
            tempID = getResources().getIdentifier(fieldNames[i],"id",getPackageName());

            tempEdit = (EditText) findViewById(tempID);

            tempEdit.setText(recordData[i]);
        }
    }
}
