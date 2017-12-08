package com.rahuldeewan.smartlearning;


import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mridul
 * on 17-11-2017.
 */

public class ResultAdapter extends ArrayAdapter {
    private Activity context;
    private List<Result> resultList = new ArrayList<>();

    ResultAdapter(@NonNull Activity context, @NonNull List<Result> resultList) {
        super(context, 0, resultList);
        this.context = context;
        this.resultList = resultList;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listViewItem = convertView;
        if (listViewItem == null) {
            listViewItem = LayoutInflater.from(context).inflate(R.layout.result_list_item, parent, false);
        }
        Result currentResult = resultList.get(position);

        TextView tvTopicName = listViewItem.findViewById(R.id.tv_topic_name);
        TextView tvDate = listViewItem.findViewById(R.id.tv_date);
        TextView tvLevel = listViewItem.findViewById(R.id.tv_level);
        TextView tvScore = listViewItem.findViewById(R.id.tv_score_value);
        tvTopicName.setText(currentResult.getTopic());
        tvDate.setText(currentResult.getDate());
        tvLevel.setText(currentResult.getLevel());
        tvScore.setText("" + currentResult.getScore());
        return listViewItem;
    }
}
