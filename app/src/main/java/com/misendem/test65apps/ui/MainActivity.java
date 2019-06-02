package com.misendem.test65apps.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.misendem.test65apps.R;
import com.misendem.test65apps.ui.fragments.list_speciality.FragmentListSpecialitys;

public class MainActivity extends AppCompatActivity {

    public static String BACK_STACK = "BACK_STACK";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragmentContainer, FragmentListSpecialitys.newInstance())
                .addToBackStack(MainActivity.BACK_STACK)
                .commit();
    }


}
