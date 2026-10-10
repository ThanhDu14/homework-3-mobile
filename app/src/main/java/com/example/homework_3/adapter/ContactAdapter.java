
package com.example.homework_3.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.homework_3.R;
import com.example.homework_3.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends BaseAdapter {

    private final LayoutInflater inflater;
    private List<Contact> items;

    @Nullable
    private Contact selectedContact;

    /** Chiều cao mỗi item (px); 0 = giữ wrap_content của item_contact.xml. */
    private int itemHeight = 0;

    public ContactAdapter(Context context, List<Contact> items) {
        this.inflater = LayoutInflater.from(context);
        this.items = new ArrayList<>(items);
    }

    public void setItems(List<Contact> items) {
        this.items = new ArrayList<>(items);
        notifyDataSetChanged();
    }

    public void setSelectedContact(@Nullable Contact c) {
        this.selectedContact = c;
        notifyDataSetChanged();
    }

    public void setItemHeight(int itemHeight) {
        if (this.itemHeight == itemHeight) {
            return;
        }
        this.itemHeight = itemHeight;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Contact getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(
            int position,
            View convertView,
            ViewGroup parent
    ) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(
                    R.layout.item_contact,
                    parent,
                    false
            );

            holder = new ViewHolder();

            holder.imgAvatar =
                    convertView.findViewById(R.id.imgAvatar);

            holder.tvName =
                    convertView.findViewById(R.id.tvName);

            holder.tvPhone =
                    convertView.findViewById(R.id.tvPhone);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (itemHeight > 0) {
            ViewGroup.LayoutParams lp = convertView.getLayoutParams();
            if (lp == null) {
                lp = new AbsListView.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, itemHeight);
            } else {
                lp.height = itemHeight;
            }
            convertView.setLayoutParams(lp);
        }

        Contact contact = getItem(position);

        holder.imgAvatar.setImageResource(
                contact.getAvatarResId()
        );

        holder.tvName.setText(contact.getName());
        holder.tvPhone.setText(contact.getPhone());

        if (contact == selectedContact) {
            convertView.setBackgroundResource(
                    R.drawable.bg_item_selected
            );
        } else {
            convertView.setBackgroundResource(
                    R.drawable.bg_item_normal
            );
        }

        return convertView;
    }

    private static class ViewHolder {
        ImageView imgAvatar;
        TextView tvName;
        TextView tvPhone;
    }
}
