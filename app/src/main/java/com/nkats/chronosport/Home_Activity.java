package com.nkats.chronosport;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nkats.chronosport.database.DataBaseHelper;

public class Home_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        DataBaseHelper.getInstance().openDataBase();
    }
}
