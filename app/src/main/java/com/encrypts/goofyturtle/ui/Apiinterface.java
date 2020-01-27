package com.encrypts.goofyturtle.ui;

//import com.Encrypts.goofyturtle.model.Categories;
import com.encrypts.goofyturtle.ui.model.Categories;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Apiinterface {

 @GET("/getcategoriesTree")
 Call<List<Categories>> getCategories();

}

