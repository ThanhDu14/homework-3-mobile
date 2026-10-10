package com.example.homework_3.pagination;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Paginator<T> {

    public static final int PAGE_SIZE = 5;

    private final List<T> items;
    private int currentPage = 0;

    public Paginator(List<T> items) {
        this.items = new ArrayList<>(items);
    }

    public int getPageCount() {
        return (items.size() + PAGE_SIZE - 1) / PAGE_SIZE;
    }

    public List<T> getPage(int pageIndex) {
        checkPageIndex(pageIndex);
        int from = pageIndex * PAGE_SIZE;
        int to = Math.min(from + PAGE_SIZE, items.size());
        return Collections.unmodifiableList(items.subList(from, to));
    }

    public int getCurrentPage() {
        return currentPage;
    }

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
