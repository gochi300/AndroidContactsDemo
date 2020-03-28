package com.ads.contactsdemo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.ads.contactsdemo.model.Contact;
import com.ads.contactsdemo.viewmodel.ContactViewModel;


public class ViewContactActivity extends AppCompatActivity {

    private Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact);

        // Get object from intent
        contact = getIntent().getParcelableExtra("contact");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // this line shows back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(contact.getContactName());
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_white_24dp);


        // **
        TextView textView1 = findViewById(R.id.textView1);
        TextView textView2 = findViewById(R.id.textView2);

        // **
        textView1.setText(contact.getContactNumber());
        textView2.setText(contact.getContactEmail());

    }

    public void deleteContactPrompt(){
        AlertDialog alertDialog = new AlertDialog.Builder(ViewContactActivity.this).create();
        //alertDialog.setTitle("Process contact");
        alertDialog.setMessage("Delete contact?");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE,"Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int i) {
                        // **
                        deleteContactFromDb();
                        Toast.makeText(ViewContactActivity.this, "Contact deleted!", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        onBackPressed();
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    public void deleteContactFromDb(){
        // **
        ContactViewModel contactViewModel = new ViewModelProvider(ViewContactActivity.this).get(ContactViewModel.class);
        contactViewModel.delete(contact);
    }

    // ActionBar Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.edit_contact_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_edit) {
            Intent intent = new Intent(ViewContactActivity.this,EditContactActivity.class);
            intent.putExtra("contact", contact);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_delete){
            deleteContactPrompt();
        }

        return super.onOptionsItemSelected(item);
    }
}
