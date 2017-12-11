package com.example.android.moodindigo.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mrunz on 8/12/17.
 */

public class Checksum {
    @SerializedName("")
    private boolean checksum;

    public Checksum(){

    }

    public boolean getChecksum() {
        return checksum;
    }

    public void setChecksum(boolean checksum) {
        this.checksum = checksum;
    }
}
