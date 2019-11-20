package com.example.blockingcallsapplication;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.provider.Telephony;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CALL_PHONE;
import static android.Manifest.permission.INTERNET;
import static android.Manifest.permission.MODIFY_PHONE_STATE;
import static android.Manifest.permission.READ_CONTACTS;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.READ_SMS;
import static android.Manifest.permission.RECEIVE_BOOT_COMPLETED;
import static android.Manifest.permission.SEND_SMS;
import static android.provider.Telephony.Sms.Intents.ACTION_CHANGE_DEFAULT;

public class MainActivity extends AppCompatActivity {

    private Button btnFromMainToSms;
    private RecyclerView rcvBlockedList;
    private FloatingActionButton fab_add;
    private BlockerAdapter adapter;
    private ArrayList<Contact> data = new ArrayList<Contact>();
    private static final int PERMISSION_REQUEST_CODE = 200;
    private String[] ALL_PERMISSION = new String[]{ACCESS_FINE_LOCATION, CALL_PHONE, READ_PHONE_STATE, MODIFY_PHONE_STATE, INTERNET, ACCESS_COARSE_LOCATION, READ_SMS, SEND_SMS, RECEIVE_BOOT_COMPLETED, READ_CONTACTS, ACTION_CHANGE_DEFAULT};

    String myPackageName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myPackageName = getPackageName();
        requestPermission();
        rcvBlockedList= (RecyclerView) findViewById(R.id.rcvBlockedList);
        rcvBlockedList.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcvBlockedList.setLayoutManager(layoutManager);

        BlockedDatabase db = new BlockedDatabase(this);
        List<Contact> dataGet = db.getAllContacts();
        data.addAll(dataGet);
        adapter = new BlockerAdapter(data, getApplicationContext());
        rcvBlockedList.setAdapter(adapter);

        fab_add = (FloatingActionButton) findViewById(R.id.fab_add);
        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AddBlock.class);
                i.putExtra("ENTER", "Enter a number phone to block");
                startActivityForResult(i, 1);
            }
        });

        rcvBlockedList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) fab_add.hide();
                else fab_add.show();
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        btnFromMainToSms = findViewById(R.id.btnFromMainToSms);
        btnFromMainToSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToSms = new Intent(MainActivity.this, SmsBlockActivity.class);
                startActivity(intentToSms);
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


    }//end onCreate

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data1) {
            if(resultCode == 100){
                BlockedDatabase db = new BlockedDatabase(this);
                List<Contact> dataGet = db.getAllContacts();
                data.clear();
                data.addAll(dataGet);
                this.adapter.notifyDataSetChanged();
            }
    }

    private void requestPermission() {
        List<String> permissionNeed = new ArrayList<>();
        for(int i = 0; i<ALL_PERMISSION.length; i++){
            if(ActivityCompat.checkSelfPermission(MainActivity.this, ALL_PERMISSION[i]) != PackageManager.PERMISSION_GRANTED ){
                permissionNeed.add(ALL_PERMISSION[i]);
            }
        }
        if (permissionNeed != null){
            String[] permission = new String[permissionNeed.size()];
            permission = permissionNeed.toArray(permission);
        ActivityCompat.requestPermissions(MainActivity.this, permission, PERMISSION_REQUEST_CODE);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0) {
                        Toast.makeText(getApplicationContext(), "Permission Denied, You cannot access location data.", Toast.LENGTH_SHORT).show();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (shouldShowRequestPermissionRationale(ACCESS_FINE_LOCATION)) {
                                showMessage("You need to allow access to both the permissions", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                requestPermissions(ALL_PERMISSION, PERMISSION_REQUEST_CODE);
                                        }
                                    }
                                });
                                return;
                            }
                        }//end if Build.VERSION.SDK_INT
                } // end if (grantResults.length > 0)
                break;
        }//end Switch
    };// end onRequestPermissionsResult


    @Override
    protected void onResume() {
        super.onResume();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            if (!Telephony.Sms.getDefaultSmsPackage(this).equals(myPackageName)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("This app is not set as your default messaging app. Do you want to set it as default?")
                        .setCancelable(false)
                        .setTitle("Alert!")
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @TargetApi(19)
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(Telephony.Sms.Intents.ACTION_CHANGE_DEFAULT);
                                intent.putExtra(Telephony.Sms.Intents.EXTRA_PACKAGE_NAME, getPackageName());
                                startActivity(intent);
                            }
                        });
                builder.show();
            }
        }
    }

    private void showMessage(String mes, DialogInterface.OnClickListener listen) {
        new AlertDialog.Builder(MainActivity.this)
                .setMessage(mes)
                .setPositiveButton("OK", listen)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    };

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
}//end MainActivity
