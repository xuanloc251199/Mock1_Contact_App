package com.xuanloc.mock1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.xuanloc.mock1.R;
import com.xuanloc.mock1.model.Contact;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private Context mContext;
    List<Contact> mContactList;

    public ContactAdapter(Context mContext, List<Contact> mContactArrayList) {
        this.mContext = mContext;
        this.mContactList = mContactArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_contact, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Contact contact = mContactList.get(position);

        holder.txtName.setText(contact.getmName());
        holder.txtPhone.setText(contact.getmPhoneNumber());

        if (contact.getmAvatar() != null){
            Glide.with(mContext).load(contact.getmAvatar()).into(holder.imgAvatar);
        }else {
            holder.imgAvatar.setImageResource(R.drawable.ic_contact);
        }

    }

    @Override
    public int getItemCount() {
        return mContactList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtName, txtPhone;
        ImageView imgAvatar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txtName);
            txtPhone = itemView.findViewById(R.id.txtPhoneNumber);
            imgAvatar = itemView.findViewById(R.id.imgAvatar);

        }
    }
}
