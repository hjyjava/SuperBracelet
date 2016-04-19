package com.huang.superbracelet.base;

/**
 * Created by 黄家远 on 16/4/14.
 */
<<<<<<< HEAD
public class BaseActivity extends AppCompatActivity{
    private int userId;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    protected void initIntent(){

    }

    protected void initIntance(){

    }

    protected void initWedgit(){

    }

    protected void initData(){

    }

    protected void initDataFromDB(){

    }

    protected void initDataFromHttp(){
        if(NetUtils.isConnected(this))
            MyToastUtils.showShort(this,"网络未连接");
            return;
    }
=======
public class BaseActivity {
>>>>>>> origin/master
}
