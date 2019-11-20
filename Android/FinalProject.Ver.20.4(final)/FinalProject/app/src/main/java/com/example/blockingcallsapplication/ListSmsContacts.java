package com.example.blockingcallsapplication;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListSmsContacts extends Activity {

    List<String> nameSms = new ArrayList<String>();
    List<String> phnoSms = new ArrayList<String>();
    ListView lvSmsContacts;
    SmsAdapter ma;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_sms_contacts);

        AllContact(this.getContentResolver());
        lvSmsContacts = (ListView) findViewById(R.id.lvSmsContacts);
        ma = new SmsAdapter();
        lvSmsContacts.setAdapter(ma);
        lvSmsContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = nameSms.get(position).toString();
                String number = phnoSms.get(position).toString();
                Intent data4 = new Intent();
                data4.putExtra("RETURN_SMS_NAME", name);
                data4.putExtra("RETURN_SMS_NUMBER", number);
                setResult(400, data4);
                finish();
            }
        });
    }// end onCreate

    public  void AllContact(ContentResolver cr) {

        Cursor phones = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null, null);
        while (phones.moveToNext())
        {
            String name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            nameSms.add(name);
            phnoSms.add(phoneNumber);
        }
        phones.close();
    }// end AllContact

    class SmsAdapter extends BaseAdapter implements CompoundButton.OnCheckedChangeListener{
        private SparseBooleanArray mCheckStates;
        LayoutInflater mInflater;
        TextView tvSms;
        SmsAdapter()
        {
            mCheckStates = new SparseBooleanArray(nameSms.size());
            mInflater = (LayoutInflater) ListSmsContacts.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }
        @Override
        public int getCount() {
            return nameSms.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View vi = convertView;
            if(convertView == null)
                vi = mInflater.inflate(R.layout.row, null);
            TextView tv = (TextView) vi.findViewById(R.id.contact_name);
            tvSms = (TextView) vi.findViewById(R.id.phone_number);
            tv.setText("Name :"+ nameSms.get(position));
            tvSms.setText("Phone No :"+ phnoSms.get(position));
            return vi;
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        }
    }
}


