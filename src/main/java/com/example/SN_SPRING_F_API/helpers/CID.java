package com.example.SN_SPRING_F_API.helpers;

import java.util.UUID;

public class CID {

    private static CID instance = null;
    private String id = null;

    private CID() {
        this.id = UUID.randomUUID().toString();
    }

    public static CID getInstance() {
        if( CID.instance == null ) {
               CID.instance = new CID();
        }
        return CID.instance;
    }

    public String getId() {
        return this.id;
    }
}
