package com.hiep.cmnonline.model

import androidx.annotation.StringRes
import com.hiep.cmnonline.R

enum class InvalidInput(@StringRes val messageRes: Int) {
    INVALID_PHONE(R.string.forgot_password_invalid_phone),
    INVALID_OTP(R.string.forgot_password_invalid_OTP),
    NOT_VERIFIED_OTP(R.string.forgot_password_not_verified_OTP),
    NOT_SAME_PASSWORD(R.string.forgot_password_not_same_password)
}