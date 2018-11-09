package edu.lewisu.fieldformapp;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

public class MainActivity extends BaseActivity {

    EditText firstName, middleName, lastName, Address, City, State, Zip, County, dateOfBirth,
            Gender, Ethnicity, SSNum, phoneNum, email, contactPref, highSchool,
            gradYear, programOfInterest, extraCurricularActivities, Hobbies,
            Scholarships, finanAid, medInfo, Consent;
    SQLDatabase sql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstName=(EditText)findViewById(R.id.editText);
        middleName=(EditText)findViewById(R.id.editText);
        lastName=(EditText)findViewById(R.id.editText);
        Address=(EditText)findViewById(R.id.editText);
        City=(EditText)findViewById(R.id.editText);
        State=(EditText)findViewById(R.id.editText);
        Zip=(EditText)findViewById(R.id.editText);
        County=(EditText)findViewById(R.id.editText);
        dateOfBirth=(EditText)findViewById(R.id.editText);
        Gender=(EditText)findViewById(R.id.editText);
        Ethnicity=(EditText)findViewById(R.id.editText);
        SSNum=(EditText)findViewById(R.id.editText);
        phoneNum=(EditText)findViewById(R.id.editText);
        email=(EditText)findViewById(R.id.editText);
        contactPref=(EditText)findViewById(R.id.editText);
        highSchool=(EditText)findViewById(R.id.editText);
        gradYear=(EditText)findViewById(R.id.editText);
        programOfInterest=(EditText)findViewById(R.id.editText);
        extraCurricularActivities=(EditText)findViewById(R.id.editText);
        Hobbies=(EditText)findViewById(R.id.editText);
        Scholarships=(EditText)findViewById(R.id.editText);
        finanAid=(EditText)findViewById(R.id.editText);
        medInfo=(EditText)findViewById(R.id.editText);
        Consent=(EditText)findViewById(R.id.editText);
        sql=new SQLDatabase(MainActivity.this);
    }

    // defined in the xml
    public void click(View v)
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
        Toast.makeText(MainActivity.this, "Saved Successfully", Toast.LENGTH_SHORT).show();

    }

    public void view(View v)
    {
        String txt=sql.get();
        Dialog d=new Dialog(MainActivity.this);
        d.setTitle("Form Data");
        TextView tv=new TextView(MainActivity.this);
        tv.setText(txt);
        d.setContentView(tv);
        d.show();
    }

    public void guiLoad(View v)
    {
        Intent intent = new Intent(this, forms_list.class);
        String tempAddress = sql.get();
        intent.putExtra("SQLDB",tempAddress);
        startActivity(intent);
    }

//    public SQLDatabase getSQLString() {
//        return this.sql;
//    }
}
