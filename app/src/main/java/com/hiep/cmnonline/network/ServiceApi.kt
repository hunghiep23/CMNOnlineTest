package com.hiep.cmnonline.network

import com.hiep.cmnonline.model.json.CheckOTPResponseJson
import com.hiep.cmnonline.model.json.OTPResponseJson
import io.reactivex.Single
import retrofit2.http.POST
import retrofit2.http.Query

interface ServiceApi {
    @POST("/api/v1/user/get-otp?")
    fun getOTP(@Query("Msisdn") phone: String): Single<OTPResponseJson>

    @POST("/api/v1/user/check-otp?")
    fun checkOTP(
        @Query("Msisdn") phone: String,
        @Query("Otp") otp: String
    ): Single<CheckOTPResponseJson>
}