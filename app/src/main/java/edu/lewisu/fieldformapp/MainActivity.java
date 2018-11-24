package edu.lewisu.fieldformapp;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends SQLHandler {

//    ItemOneFragment itemOneFrag;
//    ItemTwoFragment itemTwoFrag;
//    ItemThreeFragment itemThreeFrag;

    SQLDatabase sql;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sql = new SQLDatabase(MainActivity.this);

        itemOneFrag = ItemOneFragment.newInstance();
        itemTwoFrag = ItemTwoFragment.newInstance(this);
        itemThreeFrag = ItemThreeFragment.newInstance(this);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.main_frame_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId())
                        {
                            case R.id.navigation_forms:
                                selectedFragment = itemOneFrag;
                                break;
                            case R.id.navigation_reports:
                                selectedFragment = itemTwoFrag;
                                break;
                            case R.id.navigation_settings:
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
        transaction.replace(R.id.main_frame_layout, itemOneFrag);
        transaction.commit();
    }


    public String[] getRowDataString() {
        return sql.getRowData();
    }

    /////////////////////////////
    // Troubleshooting methods //
    /////////////////////////////
    // TODO Remove these when app completed
    // For the time being this method will generate input for us to use while troubleshooting.  Feel
    // free to add and remove the save statements to create whatever is needed for your testing.
    public void generateData(View v)
    {
        sql.open();
        sql.save(new String[] {"","R","Adam", "R", "Zas", "123 Main St", "Glen Ellyn", "IL", "60148", "DuPage", "04.19.1982","M","100001","123456789", "6308881234", "test@email.com", "Email", "Main St HS", "2004", "Computers", "Games", "Lego", "None", "N/A", "Clean bill of health","true","","","","","","",});
        sql.save(new String[] {"","R","Miles", "Tails", "Prower", "456 Washington Dr", "Woodridge", "MN", "27649", "McCook", "01.27.1998","M","001000", "456789123", "4249194748", "test2@email.com", "Phone", "Lincoln HS", "2007", "Mathematics", "Chess", "Miniature Models", "Pell Grant", "N/A", "Clean bill of health","true","None","","","","","",});
        sql.save(new String[] {"","H","Charlotte", "Marie", "Test", "789 Capital Ave", "Lombard", "OH", "76285", "Lake", "09.09.2001","F","010100", "789123456", "9818534567", "test3@email.com", "Email", "Jackson HS", "2013", "Art", "Debate", "Volleyball", "None", "N/A", "Clean bill of health","true","Daily Probiotics","Pollen","Influenza Shot","Gluten Sensitivity","None","None",});
        sql.close();
        Toast.makeText(MainActivity.this, "Saved Successfully", Toast.LENGTH_SHORT).show();
    }

    public void deleteData(View v)
    {
        sql.open();
        for (int i = 0; i < 25; i++)
            if (sql.doesIdExist(i))
                sql.deleteRecord(i);
        sql.close();
    }

    // Displays all SQL records stored in the table
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

}
