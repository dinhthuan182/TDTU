package com.messageapp;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;

import com.example.blockingcallsapplication.BlockedDatabase;
import com.example.blockingcallsapplication.Contact;

import java.util.List;

public class SMSBReceiver extends BroadcastReceiver {
    private BlockedDatabase database;
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        SmsMessage[] smsm = null;
        String sms_str = "";
        ContentValues values = new ContentValues();
        database = new BlockedDatabase(context);
        List<Contact> info = database.getAllSms();
        String num, sendFrom = null;
        if (bundle != null) {
            // Get the SMS message
            Object[] pdus = (Object[]) bundle.get("pdus");
            smsm = new SmsMessage[pdus.length];

            for(int j = 0; j<info.size(); j++){
                num = info.get(j).getNumber().toString();
                for (int x = 0; x < smsm.length; x++) {
                        smsm[x] = SmsMessage.createFromPdu((byte[]) pdus[x]);
                    sendFrom = smsm[x].getOriginatingAddress();
                        sms_str += "Sent From: " + sendFrom;
                        sms_str += "\r\n\nMessage: ";
                        sms_str += smsm[x].getMessageBody().toString();
                        sms_str += "\r\n";

                    values.put("address", sendFrom);
                    values.put("body", smsm[x].getMessageBody());
                    values.put("read", smsm[x].getStatus()); //"0" for have not read sms and "1" for have read sms
                    values.put("date", smsm[x].getTimestampMillis());
                    if(num.equalsIgnoreCase(smsm[x].getOriginatingAddress().toString())){
                        return;
                    }
                }
            }
        }


        saveSms(context, values,"inbox");

        Intent smsIntent=new Intent(context,RetrieveMessage.class);
        smsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        smsIntent.putExtra("sms_str", sms_str);
        context.startActivity(smsIntent);
    }


    public boolean saveSms(Context context,ContentValues values, String folderName) {
        boolean ret = false;
        try {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                Uri uri = Telephony.Sms.Sent.CONTENT_URI;
                if(folderName.equals("inbox")){
                    uri = Telephony.Sms.Inbox.CONTENT_URI;
                }
                context.getContentResolver().insert(uri, values);
            }
            else {
                /* folderName  could be inbox or sent */
                context.getContentResolver().insert(Uri.parse("content://sms/" + folderName), values);
            }

            ret = true;
        } catch (Exception ex) {
            ex.printStackTrace();
            ret = false;
        }
        return ret;
    }
}
