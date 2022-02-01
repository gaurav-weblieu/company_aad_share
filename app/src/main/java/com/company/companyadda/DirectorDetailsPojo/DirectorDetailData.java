package com.company.companyadda.DirectorDetailsPojo;

import java.util.List;

public class DirectorDetailData {
    List<Object> llp_data;
    List<Company_data_pojo> company_data;

    public List<Object> getLlp_data() {
        return llp_data;
    }

    public void setLlp_data(List<Object> llp_data) {
        this.llp_data = llp_data;
    }

    public List<Company_data_pojo> getCompany_data() {
        return company_data;
    }

    public void setCompany_data(List<Company_data_pojo> company_data) {
        this.company_data = company_data;
    }

    public DirectorDataPojo getDirector_data() {
        return director_data;
    }

    public void setDirector_data(DirectorDataPojo director_data) {
        this.director_data = director_data;
    }

    DirectorDataPojo director_data;

    public DirectorDetailData(List<Object> llp_data, List<Company_data_pojo> company_data, DirectorDataPojo director_data) {
        this.llp_data = llp_data;
        this.company_data = company_data;
        this.director_data = director_data;
    }




}
