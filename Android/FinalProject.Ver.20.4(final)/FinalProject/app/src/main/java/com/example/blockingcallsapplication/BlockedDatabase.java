package com.example.blockingcallsapplication;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.ArrayList;

public class BlockedDatabase extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "Block_Manager";
    private static final String TABLE_CALL_BLOCK = "block_list_table";
    private static final String COLUMN_CONTACT_NUMBER = "phone_number";
    private static final String COLUMN_CONTACT_NAME = "phone_name";
    private static final String COLUMN_CONTACT_ISBLOCK = "phone_isblock";
    private static final String TABLE_SMS_BLOCK = "sms_block_list_table";

    public BlockedDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            String createTableCallBlock = "CREATE TABLE " + TABLE_CALL_BLOCK + "("
                    + COLUMN_CONTACT_NUMBER + " TEXT PRIMARY KEY, " + COLUMN_CONTACT_NAME + " TEXT, "
                    + COLUMN_CONTACT_ISBLOCK + " INTEGER)";
            String createTableSmsBlock = "CREATE TABLE " + TABLE_SMS_BLOCK + "("
                    + COLUMN_CONTACT_NUMBER + " TEXT PRIMARY KEY, " + COLUMN_CONTACT_NAME + " TEXT, "
                    + COLUMN_CONTACT_ISBLOCK + " INTEGER)";
            String createTableMessage = "CREATE TABLE message_table(_id INREGER PRIMARY KEY," +
                    "thread_id INTEGER, address TEXT, person TEXT, date TEXT, protocol TEXT, read TEXT, " +
                    "status TEXT, type TEXT, reply_path_present TEXT, subject TEXT, body TEXT, " +
                    "service_center TEXT, locked String )" ;
            db.execSQL(createTableCallBlock);
            db.execSQL(createTableSmsBlock);
            db.execSQL(createTableMessage);
        }catch (Exception e){
            e.printStackTrace();
        }
    }// end onCreate

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CALL_BLOCK);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SMS_BLOCK);
        onCreate(db);
    }//end onUpfrade

    public void addContact(Contact contact){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CONTACT_NUMBER, contact.getNumber());
        values.put(COLUMN_CONTACT_NAME, contact.getName());
//        values.put(COLUMN_CONTACT_IMAGE, contact.getImage());
        values.put(COLUMN_CONTACT_ISBLOCK, contact.isBlock()?1:0);
        db.insert(TABLE_CALL_BLOCK, null, values);

    }//end addContact

    public Contact getContact(String number){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CALL_BLOCK, new String[]{COLUMN_CONTACT_NUMBER,
                COLUMN_CONTACT_NAME, COLUMN_CONTACT_ISBLOCK},
                COLUMN_CONTACT_NUMBER +"=?", new String[] {number},
                null, null, null, null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        Contact contact = new Contact(cursor.getString(1),cursor.getString(0),
                cursor.getInt(2)!=0);

        return contact;

    }//end getContact

    public ArrayList<Contact> getAllContacts(){
        ArrayList<Contact> contactArrayList = new ArrayList<Contact>();
        String selectQuery = "SELECT * FROM "+ TABLE_CALL_BLOCK;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact(cursor.getString(1),
                       cursor.getString(0), cursor.getInt(2)!=0);

                contactArrayList.add(contact);
            } while (cursor.moveToNext());
        }
        db.close();
        return contactArrayList;
    }// end getAllContacts

    public int updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CONTACT_NUMBER, contact.getNumber());
        values.put(COLUMN_CONTACT_NAME, contact.getName());
//        values.put(COLUMN_CONTACT_IMAGE, contact.getImage());
        values.put(COLUMN_CONTACT_ISBLOCK, contact.isBlock());

        return db.update(TABLE_CALL_BLOCK, values, COLUMN_CONTACT_NUMBER + "=?",
                new String[] {contact.getNumber()});
    }//end updateContact

    public void deleteContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CALL_BLOCK, COLUMN_CONTACT_NUMBER + "=?",
                new String[] { String.valueOf(contact.getNumber()) });
        db.close();
    }//end deleteContact

    public int getContactsCount() {
        String countQuery = "SELECT * FROM " + TABLE_CALL_BLOCK;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }//end getContactsCount
    //............................................................................................
    //SMS

    public void addSms(Contact contact){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CONTACT_NUMBER, contact.getNumber());
        values.put(COLUMN_CONTACT_NAME, contact.getName());
//        values.put(COLUMN_CONTACT_IMAGE, contact.getImage());
        values.put(COLUMN_CONTACT_ISBLOCK, contact.isBlock()?1:0);
        db.insert(TABLE_SMS_BLOCK, null, values);

    }//end addSms
    public Contact getSms(String number){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_SMS_BLOCK, new String[]{COLUMN_CONTACT_NUMBER,
                        COLUMN_CONTACT_NAME, COLUMN_CONTACT_ISBLOCK},
                COLUMN_CONTACT_NUMBER +"=?", new String[] {number},
                null, null, null, null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        Contact contact = new Contact(cursor.getString(1),cursor.getString(0),
                cursor.getInt(2)!=0);

        return contact;
    }//end getSms

    public ArrayList<Contact> getAllSms(){
        ArrayList<Contact> smsArrayList = new ArrayList<Contact>();
        String selectQuery = "SELECT * FROM "+ TABLE_SMS_BLOCK;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact(cursor.getString(1),
                        cursor.getString(0), cursor.getInt(2)!=0);

                smsArrayList.add(contact);
            } while (cursor.moveToNext());
        }
        db.close();
        return smsArrayList;
    }// end getAllSms
    public int updateSms(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CONTACT_NUMBER, contact.getNumber());
        values.put(COLUMN_CONTACT_NAME, contact.getName());
//        values.put(COLUMN_CONTACT_IMAGE, contact.getImage());
        values.put(COLUMN_CONTACT_ISBLOCK, contact.isBlock());

        return db.update(TABLE_SMS_BLOCK, values, COLUMN_CONTACT_NUMBER + "=?",
                new String[] {contact.getNumber()});
    }//end updateSms
    public void deleteSms(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SMS_BLOCK, COLUMN_CONTACT_NUMBER + "=?",
                new String[] { String.valueOf(contact.getNumber()) });
        db.close();
    }//end deleteContact

    public int getSmsCount() {
        String countQuery = "SELECT * FROM " + TABLE_SMS_BLOCK;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }//end getSmsCount

}
