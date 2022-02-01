package com.company.companyadda.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.company.companyadda.Pojo.ServiceListPojo;
import com.company.companyadda.Reposetry.ServiceList_Repo;

import java.util.List;

public class Category_View_Model extends ViewModel {

    ServiceList_Repo serviceList_repo;

    public Category_View_Model(){
        serviceList_repo= new ServiceList_Repo();
    }

    public MutableLiveData<List<ServiceListPojo>> getAllService(){
        return  serviceList_repo.getService_list();
    }
}
