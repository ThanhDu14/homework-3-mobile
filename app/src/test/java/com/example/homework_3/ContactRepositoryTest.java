package com.example.homework_3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import com.example.homework_3.data.ContactRepository;
import com.example.homework_3.model.Contact;

import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactRepositoryTest {

    private final List<Contact> contacts = ContactRepository.getContacts();

    @Test
    public void hasExactly15Contacts() {
        assertEquals(15, contacts.size());
    }

    @Test
    public void namesAreUniqueAndNotBlank() {
        Set<String> names = new HashSet<>();
        for (Contact c : contacts) {
            assertFalse(c.getName().trim().isEmpty());
            assertTrue("Trùng tên: " + c.getName(), names.add(c.getName()));
        }
    }

    @Test
    public void phonesAreUniqueTenDigitsStartingWithZero() {
        Set<String> phones = new HashSet<>();
        for (Contact c : contacts) {
            assertTrue("SĐT sai định dạng: " + c.getPhone(), c.getPhone().matches("0\\d{9}"));
            assertTrue("Trùng SĐT: " + c.getPhone(), phones.add(c.getPhone()));
        }
    }

    @Test
    public void avatarsAreSetAndDistinct() {
        Set<Integer> avatars = new HashSet<>();
        for (Contact c : contacts) {
            assertNotEquals(0, c.getAvatarResId());
            assertTrue(avatars.add(c.getAvatarResId()));
        }
    }

    @Test(expected = UnsupportedOperationException.class)
    public void listIsUnmodifiable() {
        contacts.clear();
    }

    @Test
    public void returnsSameOrderEveryCall() {
        assertEquals(contacts, ContactRepository.getContacts());
    }
}
