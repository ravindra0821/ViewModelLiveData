package test.com.gitapp.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import test.com.gitapp.apiCall.ApiClient;
import test.com.gitapp.apiCall.ApiInterface;
import test.com.gitapp.model.GitList;

/**
 * Created by Ravindra Kushwaha on 24/08/2018.
 */

public class GetProjectModel extends ViewModel {
    private MutableLiveData<List<GitList>> repositoriesList;

    /**
     * @return Repositories List
     */
    public LiveData<List<GitList>> getRepositoriesList() {

        if (repositoriesList == null) {
            repositoriesList = new MutableLiveData<>();

        }

        loadRepositories();

        return repositoriesList;
    }


    private void loadRepositories() {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        final Call<List<GitList>> collectionArrayPojoCall = apiService.getGitProjects();

        collectionArrayPojoCall.enqueue(new Callback<List<GitList>>() {
            @Override
            public void onResponse(Call<List<GitList>> call, Response<List<GitList>> response) {
                Log.d("CollectionPojos", ",," + response.code());
                if (response.code() == 200) {
                    repositoriesList.setValue(response.body());
                }

                Log.e("RESPONSE", response.body().toString());

            }

            @Override
            public void onFailure(Call<List<GitList>> call, Throwable t) {

                Log.e("RESPONSE", t.toString());
            }
        });
    }


}
