package com.company.companyadda.DirectorDetailsPojo;

public class DirectorDataPojo {
    public DirectorDataPojo(String din, String name) {
        this.din = din;
        this.name = name;
    }

    public String getDin() {
        return din;
    }

    public void setDin(String din) {
        this.din = din;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String din;
    String name;
}
