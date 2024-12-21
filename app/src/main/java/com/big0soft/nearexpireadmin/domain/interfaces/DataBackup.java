package com.big0soft.nearexpireadmin.domain.interfaces;

public interface DataBackup<T> {
    void backup(T t);

    T restore();
    void clean();
}
