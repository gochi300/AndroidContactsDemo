package com.ads.contactsdemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ads.contactsdemo.R;
import com.ads.contactsdemo.ViewContactActivity;
import com.ads.contactsdemo.model.Contact;

import java.util.List;
import java.util.Random;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder>{

    private Context mContext;
    private List<Contact> mContacts;

    public ContactAdapter(Context mContext, List<Contact> mContacts) {
        this.mContext = mContext;
        this.mContacts = mContacts;
    }

    @NonNull
    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_contact_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.ViewHolder viewHolder, final int i) {

        // - get element from your dataset at this position
        final Contact contact = mContacts.get(i);
        // **
        Random mRandom = new Random();
        final int color = Color.argb(255, mRandom.nextInt(256), mRandom.nextInt(256), mRandom.nextInt(256));
        ((GradientDrawable) viewHolder.mIcon.getBackground()).setColor(color);
        // **
        viewHolder.contactName.setText(contact.getContactName());
        viewHolder.listItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, ViewContactActivity.class);
                intent.putExtra("contact", contact);
                mContext.startActivity(intent);

            }
        });

    }


    @Override
    public int getItemCount() {
        return mContacts.size();
    }


    public void setmContacts(List<Contact> mContacts) {
        this.mContacts = mContacts;
        notifyDataSetChanged();
    }


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView mIcon;
        public TextView contactName;
        public RelativeLayout listItem;
        public ViewHolder(View itemView) {
            super(itemView);
            mIcon = itemView.findViewById(R.id.mIcon);
            contactName = itemView.findViewById(R.id.textView1);
            listItem = itemView.findViewById(R.id.contactItem);
        }
    }
}
