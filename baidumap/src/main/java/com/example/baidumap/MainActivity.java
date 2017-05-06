package com.example.baidumap;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.MyLocationData;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public LocationClient locationClient;
    private TextView postionText;
    private com.baidu.mapapi.map.MapView mapView;

    private com.baidu.mapapi.map.BaiduMap baiduMap;
    //防止多次调用animateMapStatus()方法
    private boolean isFirstLocate=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //获取全局的Context
        locationClient=new LocationClient(getApplicationContext());
        //注册一个定位监听器，当获取到位置信息后，会回调这个定位监听器
        locationClient.registerLocationListener(new MyLocationListener());
        //初始化操作，获取全局的context参数并传入
        com.baidu.mapapi.SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        mapView=(com.baidu.mapapi.map.MapView)findViewById(R.id.baiduMapView);
        baiduMap=mapView.getMap();
        //设置自己的位置信息
        baiduMap.setMyLocationEnabled(true);



        postionText=(TextView)findViewById(R.id.position_textView);
        List<String> permissionList=new ArrayList<>();
        if(ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED)
        {permissionList.add(android.Manifest.permission.ACCESS_FINE_LOCATION);}

        if(ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.READ_PHONE_STATE)!=PackageManager.PERMISSION_GRANTED)
        {permissionList.add(android.Manifest.permission.READ_PHONE_STATE);}

        if(ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED)
        {permissionList.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE);}

        if(!permissionList.isEmpty())
        {
            //将list转换为数组
            String[] permissions=permissionList.toArray(new String[permissionList.size()]);
            //一次性申请
            ActivityCompat.requestPermissions(MainActivity.this, permissions, 1);
        }
        else
        {
            requestLocation();
        }

    }


    private void requestLocation()
    {
        initLocation();
        locationClient.start();
    }

    private void initLocation()
    {
        LocationClientOption locationClientOption=new LocationClientOption();
        //设置更新的间隔
        locationClientOption.setScanSpan(5000);
        //需要地址详细信息
        locationClientOption.setIsNeedAddress(true);
        //强制指定只使用GPS模式
        //locationClientOption.setLocationMode(LocationClientOption.LocationMode.Device_Sensors);
        locationClient.setLocOption(locationClientOption);

    }


    @Override
    protected void onResume()
    {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        mapView.onPause();
    }

    //停止定位，不然会在后台持续定位，造成电池消耗！
    @Override
    protected void onDestroy() {
        super.onDestroy();
        locationClient.stop();
        mapView.onDestroy();
        baiduMap.setMyLocationEnabled(false);

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] perimissions, int[] grantResults)
    {
        switch (requestCode)
        {
            case 1:if(grantResults.length>0){
                for(int result: grantResults)
                {
                    if(result!= PackageManager.PERMISSION_GRANTED)
                    {
                        Toast.makeText(this, "必须同意所有权限才能使用本程序", Toast.LENGTH_SHORT).show();
                       finish();
                        return;
                    }
                }
                requestLocation();
            }
            else
            {
                Toast.makeText(this, "发生未知错误", Toast.LENGTH_SHORT).show();
                finish();
            }
            break;
            default:
        }
    }

    private void navigateTo(BDLocation bdLocation)
    {
        if(isFirstLocate)
        {
            //纬度，经度
            com.baidu.mapapi.model.LatLng latLng=new com.baidu.mapapi.model.LatLng(bdLocation.getLatitude(), bdLocation.getLongitude());
            com.baidu.mapapi.map.MapStatusUpdate mapStatusUpdate= com.baidu.mapapi.map.MapStatusUpdateFactory.newLatLng(latLng);
            baiduMap.animateMapStatus(mapStatusUpdate);
            mapStatusUpdate= com.baidu.mapapi.map.MapStatusUpdateFactory.zoomTo(16f);
            baiduMap.animateMapStatus(mapStatusUpdate);
        }

        MyLocationData.Builder builder=new MyLocationData.Builder();
        builder.latitude(bdLocation.getLatitude());//纬度
        builder.longitude(bdLocation.getLongitude());//经度
        MyLocationData myLocationData=builder.build();
        baiduMap.setMyLocationData(myLocationData);



    }




    public class MyLocationListener implements BDLocationListener
    {
        @Override
        public void onReceiveLocation(BDLocation bdLocation)
        {
           /* StringBuilder currentPosition=new StringBuilder();
            currentPosition.append("纬度：").append(bdLocation.getLatitude()).append("\n");
            currentPosition.append("经线：").append(bdLocation.getLongitude()).append("\n");
            currentPosition.append("国家：").append(bdLocation.getCountry()).append("\n");
            currentPosition.append("省：").append(bdLocation.getProvince()).append("\n");
            currentPosition.append("市：").append(bdLocation.getCity()).append("\n");
            currentPosition.append("区：").append(bdLocation.getDistrict()).append("\n");
            currentPosition.append("街道：").append(bdLocation.getStreet()).append("\n");


            currentPosition.append("定位方式：");
            if(bdLocation.getLocType()==BDLocation.TypeGpsLocation)
            {
                currentPosition.append("GPS");
            }
            else if (bdLocation.getLocType()==BDLocation.TypeNetWorkLocation)
            {
                currentPosition.append("网络");
            }

            postionText.setText(currentPosition);*/
           if(bdLocation.getLocType()==BDLocation.TypeGpsLocation || bdLocation.getLocType()==BDLocation.TypeNetWorkLocation)
           {
               navigateTo(bdLocation);
           }

        }

        @Override
        public void onConnectHotSpotMessage(String a, int b)
        {

        }

    }
}
