package com.hiep.cmnonline.feature.login.forgotpassword

import androidx.annotation.StringRes
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hiep.cmnonline.R
import com.hiep.cmnonline.data.Repository
import com.hiep.cmnonline.model.InvalidInput
import com.hiep.cmnonline.model.Response
import com.hiep.cmnonline.model.json.CheckOTPResponseJson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposables
import io.reactivex.schedulers.Schedulers


class ForgotPasswordViewModel : ViewModel() {
    private val repository = Repository()
    val otpLiveData = MutableLiveData<Response<String>>()
    val checkOtpLiveData = MutableLiveData<Response<CheckOTPResponseJson>>()
    val changePasswordLiveData = MutableLiveData<Boolean>()
    val invalidInputLiveData = MutableLiveData<ArrayList<InvalidInput>>()

    private var _validPhone = false
    private var _validOTP = false
    private var _samePassword = false
    private var _isVerifiedOTP = false
    val isVerifiedOTP: Boolean
        get() = _isVerifiedOTP

    private var phone: String? = null
        set(value) {
            field = value
            _validPhone = isValidPhone(value)
        }
    private var otp: String? = null
        set(value) {
            field = value
            _validOTP = isValidOTP(value)
        }
    private var password: String? = null
        set(value) {
            field = value
            _samePassword = isSamePassword(value, confirmPassword)
        }
    private var confirmPassword: String? = null
        set(value) {
            field = value
            _samePassword = isSamePassword(value, password)
        }


    fun getOTP(phone: String) {
        this.phone = phone
        if (validateInput()) {
            otpDisposable = repository.getOTP(phone).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe {
                    otpLiveData.value = Response.loading()
                }
                .subscribe({
                    if (it.data?.otp != null) {
                        otpLiveData.value = Response.succeed(it.data.otp)
                    } else {
                        otpLiveData.value = Response.empty()
                    }
                }, {
                    //do something
                })
        }
    }

    fun checkOTP(phone: String, otp: String) {
        this.phone = phone
        this.otp = otp
        if (validateInput(validateOTP = true)) {
            checkOtpDisposable = repository.checkOTP(phone, otp)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe {
                    checkOtpLiveData.value = Response.loading()
                }
                .subscribe({
                    if (it.code == 200) {
                        _isVerifiedOTP = true
                        checkOtpLiveData.value = Response.succeed(it)
                    }
                    validateInput(verifyOTP = true)
                }, {
                    checkOtpLiveData.value = Response.error(it)
                })
        }
    }

    fun changePassword(newPassword: String, confirmNewPassword: String) {
        password = newPassword
        confirmPassword = confirmNewPassword
        if (validateInput(verifyOTP = true, validatePassword = true))
            changePasswordLiveData.value = true
    }


    private fun validateInput(
        validatePhone: Boolean = true,
        validateOTP: Boolean = false,
        verifyOTP: Boolean = false,
        validatePassword: Boolean = false
    ): Boolean {
        var result = true
        val invalidList = ArrayList<InvalidInput>()
        if (validatePhone && !_validPhone) {
            invalidList.add(InvalidInput.INVALID_PHONE)
            result = false
        }
        if (validateOTP && !_validOTP) {
            invalidList.add(InvalidInput.INVALID_OTP)
            result = false
        } else if (verifyOTP && !_isVerifiedOTP) {
            invalidList.add(InvalidInput.NOT_VERIFIED_OTP)
            result = false
        }
        if (validatePassword && !_samePassword) {
            invalidList.add(InvalidInput.NOT_SAME_PASSWORD)
            result = false
        }
        invalidInputLiveData.value = invalidList
        return result
    }


    private fun isValidPhone(phone: String?): Boolean {
        if (phone == null) return false
        if (phone.length in 10..15) {
            phone.forEach { char ->
                if (char !in '0'..'9')
                    return false
            }
            return true
        }
        return false
    }

    private fun isValidOTP(otp: String?): Boolean {
        if (otp == null) return false
        if (otp.length == 6) {
            otp.forEach { char ->
                if (char !in '0'..'9')
                    return false
            }
            return true
        }
        return false
    }

    private fun isSamePassword(password: String?, newPassword: String?): Boolean {
        if (password.isNullOrEmpty() || newPassword.isNullOrEmpty()) return false
        return password == newPassword
    }

    private var otpDisposable = Disposables.empty()
    private var checkOtpDisposable = Disposables.empty()
    override fun onCleared() {
        otpDisposable.dispose()
        checkOtpDisposable.dispose()
        super.onCleared()
    }
}

