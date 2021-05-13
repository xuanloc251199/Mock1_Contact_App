package com.xuanloc.mock1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xuanloc.mock1.R;
import com.xuanloc.mock1.model.CallLog;

import java.util.List;

public class CallLogAdapter extends RecyclerView.Adapter<CallLogAdapter.ViewHolder> {

    private Context mContext;
    private List<CallLog> mCallLogList;

    public CallLogAdapter(Context mContext, List<CallLog> mCallLogList) {
        this.mContext = mContext;
        this.mCallLogList = mCallLogList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_call_log, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        CallLog callLog = mCallLogList.get(position);

        holder.phoneNumber.setText(callLog.getPhoneNumber());
        holder.duration.setText(callLog.getDuration());
        holder.date.setText(callLog.getDate());

    }

    @Override
    public int getItemCount() {
        return mCallLogList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView phoneNumber, duration, date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            phoneNumber = itemView.findViewById(R.id.txtPhone);
            duration = itemView.findViewById(R.id.txtDuration);
            date = itemView.findViewById(R.id.txtDate);

        }
    }
}
