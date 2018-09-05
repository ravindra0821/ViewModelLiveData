package test.com.gitapp.apiCall;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import test.com.gitapp.model.DetailList;
import test.com.gitapp.model.GitList;

/**
 * Created by Ravindra Kushwaha on 24/08/2018.
 */

public interface ApiInterface {

    @GET("repositories?q=tetris+language:android&sort=stars&order=desc")
    Call<List<GitList>> getGitProjects();

    @GET("users/{mojombo}")
    Call<DetailList> getItems(@Path("mojombo") String name);
}
