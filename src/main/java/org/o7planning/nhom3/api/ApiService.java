package org.o7planning.nhom3.api;

import com.google.gson.JsonObject;

import org.o7planning.nhom3.model.Assignment;
import org.o7planning.nhom3.model.Doctors;
import org.o7planning.nhom3.model.History;
import org.o7planning.nhom3.model.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @FormUrlEncoded
    @POST("auth/login")
    Call<LoginResponse> authLogin(
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("auth/register")
    Call<LoginResponse> authRegister(
            @Field("fullName") String fullName,
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @PATCH("auth/me")
    Call<LoginResponse> authUpdateProfile(
            @Header("Authorization") String authHeader,
            @Field("fullName") String fullName,
            @Field("password") String password,
            @Field("newPassword") String newPassword
    );

    @GET("user")
    Call<Doctors> getDoctors(
            @Query("role") String role
    );

    @POST("assignment")
    Call<Assignment> createAssignment(
            @Header("Authorization") String authHeader,
            @Body JsonObject body
    );

    @GET("auth/me/assignments")
    Call<History> getHistoryAssignments(
            @Header("Authorization") String authHeader
    );

}
