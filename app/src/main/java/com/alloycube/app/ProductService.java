package com.alloycube.app;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProductService {

    @GET("/order/{company_app}/{name}/{company}/{school}/{email}/{productListStr}")
    Call<ResponseBody> postOrder(@Path("company_app") String app,
                                 @Path("name") String name,
                                 @Path("company") String company,
                                 @Path("school") String school,
                                 @Path("email") String email,
                                 @Path("productListStr") String productListStr);
}
