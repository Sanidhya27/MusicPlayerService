package com.example.android.moodindigo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.android.moodindigo.data.ContactDetails;
import com.example.android.moodindigo.data.ContactItem;

import java.util.ArrayList;
import java.util.List;

public class ContactsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);


        RecyclerView recyclerView = findViewById(R.id.list);
        ContactDetails contactDetails1;
        List<ContactDetails> details = new ArrayList<>();

        List<ContactItem> items = new ArrayList<>();
        items.add(new ContactItem("Bhavishya Rathore", "bhavishya@moodi.org", "+91-8770870693",this.getResources().getDrawable(R.drawable.bhavishya)));
        items.add(new ContactItem("Rajat Kumar", "rajat@moodi.org", "+91-8860574839",this.getResources().getDrawable(R.drawable.rajat)));
        details.add(new ContactDetails("Accomodations & Public Relations", items));

        items = new ArrayList<>();
        items.add(new ContactItem("Ajay Suwalka", "ajay@moodi.org", "+91-8828292009",this.getResources().getDrawable(R.drawable.ajay)));
        items.add(new ContactItem("Unnishankar Jayaprakash", "unni@moodi.org", "+91-9594567827",this.getResources().getDrawable(R.drawable.unnishankar)));
        details.add(new ContactDetails("Competitions & LYP", items));

        items = new ArrayList<>();
        items.add(new ContactItem("Bakhsh Mangat", "bakhsh @moodi.org", "+91 - 8879135156",this.getResources().getDrawable(R.drawable.baksh)));
        items.add(new ContactItem("Darshan Tank", "darshan @moodi.org", " +91 - 9969575065",this.getResources().getDrawable(R.drawable.darshan)));
        items.add(new ContactItem("Manit Rambhia", "manit @moodi.org", "+91 - 9619120821",this.getResources().getDrawable(R.drawable.manit)));
        details.add(new ContactDetails("Creatives", items));

        items = new ArrayList<>();
        items.add(new ContactItem(" Shreyansh Mehta", "shreyansh @moodi.org", "+91 - 7045800361",this.getResources().getDrawable(R.drawable.shreyansh)));
        details.add(new ContactDetails("Food & Beverages", items));

        items = new ArrayList<>();
        items.add(new ContactItem("Anant Jain", "anant @moodi.org", "+91 - 7045800195",this.getResources().getDrawable(R.drawable.anant)));
        items.add(new ContactItem("Deepshikha Meena", "deepshikha @moodi.org", "+91 - 8828234577",this.getResources().getDrawable(R.drawable.deepshikha)));
        items.add(new ContactItem("Siddhant Jain", "siddhant @moodi.org", "+91 - 8879154549",this.getResources().getDrawable(R.drawable.siddhant)));
        details.add(new ContactDetails("Horizons & Workshops", items));

        items = new ArrayList<>();
        items.add(new ContactItem("Katyayani Shinde", "katy @moodi.org", "+91 - 9029004596",this.getResources().getDrawable(R.drawable.katyayani)));
        items.add(new ContactItem("Shashank Shekhar", "shashank @moodi.org", "+91 - 9969602862",this.getResources().getDrawable(R.drawable.shashank)));
        details.add(new ContactDetails("Informals", items));

        items = new ArrayList<>();
        items.add(new ContactItem("Chaitanya Jain", "chaitanya @moodi.org", "+91 - 9167338233",this.getResources().getDrawable(R.drawable.chaitanya)));
        items.add(new ContactItem("Harsh Prasad", "harsh @moodi.org", "+91 - 8879443797",this.getResources().getDrawable(R.drawable.harsh)));
        items.add(new ContactItem("Sanket Agrawal", "sanket @moodi.org", "+91 - 7045800191",this.getResources().getDrawable(R.drawable.sanket)));
        details.add(new ContactDetails("Marketing", items));

        items = new ArrayList<>();
        items.add(new ContactItem("Karan Trichal", "karan @moodi.org", "+91 - 9833895389",this.getResources().getDrawable(R.drawable.karan)));
        items.add(new ContactItem("Kunal Jain", "kunal @moodi.org", "+91 - 9967553061",this.getResources().getDrawable(R.drawable.kunal)));
        details.add(new ContactDetails("Media & Publicity", items));

        items = new ArrayList<>();
        items.add(new ContactItem("Ayush Chouksey", "ayush @moodi.org", "+91 - 7045800477",this.getResources().getDrawable(R.drawable.ayush)));
        items.add(new ContactItem(" Nikhil Vaidya", "nikhil @moodi.org", "+91 - 9004932980",this.getResources().getDrawable(R.drawable.nikhil)));
        details.add(new ContactDetails("Pronites", items));

        items = new ArrayList<>();
        items.add(new ContactItem("Mayank Gurjar", "mayank @moodi.org", "+91 - 8879249203",this.getResources().getDrawable(R.drawable.mayank)));
        items.add(new ContactItem("Rohit Patidar", "rohit @moodi.org", "+91 - 9004685969",this.getResources().getDrawable(R.drawable.rohit)));
        details.add(new ContactDetails("Services", items));

        items = new ArrayList<>();
        items.add(new ContactItem("Amiya Maitreya", "amiya @moodi.org", "+91 - 9819933199",this.getResources().getDrawable(R.drawable.amiya)));
        items.add(new ContactItem("Anmol Rawat", "anmol.r @moodi.org", "+91 - 9167848190",this.getResources().getDrawable(R.drawable.anmol)));
        details.add(new ContactDetails("Overall Co-ordinators", items));

        ContactsAdapter contactsAdapter = new ContactsAdapter(details, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(contactsAdapter);

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
