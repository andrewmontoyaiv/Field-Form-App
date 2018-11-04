package edu.lewisu.fieldformapp;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends BaseActivity {

    EditText firstName,Address;
    SQLDatabase sql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstName=(EditText)findViewById(R.id.editText);
        Address=(EditText)findViewById(R.id.editText2);
        sql=new SQLDatabase(MainActivity.this);
    }

    // defined in the xml
    public void click(View v)
    {
        String fName = firstName.getText().toString();
        String add = Address.getText().toString();
        sql.open();
        sql.save(fName, add);
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
}
