package com.ads.contactsdemo.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.ads.contactsdemo.model.Contact;
import com.ads.contactsdemo.repository.ContactRepository;

import java.util.List;

public class ContactViewModel extends AndroidViewModel {
    private ContactRepository contactRepository;
    private LiveData<List<Contact>> allContacts;

    public ContactViewModel(@NonNull Application application) {
        super(application);
        contactRepository = new ContactRepository(application);
        allContacts = contactRepository.getAllContacts();
    }

    public void insert(Contact contact) {
        contactRepository.insert(contact);
    }

    public void update(Contact contact) {
        contactRepository.update(contact);
    }

    public void delete(Contact contact) {
        contactRepository.delete(contact);
    }

    public LiveData<List<Contact>> getAllContacts() {
        return allContacts;
    }


}
