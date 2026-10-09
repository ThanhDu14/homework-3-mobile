package com.example.homework_3.pagination;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Chia danh sách thành các trang, mỗi trang {@link #PAGE_SIZE} phần tử.
 * Java thuần (không phụ thuộc Android) để unit test được.
 */
public class Paginator<T> {

    public static final int PAGE_SIZE = 5;

    private final List<T> items;
    private int currentPage = 0;

    public Paginator(List<T> items) {
        this.items = new ArrayList<>(items);
    }

    /** Số trang, làm tròn lên: 15 item → 3, 12 item → 3. */
    public int getPageCount() {
        return (items.size() + PAGE_SIZE - 1) / PAGE_SIZE;
    }

    /** Trả về tối đa 5 phần tử của trang {@code pageIndex} (bắt đầu từ 0), không đổi trang hiện tại. */
    public List<T> getPage(int pageIndex) {
        checkPageIndex(pageIndex);
        int from = pageIndex * PAGE_SIZE;
        int to = Math.min(from + PAGE_SIZE, items.size());
        return Collections.unmodifiableList(items.subList(from, to));
    }

    public int getCurrentPage() {
        return currentPage;
    }

    /** Đặt trang hiện tại và trả về các phần tử của trang đó. */
    public List<T> goToPage(int pageIndex) {
        List<T> page = getPage(pageIndex);
        currentPage = pageIndex;
        return page;
    }

    private void checkPageIndex(int pageIndex) {
        if (pageIndex < 0 || pageIndex >= getPageCount()) {
            throw new IllegalArgumentException(
                    "pageIndex " + pageIndex + " ngoài khoảng [0, " + getPageCount() + ")");
        }
    }
}
