package test.com.gitapp.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bumptech.glide.Glide;

import java.util.List;

import test.com.gitapp.R;
import test.com.gitapp.adapter.GitListAdapter;
import test.com.gitapp.databinding.ActivityLoginBinding;
import test.com.gitapp.databinding.DetailActivityBinding;
import test.com.gitapp.model.DetailList;
import test.com.gitapp.model.GitList;
import test.com.gitapp.viewModel.GetDetailModel;
import test.com.gitapp.viewModel.GetProjectModel;

public class GitDetailActivity extends AppCompatActivity {

    private DetailActivityBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mBinding = DataBindingUtil.setContentView(this, R.layout.detail_activity);

        mBinding.toolBar.tvTitle.setText(R.string.git_project);
        mBinding.toolBar.ibLeft.setVisibility(View.VISIBLE);
        mBinding.toolBar.ibLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        callGetRepositories();

    }

    /**
     * Calling API to get the Repositories
     */
    private void callGetRepositories() {


        if (getIntent().hasExtra("DETAIL_URL")) {

            GetDetailModel categoryModel = ViewModelProviders.of(this).get(GetDetailModel
                    .class);

            categoryModel.getRepositoriesList(getIntent().getStringExtra("DETAIL_URL")).observe(this, new Observer<DetailList>() {

                @Override
                public void onChanged(@Nullable DetailList detailList) {


                    Glide.with(GitDetailActivity.this)
                            .load(detailList.getAvatarUrl())
                            .into(mBinding.ivUser);

                    mBinding.tvUserName.setText("Name :- "+detailList.getName());
                    mBinding.tvEmail.setText("Email :- "+detailList.getEmail());
                    mBinding.tvFollowers.setText("Followers :- "+detailList.getFollowers());
                    mBinding.tvFollowing.setText("Following :- "+detailList.getFollowing());

                }


            });

        }
    }
}
