package com.tony.timemanager.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tony.timemanager.R;
import com.tony.timemanager.module.Note;

import java.util.List;

/**
 * Created by tony on 8/16/16.
 */
public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ViewHolder> implements View.OnClickListener, View.OnLongClickListener {
    private Context mContext;
    private List<Note> mList;
    private LayoutInflater mLayoutInflater;
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    public ToDoAdapter(Context mContext, List<Note> mList) {
        this.mContext = mContext;
        this.mList = mList;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_todo_recyclerview, parent, false);
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setTag(position);
        holder.mTextView.setText(mList.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onClick(View view) {
        if (mOnItemClickListener != null) {
            int position = (int) view.getTag();
            mOnItemClickListener.onItemClick(view, position, mList.get(position));
        }
    }

    @Override
    public boolean onLongClick(View view) {
        if (mOnItemLongClickListener != null) {
            int position = (int) view.getTag();
            mOnItemLongClickListener.onItemLongClick(view, position, mList.get(position));
        }
        return true;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position, Object data);
    }

    public interface OnItemLongClickListener{
        void onItemLongClick(View view, int position, Object data);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.mOnItemLongClickListener = onItemLongClickListener;
    }

    public static final class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }
}
