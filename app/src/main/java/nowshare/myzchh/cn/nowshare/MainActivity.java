package nowshare.myzchh.cn.nowshare;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nowshare.myzchh.cn.nowshare.util.BaseActivity;
import nowshare.myzchh.cn.nowshare.util.MyAdapter;


public class MainActivity extends BaseActivity {

    private ImageView btn_add_shadow;
    private ImageView btn_add;
    private ListView lv;
    private TextView title_Name;
    private MyAdapter sa;
    private RelativeLayout newBox;
    private EditText edit_newBox;
    private ImageView img_add_tip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_add=(ImageView)findViewById(R.id.btn_add);
        btn_add_shadow=(ImageView)findViewById(R.id.btn_add_shadow);
        title_Name=(TextView)findViewById(R.id.title_Name);
        lv=(ListView)findViewById(R.id.listView);
        newBox=(RelativeLayout)findViewById(R.id.newBox);
        edit_newBox=(EditText)findViewById(R.id.edit_newBox);
        img_add_tip=(ImageView)findViewById(R.id.img_add_tip);

        Intent intent = getIntent();
        String userStr = intent.getStringExtra("username");
        title_Name.setText(userStr);

        Animation aIn = new AlphaAnimation(1f, 0f);
        aIn.setDuration(1);
        aIn.setFillAfter(true);
        btn_add_shadow.startAnimation(aIn);

        loadSomeItem();

        Animation translateIn2 = new TranslateAnimation(0, 0, dip2px(this, 640), 0);
        translateIn2.setDuration(1000);
        translateIn2.setStartOffset(200);
        translateIn2.setFillAfter(true);
        lv.startAnimation(translateIn2);

        btn_add.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //Log.i("sd","ff");
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    //Log.i("aa", "ACTION_DOWN");
                    Animation aIn = new AlphaAnimation(0f, 1f);
                    aIn.setDuration(200);
                    aIn.setFillAfter(true);
                    btn_add_shadow.startAnimation(aIn);
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                        Animation aIn = new AlphaAnimation(1f, 0f);
                        aIn.setDuration(800);
                        aIn.setFillAfter(true);
                        btn_add_shadow.startAnimation(aIn);
                }
                return false;
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Log.i("bb", "Click");
                Animation aIn = new AlphaAnimation(0f, 1f);
                aIn.setDuration(200);
                aIn.setFillAfter(true);
                btn_add_shadow.startAnimation(aIn);

                newBox.setEnabled(true);
                newBox.setVisibility(View.VISIBLE);
                newBox.setClickable(true);
                edit_newBox.setEnabled(true);
                edit_newBox.setVisibility(View.VISIBLE);
                img_add_tip.setVisibility(View.VISIBLE);

                Animation aIn2 = new AlphaAnimation(0f, 1f);
                aIn2.setDuration(400);
                aIn2.setFillAfter(true);
                newBox.startAnimation(aIn2);
            }
        });

        newBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                img_add_tip.setVisibility(View.GONE);

                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(MainActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                Animation aIn = new AlphaAnimation(1f, 0f);
                aIn.setDuration(200);
                aIn.setFillAfter(true);
                btn_add_shadow.startAnimation(aIn);

                Animation aIn2 = new AlphaAnimation(1f, 0f);
                aIn2.setDuration(400);
                aIn2.setFillAfter(true);
                newBox.startAnimation(aIn2);

                aIn2.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        newBox.setEnabled(false);
                        newBox.setVisibility(View.GONE);
                        newBox.setClickable(false);
                        edit_newBox.setEnabled(false);
                        edit_newBox.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        });
    }

    public void loadSomeItem(){

        List<Map<String, Object>> datas;
        datas=new ArrayList<>();

        Map<String,Object> data0= new HashMap<String,Object>();
        data0.put("text","");

        Map<String,Object> data1= new HashMap<String,Object>();
        data1.put("text","我跟他们好过，我曾经很爱他们，也被爱过，所以大不了大家老死不相往来，我也不会害你，我从来不否认自己的过去，恩我记得，仇我也不报，现在一笔勾销。");
        data1.put("name","荷花");
        data1.put("date","@2015.5.17 12:20");
        data1.put("personico",R.drawable.p_hua);
        data1.put("p1","@肖逗"+"：");
        data1.put("p1comment","逗逼");
        data1.put("p2","@杨贵妃"+"：");
        data1.put("p2comment","逗逼+1");

        Map<String,Object> data2= new HashMap<String,Object>();
        data2.put("text","最美的九组词牌名~~ 声声慢 长相思 蝶恋花 如梦令 临江仙 西江月 虞美人 相见欢 江城子 ~ 转发说出你喜欢哪个？");
        data2.put("name","刘帅哥");
        data2.put("date","@2015.5.17 12:20");
        data2.put("personico",R.drawable.p_shuai);
        data2.put("p1","@超哥"+"：");
        data2.put("p1comment","咱家帅真有才");
        data2.put("p2","@荷花"+"：");
        data2.put("p2comment","声声慢 长相思 蝶恋花 如梦令 临江仙 西江月 虞美人 相见欢 江城子 都喜欢咋办？");

        Map<String,Object> data3= new HashMap<String,Object>();
        data3.put("text", "附近有个开放的小游乐场，白天带妞妞路过，它总是很向往，拽绳子想玩，但小朋友们怕它。晚上没人时，我常带它来，它并不知道咋玩，但开心的上窜下跳，像个孩子。我在旁边守着，被蚊子叮一身包，像只慈祥的蛤蟆……");
        data3.put("name","杨贵妃");
        data3.put("date","@2015.5.17 12:20");
        data3.put("personico",R.drawable.p_yun);
        data3.put("p1","@荷花"+"：");
        data3.put("p1comment","妞妞是狗么？");
        data3.put("p2","@肖逗"+"：");
        data3.put("p2comment","花花你可以去死么？");

        Map<String,Object> data4= new HashMap<String,Object>();
        data4.put("text", "意大利的三个城镇正在往外卖房子，一个房子1英镑，虽然装修要花点钱，但感觉还是挺棒...");
        data4.put("name","超哥");
        data4.put("date","@2015.5.17 12:20");
        data4.put("personico",R.drawable.p_chao);
        data4.put("p1","@荷花"+"：");
        data4.put("p1comment","买买买");
        data4.put("p2","@肖逗"+"：");
        data4.put("p2comment","不买不是人！");

        Map<String,Object> data5= new HashMap<String,Object>();
        data5.put("text", "如果有人愿意在你们聊天结束时，每次都以他的话为结尾，诸如“嗯嗯”之类毫无营养的，甚至把说过的晚安再重复一遍，不要以为他啰嗦，他只是把话语中断的失落感揽到自己身上。这样的人内心是很温柔的，错过了就很难再遇到了。");
        data5.put("name","肖逗");
        data5.put("date","@2015.5.17 12:20");
        data5.put("personico",R.drawable.p_dou);
        data5.put("p1","@荷花"+"：");
        data5.put("p1comment","嗯嗯");
        data5.put("p2","@谭美女"+"：");
        data5.put("p2comment","上边的这位证明了一切...");

        Map<String,Object> data6= new HashMap<String,Object>();
        data6.put("text", "一句好暖心的话：“因为她才一米五，所以一旦吵架了，我就必须先低头”");
        data6.put("name","谭美女");
        data6.put("date","@2015.5.17 12:20");
        data6.put("personico", R.drawable.p_tan);
        data6.put("p1","@刘帅哥"+"：");
        data6.put("p1comment","不低头看不到- -|||");
        data6.put("p2","@肖逗"+"：");
        data6.put("p2comment", "温暖，赞一个");


        //26datas.add(data0);
        datas.add(data1);
        datas.add(data2);
        datas.add(data3);
        datas.add(data4);
        datas.add(data5);
        datas.add(data6);

        sa=new MyAdapter(this,datas,R.layout.listviewitem_main,new String[]{"text","name","date",
                "personico","p1","p2","p1comment","p2comment"},
                new int[]{R.id.text_Text,R.id.text_Name,R.id.text_date,R.id.ico_Person,R.id.text_critics1
                ,R.id.text_critics2,R.id.text_comment1,R.id.text_comment2});
        lv.setAdapter(sa);

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
}
