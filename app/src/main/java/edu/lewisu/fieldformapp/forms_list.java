package edu.lewisu.fieldformapp;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;

public class forms_list extends AppCompatActivity {

    SQLDatabase sql;
    private TextView mTextMessage;
    private TextView message;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_forms:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_reports:
                    mTextMessage.setText(R.string.title_reports);
                    return true;
                case R.id.navigation_settings:
                    mTextMessage.setText(R.string.title_settings);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forms_list);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        // Tests to use data from the SQL database

        Bundle b = getIntent().getExtras();

        //if (b!=null)
//        sql = this.getReadableDatabase();

        String tempSQL = (String) b.getString("SQLDB");
        ItemData itemsData2[] = new ItemData[5];

        // Output from the SQL database at the moment of loading
        mTextMessage.setText(tempSQL);

        // TODO Find way to parse text from the output.
//        String tempSub, tempSub2;
//        int i = 0, j, k, l;
//
//        while (tempSQL != null) {
//            j = tempSQL.indexOf(',');
//            k = tempSQL.substring(j+1).indexOf(',');
//            l = tempSQL.substring(k+1).indexOf('\n');
//            tempSub = tempSQL.substring(j+1, k);
//            if (l != -1) {
//                tempSub2 = tempSQL.substring(k + 1, l);
//                itemsData2[i] = new ItemData(tempSub, tempSub2);
//                break();
//            }
//            else
//                tempSub2 = tempSQL.substring(k+1);
//            tempSQL = tempSQL.substring(l);
////            if (tempSQL.indexOf('\n') != -1) {
////                tempSub2 = tempSQL.substring(0, tempSQL.indexOf('\n'));
////                tempSQL = tempSQL.substring(tempSQL.indexOf('\n' + 1));
////            } else
////                tempSub2 = tempSQL;
//            itemsData2[i] = new ItemData(tempSub, tempSub2);
//            i++;
//        }

        // Testing RecyclerView with static data entries
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvForms);
        ItemData itemsData[] = { new ItemData("Sample Form 1","11.01.2018"),
                new ItemData("Sample Form 2", "10.31.2018"),
                new ItemData("Sample Form 3", "10.16.2018")};
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MyAdapter myAdapter = new MyAdapter(itemsData);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }

}
