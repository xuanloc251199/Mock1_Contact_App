package com.xuanloc.mock1.ui;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.xuanloc.mock1.R;
import com.xuanloc.mock1.adapter.ContactAdapter;
import com.xuanloc.mock1.model.Contact;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

public class ContactFragment extends Fragment {

    private View mView;

    private List<Contact> mListContacts = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private ContactAdapter mContactAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_contact, container, false);

        initId();

        setUpRecyclerView();

        checkPermissionContact();

        return mView;
    }

    private void checkPermissionContact() {
        Dexter.withContext(getContext())
                .withPermission(READ_CONTACTS)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        if (permissionGrantedResponse.getPermissionName().equals(READ_CONTACTS)){
                            getContactList();
                        }
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                        Toast.makeText(getContext(), "Permission should be granted!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();

    }

    private void setUpRecyclerView() {

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mContactAdapter = new ContactAdapter(getContext(), mListContacts);
        mRecyclerView.setAdapter(mContactAdapter);
        mContactAdapter.notifyDataSetChanged();

    }

    private void getContactList() {
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor phones = getContext().getContentResolver()
                .query(uri, null, null, null, null);
        while (phones.moveToNext()){

            String name = phones.getString(phones.getColumnIndex(
                    ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
            ));
            String phone = phones.getString(phones.getColumnIndex(
                    ContactsContract.CommonDataKinds.Phone.NUMBER
            ));
            String avt = phones.getString(phones.getColumnIndex(
                    ContactsContract.CommonDataKinds.Phone.PHOTO_URI
            ));

            Contact contact = new Contact(name, phone, avt);

            mListContacts.add(contact);

            mContactAdapter.notifyDataSetChanged();

        }
    }

    private void initId() {
        mRecyclerView = mView.findViewById(R.id.rvContact);
    }

}