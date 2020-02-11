package com.hiep.cmnonline.model


enum class State {
    LOADING,
    SUCCEED,
    ERROR,
    HTTP_ERROR,
    NETWORK_LOST,
    EMPTY
}

data class Response<out T>(
    val state: State,
    val data: T?,
    val error: Throwable?) {
    companion object {
        fun <T> loading() = Response<T>(
            State.LOADING,
            null,
            null
        )
        fun <T> succeed(data: T) = Response(
            State.SUCCEED,
            data,
            null
        )
        fun <T> error(throwable: Throwable) =
            Response<T>(
                State.ERROR,
                null,
                throwable
            )
        fun <T> empty() =
            Response<T>(
                State.EMPTY,
                null,
                null
            )
    }
}
