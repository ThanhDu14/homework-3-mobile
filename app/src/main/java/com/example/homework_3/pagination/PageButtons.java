package com.example.homework_3.pagination;

import android.view.View;

/** Cập nhật trạng thái các nút phân trang theo quy ước "mờ" trong docs/02-quy-uoc-ky-thuat.md. */
public final class PageButtons {

    static final float DIMMED_ALPHA = 0.4f;

    private PageButtons() {
    }

    /**
     * Nút của trang hiện tại bị mờ và không bấm được; các nút còn lại sáng và bấm được.
     * {@code buttons[i]} là nút của trang {@code i}.
     */
    public static void update(int currentPage, View... buttons) {
        for (int i = 0; i < buttons.length; i++) {
            boolean isCurrent = i == currentPage;
            buttons[i].setEnabled(!isCurrent);
            buttons[i].setAlpha(isCurrent ? DIMMED_ALPHA : 1f);
        }
    }
}
