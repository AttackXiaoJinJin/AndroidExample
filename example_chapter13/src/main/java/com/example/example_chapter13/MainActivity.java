package com.example.example_chapter13;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      /*  Person person=new Person();
        person.setName("CJ");
        person.setAge(22);
        Intent intent=new Intent(FirstActivity.this, SecondActivity.class);
        intent.putExtra("person_data", person);
        startActivity(intent);*/

       // Person person=(Person)getIntent().getSerializableExtra("person_data");

       LogUtil.d("TAG", "debug log");
        LogUtil.w("TAG", "warn log");

    }
}
