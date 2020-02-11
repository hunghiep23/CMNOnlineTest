package com.hiep.cmnonline.data

import com.hiep.cmnonline.model.json.CheckOTPResponseJson
import com.hiep.cmnonline.model.json.OTPResponseJson
import com.hiep.cmnonline.network.ApiUtils
import io.reactivex.Single

class Repository {
    fun getOTP(phone: String): Single<OTPResponseJson> {
        return ApiUtils.getServiceApi().getOTP(phone)
    }

    fun checkOTP(phone: String, otp: String): Single<CheckOTPResponseJson> {
        return ApiUtils.getServiceApi().checkOTP(phone, otp)
    }
}