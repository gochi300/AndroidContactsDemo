package com.ads.contactsdemo.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "contacts")
public class Contact implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String contactName;
    private String contactNumber;
    private String contactEmail;

    public Contact() {
    }

    @Ignore
    public Contact(int id, String contactName, String contactNumber, String contactEmail) {
        this.id = id;
        this.contactName = contactName;
        this.contactNumber = contactNumber;
        this.contactEmail = contactEmail;
    }


    protected Contact(Parcel in) {
        id = in.readInt();
        contactName = in.readString();
        contactNumber = in.readString();
        contactEmail = in.readString();
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", contactName='" + contactName + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", contactEmail='" + contactEmail + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(contactName);
        parcel.writeString(contactNumber);
        parcel.writeString(contactEmail);
    }
}
