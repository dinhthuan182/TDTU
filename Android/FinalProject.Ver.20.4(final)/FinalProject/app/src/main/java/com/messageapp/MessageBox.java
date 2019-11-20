package com.messageapp;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.example.blockingcallsapplication.R;

public class MessageBox extends AppCompatActivity implements AdapterView.OnItemClickListener {

    Button btnSent, btnInbox, btnDraft, btnSend1;
//    TextView lblMsg, lblNo;
    ListView lvMsg;
    SimpleCursorAdapter adapter;

    Context context;

    //local members
    String myPackageName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_box);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        context = MessageBox.this;
        myPackageName = getPackageName();
        lvMsg = (ListView) findViewById(R.id.lvMsg);

        btnInbox = (Button) findViewById(R.id.btnInbox);
        btnInbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchInbox();
            }
        });

        btnSent = (Button)findViewById(R.id.btnSentBox);
        btnSent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri sentURI = Uri.parse("content://sms/sent");
                String[] reqCols = new String[]{"_id", "address", "body"};
                ContentResolver cr = getContentResolver();
                Cursor c = cr.query(sentURI, reqCols, null, null, null);

                adapter = new SimpleCursorAdapter(MessageBox.this, R.layout.row2, c,
                        new String[]{"body", "address"}, new int[]{
                        R.id.lblMsg, R.id.lblNumber}, 0);
                lvMsg.setAdapter(adapter);
            }
        });

        btnDraft = (Button)findViewById(R.id.btnDraft);
        btnDraft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri draftURI = Uri.parse("content://sms/draft");
                String[] reqCols = new String[]{"_id", "address", "body"};
                ContentResolver cr = getContentResolver();
                Cursor c = cr.query(draftURI, reqCols, null, null, null);

                adapter = new SimpleCursorAdapter(MessageBox.this, R.layout.row2, c,
                        new String[]{"body", "address"}, new int[]{
                        R.id.lblMsg, R.id.lblNumber}, 0);
                lvMsg.setAdapter(adapter);
            }
        });
        btnSend1 = (Button) findViewById(R.id.btnSend1);
        btnSend1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MessageBox.this, SendSms.class);
                startActivity(i);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.context_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void fetchInbox() {
        Uri inboxURI = Uri.parse("content://sms/inbox");
        String[] reqCols = new String[]{"_id", "address", "body"};
        ContentResolver cr = getContentResolver();
        Cursor c = cr.query(inboxURI, reqCols, null, null, null);

        adapter = new SimpleCursorAdapter(this, R.layout.row2, c,
                new String[]{"body", "address"}, new int[]{
                R.id.lblMsg, R.id.lblNumber}, 0);

        lvMsg.setAdapter(adapter);
        lvMsg.setOnItemClickListener(this);

    }
    public void deleteSMS(Context context, long messageId) {
        try {
            Uri uriSms = Uri.parse("content://sms/inbox");
            Cursor c = context.getContentResolver().query(uriSms, new String[]{"_id"}, null, null, null);

            if (c != null && c.moveToFirst()) {
                do {
                    long id = c.getLong(0);

                    if (id == messageId) {
                        context.getContentResolver().delete(
                                Uri.parse("content://sms/" + id), null, null);
                        Log.e("Message:", "Message is Deleted successfully");
                    }

                } while (c.moveToNext());
            }

            if (c != null) {
                c.close();
            }
        } catch (Exception e) {
            Log.e("Exception", e.toString());
        }

    }


    @Override
    protected void onResume() {
        super.onResume();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {

            if (!Telephony.Sms.getDefaultSmsPackage(this).equals(myPackageName)) {

                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(MessageBox.this);
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final Cursor c = (Cursor) adapter.getItem(position);
        final long cid = c.getLong(c.getColumnIndex("_id"));
        String message = c.getString(c.getColumnIndex("body"));
        String number = c.getString(c.getColumnIndex("address"));
        Log.e("id", cid + "");
        Log.e("message label", number + "");
        Log.e("message body", message + "");


        android.app.AlertDialog.Builder alert = new AlertDialog.Builder(MessageBox.this);
        alert.setMessage(" Are you sure you want to delete this message?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                deleteSMS(MessageBox.this, cid);
                fetchInbox();

            }


        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });
        alert.create();
        alert.show();

//        return false;
    }

}
