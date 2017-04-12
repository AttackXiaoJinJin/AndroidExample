package com.example.exmplecontentprovider;

/*
 * Created by chenjin on 2017/4/10.
 */

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

public class UserInfoProvider extends ContentProvider
{
    private static final String CONTENT = "content://";
    public  static final String AUTHORIY = ".UserInfoProvider";
    //该ContentProvider所返回的数据类型定义、数据集合
    public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd." + AUTHORIY;
    //单项数据
    public static final  String CONTENT_TYPE_ITEM = "vnd.android.cursor.item/vnd." + AUTHORIY;
    //用户数据集合操作时的Uri
    //Uri.parse("content://com.example.exmplecontentprovider.UserInfoProvider/userinfo");
    public static final Uri POSTCODE_URI = Uri.parse(CONTENT + AUTHORIY + "/" + UserInfoDbHelper.TABLE_USER_INFO);
    //公司数据集合操作时的Uri
    //Uri.parse("content://com.example.exmplecontentprovider.UserInfoProvider/company");
    public static final Uri COMPANY_URI = Uri.parse(CONTENT + AUTHORIY + "/" + UserInfoDbHelper.TABLE_COMPANY);

    private UserInfoDbHelper userInfoDbHelper;
    private SQLiteDatabase dbWriter;
    private SQLiteDatabase dbReader;

    static final int USER_INFOS = 0;
    static final int USER_INFO_ITEM = 1;
    static final int COMPANY = 2;
    static final int COMPANY_ITEM = 3;

    static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static
    {
        uriMatcher.addURI(AUTHORIY, "userinfo", USER_INFOS);
        uriMatcher.addURI(AUTHORIY, "userinfo/*", USER_INFO_ITEM);
        uriMatcher.addURI(AUTHORIY, "company", COMPANY);
        uriMatcher.addURI(AUTHORIY, "company/#", COMPANY_ITEM);

    }


    public boolean onCreate()
    {
        /*try {*/
        //数据库表的创建
        userInfoDbHelper = new UserInfoDbHelper(getContext());
        //实例化可读可写对象
        dbWriter=userInfoDbHelper.getWritableDatabase();
        dbReader=userInfoDbHelper.getReadableDatabase();
       /* }
        catch (Exception e)
        {return false;}*/
        return true;
    }


    public String getType(Uri uri)
    {
        switch (uriMatcher.match(uri))
        {
            case USER_INFOS:

            case COMPANY:
                return CONTENT_TYPE;

            case USER_INFO_ITEM:
            case COMPANY_ITEM:
                return CONTENT_TYPE_ITEM;

            default:throw new RuntimeException("错误的Uri");
        }
    }


    //插入
    public  Uri insert(Uri uri, ContentValues values)
    {
        long newId = 0;
        Uri newUri = null;
        switch (uriMatcher.match(uri))
        {
            case USER_INFOS:
                newId = dbWriter.insert(UserInfoDbHelper.TABLE_USER_INFO, null, values);
                newUri = Uri.parse(CONTENT + AUTHORIY + "/" + UserInfoDbHelper.TABLE_USER_INFO+ "/" + newId);
                break;

            case COMPANY:
                newId = dbWriter.insert(UserInfoDbHelper.TABLE_COMPANY, null, values);
                newUri = Uri.parse(CONTENT + AUTHORIY + "/" + UserInfoDbHelper.TABLE_COMPANY + "/" + newId);
                break;
        }

        if(newId > 0)
        {
            return newUri;
        }
        throw new IllegalArgumentException("Failed to insert row into" + uri);
    }
       //数据库关于表的查询
       public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder)
       {
           Cursor cursor=null;
           switch (uriMatcher.match(uri))
           {
               case USER_INFOS:
                   cursor= dbReader.query(UserInfoDbHelper.TABLE_USER_INFO, projection, selection, selectionArgs, null, null, sortOrder);
                   break;
               //getPathSegments得到uri的path部分，并拆分，去掉"/"，取到第一个元素(从第0个开始)。
               case USER_INFO_ITEM:
                   String phone=uri.getPathSegments().get(1);
                   cursor = dbReader.query(UserInfoDbHelper.TABLE_USER_INFO, projection, "phone_num = ?", new String[]{phone}, null, null, sortOrder);
                   break;

               case COMPANY:
                   cursor= dbReader.query(UserInfoDbHelper.TABLE_COMPANY, projection, selection, selectionArgs, null, null, sortOrder);
                   break;

               case COMPANY_ITEM:
                   String cid=uri.getPathSegments().get(1);
                   cursor = dbReader.query(UserInfoDbHelper.TABLE_COMPANY, projection, "id = ?", new String[]{cid}, null, null, sortOrder);
                   break;
           }

           return cursor;
       }

    public Bundle call(String method, String arg, Bundle extras) {
        return super.call(method, arg, extras);
    }




    //更新
       public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs)
       {
           return  0;
       }
     //删除
    public int delete(Uri arg0, String arg1, String[] arg2)
    {
        return  0;
    }

}
