package com.example.homework_3;

import static org.junit.Assert.assertEquals;

import com.example.homework_3.data.ContactRepository;
import com.example.homework_3.model.Contact;
import com.example.homework_3.pagination.Paginator;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PaginatorTest {

    private static List<Integer> range(int fromInclusive, int toExclusive) {
        List<Integer> list = new ArrayList<>();
        for (int i = fromInclusive; i < toExclusive; i++) {
            list.add(i);
        }
        return list;
    }

    private final Paginator<Integer> paginator = new Paginator<>(range(0, 15));

    @Test
    public void pageSizeIsFive() {
        assertEquals(5, Paginator.PAGE_SIZE);
    }

    @Test
    public void fifteenItemsGiveThreePages() {
        assertEquals(3, paginator.getPageCount());
    }

    @Test
    public void eachPageHasTheRightFiveItems() {
        assertEquals(range(0, 5), paginator.getPage(0));
        assertEquals(range(5, 10), paginator.getPage(1));
        assertEquals(range(10, 15), paginator.getPage(2));
    }

    @Test
    public void currentPageDefaultsToZero() {
        assertEquals(0, paginator.getCurrentPage());
    }

    @Test
    public void goToPageUpdatesCurrentPageAndReturnsItsItems() {
        assertEquals(range(5, 10), paginator.goToPage(1));
        assertEquals(1, paginator.getCurrentPage());

        assertEquals(range(10, 15), paginator.goToPage(2));
        assertEquals(2, paginator.getCurrentPage());

        assertEquals(range(0, 5), paginator.goToPage(0));
        assertEquals(0, paginator.getCurrentPage());
    }

    @Test
    public void getPageDoesNotChangeCurrentPage() {
        paginator.getPage(2);
        assertEquals(0, paginator.getCurrentPage());
    }

    @Test(expected = IllegalArgumentException.class)
    public void goToNegativePageThrows() {
        paginator.goToPage(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void goToPageEqualToPageCountThrows() {
        paginator.goToPage(3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getPageOutOfRangeThrows() {
        paginator.getPage(3);
    }

    @Test
    public void invalidPageDoesNotChangeCurrentPage() {
        paginator.goToPage(1);
        try {
            paginator.goToPage(5);
        } catch (IllegalArgumentException expected) {
            // trang không hợp lệ → giữ nguyên trang hiện tại
        }
        assertEquals(1, paginator.getCurrentPage());
    }

    @Test
    public void twelveItemsGiveLastPageWithTwoItems() {
        Paginator<Integer> p = new Paginator<>(range(0, 12));
        assertEquals(3, p.getPageCount());
        assertEquals(Arrays.asList(10, 11), p.getPage(2));
    }

    @Test
    public void emptyListHasZeroPages() {
        Paginator<Integer> p = new Paginator<>(Collections.emptyList());
        assertEquals(0, p.getPageCount());
    }

    @Test
    public void changingSourceListAfterwardsDoesNotAffectPaginator() {
        List<Integer> source = range(0, 15);
        Paginator<Integer> p = new Paginator<>(source);
        source.clear();
        assertEquals(3, p.getPageCount());
        assertEquals(range(0, 5), p.getPage(0));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void returnedPageIsUnmodifiable() {
        paginator.getPage(0).clear();
    }

    @Test
    public void realContactsSplitIntoThreeFullPages() {
        Paginator<Contact> p = new Paginator<>(ContactRepository.getContacts());
        assertEquals(3, p.getPageCount());
        for (int i = 0; i < p.getPageCount(); i++) {
            assertEquals(Paginator.PAGE_SIZE, p.getPage(i).size());
        }
    }
}
