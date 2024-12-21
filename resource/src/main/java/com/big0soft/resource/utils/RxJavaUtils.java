package com.big0soft.resource.utils;

import static com.big0soft.resource.utils.StrUtils.isEmpty;

import android.util.Log;

import com.big0soft.resource.BuildConfig;
import com.big0soft.resource.helper.TAGs;
import com.big0soft.resource.http.HttpStatusUtils;
import com.big0soft.resource.http.handle.HandleErrorResponseBody;
import com.big0soft.resource.model.ErrorResponse;
import com.big0soft.resource.model.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class RxJavaUtils {
    public static Disposable observableInterval(Consumer<Long> subscribe) {
        long interval = BuildConfig.DEBUG ? 1L : 10L;
        return observableInterval(interval, TimeUnit.SECONDS, subscribe);
    }
    public static ErrorResponse handleErrorResponse(Throwable throwable) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(LocalDateTime.now().toString());
        if (throwable instanceof HttpException) {
            HttpException httpException = ((HttpException) throwable);
            retrofit2.Response<?> httpResponse = httpException.response();
            Log.i(TAGs.TAG, "handleErrorResponse message 1: " + httpException.message());
            Log.i(TAGs.TAG, "handleErrorResponse message: " + httpResponse.message());
            Log.i(TAGs.TAG, "handleErrorResponse toString: " + httpResponse.toString());
            Log.i(TAGs.TAG, "handleErrorResponse raw: " + httpResponse.raw());
            try {
                String string = httpResponse.errorBody().string();
                errorResponse = (ErrorResponse) errorResponse.fromJson(string);
                Log.i(TAGs.TAG, "handleErrorResponse string: " + string);
                Log.i(TAGs.TAG, "handleErrorResponse string: " + errorResponse);
            } catch (Exception e) {
                Log.e(TAGs.TAG, "handleErrorResponse: ", e);
            }
//            Log.i(TAGs.TAG, "handleErrorResponse errorBody: "+httpResponse.errorBody().contentLength());
//            try (ResponseBody responseBody = httpResponse.errorBody()) {
//                if (responseBody != null && isValid(responseBody.string())) {
//                    Log.i(TAGs.TAG, "handleErrorResponse: " + responseBody.string());
//                    try {
//                        JSONObject jsonObject = new JSONObject(responseBody.string());
//                        errorResponse.setError(jsonObject.getString("message"));
//                    } catch (JSONException e) {
//                        errorResponse = (ErrorResponse) errorResponse.fromJson(responseBody.string());
//                    }
//                } else {
//                    errorResponse.setStatus(httpException.code());
//                    errorResponse.setMessage(HttpStatusUtils.getMessageForStatusCode(httpException.code()));
//                }
//            } catch (IOException e) {
//                errorResponse.setMessage(e.getMessage());
//            }
//        }
        }
        return errorResponse;
    }


    public static boolean isValid(String json) {
        if (isEmpty(json)) {
            return false;
        }
        try {
            new JSONObject(json);
        } catch (JSONException e) {
            return false;
        }
        return true;
    }

    @Deprecated
    public static Response handleHttpException(Throwable throwable) {
        if (throwable instanceof HttpException) {
            HttpException httpException = ((HttpException) throwable);
            Response response = new Response();
            try {
                retrofit2.Response<?> httpResponse = httpException.response();
                if (httpResponse.errorBody() != null && isValid(httpResponse.errorBody().string())) {
                    return (Response) response.fromJson(httpResponse.errorBody().string());
                } else {
                    response.setResponseCode(httpException.code());
                    return response.setMessage(HttpStatusUtils.getMessageForStatusCode(httpException.code()));
                }
            } catch (IOException e) {
                return new Response(true);
            }
//            Log.i(TAGs.TAG(getClass()), "login asdasd: "+response);
        }
        return new Response(throwable.getMessage(), true);
    }

    public static Response handleHttpException2(Throwable throwable) {
        if (throwable instanceof HttpException) {
            Log.i(TAGs.TAG, "handleHttpException2: 1");
            HttpException httpException = ((HttpException) throwable);
            retrofit2.Response<?> httpResponse = httpException.response();
            return HandleErrorResponseBody.responseErrorBody(httpResponse);
        }
        Log.i(TAGs.TAG, "handleHttpException2: 2");
        return new Response("Unknown error", 0, true);
    }

    public static Disposable observableInterval(long period, TimeUnit unit, Consumer<Long> subscribe) {
        return Observable.interval(period, unit, Schedulers.io())
                .distinctUntilChanged()
                .subscribe(subscribe, throwable -> {
                    Log.e(TAGs.TAG, "observableInterval: ", throwable);
                });

    }

    /**
     * Creates a countdown timer that emits values from the specified duration down to 0.
     *
     * @param durationInSeconds The duration of the countdown in seconds.
     * @return An Observable that emits the countdown values.
     */
    public static Observable<Integer> createCountdown(int durationInSeconds) {
        return Observable.interval(0, 1, TimeUnit.SECONDS)
                .map(tick -> durationInSeconds - tick.intValue())
                .take(durationInSeconds + 1)
                .flatMap(remaining -> remaining == 0 ? Single.just(remaining).toObservable() : Observable.just(remaining));
    }
}
