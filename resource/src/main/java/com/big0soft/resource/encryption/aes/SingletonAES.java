package com.big0soft.resource.encryption.aes;


import com.big0soft.resource.encryption.CustomKeyEncoder;

public final class SingletonAES {
    private static SingletonAES instance;
    private CustomKeyEncoder customKeyEncoder;

    private SingletonAES(CustomKeyEncoder customKeyEncoder) {
        this.customKeyEncoder = customKeyEncoder;
    }


    public static SingletonAES getInstance(CustomKeyEncoder customKeyEncoder) {
        if (instance == null) {
            synchronized (SingletonAES.class) {
                instance = new SingletonAES(customKeyEncoder);

            }
        }
        return instance;
    }

    public CustomKeyEncoder getEncoder() {
        return customKeyEncoder;
    }

}
