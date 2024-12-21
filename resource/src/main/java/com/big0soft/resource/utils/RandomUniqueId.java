package com.big0soft.resource.utils;

import androidx.annotation.NonNull;

import java.util.Objects;
import java.util.UUID;

public final class RandomUniqueId {
    private static RandomUniqueId INSTANCE;
    private final int uniqueInteger;
    private final String uniqueString;

    private RandomUniqueId() {
        uniqueInteger = (int) (System.currentTimeMillis() & 0xfffffff);
        uniqueString = UUID.randomUUID().toString();

    }

    public static RandomUniqueId getInstance() {
        if (INSTANCE == null) {
            synchronized (RandomUniqueId.class) {
                INSTANCE = new RandomUniqueId();
            }
        }
        return INSTANCE;
    }

    public String uniqueString() {
        return uniqueString;
    }

    public int uniqueInteger() {
        return uniqueInteger;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RandomUniqueId)) return false;
        RandomUniqueId that = (RandomUniqueId) o;
        return uniqueInteger == that.uniqueInteger && uniqueString.equals(that.uniqueString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uniqueInteger, uniqueString);
    }

    @NonNull
    @Override
    public String toString() {
        return "RandomUniqueId{" + "uniqueInteger=" + uniqueInteger +
                ", uniqueString='" + uniqueString + '\'' +
                '}';
    }
}
