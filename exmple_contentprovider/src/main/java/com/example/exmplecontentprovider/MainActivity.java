package com.example.exmplecontentprovider;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    EditText name;
    EditText phone;
    EditText companyid;
    EditText idcompany;
    EditText companywork;
    EditText companyaddress;

    Button saveperson;
    Button savecompany;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidgets();

    }

    private void initWidgets()
    {
        //用户信息相关的View
        name=(EditText)findViewById(R.id.name);
        phone=(EditText)findViewById(R.id.phone);
        companyid=(EditText)findViewById(R.id.companyid);

        saveperson=(Button)findViewById(R.id.saveperson);
        saveperson.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view)
            {
                saveUserInfoRecord();
                saveperson.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        queryPostCode();
                    }
                }, 1000);

            }
        });



        //公司信息相关的View
        idcompany=(EditText)findViewById(R.id.idcompany);
        companywork=(EditText)findViewById(R.id.companywork);
        companyaddress=(EditText)findViewById(R.id.companyaddress);

        savecompany=(Button)findViewById(R.id.savecompany);
        savecompany.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view)
            {
               saveCompanyRecord();


            }
        });
    }



    //存储用户信息到ContentProvider
    private void saveUserInfoRecord()
    {
        ContentValues newRecord = new ContentValues();
        newRecord.put(UserInfoDbHelper.DESC_COLUMN, name.getText().toString());
        newRecord.put(UserInfoDbHelper.PHONE_COLUMN, phone.getText().toString());
        newRecord.put(UserInfoDbHelper.COMPANY_ID_COLUMN, companyid.getText().toString());
        getContentResolver().insert(UserInfoProvider.POSTCODE_URI, newRecord);
    }

    //存储公司信息到ContentProvider
    private void saveCompanyRecord()
    {
        ContentValues newRecord = new ContentValues();
        newRecord.put(UserInfoDbHelper.ADDRESS_COLUMN, companyaddress.getText().toString());
        newRecord.put(UserInfoDbHelper.BUSINESS_COLUMN, companywork.getText().toString());
        newRecord.put(UserInfoDbHelper.ID_COLUMN, idcompany.getText().toString());
        getContentResolver().insert(UserInfoProvider.COMPANY_URI, newRecord);
    }

    //通过电话号码查询相关信息
    private void queryPostCode()
    {
        Uri queryUri = Uri.parse("content://.UserInfoProvider/userinfo/123456");
        Cursor cursor=getContentResolver().query(queryUri, null, null, null, null);
        if(cursor.moveToFirst())
        {
            Toast.makeText(this, "电话来自 : " + cursor.getString(2), Toast.LENGTH_SHORT).show();
        }
        cursor.close();
    }


}
