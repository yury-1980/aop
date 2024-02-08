package ru.clevertec.cache.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.clevertec.cache.LRUCache;
import ru.clevertec.entity.Person;
import ru.clevertec.util.TestData;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNull;

class LRUCacheImplTest {

    LRUCache<UUID, Person> lruCache;
    Person expected;
    UUID uuidPerson;

    @BeforeEach
    void setUp() {
        lruCache = new LRUCacheImpl<>(5);
        expected = TestData.getPerson();
        uuidPerson = TestData.UUID_PERSON;
    }

    @Test
    void shouldGetObject() {
        lruCache.put(expected.getUuid(), expected);
        Person actual = lruCache.get(uuidPerson);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldPutAndGet() {
        lruCache.put(expected.getUuid(), expected);
        Person actual = lruCache.get(uuidPerson);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldRemove() {
        lruCache.put(uuidPerson, expected);
        lruCache.remove(uuidPerson);
        Person actual = lruCache.get(uuidPerson);

        assertNull(actual);
    }
}