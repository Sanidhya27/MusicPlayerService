package com.example.android.moodindigo;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.example.android.moodindigo.data.RegistrationRequest;
import com.example.android.moodindigo.data.RegistrationResponse;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    RetrofitClass rcinitiate;
    RegistrationRequest registrationRequest=new RegistrationRequest();

    @BindView(R.id.name)
    EditText et_name;
    @BindView(R.id.email)
    EditText et_email;
    @BindView(R.id.mobile)
    EditText et_mobile;
    @BindView(R.id.city)
    EditText et_city;
    @BindView(R.id.college)
    EditText et_college;
    @BindView(R.id.address)
    EditText et_address;
    @BindView(R.id.zip)
    EditText et_zip;
    @BindView(R.id.year_spinner)
    Spinner year_spinner;
    @BindView(R.id.submit)
    Button submit_button;
    String year;
    @BindView(R.id.dob)
    EditText dob;

    String userdetails="user details";
    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    String id;
    Object image;
    String name;
    RegistrationResponse registrationResponse=new RegistrationResponse();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        ButterKnife.bind(this);

        prefs=getSharedPreferences(userdetails,MODE_PRIVATE);
        name=prefs.getString("name",null);
        et_name.setText(name);
        id= prefs.getString("fbid",null);
        image=prefs.getString("image",null);
        editor.remove("name");
        editor.remove("fbid");
        editor.remove("image");
        editor.commit();
        year_spinner.setOnItemSelectedListener(this);

        final List<String> years=new ArrayList<>();
        years.add("First");
        years.add("Second");
        years.add("Third");
        years.add("Fourth");
        years.add("Fifth");
        years.add("Select Year of Study");

        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,years);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        year_spinner.setAdapter(arrayAdapter);
        year_spinner.setSelection(5);

        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int mYear = calendar.get(Calendar.YEAR);
                int mMonth = calendar.get(Calendar.MONTH);
                int mDay = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog=new DatePickerDialog(RegistrationActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dateofmonth) {
                        dob.setText(dateofmonth+"/"+month+"/"+year);
                    }
                },mYear,mMonth,mDay);

                datePickerDialog.show();

            }
        });





        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("No Error" ,"No Error");
                registrationRequest.setName(et_name.getText().toString());
                Log.e("Error" ,"Error");
                registrationRequest.setEmail(et_email.getText().toString());
                registrationRequest.setMobile_number(et_mobile.getText().toString());
                registrationRequest.setPresent_city(et_city.getText().toString());
                registrationRequest.setPresent_college(et_college.getText().toString());
                registrationRequest.setPostal_address(et_address.getText().toString());
                registrationRequest.setZip_code(Integer.parseInt(et_zip.getText().toString()));
                registrationRequest.setFb_id(id);
                registrationRequest.setYear_of_study(year);

                registrationRequest.setDob(dob.getText().toString());




                rcinitiate = new RetrofitClass(RegistrationActivity.this);

                SearchInterface client = rcinitiate.createBuilder().create(SearchInterface.class);
                rcinitiate.startLogging();

                Call<RegistrationResponse> call = client.fillRegistrationForm(registrationRequest);

                call.enqueue(new retrofit2.Callback<RegistrationResponse>() {
                    @Override
                    public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {
                        Log.i("Indicator","Entered");
                        Intent main = new Intent(RegistrationActivity.this, MainActivity.class);
                        editor.putString("name", registrationResponse.getName());
                        editor.putString("email", registrationResponse.getEmail());
                        editor.putString("image",image.toString());
                        editor.putString("mi number",registrationResponse.getMi_number());
                        editor.putString("fbid",registrationResponse.getFb_id());
                        editor.putString("college",registrationResponse.getPresent_college());
                        editor.putString("city",registrationResponse.getPresent_city());
                        editor.putString("address",registrationResponse.getPostal_address());
                        editor.putInt("zip",registrationResponse.getZip_code());
                        editor.putString("mobile",registrationResponse.getMobile_number());
                        editor.putString("year",registrationResponse.getYear_of_study());
                        editor.putString("dob",registrationResponse.getDob());
                        editor.putBoolean("isLoggedIn",true);
                        editor.commit();
                        startActivity(main);


                    }

                    @Override
                    public void onFailure(Call<RegistrationResponse> call, Throwable t) {
                        Toast.makeText(RegistrationActivity.this, "Retry re!", Toast.LENGTH_SHORT).show();
                    }
                });

            }

        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        year=adapterView.getItemAtPosition(i).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
