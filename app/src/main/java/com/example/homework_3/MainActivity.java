package com.example.homework_3;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.homework_3.adapter.ContactAdapter;
import com.example.homework_3.data.ContactRepository;
import com.example.homework_3.model.Contact;
import com.example.homework_3.pagination.PageButtons;
import com.example.homework_3.pagination.Paginator;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_CURRENT_PAGE = "current_page";
    private static final String KEY_SELECTED_INDEX = "selected_index";

    private final List<Contact> contacts = ContactRepository.getContacts();
    private final Paginator<Contact> paginator = new Paginator<>(contacts);

    private TextView tvSelected;
    private ContactAdapter adapter;
    private Button[] pageButtons;

    @Nullable
    private Contact selectedContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvSelected = findViewById(R.id.tvSelected);
        ListView lvContacts = findViewById(R.id.lvContacts);
        pageButtons = new Button[]{
                findViewById(R.id.btnPage1),
                findViewById(R.id.btnPage2),
                findViewById(R.id.btnPage3)
        };

        adapter = new ContactAdapter(this, paginator.getPage(0));
        lvContacts.setAdapter(adapter);

        // Chia đều chiều cao ListView cho 5 item để một trang lấp kín màn hình
        lvContacts.addOnLayoutChangeListener((v, left, top, right, bottom,
                                              oldLeft, oldTop, oldRight, oldBottom) -> {
            int listHeight = (bottom - top) - v.getPaddingTop() - v.getPaddingBottom();
            if (listHeight > 0) {
                v.post(() -> adapter.setItemHeight(listHeight / Paginator.PAGE_SIZE));
            }
        });

        lvContacts.setOnItemClickListener((parent, view, position, id) ->
                selectContact(adapter.getItem(position)));

        for (int i = 0; i < pageButtons.length; i++) {
            final int pageIndex = i;
            pageButtons[i].setOnClickListener(v -> showPage(pageIndex));
        }

        int page = 0;
        if (savedInstanceState != null) {
            page = savedInstanceState.getInt(KEY_CURRENT_PAGE, 0);
            int selectedIndex = savedInstanceState.getInt(KEY_SELECTED_INDEX, -1);
            if (selectedIndex >= 0 && selectedIndex < contacts.size()) {
                selectContact(contacts.get(selectedIndex));
            }
        }
        showPage(page);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_CURRENT_PAGE, paginator.getCurrentPage());
        outState.putInt(KEY_SELECTED_INDEX,
                selectedContact == null ? -1 : contacts.indexOf(selectedContact));
    }

    /** Chuyển sang trang {@code pageIndex}; giữ nguyên lựa chọn ở TextView. */
    private void showPage(int pageIndex) {
        adapter.setItems(paginator.goToPage(pageIndex));
        PageButtons.update(pageIndex, pageButtons);
    }

    private void selectContact(@NonNull Contact contact) {
        selectedContact = contact;
        tvSelected.setText(getString(R.string.you_choose, contact.getName()));
        adapter.setSelectedContact(contact);
    }
}
