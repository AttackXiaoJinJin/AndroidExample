package com.project.chenjin.example_cameraalbum;

import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    public static final int TAKE_PHOTO=1;
    private ImageView picture;
    private Uri imgUri;
    public static final int CHOOSE_PHOTO=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button takePhoto=(Button)findViewById(R.id.take_photo);
        picture=(ImageView)findViewById(R.id.picture);
        Button chooseFromAlbum=(Button)findViewById(R.id.choose_from_album);

        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建File对象,用于存储拍照后的照片,将图片命名为output_image.jpg
                //getExternalCacheDir(),SD卡的应用关联缓存目录
                File outputImage=new File(getExternalCacheDir(), "output_image.jpg");
                try{
                    if (outputImage.exists())
                    {
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }

                //Android7.0开始，直接使用本地真实路径被认为是不安全的，会抛出FileUriExposedException异常。

                if(Build.VERSION.SDK_INT>=24)
                {   //将File对象转换为封装过的Uri对象
                                                      //Context对象，任意唯一的字符串，File对象
                    imgUri= FileProvider.getUriForFile(MainActivity.this, "com.project.chenjin.example_cameraalbum.fileprovider", outputImage);
                    //FildeProvider是一种特殊的内容提供器，使用和内容提供器类似的机制来对数据进行保护，可以选择性地将封装过的Uri共享给外部，从而提供应用的安全性
                }
                else {
                    //将File对象转换为Uri对象，Uri标识着图片本地的真实路径
                    imgUri=Uri.fromFile(outputImage);
                }
                //启动相机程序,将Intent的Action指定为android.media.action.IMAGE_CAPTURE
                //隐式intent
                Intent intent=new Intent("android.media.action.IMAGE_CAPTURE");
                //指定图片的输出地址
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imgUri);
                //系统会找到能够响应 该Intent的活动去启动
                //forResult会把结果返回到onActivityResult()方法中
                startActivityForResult(intent, TAKE_PHOTO);



            }
        });


        chooseFromAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                }
                else
                {
                    openAlbum();
                }
            }
        });


}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        switch(requestCode){
            case 1:
                //若拍照成功
                if(resultCode==RESULT_OK)
                {
                    try {
                        //解析成Bitmap对象
                        Bitmap bitmap= BitmapFactory.decodeStream(getContentResolver().openInputStream(imgUri));
                        //将拍摄的照片显示出来,picture即ImageView
                        picture.setImageBitmap(bitmap);

                    }
                    catch (FileNotFoundException e)
                    {
                        e.printStackTrace();
                    }
                }
                break;

            case 2:
                if(resultCode==RESULT_OK){
                    //判断手机系统版本号
                    if(Build.VERSION.SDK_INT>=19){
                        //4.4及以上系统使用这个方法处理图片
                        handleImageOnKitKat(data);
                    }
                    else{
                        //4.4以下系统使用该方法处理图片
                        handleImageBeforeKitKat(data);
                    }

                }
                break;
            default:break;
        }
    }


    @TargetApi(19)
    private void handleImageOnKitKat(Intent data){
        String imagePath=null;
        Uri uri=data.getData();
        if(DocumentsContract.isDocumentUri(this, uri)){
            //如果是document类型的Uri,则通过document id处理
            String docId=DocumentsContract.getDocumentId(uri);
            if("com.android.providers.media.documents".equals(uri.getAuthority())){
                //解析出数字格式的id
                String id=docId.split(":")[1];
                String selection=MediaStore.Images.Media._ID + "=" + id;
                imagePath=getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);

            }
            else if("com.android.providers.downloads.documents".equals(uri.getAuthority())){
                Uri contentUri= ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath=getImagePath(contentUri, null);

            }
        }
        else if("content".equalsIgnoreCase(uri.getScheme())){
            //如果是content类型的Uri,则使用普通方式处理
            imagePath=getImagePath(uri, null);
        }
        else if("file".equalsIgnoreCase(uri.getScheme())){
            //如果是file类型的Uri,直接获取图片路径即可
            imagePath=uri.getPath();

        }
        //根据图片路径显示图片
        displayImage(imagePath);

    }


    private void handleImageBeforeKitKat(Intent data){
        Uri uri=data.getData();
        String imagePath=getImagePath(uri, null);
        displayImage(imagePath);

    }

    private String getImagePath(Uri uri, String selection){
        String path=null;
        //通过Uri和selection来获取真实的图片路径
        Cursor cursor=getContentResolver().query(uri, null, selection, null, null);
        if(cursor !=null){
            if(cursor.moveToFirst()){
                path=cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;

    }


    private void displayImage(String imagePath){
        if(imagePath != null){
            Bitmap bitmap=BitmapFactory.decodeFile(imagePath);
            picture.setImageBitmap(bitmap);
        }
        else{
            Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show();
        }
    }



    private void openAlbum(){
        Intent intent=new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        //打开相册
        startActivityForResult(intent, CHOOSE_PHOTO);


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {
        switch (requestCode){
            case 1:
                if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    openAlbum();
                }
                else{
                    Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show();
                }
                break;

            default:
        }
    }









}
