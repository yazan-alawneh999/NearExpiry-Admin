package com.big0soft.resource.helper;

public class CreateImageFileException extends Throwable {
    public CreateImageFileException(String fullPath) {
        super(fullPath);
    }
}
