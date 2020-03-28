package com.ads.contactsdemo;

import android.os.Bundle;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.ads.contactsdemo.model.Contact;
import com.ads.contactsdemo.viewmodel.ContactViewModel;

public class AddContactActivity extends AppCompatActivity {

    private ContactViewModel contactViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        // **
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_white_24dp);
    }

    private void addContactToDb() {
        EditText contactNameEditText = findViewById(R.id.editText1);
        EditText contactNumberEditText = findViewById(R.id.editText2);
        EditText contactEmailEditText = findViewById(R.id.editText3);

        // **
        String contactName = contactNameEditText.getText().toString();
        String contactNumber = contactNumberEditText.getText().toString();
        String contactEmail = contactEmailEditText.getText().toString();
        // **
        Contact contact = new Contact();
        contact.setContactName(contactName);
        contact.setContactNumber(contactNumber);
        contact.setContactEmail(contactEmail);

        if (contactName.isEmpty() || contactNumber.isEmpty() || contactEmail.isEmpty() ){
            Toast.makeText(this, "Please insert data in all fields!", Toast.LENGTH_SHORT).show();
        } else if(!Patterns.EMAIL_ADDRESS.matcher(contactEmail).matches()){
            Toast.makeText(this, "Please enter valid email!", Toast.LENGTH_SHORT).show();
        } else {
            // **
            contactViewModel = new ViewModelProvider(AddContactActivity.this).get(ContactViewModel.class);
            contactViewModel.insert(contact);
            // **
            Toast.makeText(this, "Contact saved!", Toast.LENGTH_SHORT).show();
            onBackPressed();
        }

    }

    // ActionBar Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_contact_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_save) {
            // **
            addContactToDb();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
