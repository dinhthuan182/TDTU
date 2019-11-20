package com.example.blockingcallsapplication;

import android.support.annotation.Nullable;

public class Contact {
    private String name;
    private String number;
    private boolean isBlock;

    public Contact(String name, String number, boolean isBlock) {
        this.name = name;
        this.number = number;
        this.isBlock = isBlock;
    }
    public Contact(){

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setBlock(boolean block) {
        isBlock = block;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public boolean isBlock() {
        return isBlock;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(obj.getClass().isInstance(new Contact()))
        {
            // Cast the object to Blacklist
            final Contact bl = (Contact) obj;

            // Compare whether the phone numbers are same, if yes, it defines the objects are equal
            if(bl.number.equalsIgnoreCase(this.number))
                return true;
        }
        return false;
    }
}
