package com.islam97.android.apps.onx.data.repositories

import android.content.Context
import com.google.gson.Gson
import com.islam97.android.apps.onx.R
import com.islam97.android.apps.onx.data.dto.Response
import com.islam97.android.apps.onx.presentation.Result
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

open class BaseRepository {
    @Inject
    lateinit var gson: Gson

    @Inject
    @ApplicationContext
    lateinit var context: Context

    fun <T, S> callApi(apiCall: suspend () -> Response<T>, mapper: (T?) -> S?): Flow<Result> {
        return flow {
            emit(Result.Loading)
            emit(
                try {
                val response = apiCall()
                if (response.isSuccessful) {
                    Result.Success(
                        response.result.message ?: context.getString(R.string.message_success),
                        mapper(response.data)
                    )
                } else {
                    Result.Error(
                        response.result.message
                            ?: context.getString(R.string.error_something_went_wrong)
                    )
                }
            } catch (e: HttpException) {
                try {
                    e.response()?.errorBody()?.string()?.let {
                        val response = gson.fromJson(it, Response::class.java)
                        Result.Error(
                            response.result.message
                                ?: context.getString(R.string.error_something_went_wrong)
                        )
                    } ?: Result.Error(context.getString(R.string.error_something_went_wrong))
                } catch (t: Throwable) {
                    e.response()?.errorBody()?.string()?.let {
                        if (it.isNotBlank()) {
                            Result.Error(it)
                        } else {
                            Result.Error(context.getString(R.string.error_something_went_wrong))
                        }
                    } ?: Result.Error(context.getString(R.string.error_something_went_wrong))
                }
            } catch (e: Throwable) {
                Result.Error(
                    e.message ?: context.getString(R.string.error_something_went_wrong)
                )
            })
        }
    }
}