package com.example.homework_3.data;

import com.example.homework_3.R;
import com.example.homework_3.model.Contact;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class ContactRepository {

    private static final List<Contact> CONTACTS = Collections.unmodifiableList(Arrays.asList(
            new Contact("Nguyễn Minh Khang", "0912345671", R.drawable.avatar_01),
            new Contact("Trần Thu Hà", "0987654312", R.drawable.avatar_02),
            new Contact("Lê Quốc Bảo", "0903456723", R.drawable.avatar_03),
            new Contact("Phạm Gia Huy", "0938567134", R.drawable.avatar_04),
            new Contact("Võ Ngọc Anh", "0976123845", R.drawable.avatar_05),
            new Contact("Đặng Hoàng Nam", "0965234956", R.drawable.avatar_06),
            new Contact("Bùi Khánh Linh", "0918345267", R.drawable.avatar_07),
            new Contact("Hồ Đức Thịnh", "0947456378", R.drawable.avatar_08),
            new Contact("Ngô Thảo Vy", "0989567489", R.drawable.avatar_09),
            new Contact("Dương Tuấn Kiệt", "0909678590", R.drawable.avatar_10),
            new Contact("Lý Mai Phương", "0932789601", R.drawable.avatar_11),
            new Contact("Huỳnh Thanh Tùng", "0971890712", R.drawable.avatar_12),
            new Contact("Phan Bảo Ngọc", "0961901823", R.drawable.avatar_13),
            new Contact("Trịnh Văn Long", "0914012934", R.drawable.avatar_14),
            new Contact("Mai Hồng Nhung", "0945123045", R.drawable.avatar_15)
    ));

    private ContactRepository() {
    }

    public static List<Contact> getContacts() {
        return CONTACTS;
    }
}
