package com.hiep.cmnonline.network

class ApiUtils {
    companion object {
        private const val BASE_URL = "https://viva123.net/"

        @JvmStatic
        fun getServiceApi(): ServiceApi {
            return RetrofitClient.getClient(BASE_URL).create(ServiceApi::class.java)
        }
    }
}