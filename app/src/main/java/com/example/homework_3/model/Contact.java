package com.example.homework_3.model;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;

/** Một liên hệ hiển thị trong ListView: ảnh đại diện, họ tên và số điện thoại. */
public class Contact {

    private final String name;
    private final String phone;
    @DrawableRes
    private final int avatarResId;

    public Contact(@NonNull String name, @NonNull String phone, @DrawableRes int avatarResId) {
        this.name = name;
        this.phone = phone;
        this.avatarResId = avatarResId;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public String getPhone() {
        return phone;
    }

    @DrawableRes
    public int getAvatarResId() {
        return avatarResId;
    }
}
