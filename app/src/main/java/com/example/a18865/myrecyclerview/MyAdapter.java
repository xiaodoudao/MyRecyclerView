package com.example.a18865.myrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 18865 on 2017/11/13.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> implements View.OnClickListener{

    public ArrayList<String> datas = null;
    private OnRecyclerViewItemClickListener itemClickListener = null;
    private OnItemTextViewClickListener textViewClickListener = null;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener){
        this.itemClickListener = listener;
    }

    public void setOnTextViewClickListener(OnItemTextViewClickListener textViewClickListener){
        this.textViewClickListener = textViewClickListener;
    }
    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.rl:
                if (itemClickListener != null){
                    //item的点击事件
//            itemClickListener.onItemClick(view, (String) view.getTag(R.id.tag1), (TextView) view.getTag(R.id.tag2));
                    itemClickListener.onItemClick(view, (Model) view.getTag());
                }
                break;
            case R.id.tv:
                if (textViewClickListener != null){
                    textViewClickListener.onTextViewClick(view,(Model) view.getTag());
                }
                break;
        }

    }

    public static interface OnRecyclerViewItemClickListener{
//        void onItemClick(View view,String data,TextView textv);
        void onItemClick(View view,Model model);
    }

    public interface OnItemTextViewClickListener{
        void onTextViewClick(View v,Model m);
    }
        public MyAdapter(ArrayList<String> datas){
        this.datas = datas;
    }
    //创建新view，被LayoutManager调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        //将创建的view注册点击事件
        view.setOnClickListener(this);
        return viewHolder;
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(datas.get(position));
        holder.textView.setOnClickListener(this);
        //注册点击事件
//        holder.textView.setOnClickListener(this);
        //将数据保存在itemView的Tag中，以便点击时获取 如果要改变item内部控件 ，设置多个tag 注意：tag的key值必须为从R文件中获取的
//        holder.itemView.setTag(R.id.tag1,datas.get(position));
//        holder.itemView.setTag(R.id.tag2,holder.textView);

        //将数据保存在itemView的Tag中，以便点击时获取 如果要改变item内部控件 可将参数放入一个对象中，再将对象放入tag中
        Model mo = new Model();
        mo.setString("kakaxi"+position);
        mo.setTextView(holder.textView);
        holder.itemView.setTag(mo);

        holder.textView.setTag(mo);

    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        return datas.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv);
        }
    }
}
