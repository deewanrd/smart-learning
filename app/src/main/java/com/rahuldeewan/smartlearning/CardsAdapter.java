package com.rahuldeewan.smartlearning;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Pallav
 * on 10/12/2017.
 */

public class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.MyViewHolder> {
    private Context mContext;
    private List<Categories> categoryList;
    private Fragment fragment;
    private Activity context;

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, count;
        ImageView thumbnail;

        MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            count = view.findViewById(R.id.count);
            thumbnail = view.findViewById(R.id.thumbnail);
        }
    }

    CardsAdapter(Context mContext, List<Categories> categoryList, Activity context, Fragment fragment) {
        this.mContext = mContext;
        this.categoryList = categoryList;
        this.context = context;
        this.fragment = fragment;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Categories category = categoryList.get(position);
        holder.title.setText(category.getName());
        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (context != null) {
                    Toast.makeText(fragment.getActivity(), holder.title.getText(), Toast.LENGTH_SHORT).show();
                    if (holder.title.getText().equals("Articles")) {
                        Intent intent = new Intent(fragment.getActivity(), ArticlesActivity.class);
                        fragment.getActivity().startActivity(intent);
                        fragment.getActivity().getFragmentManager().popBackStack();
                    }
                    if (holder.title.getText().equals("Engineering")) {
                        Intent intent = new Intent(context, TopicListActivity.class);
                        context.startActivity(intent);
                        fragment.getActivity().getFragmentManager().popBackStack();
                    }
                }
            }
        });
        Glide.with(mContext).load(category.getThumbnail()).into(holder.thumbnail);
    }

    private void showPopupMenu(View view) {
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_card, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }

    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_add_favourite:
                    Toast.makeText(mContext, "Add to favourite", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }
}