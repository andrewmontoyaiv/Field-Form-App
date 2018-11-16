package edu.lewisu.fieldformapp;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends SQLHandler {

    ItemOneFragment itemOneFrag;
    ItemTwoFragment itemTwoFrag;
    ItemThreeFragment itemThreeFrag;

    RecyclerView rvFrag2;

    EditText firstName, middleName, lastName, Address, City, State, Zip, County, dateOfBirth,
            Gender, Ethnicity, SSNum, phoneNum, email, contactPref, highSchool,
            gradYear, programOfInterest, extraCurricularActivities, Hobbies,
            Scholarships, finanAid, medInfo, Consent;
    SQLDatabase sql;

// TODO Remove unneeded code
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        firstName=(EditText)findViewById(R.id.fname);
//        middleName=(EditText)findViewById(R.id.mname);
//        lastName=(EditText)findViewById(R.id.lname);
//        Address=(EditText)findViewById(R.id.fname);
//        City=(EditText)findViewById(R.id.city);
//        State=(EditText)findViewById(R.id.state);
//        Zip=(EditText)findViewById(R.id.zip);
//        County=(EditText)findViewById(R.id.county);
//        dateOfBirth=(EditText)findViewById(R.id.dob);
//        Gender=(EditText)findViewById(R.id.gender);
//        Ethnicity=(EditText)findViewById(R.id.ethnicity);
//        SSNum=(EditText)findViewById(R.id.ssnum);
//        phoneNum=(EditText)findViewById(R.id.phoneNum);
//        email=(EditText)findViewById(R.id.email);
//        contactPref=(EditText)findViewById(R.id.contactPref);
//        highSchool=(EditText)findViewById(R.id.highschool);
//        gradYear=(EditText)findViewById(R.id.gradYear);
//        programOfInterest=(EditText)findViewById(R.id.poi);
//        extraCurricularActivities=(EditText)findViewById(R.id.eca);
//        Hobbies=(EditText)findViewById(R.id.hobbies);
//        Scholarships=(EditText)findViewById(R.id.scholarship);
//        finanAid=(EditText)findViewById(R.id.financialAid);
//        medInfo=(EditText)findViewById(R.id.medInfo);
//        Consent=(EditText)findViewById(R.id.consent);
//        sql=new SQLDatabase(MainActivity.this);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sql = new SQLDatabase(MainActivity.this);

        FragmentManager fm = getSupportFragmentManager();
        //itemOneFrag = ItemOneFragment.newInstance(this);
        itemTwoFrag = ItemTwoFragment.newInstance(this);
        itemThreeFrag = ItemThreeFragment.newInstance(this);

        fm.findFragmentById(R.id.button2);

        rvFrag2 = findViewById(R.id.rvFormsFrag2);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.main_frame_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId())
                        {
                            case R.id.navigation_forms:
                                selectedFragment = ItemOneFragment.newInstance();
                                break;
                            case R.id.navigation_reports:
//                                selectedFragment = ItemTwoFragment.newInstance();
                                selectedFragment = itemTwoFrag;
                                break;
                            case R.id.navigation_settings:
//                                selectedFragment = ItemThreeFragment.newInstance();
                                selectedFragment = itemThreeFrag;
                                break;
                    }

                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.main_frame_layout, selectedFragment);
                    transaction.commit();
                    return true;
                    }
                });

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_frame_layout, ItemOneFragment.newInstance());
        transaction.commit();
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

    // For the time being this method will generate input for us to use while troubleshooting.  Feel
    // free to add and remove the save statements to create whatever is needed for your testing.
    public void generateData(View v)
    {
        sql.open();
        sql.save("Adam", "R", "Zas", "123 Main St", "Glen Ellyn", "IL", "60148", "DuPage", "04.19.1982", "Male", "Caucasian", "123-45-6789", "(630) 888-1234", "test@email.com", "Email", "Main St HS", "2004", "Computers", "Games", "Lego", "None", "N/A", "Clean bill of health", "Yes");
        sql.save("Miles", "Tails", "Prower", "456 Washington Dr", "Woodridge", "MN", "27649", "McCook", "01.27.1998", "Male", "Hispanic", "456-78-9123", "(424) 919-2748", "test2@email.com", "Phone", "Lincoln HS", "2007", "Mathematics", "Chess", "Miniature Models", "Pell Grant", "N/A", "Clean bill of health", "Yes");
        sql.save("Charlotte", "Marie", "Test", "789 Capital Ave", "Lombard", "OH", "76285", "Lake", "09.09.2001", "Feale", "Caucasian", "789-12-3456", "(981) 853-4567", "test3@email.com", "Email", "Jackson HS", "2013", "Art", "Debate", "Volleyball", "None", "N/A", "Clean bill of health", "Yes");
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

//    public void getRows(View v) {
//        String[] rowData = sql.getRowData();
//
//        String rowID, rowFN, rowMN, rowLN;
//        String tempID, tempFN, tempMN, tempLN;
//        // Starting at 1 since the headers automatically take 1 place
//        int numRows = 1;
//
//        rowID = rowData[0];
//        rowFN = rowData[1];
//        rowMN = rowData[2];
//        rowLN = rowData[3];
//
//        for (int i = 0; i < rowID.length(); i++)
//            if (rowData[0].charAt(i) == ',')
//                numRows++;
//
//        RowItemData[] rowItems = new RowItemData[numRows+1];
//
//        for (int i = 0; i < numRows; i++) {
//            tempID = rowID.substring(0,rowID.indexOf(','));
//            tempFN = rowFN.substring(0,rowFN.indexOf(','));
//            tempMN = rowMN.substring(0,rowMN.indexOf(','));
//            tempLN = rowLN.substring(0,rowLN.indexOf(','));
//
//
//            if (i < (numRows - 1)) {
//                rowID = rowID.substring(rowID.indexOf(',') + 1);
//                rowFN = rowFN.substring(rowFN.indexOf(',') + 1);
//                rowMN = rowMN.substring(rowMN.indexOf(',') + 1);
//                rowLN = rowLN.substring(rowLN.indexOf(',') + 1);
//            }
//
//            if (i != 0)
//                rowItems[i-1] = new RowItemData(tempID, tempFN, tempMN, tempLN);
//        }
//
////        setContentView(v);
//
//        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvFormsFrag2);
//        // Code excecutes until this point
//        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
//        RowItemAdapter rowItemAdapter = new RowItemAdapter(rowItems);
//        recyclerView.setAdapter(rowItemAdapter);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//
//        // Reset ContentView to the main activity
////        setContentView(R.layout.activity_main);
//    }

    public String[] getRowDataString() {
        return sql.getRowData();
    }

//    public void guiLoad(View v)
//    {
//        Intent intent = new Intent(this, forms_list.class);
//        String tempAddress = sql.get();
////        intent.putExtra("SQLDB",tempAddress);
//        startActivity(intent);
//    }
}
