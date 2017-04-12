package com.example.exmplecontentprovider;

/*
 * Created by chenjin on 2017/4/10.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserInfoDbHelper extends SQLiteOpenHelper
{   //数据库表名，列名提取城全局静态变量
    private static final String DB_NAME = "userinfo.db";
    private static final int DB_VERSION = 1;

    public static final String TABLE_USER_INFO = "userinfo";
    public static final String TABLE_COMPANY = "company";

    public static final String PHONE_COLUMN = "phone_num";
    public static final String DESC_COLUMN = "desc";
    public static final String COMPANY_ID_COLUMN = "company_id";
    public static final String ID_COLUMN = "id";
    public static final String BUSINESS_COLUMN = "business";
    public static final String ADDRESS_COLUMN = "address";

    //SQL语句
    private static final String POSTCODE_TABLE_SQL = " CREATE TABLE IF NOT EXISTS " + TABLE_USER_INFO + " ( " + PHONE_COLUMN + " TEXT , " + COMPANY_ID_COLUMN + " INTEGER , " + DESC_COLUMN + " TEXT " + " ) " ;

    private static final String COMPANY_TABLE_SQL = " CREATE TABLE IF NOT EXISTS " + TABLE_COMPANY + " ( " + ID_COLUMN + " INTEGER PRIMARY KEY , " + BUSINESS_COLUMN + " TEXT , " + ADDRESS_COLUMN + " TEXT " + " ) " ;
    //构造方法
    public UserInfoDbHelper(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }
    //数据表的创建
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(POSTCODE_TABLE_SQL);
        db.execSQL(COMPANY_TABLE_SQL);
    }
     //数据表的更新
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {}
}
