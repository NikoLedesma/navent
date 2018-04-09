package com.exercise.navent.com.exercise.navent.cache;

public class BumexMemcached {

    private static BumexMemcached INSTANCE = null;

    public void set(String key, Object value) { }

    public Object get(String key) {
        return null;
    }

    public void delete(String key) { }

    public static BumexMemcached getInstance() {
        if (INSTANCE == null) {
            synchronized (BumexMemcached.class) {
                if (INSTANCE == null) {
                    INSTANCE = new BumexMemcached();
                }
            }
        }
        return INSTANCE;
    }

}
