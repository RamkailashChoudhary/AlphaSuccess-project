package com.app.alphasucess.ui.tabui.test.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.alphasucess.R;
import com.app.alphasucess.ui.view.CircleTextView;

import java.util.ArrayList;

public class LeaderBoardAdapter extends RecyclerView.Adapter<LeaderBoardAdapter.ViewHolder> {

    private ArrayList<LeaderboardData> mValues;
    private Context mContext;
    private String fileName;

    public LeaderBoardAdapter(Context context, ArrayList<LeaderboardData> values) {
        mValues = values;
        mContext = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView leaderboardName,leaderboardRank,leaderboardMarks;

        public ViewHolder(View v) {
            super(v);
            leaderboardName = v.findViewById(R.id.leaderboardName);
            leaderboardRank = v.findViewById(R.id.leaderboardRank);
            leaderboardMarks = v.findViewById(R.id.leaderboardMarks);
        }

        public void setData(LeaderboardData item) {
            leaderboardName.setText(item.getName().toUpperCase());
            leaderboardRank.setText(item.getRank());
            leaderboardMarks.setText(item.getMarks());
        }

        @Override
        public void onClick(View view) {

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.leader_board_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        viewHolder.setData(mValues.get(position));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }
}

