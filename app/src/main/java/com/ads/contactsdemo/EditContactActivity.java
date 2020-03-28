package com.ads.contactsdemo;

import android.content.Intent;
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


public class EditContactActivity extends AppCompatActivity {

    private Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        // **
        contact = getIntent().getParcelableExtra("contact");

        // **
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_white_24dp);

        // **
        EditText contactNameEditText = findViewById(R.id.editText1);
        EditText contactNumberEditText = findViewById(R.id.editText2);
        EditText contactEmailEditText = findViewById(R.id.editText3);

        // **
        contactNameEditText.setText(contact.getContactName());
        contactNumberEditText.setText(contact.getContactNumber());
        contactEmailEditText.setText(contact.getContactEmail());
    }

    private void updateContactInDb() {
        EditText contactNameEditText = findViewById(R.id.editText1);
        EditText contactNumberEditText = findViewById(R.id.editText2);
        EditText contactEmailEditText = findViewById(R.id.editText3);
        // **
        String contactName = contactNameEditText.getText().toString();
        String contactNumber = contactNumberEditText.getText().toString();
        String contactEmail = contactEmailEditText.getText().toString();
        // **
        contact.setContactName(contactName);
        contact.setContactNumber(contactNumber);
        contact.setContactEmail(contactEmail);

        if (contactName.isEmpty() || contactNumber.isEmpty() || contactEmail.isEmpty() ){
            Toast.makeText(this, "Please insert data in all fields!", Toast.LENGTH_SHORT).show();
        } else if(!Patterns.EMAIL_ADDRESS.matcher(contactEmail).matches()){
            Toast.makeText(this, "Please enter valid email!", Toast.LENGTH_SHORT).show();
        } else {
            // **
            ContactViewModel contactViewModel = new ViewModelProvider(EditContactActivity.this).get(ContactViewModel.class);
            contactViewModel.update(contact);
            // **
            Toast.makeText(this, "Contact updated!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(EditContactActivity.this,MainActivity.class));
            finish();
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
            updateContactInDb();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
