# AndroidExample
0、说明
0.（1）配置

![Image text](https://raw.githubusercontent.com/AttackXiaoJinJin/AndroidExample/master/img-androidexample/src/main/res/drawable/配置1.JPG)

0.（2）关于代码的解释基本在代码里面，有的还没添加，待定

1、实现SSO远程登录（example_aidl_server，exampleaidl_client）
需要注意的是将client端的AIDL文件夹复制到server端下,令它们的包名、类名完全一致，生成的SsoAuth.java类完全一致。

效果图：

![Image text](https://raw.githubusercontent.com/AttackXiaoJinJin/AndroidExample/master/img-androidexample/src/main/res/drawable/Example_AIDL_Server.png)

![Image text](https://raw.githubusercontent.com/AttackXiaoJinJin/AndroidExample/master/img-androidexample/src/main/res/drawable/Example_AIDL_Client.png)

![Image text](https://raw.githubusercontent.com/AttackXiaoJinJin/AndroidExample/master/img-androidexample/src/main/res/drawable/Sso_Auth_Result.png)

2、外共享数据(example_contentprovider)
注意：
在AndroidManifest.xml中添加如下代码：

![Image text](https://raw.githubusercontent.com/AttackXiaoJinJin/AndroidExample/master/img-androidexample/src/main/res/drawable/example_contentexample_androidmanifest.png)

效果：

![Image text](https://raw.githubusercontent.com/AttackXiaoJinJin/AndroidExample/master/img-androidexample/src/main/res/drawable/example_contentprovider_result1.png)

![Image text](https://raw.githubusercontent.com/AttackXiaoJinJin/AndroidExample/master/img-androidexample/src/main/res/drawable/example_contentprovider_result2.png)


缺陷：要查看数据库要获取权限，但我导出数据库时被拒绝了，还没解决该问题
3、
主要参考：https://github.com/LuckyJayce/ViewPagerIndicator
          http://blog.csdn.net/lmj623565791/article/details/45059587

说明：（1）该例中的代码由于AndroidStudio的版本与上述两位的源码略有不同，代码解释均在注释里
      （2）本来想利用recycler的瀑布流形式来触发startActivity事件，但还没搞懂瀑布流，就先拿来用了，所以待定。
      （3）非常感谢两位作者提供的类库和源码
效果图：
轮播banner:

![Image text](https://raw.githubusercontent.com/AttackXiaoJinJin/AndroidExample/master/img-androidexample/src/main/res/drawable/%E8%BD%AE%E6%92%ADbanner.gif)

引导界面（FIXEDINDICATORVIEW）:

![Image text](https://raw.githubusercontent.com/AttackXiaoJinJin/AndroidExample/master/img-androidexample/src/main/res/drawable/guide.gif)

Tab主导界面（FIXEDINDICATORVIEW）

![Image text](https://raw.githubusercontent.com/AttackXiaoJinJin/AndroidExample/master/img-androidexample/src/main/res/drawable/TAB主界面.gif)

可滑动Tab界面（SCROLLINDICATORVIEW）

![Image text](https://raw.githubusercontent.com/AttackXiaoJinJin/AndroidExample/master/img-androidexample/src/main/res/drawable/可滑动TAB.gif)

可滑动Tab界面（SCROLLINDICATORVIEW详细配置）

![Image text](https://raw.githubusercontent.com/AttackXiaoJinJin/AndroidExample/master/img-androidexample/src/main/res/drawable/可滑动TAB详细配置.gif)

SPRINGTab界面（SCROLLINDICATORVIEW）

![Image text](https://raw.githubusercontent.com/AttackXiaoJinJin/AndroidExample/master/img-androidexample/src/main/res/drawable/SPRINGTAB.gif)


RECYCLER瀑布流

![Image text](https://raw.githubusercontent.com/AttackXiaoJinJin/AndroidExample/master/img-androidexample/src/main/res/drawable/RECYCLER瀑布流.gif)

问题：

![Image text](https://raw.githubusercontent.com/AttackXiaoJinJin/AndroidExample/master/img-androidexample/src/main/res/drawable/recycler_problem.png)

解决方法：

![Image text](https://raw.githubusercontent.com/AttackXiaoJinJin/AndroidExample/master/img-androidexample/src/main/res/drawable/recycler_solve.jpg)


