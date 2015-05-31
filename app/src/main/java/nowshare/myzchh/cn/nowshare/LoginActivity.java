package nowshare.myzchh.cn.nowshare;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.List;

import nowshare.myzchh.cn.nowshare.util.BaseActivity;
import nowshare.myzchh.cn.nowshare.util.localUser;
import nowshare.myzchh.cn.nowshare.util.sqlliteDB.DBManager;


public class LoginActivity extends BaseActivity {

    private ImageView imgTitle;
    private RelativeLayout loginBox;
    private ImageView button_login;
    private ImageView button_login_shadow;
    private ImageView image_title;
    private RelativeLayout loginTip;
    private ImageView button_iknow_shadow;
    private ImageView button_problem_shadow;
    private Button button_iknow;
    private TextView button_problem;
    private EditText textbox_user;
    private EditText textbox_password;
    private TextView text_logintip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        imgTitle = (ImageView) findViewById(R.id.imgTitle);
        loginBox = (RelativeLayout) findViewById(R.id.loginBox);
        button_login = (ImageView) findViewById(R.id.button_login);
        button_login_shadow = (ImageView) findViewById(R.id.button_login_shadow);
        button_iknow_shadow = (ImageView) findViewById(R.id.button_iknow_shadow);
        button_problem_shadow = (ImageView) findViewById(R.id.button_problem_shadow);
        image_title = (ImageView) findViewById(R.id.image_title);
        loginTip = (RelativeLayout) findViewById(R.id.loginTip);
        button_iknow = (Button) findViewById(R.id.button_iknow);
        button_problem = (TextView) findViewById(R.id.button_problem);
        textbox_user = (EditText) findViewById(R.id.textbox_user);
        textbox_password = (EditText) findViewById(R.id.textbox_password);
        text_logintip = (TextView) findViewById(R.id.text_logintip);

        Intent intent = getIntent();
        textbox_user.setText(intent.getStringExtra("username"));
        textbox_password.setText(intent.getStringExtra("password"));

        DBManager mgr=new DBManager(this);
        mgr.getLocalUser(this);
        String strLocalUser=localUser.getUSERNAME();
        //showMessageByToast(strLocalUser);
        textbox_user.setText(strLocalUser);

        Animation aIn = new AlphaAnimation(1f, 0f);
        aIn.setDuration(1);
        aIn.setFillAfter(true);
        button_login_shadow.startAnimation(aIn);
        button_iknow_shadow.startAnimation(aIn);
        button_problem_shadow.startAnimation(aIn);
        if (textbox_user.getText().toString().equals("")) {

            Animation aIn2 = new AlphaAnimation(0f, 1f);
            aIn2.setStartOffset(2000);
            aIn2.setDuration(800);
            aIn2.setFillAfter(true);
            loginTip.startAnimation(aIn2);

            Animation translateIn2 = new TranslateAnimation(0, 0, dip2px(this, 640), 0);
            translateIn2.setDuration(1000);
            translateIn2.setStartOffset(1000);
            translateIn2.setFillAfter(true);
            loginBox.startAnimation(translateIn2);

            Animation translateIn = new TranslateAnimation(0, 0, 0, -dip2px(this, 100));
            translateIn.setDuration(1000);
            translateIn.setStartOffset(1000);
            translateIn.setFillAfter(true);
            imgTitle.startAnimation(translateIn);

            Animation translateIn3 = new TranslateAnimation(0, 0, dip2px(this, 167), 0);
            translateIn3.setDuration(1000);
            translateIn3.setStartOffset(1000);
            translateIn3.setFillAfter(true);
            image_title.startAnimation(translateIn3);
        } else {
            loginTip.setAnimationCacheEnabled(false);
            loginTip.setClickable(false);
            loginTip.setEnabled(false);
            loginTip.setVisibility(View.GONE);
            button_login_shadow.setClickable(false);
            button_login_shadow.setEnabled(false);
            button_login_shadow.setVisibility(View.GONE);
            button_iknow_shadow.setClickable(false);
            button_iknow_shadow.setEnabled(false);
            button_iknow_shadow.setVisibility(View.GONE);
            button_problem_shadow.setClickable(false);
            button_problem_shadow.setEnabled(false);
            button_problem_shadow.setVisibility(View.GONE);

            //以下无动画，纯粹为固定位置，别打我
            Animation translateIn = new TranslateAnimation(0, 0, 0, 0);
            translateIn.setDuration(200);
            translateIn.setFillAfter(true);
            imgTitle.startAnimation(translateIn);
            Animation translateIn2 = new TranslateAnimation(0, 0, dip2px(LoginActivity.this, 367), dip2px(LoginActivity.this, 367));
            translateIn2.setDuration(200);
            translateIn2.setFillAfter(true);
            loginBox.startAnimation(translateIn2);
            Animation translateIn3 = new TranslateAnimation(0, 0, dip2px(LoginActivity.this, 167), dip2px(LoginActivity.this, 167));
            translateIn3.setDuration(200);
            translateIn3.setFillAfter(true);
            image_title.startAnimation(translateIn3);
            doStartLogin(translateIn2);
            return;
        }

        textbox_password.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    textbox_password.setHintTextColor(getResources().getColor(R.color.hint_foreground_material_light));
                    textbox_password.setHint("密码");
                }
            }
        });
        button_login.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //Log.i("sd","ff");
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Animation aIn = new AlphaAnimation(0f, 1f);
                    aIn.setDuration(500);
                    aIn.setFillAfter(true);
                    button_login_shadow.startAnimation(aIn);
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    Animation aIn = new AlphaAnimation(1f, 0f);
                    aIn.setDuration(200);
                    aIn.setFillAfter(true);
                    button_login_shadow.startAnimation(aIn);
                }
                return false;
            }
        });

        //点击登录按钮
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //显示动画，向下滚动
                Animation translateIn = new TranslateAnimation(0, 0, -dip2px(LoginActivity.this,100), 0);
                translateIn.setDuration(800);
                translateIn.setFillAfter(true);
                imgTitle.startAnimation(translateIn);
                Animation translateIn2 = new TranslateAnimation(0, 0,0 ,dip2px(LoginActivity.this,367) );
                translateIn2.setDuration(800);
                translateIn2.setFillAfter(true);
                loginBox.startAnimation(translateIn2);
                Animation translateIn3 = new TranslateAnimation(0, 0, 0, dip2px(LoginActivity.this,167));
                translateIn3.setDuration(800);
                translateIn3.setFillAfter(true);
                image_title.startAnimation(translateIn3);
                doStartLogin(translateIn2);
            }
        });

        button_iknow.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //Log.i("sd","ff");
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Animation aIn = new AlphaAnimation(0f, 1f);
                    aIn.setDuration(500);
                    aIn.setFillAfter(true);
                    button_iknow_shadow.startAnimation(aIn);
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    Animation aIn = new AlphaAnimation(1f, 0f);
                    aIn.setDuration(200);
                    aIn.setFillAfter(true);
                    button_iknow_shadow.startAnimation(aIn);
                }
                return false;
            }
        });


        button_problem.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //Log.i("sd","ff");
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Animation aIn = new AlphaAnimation(0f, 1f);
                    aIn.setDuration(500);
                    aIn.setFillAfter(true);
                    button_problem_shadow.startAnimation(aIn);
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    Animation aIn = new AlphaAnimation(1f, 0f);
                    aIn.setDuration(200);
                    aIn.setFillAfter(true);
                    button_problem_shadow.startAnimation(aIn);
                }
                return false;
            }
        });

        button_problem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistActivity.class);
                startActivity(intent);
                int version = Integer.valueOf(android.os.Build.VERSION.SDK);
                if (version > 5) {
                    overridePendingTransition(R.anim.fade, R.anim.hold);
                }
            }
        });

        button_iknow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation aIn2 = new AlphaAnimation(1f, 0f);
                aIn2.setDuration(500);
                aIn2.setFillAfter(true);
                loginTip.startAnimation(aIn2);
                loginTip.setClickable(false);
                aIn2.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        //hide button_iknow
                        button_iknow.setEnabled(false);
                        button_iknow.setVisibility(View.GONE);
                        button_iknow_shadow.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        });

    }

    /**
     * 根据手机的分辨率从 转换 dp/px
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public void showMessageByToast(String Msg) {
        Toast.makeText(LoginActivity.this, Msg, Toast.LENGTH_LONG).show();
    }

    public void intentMainActivity() {
        //登陆成功动画，向上滚动
        //final String userNickname = nickname;

        Animation translateIn = new TranslateAnimation(0, 0, 0, -dip2px(LoginActivity.this, 100));
        translateIn.setDuration(500);
        translateIn.setStartOffset(1000);
        translateIn.setFillAfter(true);
        imgTitle.startAnimation(translateIn);
        Animation translateIn2 = new TranslateAnimation(0, 0, dip2px(LoginActivity.this, 367), -dip2px(LoginActivity.this, 213));
        translateIn2.setDuration(500);
        translateIn2.setStartOffset(1000);
        translateIn2.setFillAfter(true);
        loginBox.startAnimation(translateIn2);
        Animation translateIn3 = new TranslateAnimation(0, 0, dip2px(LoginActivity.this, 167), 0);
        translateIn3.setDuration(500);
        translateIn3.setStartOffset(1000);
        translateIn3.setFillAfter(true);
        image_title.startAnimation(translateIn3);
        translateIn3.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //滚动完毕，跳转
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("username", localUser.getUSERNAME());
                startActivity(intent);
                finish();
                int version = Integer.valueOf(android.os.Build.VERSION.SDK);
                if (version > 5) {
                    overridePendingTransition(R.anim.fade, R.anim.hold);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        //finish();
    }

    public void doErrorLogin() {
        textbox_user.setEnabled(true);
        textbox_password.setEnabled(true);
        button_login.setEnabled(true);
        button_problem.setEnabled(true);
        textbox_user.setVisibility(View.VISIBLE);
        textbox_password.setVisibility(View.VISIBLE);
        button_login.setVisibility(View.VISIBLE);
        button_problem.setVisibility(View.VISIBLE);
        Animation translateIn = new TranslateAnimation(0, 0, 0, -dip2px(LoginActivity.this, 100));
        translateIn.setDuration(500);
        translateIn.setStartOffset(1000);
        translateIn.setFillAfter(true);
        imgTitle.startAnimation(translateIn);
        Animation translateIn2 = new TranslateAnimation(0, 0, dip2px(LoginActivity.this, 367), 0);
        translateIn2.setDuration(500);
        translateIn2.setStartOffset(1000);
        translateIn2.setFillAfter(true);
        loginBox.startAnimation(translateIn2);
        Animation translateIn3 = new TranslateAnimation(0, 0, dip2px(LoginActivity.this, 167), 0);
        translateIn3.setDuration(500);
        translateIn3.setStartOffset(1000);
        translateIn3.setFillAfter(true);
        image_title.startAnimation(translateIn3);
        Animation aIn = new AlphaAnimation(1f, 0f);
        aIn.setDuration(500);
        aIn.setFillAfter(true);
        text_logintip.setText("正在登录...");
        text_logintip.startAnimation(aIn);
        textbox_password.setText("");
        textbox_password.setHintTextColor(getResources().getColor(R.color.color_error));


    }

    public void doStartLogin(Animation translateIn) {
        translateIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //正在登录，禁用所有元素
                textbox_user.setEnabled(false);
                textbox_password.setEnabled(false);
                button_login.setEnabled(false);
                button_problem.setEnabled(false);
                textbox_user.setVisibility(View.GONE);
                textbox_password.setVisibility(View.GONE);
                button_login.setVisibility(View.GONE);
                button_problem.setVisibility(View.GONE);
                //显示正在登录文字动画
                Animation aIn = new AlphaAnimation(0f, 1f);
                aIn.setDuration(500);
                aIn.setFillAfter(true);
                text_logintip.setText("正在登录...");
                text_logintip.startAnimation(aIn);

                if (textbox_user.getText().toString().equals("")) {
                    doErrorLogin();
                    textbox_password.setHint("登录失败，请检查用户名和密码");
                } else {
                    if (textbox_password.getText().toString().equals("") || (textbox_password.getText().toString().length() < 6)) {
                        if(!localUser.getUUID().equals("")){
                            intentMainActivity();
                        }else {
                            doErrorLogin();
                            textbox_password.setHint("密码不能为空");
                        }
                    } else {
                        //开始登录
                        new AsyncTask<String, Void, Object>() {
                            @Override
                            protected Object doInBackground(String... params) {
                                String url = "http://sanjin6035.vicp.cc/Weibo/user/login";
                                String result = "";
                                try {
                                    //创建连接
                                    HttpClient httpClient = new DefaultHttpClient();
                                    HttpPost post = new HttpPost(url);
                                    //设置参数，仿html表单提交
                                    List<NameValuePair> paramList = new ArrayList<NameValuePair>();
                                    BasicNameValuePair param = new BasicNameValuePair("uusername", textbox_user.getText().toString());
                                    BasicNameValuePair param2 = new BasicNameValuePair("upassword", textbox_password.getText().toString());
                                    paramList.add(param);
                                    paramList.add(param2);

                                    post.setEntity(new UrlEncodedFormEntity(paramList, HTTP.UTF_8));
                                    //发送HttpPost请求，并返回HttpResponse对象
                                    HttpResponse httpResponse = httpClient.execute(post);
                                    // 判断请求响应状态码，状态码为200表示服务端成功响应了客户端的请求
                                    if (httpResponse.getStatusLine().getStatusCode() == 200) {
                                        //获取返回结果
                                        result = EntityUtils.toString(httpResponse.getEntity());
                                        //showMessageByToast(result);
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    //showMessageByToast("错误!");
                                }
                                return result;
                            }

                            protected void onPostExecute(Object result) {
                                super.onPostExecute(result);
                                //showMessageByToast(result+"");
                                try {
                                    JSONTokener jsonParser = new JSONTokener(result + "");
                                    JSONObject loginmsg = (JSONObject) jsonParser.nextValue();
                                    //showMessageByToast("getResult:" + result);
                                    if (loginmsg.getBoolean("status")) {
                                        JSONObject loginUserJSON = loginmsg.getJSONObject("user");
                                        DBManager mgr=new DBManager(LoginActivity.this);
                                        String strUsername=loginUserJSON.getString("uusername");
                                        String strUuid=loginUserJSON.getString("uuid");
                                        mgr.setLocalUser(strUsername,strUuid);
                                        localUser.setUSERNAME(strUsername);
                                        localUser.setUUID(strUuid);
                                        intentMainActivity();
                                    } else {
                                        //showMessageByToast("Error1001,Username & Password is not true");
                                        doErrorLogin();
                                        textbox_password.setHint("密码错误，无法登录");
                                    }

                                } catch (Exception e) {
                                    //showMessageByToast("Error0000,Exception @JSON");
                                    e.printStackTrace();
                                    doErrorLogin();
                                    textbox_password.setHint("密码错误，无法登录");
                                }
                                //
                                //showMessageByToast("" + result);
                            }
                        }.execute();
                    }
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

}
