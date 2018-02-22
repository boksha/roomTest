package com.example.miodragmilosevic.roomtest.attacklist;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.miodragmilosevic.roomtest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by miodrag.milosevic on 2/6/2018.
 */

public class AttackListAdapter  extends
            RecyclerView.Adapter
                    <AttackListAdapter.ListItemViewHolder> {
        private OnRecyclerItemClickListener mOnItemClickListener;
        private List<AttackListItemUiModel> mItems;

        public AttackListAdapter() {
            mItems = new ArrayList<>();
//            mItems.add(new EpiAttack());//test
        }

        @Override
        public ListItemViewHolder onCreateViewHolder(
                ViewGroup viewGroup, int viewType) {
            View itemView = LayoutInflater.
                    from(viewGroup.getContext()).
                    inflate(R.layout.item_attack,
                            viewGroup,
                            false);
            return new ListItemViewHolder(itemView);
        }

        @Override
            public void onBindViewHolder(ListItemViewHolder viewHolder, int position) {
            Log.i("Miki", "onBindViewHolder: position" + position);
            AttackListItemUiModel attackItem = mItems.get(position);
            viewHolder.attackDuration.setText((attackItem.getElapsedTime()));
            viewHolder.attackTitle.setText(attackItem.getAttackTitle());
            viewHolder.attackDayOfMonth.setText("21");
            viewHolder.attackDayOfWeek.setText("Sre");
            viewHolder.attackLayout.setOnClickListener(view -> {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(mItems.get(position).getId());
                }
            });
        }

        @Override
        public int getItemCount() {
            Log.i("Miki", "getItemCount: size" + mItems.size());
            return mItems.size();
        }

        public final static class ListItemViewHolder
                extends RecyclerView.ViewHolder {
            public TextView attackTitle;
            public TextView attackDuration;
            public TextView attackDayOfWeek;
            public TextView attackDayOfMonth;
            public RelativeLayout attackLayout;


            public ListItemViewHolder(View itemView) {
                super(itemView);
                attackTitle = itemView.findViewById(R.id.attack_type);
                attackDuration = itemView.findViewById(R.id.attack_duration);
                attackDayOfWeek = itemView.findViewById(R.id.attack_day_of_week);
                attackDayOfMonth = itemView.findViewById(R.id.attack_day_of_month);
                attackLayout = itemView.findViewById(R.id.attack_layout);
            }
        }

        public void setAttackItems(List<AttackListItemUiModel> epiAttacks) {
            mItems.clear();
            if (epiAttacks != null) {
                Log.i("Miki", "setAttackItems: size" + epiAttacks.size());
                mItems.addAll(epiAttacks);
            }
            notifyDataSetChanged();
        }

        public void setOnItemClickListener(OnRecyclerItemClickListener listener) {
            mOnItemClickListener = listener;
        }

        public interface OnRecyclerItemClickListener {
            void onItemClick(long id);
        }

}
