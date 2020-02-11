package com.hiep.cmnonline.model.json

data class OTPResponseJson(
    val data: OTPDataJson?,
    val message: String?,
    val code: Int?,
    val meta: OTPMetadataJson?
) {
    data class OTPDataJson(val otp: String?)
    data class OTPMetadataJson(val x: String?)
}