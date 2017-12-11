package com.example.android.moodindigo.data;

import java.util.List;

/**
 * Created by mrunz on 27/9/17.
 */

public class ContactDetails {

    private String title;
    private List<ContactItem> contactItems;

    public ContactDetails(String title, List<ContactItem> contactItems) {
        this.contactItems = contactItems;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public List<ContactItem> getContactItems() {
        return contactItems;
    }
}
