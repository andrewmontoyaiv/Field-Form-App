package edu.lewisu.fieldformapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
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
    TextView currID;

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

    public void updateData(View v)
    {
        String[] modifiedRecord = new String [25];
        modifiedRecord[0] = currID.getText().toString();
        modifiedRecord[1] = firstName.getText().toString();
        modifiedRecord[2] = middleName.getText().toString();
        modifiedRecord[3] = lastName.getText().toString();
        modifiedRecord[4] = Address.getText().toString();
        modifiedRecord[5] = City.getText().toString();
        modifiedRecord[6] = State.getText().toString();
        modifiedRecord[7] = Zip.getText().toString();
        modifiedRecord[8] = County.getText().toString();
        modifiedRecord[9] = dateOfBirth.getText().toString();
        modifiedRecord[10] = Gender.getText().toString();
        modifiedRecord[11] = Ethnicity.getText().toString();
        modifiedRecord[12] = SSNum.getText().toString();
        modifiedRecord[13] = phoneNum.getText().toString();
        modifiedRecord[14] = email.getText().toString();
        modifiedRecord[15] = contactPref.getText().toString();
        modifiedRecord[16] = highSchool.getText().toString();
        modifiedRecord[17] = gradYear.getText().toString();
        modifiedRecord[18] = programOfInterest.getText().toString();
        modifiedRecord[19] = extraCurricularActivities.getText().toString();
        modifiedRecord[20] = Hobbies.getText().toString();
        modifiedRecord[21] = Scholarships.getText().toString();
        modifiedRecord[22] = finanAid.getText().toString();
        modifiedRecord[23] = medInfo.getText().toString();
        modifiedRecord[24] = Consent.getText().toString();



        sql.open();
        sql.editRecord(modifiedRecord);
        sql.close();
        Toast.makeText(this, "Updated Successfully", Toast.LENGTH_SHORT).show();


        finish();
    }


    public void openRecord(String[] recordData) {
        firstName.setText(recordData[0]);
        middleName.setText(recordData[1]);
        lastName.setText(recordData[2]);
        Address.setText(recordData[3]);
        City.setText(recordData[4]);
        State.setText(recordData[5]);
        Zip.setText(recordData[6]);
        County.setText(recordData[7]);
        dateOfBirth.setText(recordData[8]);
        Gender.setText(recordData[9]);
        Ethnicity.setText(recordData[10]);
        SSNum.setText(recordData[11]);
        phoneNum.setText(recordData[12]);
        email.setText(recordData[13]);
        contactPref.setText(recordData[14]);
        highSchool.setText(recordData[15]);
        gradYear.setText(recordData[16]);
        programOfInterest.setText(recordData[17]);
        extraCurricularActivities.setText(recordData[18]);
        Hobbies.setText(recordData[19]);
        Scholarships.setText(recordData[20]);
        finanAid.setText(recordData[21]);
        medInfo.setText(recordData[22]);
        Consent.setText(recordData[23]);
    }
}
