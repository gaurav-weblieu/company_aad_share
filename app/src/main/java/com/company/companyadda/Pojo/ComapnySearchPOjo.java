package com.company.companyadda.Pojo;

public class ComapnySearchPOjo {
    public String getDataid() {
        return dataid;
    }

    public void setDataid(String dataid) {
        this.dataid = dataid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String dataid;

    public ComapnySearchPOjo(String dataid, String name) {
        this.dataid = dataid;
        this.name = name;
    }

    String name;
}
