package com.company.companyadda.api_interface;

import com.company.companyadda.ApiModels.AllDetaislModels;
import com.company.companyadda.ApiModels.DocModel;
import com.company.companyadda.ApiModels.FormModels;
import com.company.companyadda.ApiModels.LoginModels;
import com.company.companyadda.ApiModels.MyRequestDetailsModel;
import com.company.companyadda.ApiModels.PaymentPojoModel;
import com.company.companyadda.ApiModels.ProfileModel;
import com.company.companyadda.ApiModels.RTIModel;
import com.company.companyadda.ApiModels.RaiseAQueryModel;
import com.company.companyadda.ApiModels.ReferModel;
import com.company.companyadda.ApiModels.SearchServiceApiModel;
import com.company.companyadda.ApiModels.ServiceModels;
import com.company.companyadda.ApiModels.WalletListModel;
import com.company.companyadda.Pojo.CapitalResult;
import com.company.companyadda.Pojo.ModelFeedback;
import com.company.companyadda.Pojo.ServiceListPojo;
import com.company.companyadda.Pojo.SettingPinPojo;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface Apis {

    @POST("Search_services")
    Call<SearchServiceApiModel> getSearchServices();


    @POST("Get_Service_List")
    Call<ServiceModels> getServices();

    @FormUrlEncoded
    @POST("quotes")
    Call<FormModels> quotes(
            @Field("user_name") String user_name,
            @Field("user_email") String user_email,
            @Field("user_mobile") String user_mobile,
            @Field("service_id") String service_id,
            @Field("user_id") String user_id,
            @Field("user_subject") String user_subject
    );

    @FormUrlEncoded
    @POST("login")
    Call<LoginModels> login(
            @Field("phone") String phone
    );

    @FormUrlEncoded
    @POST("AllDetails")
    Call<AllDetaislModels> AllDetails(
            @Field("phone") String phone
    );

    @FormUrlEncoded
    @POST("referal_code")
    Call<ReferModel> ReferCode(
            @Field("user_id") String user_id,
            @Field("referal_code") String referal_code
    );

    @FormUrlEncoded
    @POST("Get_Wallet_list")
    Call<WalletListModel> getWalletList(
            @Field("user_id") String user_id
    );

    @FormUrlEncoded
    @POST("RaiseQuery")
    Call<RaiseAQueryModel> RaiseQuery(
            @Field("name") String name,
            @Field("email") String email,
            @Field("mobile") String mobile,
            @Field("message") String message,
            @Field("user_id") String user_id,
            @Field("service_id") String service_id
    );


    @Multipart
    @POST("profile")
    Call<ProfileModel> profile(
            @Part("user_id") RequestBody user_id,
            @Part("name") RequestBody name,
            @Part("email") RequestBody email,
            @Part MultipartBody.Part image
            );

    @Multipart
    @POST("profile")
    Call<ProfileModel> profile(
            @Part("user_id") RequestBody user_id
    );


    @GET("U72900UP2020PTC125930?consent=Y&reason=Forcompanyaddaproject")
    Call<CapitalResult> getCompany();


    @FormUrlEncoded
    @POST("Get_Myrequest_Details")
    Call<MyRequestDetailsModel> Get_Myrequest_Details(
            @Field("user_id") String user_id,
            @Field("status") String status
    );

    @FormUrlEncoded
    @POST("Payment")
    Call<PaymentPojoModel> Payment(
            @Field("user_id") String user_id,
            @Field("razorpay_payment_id") String razorpay_payment_id,
            @Field("payment_via") String payment_via,
            @Field("razorpay_order_id") String razorpay_order_id,
            @Field("razorpay_signature") String razorpay_signature,
            @Field("Status") String Status,
            @Field("amount") String amount,
            @Field("note") String note
    );

    @FormUrlEncoded
    @POST("Payment")
    Call<PaymentPojoModel> Payment(
            @Field("user_id") String user_id
    );


    @Multipart
    @POST("UploadDocuments")
    Call<DocModel> UploadDocuments(
            @Part("user_id") RequestBody user_id,
            @Part("document_name") RequestBody document_name,
            @Part MultipartBody.Part document_image
    );

    @Multipart
    @POST("Free_ITR")
    Call<RTIModel> Free_ITR(
            @Part("user_id") RequestBody user_id,
            @Part("name") RequestBody name,
            @Part("phone") RequestBody phone,
            @Part("email") RequestBody email,
            @Part MultipartBody.Part form16
    );

    @FormUrlEncoded
    @POST("Free_ITR")
    Call<RTIModel> Free_ITR_to_String(
            @Field("user_id") String user_id,
            @Field("name") String name,
            @Field("phone") String phone,
            @Field("email") String email,
            @Field("form16") String form16
    );

    @FormUrlEncoded
    @POST("UploadDocuments")
    Call<DocModel> UploadDocuments(
            @Field("user_id") String user_id
    );

    @FormUrlEncoded
    @POST("UploadDocuments")
    Call<DocModel> UploadDocumentsDelete(
            @Field("user_document_id") String user_document_id
    );


    @FormUrlEncoded
    @POST("forgotDocsPin")
    Call<SettingPinPojo> forgotDocsPin(
            @Field("user_id") String user_id,
            @Field("pin") String pin
    );

    @FormUrlEncoded
    @POST("forgotDocsPin")
    Call<SettingPinPojo> SetforgotDocsPin(
            @Field("user_id") String user_id,
            @Field("new_pin") String new_pin,
            @Field("confirm_pin") String confirm_pin
    );


    @FormUrlEncoded
    @POST("Feedback")
    Call<ModelFeedback> feedBack(
            @Field("user_id") String user_id,
            @Field("message") String message
    );
}
