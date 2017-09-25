package com.golfscorecard.cws.golfscorecard.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.golfscorecard.cws.golfscorecard.R;
import com.golfscorecard.cws.golfscorecard.hole.Hole;

/**
 * Created by cindymichalowski on 9/3/17.
 */

public class HoleAdapter extends BaseAdapter {

    private final Context mContext;
    private final Hole[] mHoles;

    public HoleAdapter(Context context, Hole[] holes) {
        mContext = context;
        mHoles = holes;
    }

    @Override
    public int getCount() {
        return mHoles.length;
    }

    @Override
    public Object getItem(int position) {
        return mHoles[position];
    }

    @Override
    public long getItemId(int position) {
        return 0; // not going to use this, but it's for tagging items for easy reference
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;

        // if convertView is null, it must be set to a view
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.row, null);

            holder = new ViewHolder();
            holder.holeLabel = (TextView) convertView.findViewById(R.id.holeLabel);
            holder.strokeCount = (TextView) convertView.findViewById(R.id.strokeCount);
            holder.btnPlus = (Button) convertView.findViewById(R.id.btnPlus);
            holder.btnMinus = (Button) convertView.findViewById(R.id.btnMinus);

            convertView.setTag(holder);

        } else {
            // if convertView is not null, then we just need to get the holder from convertView
            holder = (ViewHolder) convertView.getTag();
        }

        holder.holeLabel.setText(mHoles[position].getLabel());
        holder.strokeCount.setText(mHoles[position].getStrokeCount() + "");

        holder.btnMinus.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int updatedStrokeCount = mHoles[position].getStrokeCount() - 1;
                if (updatedStrokeCount < 0) updatedStrokeCount = 0;
                mHoles[position].setStrokeCount(updatedStrokeCount);
                holder.strokeCount.setText(updatedStrokeCount + "");
            }
        });

        holder.btnPlus.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int updatedStrokeCount = mHoles[position].getStrokeCount() + 1;
                mHoles[position].setStrokeCount(updatedStrokeCount);
                holder.strokeCount.setText(updatedStrokeCount + "");
            }
        });

        return convertView;
    }

    // need a View Holder class to hold the views; need varables for each view in row.xml
    private static class ViewHolder {
        TextView holeLabel;
        TextView strokeCount;
        Button btnPlus;
        Button btnMinus;
    }

}
