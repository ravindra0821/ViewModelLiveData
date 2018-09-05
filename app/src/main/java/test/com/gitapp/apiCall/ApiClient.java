package test.com.gitapp.apiCall;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ravindra Kushwaha on 24/08/2018.
 */

public class ApiClient {

    public static final String BASE_URL = "https://api.github.com/";
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {

         OkHttpClient.Builder httpClient;
        httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(provideHeaderInterceptor());


        if (retrofit==null) {
                   retrofit = new Retrofit.Builder().
                   addConverterFactory(GsonConverterFactory.create()).
                   baseUrl(BASE_URL).
                   client(httpClient.build())
                   .build();

        }
        return retrofit;
    }

     private static Interceptor provideHeaderInterceptor() {
        return new Interceptor() {
            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();
                Request request = original.newBuilder()
                        .method(original.method(), original.body()).build();

                return chain.proceed(request);
            }
        };
    }
}
