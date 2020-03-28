package com.ads.contactsdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;


import com.ads.contactsdemo.adapter.ContactAdapter;
import com.ads.contactsdemo.model.Contact;
import com.ads.contactsdemo.viewmodel.ContactViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

   private ContactViewModel contactViewModel;
   private ContactAdapter contactAdapter;
   private List<Contact> contactList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // **
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,AddContactActivity.class));
            }
        });

        /*RecyclerView*/
        RecyclerView mRecyclerView = findViewById(R.id.recycler_view);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        LayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(MainActivity.this, DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(dividerItemDecoration);
        contactAdapter = new ContactAdapter(MainActivity.this, contactList);
        contactAdapter.setmContacts(contactList);
        mRecyclerView.setAdapter(contactAdapter);

        getContactsFromDb();
    }

    private void getContactsFromDb() {
        // **
        contactViewModel = new ViewModelProvider(MainActivity.this).get(ContactViewModel.class);
        contactViewModel.getAllContacts().observe(this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(@Nullable List<Contact> contactList1) {
                contactAdapter.setmContacts(contactList1);
                contactList.addAll(contactList1);
            }
        });
    }
}
