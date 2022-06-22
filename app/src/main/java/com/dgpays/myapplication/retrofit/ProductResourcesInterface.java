package com.dgpays.myapplication.retrofit;

import com.dgpays.myapplication.model.LoginResource;
import com.dgpays.myapplication.model.MyBagResources;
import com.dgpays.myapplication.model.ProductResource;
import com.dgpays.myapplication.model.UserResource;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ProductResourcesInterface {
     @GET("/products")
    Call<List<ProductResource>> getAllProduct();

     @GET("/products?sort=desc")
    Call<List<ProductResource>> getOneProducts();

    @GET("/products/categories")
    Call<List<String>> getCategories();

    @GET("/carts?limit=5")
    Call<List<MyBagResources>> getMyBag();

    @GET("/users")
    Call<List<UserResource>> getAllUsers();

    @POST("/users")
    Call<List<LoginResource>> getNewUsers(@Body LoginResource loginsResorces);

    @PUT("/users/7")
    Call<List<LoginResource>> getForgotPassword(@Body LoginResource loginsResorces);

}
