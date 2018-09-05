package test.com.gitapp.adapter;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

import test.com.gitapp.activity.GitDetailActivity;
import test.com.gitapp.databinding.ListItemBinding;
import test.com.gitapp.R;
import test.com.gitapp.model.GitList;

public class GitListAdapter extends RecyclerView.Adapter<GitListAdapter.ViewHolder> {


    private List<GitList> gitList;
    private Context mContext;

    public GitListAdapter(List<GitList> gitList, Context mContext) {
        this.gitList = gitList;
        this.mContext = mContext;
    }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.list_item, viewGroup, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        final GitList model = gitList.get(i);


        viewHolder.mBinding.tvGitName.setText(model.getFullName());
        viewHolder.mBinding.tvGitDiscription.setText(model.getDescription());

        Glide.with(mContext)
                .load(model.getOwner().getAvatarUrl())
                .into(viewHolder.mBinding.ivUser);

        viewHolder.mBinding.rlMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mContext, GitDetailActivity.class);
                intent.putExtra("DETAIL_URL", model.getOwner().getLogin());
                mContext.startActivity(intent);

            }
        });
    }


    @Override
    public int getItemCount() {
        return gitList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ListItemBinding mBinding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);

        }
    }

}