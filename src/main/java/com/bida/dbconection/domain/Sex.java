package com.bida.dbconection.domain;

enum Sex {
    MALE, FEMALE;

    public static Sex getSex(String sex) {
        Sex[] s = Sex.values();
        for (int i = 0; i < s.length; i++) {
            if (sex.equals(s.toString())) {
                return s[i];
            }
        }
        return null;
    }
}
