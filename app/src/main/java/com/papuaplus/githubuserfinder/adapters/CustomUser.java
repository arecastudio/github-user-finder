package com.papuaplus.githubuserfinder.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.papuaplus.githubuserfinder.R;
import com.papuaplus.githubuserfinder.models.UserData;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomUser extends RecyclerView.Adapter<CustomUser.ViewHolder> implements View.OnClickListener {
    private Context context;
    private List<UserData> userData;

    public CustomUser(Context context, List<UserData> userData) {
        this.context = context;
        this.userData = userData;
    }

    @Override
    public void onClick(View v) {

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_item,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserData user=userData.get(position);
        holder.textUserLogin.setText(user.getLoginName());
        //holder.imageAvatarURL.setImageBitmap();
        Picasso.get().load(user.getAvatarURL()).into(holder.imageAvatarURL);
    }

    @Override
    public int getItemCount() {
        return userData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textUserLogin;
        public ImageView imageAvatarURL;

        public ViewHolder(@NonNull View view) {
            super(view);

            textUserLogin=(TextView) view.findViewById(R.id.textView);
            imageAvatarURL=(ImageView)view.findViewById(R.id.imageView);
        }
    }
}
