package com.xuanloc.mock1.ui;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.database.Cursor;
import android.icu.text.SimpleDateFormat;
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
import com.xuanloc.mock1.adapter.CallLogAdapter;
import com.xuanloc.mock1.adapter.ContactAdapter;
import com.xuanloc.mock1.model.CallLog;
import com.xuanloc.mock1.model.Contact;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.Manifest.permission.READ_CALL_LOG;
import static android.Manifest.permission.READ_CONTACTS;

public class CallLogFragment extends Fragment {

    private View mView;
    private List<CallLog> mCallLogList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private CallLogAdapter mCallLogAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_call_log, container, false);

        initId();

        setUpRecyclerView();

        checkPermissionCallLog();

        return mView;
    }

    private void setUpRecyclerView() {

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mCallLogAdapter = new CallLogAdapter(getContext(), mCallLogList);
        mRecyclerView.setAdapter(mCallLogAdapter);
        mCallLogAdapter.notifyDataSetChanged();

    }

    private void initId() {
        mRecyclerView = mView.findViewById(R.id.rvCallLog);
    }

    private void checkPermissionCallLog() {
        Dexter.withContext(getContext())
                .withPermission(READ_CALL_LOG)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        if (permissionGrantedResponse.getPermissionName().equals(READ_CALL_LOG)) {
                            getCallLogs();
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

    private void getCallLogs() {
        String sort = android.provider.CallLog.Calls.DATE + " DESC";
        Uri callUri = android.provider.CallLog.Calls.CONTENT_URI;
        Cursor curLog = getContext().getContentResolver().query(callUri, null, null, null, sort);

        while (curLog.moveToNext()) {
            String callNumber = curLog.getString(curLog
                    .getColumnIndex(android.provider.CallLog.Calls.NUMBER));

            String callDate = curLog.getString(curLog.getColumnIndex(
                    android.provider.CallLog.Calls.DATE
            ));
            @SuppressLint("SimpleDateFormat") SimpleDateFormat formatDate = new SimpleDateFormat(
                    "HH:mm:ss dd-MM-yyyy");
            String dateString = formatDate.format(new Date(Long
                    .parseLong(callDate)));

            String callDuration = curLog.getString(
                    curLog.getColumnIndex(android.provider.CallLog.Calls.DURATION
                    ));

            CallLog callLog = new CallLog(callNumber, durationString(callDuration), dateString);

            mCallLogList.add(callLog);

            mCallLogAdapter.notifyDataSetChanged();
        }

    }

    private String durationString(String duration) {

        String formatTime = "";

        int minute = Integer.parseInt(duration) / 60;
        int seconds = Integer.parseInt(duration) % 60;

        formatTime = "[" + minute + ":" + seconds + "]";

        return formatTime;
    }
}