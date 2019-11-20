package com.example.blockingcallsapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import com.android.internal.telephony.ITelephony;
import java.lang.reflect.Method;
import java.util.List;

public class IncomingCallReceiver extends BroadcastReceiver {
    private ITelephony telephonyService;
    private String state;
    private String number;
    private BlockedDatabase database;
    @Override
    public void onReceive(Context context, Intent intent) {
        database = new BlockedDatabase(context);
        List<Contact> info = database.getAllContacts();
        String num;
        try {
            state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
            number = intent.getExtras().getString(TelephonyManager.EXTRA_INCOMING_NUMBER);

            if(state.equalsIgnoreCase(TelephonyManager.EXTRA_STATE_RINGING)){
                TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                try {
                    Method m = tm.getClass().getDeclaredMethod("getITelephony");
                    m.setAccessible(true);
                    telephonyService = (ITelephony) m.invoke(tm);
                    for(int i = 0; i<info.size(); i++){
                        num = info.get(i).getNumber();
                        if(num.equalsIgnoreCase(number) == true){
                            telephonyService.endCall();
                            Toast.makeText(context, "Ending the call from: " + number, Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
//                Toast.makeText(context, "Ring " + number, Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
