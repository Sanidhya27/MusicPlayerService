package com.example.android.moodindigo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.moodindigo.data.ContactDetails;
import com.example.android.moodindigo.data.ContactItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mrunz on 27/9/17.
 */

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder>{
    List<ContactDetails> details;
    Context context;


    public ContactsAdapter( List<ContactDetails> details,
    Context context) {
        this.details=details;
        this.context=context;
    }

    @Override
    public ContactsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());

        View view=inflater.inflate(R.layout.contact_list_item,parent,false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(ContactsAdapter.ViewHolder holder, int position) {

        holder.title.setText(details.get(position).getTitle());

        List<ContactItem> items=details.get(position).getContactItems();
        int size=items.size();
        if(size==1){
            holder.layout1.setVisibility(View.GONE);
            holder.layout2.setVisibility(View.VISIBLE);
            holder.name3.setText(items.get(0).getName());
            holder.phone3.setText(items.get(0).getPhone());
            holder.email3.setText(items.get(0).getEmail());
            holder.pic3.setBackground(items.get(0).getImage());
        }
        else if(size==2){
            holder.layout2.setVisibility(View.GONE);
            holder.layout1.setVisibility(View.VISIBLE);
            holder.name1.setText(items.get(0).getName());
            holder.phone1.setText(items.get(0).getPhone());
            holder.email1.setText(items.get(0).getEmail());
            holder.pic1.setBackground(items.get(0).getImage());
            holder.name2.setText(items.get(1).getName());
            holder.phone2.setText(items.get(1).getPhone());
            holder.email2.setText(items.get(1).getEmail());
            holder.pic2.setBackground(items.get(1).getImage());
        }
        else if(size==3){
            holder.layout2.setVisibility(View.VISIBLE);
            holder.layout1.setVisibility(View.VISIBLE);
            holder.name1.setText(items.get(0).getName());
            holder.phone1.setText(items.get(0).getPhone());
            holder.email1.setText(items.get(0).getEmail());
            holder.pic1.setBackground(items.get(0).getImage());
            holder.name2.setText(items.get(1).getName());
            holder.phone2.setText(items.get(1).getPhone());
            holder.email2.setText(items.get(1).getEmail());
            holder.pic2.setBackground(items.get(1).getImage());
            holder.name3.setText(items.get(2).getName());
            holder.phone3.setText(items.get(2).getPhone());
            holder.email3.setText(items.get(2).getEmail());
            holder.pic3.setBackground(items.get(2).getImage());
        }



    }

    @Override
    public int getItemCount() {
        return details.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name1;
        TextView phone1;
        TextView email1;
        TextView name2;
        TextView phone2;
        TextView email2;
        TextView name3;
        TextView phone3;
        TextView email3;
        LinearLayout layout2;
        LinearLayout layout1;
        TextView title;
        LinearLayout mainLayout;
        FrameLayout pic1;
        FrameLayout pic2;
        FrameLayout pic3;
        public ViewHolder(View itemView) {
            super(itemView);

            name1=itemView.findViewById(R.id.name1);
            phone1=itemView.findViewById(R.id.number1);
            email1=itemView.findViewById(R.id.email1);

            name2=itemView.findViewById(R.id.name2);
            phone2=itemView.findViewById(R.id.number2);
            email2=itemView.findViewById(R.id.email2);

            name3=itemView.findViewById(R.id.name3);
            phone3=itemView.findViewById(R.id.number3);
            email3=itemView.findViewById(R.id.email3);

            layout1=itemView.findViewById(R.id.contact1);
            layout2=itemView.findViewById(R.id.contact2);
            mainLayout=itemView.findViewById(R.id.main_layout);

            title=itemView.findViewById(R.id.title);

            pic1=itemView.findViewById(R.id.pic1);
            pic2=itemView.findViewById(R.id.pic2);
            pic3=itemView.findViewById(R.id.pic3);

        }
    }
}
