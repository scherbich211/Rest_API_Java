package com.example.moviejava.MainAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviejava.R;
import com.example.moviejava.API.MainRepo;
import com.example.moviejava.SingleActivity.SingleActivity;
import com.example.moviejava.API.PostModel;

import java.util.List;

public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.MyViewHolder> {

    private Context context;
    private List<PostModel> postModelList;

    public PostListAdapter(Context context, List<PostModel> postModelList) {
        this.context = context;
        this.postModelList = postModelList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_post_item, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
       holder.setData(position);
    }

    @Override
    public int getItemCount() {
        return postModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout ll_item;
        TextView textTitle, txtBody,txtUserId;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textTitle = itemView.findViewById(R.id.txtTitle);
            txtBody = itemView.findViewById(R.id.txtBody);
            txtUserId = itemView.findViewById(R.id.txtUserId);
            ll_item = itemView.findViewById(R.id.ll_item);

        }
        private void setData(final int pos){
            textTitle.setText(String.valueOf(postModelList.get(pos).getTitle()));
            txtBody.setText(new StringBuilder(postModelList.get(pos)
                    .getBody()
                    .substring(0,20))
                    .append("...")
                    .toString());
            txtUserId.setText(String.valueOf(postModelList.get(pos).getId()));

            ll_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MainRepo.g_selected_post = pos + 1;
                    Intent intent = new Intent(view.getContext(), SingleActivity.class);
                    view.getContext().startActivity(intent);
                }
            });

        }
    }
}
