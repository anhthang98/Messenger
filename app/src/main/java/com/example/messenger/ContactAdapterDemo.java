package com.example.messenger;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ContactAdapterDemo extends RecyclerView.Adapter<ContactAdapterDemo.Viewhoder> {
    private static OnItemClickListener listener;
    List<Contact> contacts;
    IonClickContact ionClickContact;
    Context context;


    public void setIonClickContact(IonClickContact ionClickContact) {
        this.ionClickContact = ionClickContact;
    }

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public ContactAdapterDemo(List<Contact> contacts) {
        this.contacts = contacts;

    }

    @NonNull
    @Override
    public Viewhoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_contact, parent, false);

        Viewhoder viewhoder = new Viewhoder(view);
        return viewhoder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewhoder holder, final int position) {

        final Contact contact = contacts.get(position);

        holder.tvName.setText(contact.getName());


        holder.tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ionClickContact.onClickName(contact.getName(), position);
            }
        });


        holder.tvNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ionClickContact.onClickPhone(contact);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class Viewhoder extends RecyclerView.ViewHolder {
        TextView tvName, tvNumber;
        ImageView imgIcon;

        public Viewhoder(@NonNull final View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvNumber = itemView.findViewById(R.id.tvPhone);
            imgIcon = itemView.findViewById(R.id.imgIcon);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.onItemClick(itemView, getLayoutPosition());
                    }
                }
            });
        }
    }
}
