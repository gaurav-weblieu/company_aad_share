package com.company.companyadda.CompanyDetailsPojo;

import com.google.gson.annotations.SerializedName;

public class company_master_data {

    public String company_category;
    public String email_id;
    public String class_of_company;
    @SerializedName("number_of_members(applicable_in_case_of_company_without_share_capital)")
    public String numberOfMembersApplicableInCaseOfCompanyWithoutShareCapital;
    @SerializedName("address_other_than_r/o_where_all_or_any_books_of_account_and_papers_are_maintained")
    public String addressOtherThanROWhereAllOrAnyBooksOfAccountAndPapersAreMaintained;
    public String date_of_last_agm;
    public String registered_address;
    public String active_compliance;
    public String registration_number;
    @SerializedName("paid_up_capital(rs)")
    public String paidUpCapitalRs;
    public String whether_listed_or_not;
    public String suspended_at_stock_exchange;
    public String company_subcategory;
    @SerializedName("authorised_capital(rs)")
    public String authorisedCapitalRs;

    public String getCompany_category() {
        return company_category;
    }

    public void setCompany_category(String company_category) {
        this.company_category = company_category;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public String getClass_of_company() {
        return class_of_company;
    }

    public void setClass_of_company(String class_of_company) {
        this.class_of_company = class_of_company;
    }

    public String getNumberOfMembersApplicableInCaseOfCompanyWithoutShareCapital() {
        return numberOfMembersApplicableInCaseOfCompanyWithoutShareCapital;
    }

    public void setNumberOfMembersApplicableInCaseOfCompanyWithoutShareCapital(String numberOfMembersApplicableInCaseOfCompanyWithoutShareCapital) {
        this.numberOfMembersApplicableInCaseOfCompanyWithoutShareCapital = numberOfMembersApplicableInCaseOfCompanyWithoutShareCapital;
    }

    public String getAddressOtherThanROWhereAllOrAnyBooksOfAccountAndPapersAreMaintained() {
        return addressOtherThanROWhereAllOrAnyBooksOfAccountAndPapersAreMaintained;
    }

    public void setAddressOtherThanROWhereAllOrAnyBooksOfAccountAndPapersAreMaintained(String addressOtherThanROWhereAllOrAnyBooksOfAccountAndPapersAreMaintained) {
        this.addressOtherThanROWhereAllOrAnyBooksOfAccountAndPapersAreMaintained = addressOtherThanROWhereAllOrAnyBooksOfAccountAndPapersAreMaintained;
    }

    public String getDate_of_last_agm() {
        return date_of_last_agm;
    }

    public void setDate_of_last_agm(String date_of_last_agm) {
        this.date_of_last_agm = date_of_last_agm;
    }

    public String getRegistered_address() {
        return registered_address;
    }

    public void setRegistered_address(String registered_address) {
        this.registered_address = registered_address;
    }

    public String getActive_compliance() {
        return active_compliance;
    }

    public void setActive_compliance(String active_compliance) {
        this.active_compliance = active_compliance;
    }

    public String getRegistration_number() {
        return registration_number;
    }

    public void setRegistration_number(String registration_number) {
        this.registration_number = registration_number;
    }

    public String getPaidUpCapitalRs() {
        return paidUpCapitalRs;
    }

    public void setPaidUpCapitalRs(String paidUpCapitalRs) {
        this.paidUpCapitalRs = paidUpCapitalRs;
    }

    public String getWhether_listed_or_not() {
        return whether_listed_or_not;
    }

    public void setWhether_listed_or_not(String whether_listed_or_not) {
        this.whether_listed_or_not = whether_listed_or_not;
    }

    public String getSuspended_at_stock_exchange() {
        return suspended_at_stock_exchange;
    }

    public void setSuspended_at_stock_exchange(String suspended_at_stock_exchange) {
        this.suspended_at_stock_exchange = suspended_at_stock_exchange;
    }

    public String getCompany_subcategory() {
        return company_subcategory;
    }

    public void setCompany_subcategory(String company_subcategory) {
        this.company_subcategory = company_subcategory;
    }

    public String getAuthorisedCapitalRs() {
        return authorisedCapitalRs;
    }

    public void setAuthorisedCapitalRs(String authorisedCapitalRs) {
        this.authorisedCapitalRs = authorisedCapitalRs;
    }

    public String getCompanyStatusForEfiling() {
        return companyStatusForEfiling;
    }

    public void setCompanyStatusForEfiling(String companyStatusForEfiling) {
        this.companyStatusForEfiling = companyStatusForEfiling;
    }

    public String getRoc_code() {
        return roc_code;
    }

    public void setRoc_code(String roc_code) {
        this.roc_code = roc_code;
    }

    public String getDate_of_balance_sheet() {
        return date_of_balance_sheet;
    }

    public void setDate_of_balance_sheet(String date_of_balance_sheet) {
        this.date_of_balance_sheet = date_of_balance_sheet;
    }

    public String getDate_of_incorporation() {
        return date_of_incorporation;
    }

    public void setDate_of_incorporation(String date_of_incorporation) {
        this.date_of_incorporation = date_of_incorporation;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    @SerializedName("company_status(for_efiling)")
    public String companyStatusForEfiling;
    public String roc_code;
    public String date_of_balance_sheet;
    public String date_of_incorporation;
    @SerializedName("cin ")
    public String cin;
    public String company_name;

    public company_master_data(String company_category, String email_id, String class_of_company, String numberOfMembersApplicableInCaseOfCompanyWithoutShareCapital, String addressOtherThanROWhereAllOrAnyBooksOfAccountAndPapersAreMaintained, String date_of_last_agm, String registered_address, String active_compliance, String registration_number, String paidUpCapitalRs, String whether_listed_or_not, String suspended_at_stock_exchange, String company_subcategory, String authorisedCapitalRs, String companyStatusForEfiling, String roc_code, String date_of_balance_sheet, String date_of_incorporation, String cin, String company_name) {
        this.company_category = company_category;
        this.email_id = email_id;
        this.class_of_company = class_of_company;
        this.numberOfMembersApplicableInCaseOfCompanyWithoutShareCapital = numberOfMembersApplicableInCaseOfCompanyWithoutShareCapital;
        this.addressOtherThanROWhereAllOrAnyBooksOfAccountAndPapersAreMaintained = addressOtherThanROWhereAllOrAnyBooksOfAccountAndPapersAreMaintained;
        this.date_of_last_agm = date_of_last_agm;
        this.registered_address = registered_address;
        this.active_compliance = active_compliance;
        this.registration_number = registration_number;
        this.paidUpCapitalRs = paidUpCapitalRs;
        this.whether_listed_or_not = whether_listed_or_not;
        this.suspended_at_stock_exchange = suspended_at_stock_exchange;
        this.company_subcategory = company_subcategory;
        this.authorisedCapitalRs = authorisedCapitalRs;
        this.companyStatusForEfiling = companyStatusForEfiling;
        this.roc_code = roc_code;
        this.date_of_balance_sheet = date_of_balance_sheet;
        this.date_of_incorporation = date_of_incorporation;
        this.cin = cin;
        this.company_name = company_name;
    }


}
