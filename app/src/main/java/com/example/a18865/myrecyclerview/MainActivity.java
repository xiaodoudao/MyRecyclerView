package com.example.a18865.myrecyclerview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    //普通的list
    private RecyclerView.LayoutManager mLayoutManager;
    //横向的list
//    private LinearLayoutManager mLayoutManager;
    private MyAdapter myAdapter;
    private ArrayList<String> datas = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        for (int i = 0; i < 31; i++) {
            datas.add("拦汪叽喂wifi"+i);
        }
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        //创建默认的LayoutManager
//        mLayoutManager = new LinearLayoutManager(this);
        //横向list只需设置如下代码，注意要声明mLayoutManager的类型是LinearLayoutManager而不是父类LayoutManager：
//        mLayoutManager.setOrientation(LinearLayout.HORIZONTAL);
        // Grid布局的列表，
        //注意，在Grid布局中也可以设置列表的Orientation属性，来实现横向和纵向的Grid布局。
        mLayoutManager = new GridLayoutManager(this,3);
        //瀑布流
//        mLayoutManager = new StaggeredGridLayoutManager(3,LinearLayout.HORIZONTAL);

        recyclerView.setLayoutManager(mLayoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        recyclerView.setHasFixedSize(true);
        //创建并设置适配器
        myAdapter = new MyAdapter(datas);
        recyclerView.setAdapter(myAdapter);

//        recyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayout.VERTICAL));
        recyclerView.addItemDecoration(new DividerGridItemDecoration(this));
        myAdapter.setOnTextViewClickListener(new MyAdapter.OnItemTextViewClickListener() {
            @Override
            public void onTextViewClick(View v, Model m) {
                TextView tv =m.getTextView();
                tv.setTextColor(Color.rgb(255,250,1));
                Log.d("UGD", "onTextViewClick: "+ v.getId());
            }
        });
        myAdapter.setOnItemClickListener(new MyAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Model model) {
                TextView tv =model.getTextView();
                Log.d("UGD", "onItemClick: "+view.getId());
                tv.setText(model.getString());
            }

        });
    }

    //添加数据
    public void addItem(String data,int position){
        datas.add(position,data);
        myAdapter.notifyItemInserted(position);
    }

    //删除数据
    public void removeItem(String data){
        int position = datas.indexOf(data);
        datas.remove(position);
        myAdapter.notifyItemRemoved(position);
    }
}
