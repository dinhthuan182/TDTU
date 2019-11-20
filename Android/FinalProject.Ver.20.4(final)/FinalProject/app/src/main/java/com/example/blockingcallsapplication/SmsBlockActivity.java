package com.example.blockingcallsapplication;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.messageapp.MessageBox;

import java.util.ArrayList;
import java.util.List;

public class SmsBlockActivity extends AppCompatActivity {

    private Button btnFromSmsToCall;
    private RecyclerView rcvSmsBlockList;
    private FloatingActionButton fab_sms, fab_message, fab_add_sms;
    private BlockerAdapter adapter;
    private ArrayList<Contact> data = new ArrayList<Contact>();
    private boolean isFABOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_block);

        rcvSmsBlockList= (RecyclerView) findViewById(R.id.rcvSmsBlockList);
        rcvSmsBlockList.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcvSmsBlockList.setLayoutManager(layoutManager);

        BlockedDatabase db = new BlockedDatabase(this);
        List<Contact> dataGet = db.getAllSms();
        data.addAll(dataGet);
        adapter = new BlockerAdapter(data, getApplicationContext());
        rcvSmsBlockList.setAdapter(adapter);

        btnFromSmsToCall = findViewById(R.id.btnFromSmsToCall);
        btnFromSmsToCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSmsToCall = new Intent(SmsBlockActivity.this, MainActivity.class);
                startActivity(intentSmsToCall);
            }
        });
        rcvSmsBlockList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) fab_sms.hide();
                else fab_sms.show();
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        fab_sms = (FloatingActionButton) findViewById(R.id.fab_sms);
        fab_add_sms = (FloatingActionButton) findViewById(R.id.fab_add_sms);
        fab_message = (FloatingActionButton) findViewById(R.id.fab_message);
        fab_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isFABOpen){
                    showFABMenu();
                }else{
                    closeFABMenu();
                }
            }
        });
        fab_add_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SmsBlockActivity.this,AddBlockSmsActivity.class);
                intent.putExtra("SELECT_SMS_NUMBER", "Select a number phone");
                startActivityForResult(intent, 3);
            }
        });
        fab_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SmsBlockActivity.this, MessageBox.class);
                startActivity(intent);
            }
        });

        TypedArray styledAttributes =
                getTheme().obtainStyledAttributes(new int[] { android.R.attr.actionBarSize });
        int actionBarSize = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();;
        Drawable drawable= getResources().getDrawable(R.mipmap.ic_avatar_launcher);
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        Drawable newdrawable = new BitmapDrawable(getResources(),
                Bitmap.createScaledBitmap(bitmap, actionBarSize,  actionBarSize, true));

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(newdrawable);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
    }// end onCreate

    private void showFABMenu(){
        isFABOpen = true;
        Animation show_fab_1 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab1_show);
        FrameLayout.LayoutParams layoutParams1s = (FrameLayout.LayoutParams) fab_message.getLayoutParams();
        layoutParams1s.rightMargin += (int) (fab_message.getWidth() * 1.7);
        layoutParams1s.bottomMargin += (int) (fab_message.getHeight() * 0.25);
        fab_message.setLayoutParams(layoutParams1s);
        fab_message.startAnimation(show_fab_1);
        fab_message.setClickable(true);

        Animation show_fab_2 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab2_show);
        FrameLayout.LayoutParams layoutParams2s = (FrameLayout.LayoutParams) fab_add_sms.getLayoutParams();
        layoutParams2s.rightMargin += (int) (fab_add_sms.getWidth() * 1.5);
        layoutParams2s.bottomMargin += (int) (fab_add_sms.getHeight() * 1.5);
        fab_add_sms.setLayoutParams(layoutParams2s);
        fab_add_sms.startAnimation(show_fab_2);
        fab_add_sms.setClickable(true);
    }

    private void closeFABMenu(){
        isFABOpen = false;
        Animation hide_fab_1 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab1_hide);
        FrameLayout.LayoutParams layoutParams1c = (FrameLayout.LayoutParams) fab_message.getLayoutParams();
        layoutParams1c.rightMargin -= (int) (fab_message.getWidth() * 1.7);
        layoutParams1c.bottomMargin -= (int) (fab_message.getHeight() * 0.25);
        fab_message.setLayoutParams(layoutParams1c);
        fab_message.startAnimation(hide_fab_1);
        fab_message.setClickable(false);

        Animation hide_fab_2 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab2_hide);
        FrameLayout.LayoutParams layoutParams2c = (FrameLayout.LayoutParams) fab_add_sms.getLayoutParams();
        layoutParams2c.rightMargin -= (int) (fab_add_sms.getWidth() * 1.5);
        layoutParams2c.bottomMargin -= (int) (fab_add_sms.getHeight() * 1.5);
        fab_add_sms.setLayoutParams(layoutParams2c);
        fab_add_sms.startAnimation(hide_fab_2);
        fab_add_sms.setClickable(false);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data1) {
        if(resultCode == 300){
            BlockedDatabase db = new BlockedDatabase(this);
            List<Contact> dataGet = db.getAllSms();
            data.clear();
            data.addAll(dataGet);
            this.adapter.notifyDataSetChanged();
        }
        if(requestCode == 4 && resultCode == 400){
            Toast.makeText(SmsBlockActivity.this, "OK", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.context_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_settings:
                //code xử lý khi bấm menu1
                break;
            default:break;
        }
        return super.onOptionsItemSelected(item);
    }
}
