package com.example.project03;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.project03.tools.Icon;
import com.example.project03.tools.MyAdapter;

import java.util.ArrayList;
import java.util.Random;


public class Fragment0 extends Fragment {

    private TextView textView;
    private SearchView searchView;
    private Fragment0 mContext;
    private GridView grid_photo;
    private BaseAdapter mAdapter = null;
    private ArrayList<Icon> mData = null;
    private int num_shujia,num_pai;//检索的信息
    private int image[] = new int[6];//创建图片集合

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                    textView.setText("第" + num_shujia + "书架" + "\n" + "第" + num_pai + "排");//显示的信息
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_fragment0);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment0, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView();
        initSearch();
        initGridView();
    }
    //绑定控件
    private void initView() {
        textView = (TextView) getActivity().findViewById(R.id.tv_jiansuo);
        searchView = (SearchView) getActivity().findViewById(R.id.search);
        mContext = Fragment0.this;
        grid_photo = (GridView) getActivity().findViewById(R.id.grid_photo);
        //图片集合添加数据
        image[0] = R.mipmap.book1;
        image[1] = R.mipmap.book2;
        image[2] = R.mipmap.book3;
        image[3] = R.mipmap.book4;
        image[4] = R.mipmap.book5;
        image[5] = R.mipmap.book6;

    }

    //搜索框部分
    private void initSearch() {
        //显示完整搜索栏，true为搜索图标
//        searchView.setIconifiedByDefault(false);
        //提示文本
        searchView.setQueryHint("查询您想看的书");
        //监听器
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                myRandom();
                handler.sendEmptyMessage(1);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //搜索输入不为空
                if (TextUtils.isEmpty(newText)){
                    myRandom();
                    handler.sendEmptyMessage(1);
                }else {
                    //此处暂存疑虑
                }
                return false;
            }
        });
    }

//    gridview部分
    private void initGridView() {
        //添加数据
        mData = new ArrayList<Icon>();
        mData.add(new Icon(image[0],"傲慢与偏见"));
        mData.add(new Icon(R.mipmap.book2,"雾都孤儿"));//两种表达方式
        mData.add(new Icon(image[2],"小王子"));
        mData.add(new Icon(image[3],"镜花缘"));
        mData.add(new Icon(image[4],"老人与海"));
        mData.add(new Icon(image[5],"神秘岛"));
        //绑定适配器
        mAdapter = new MyAdapter<Icon>(mData,R.layout.item_grid_icon) {
            @Override
            public void bindView(ViewHolder holder, Icon obj) {
                holder.setImageResource(R.id.img_icon,obj.getiId());
                holder.setText(R.id.txt_icon,obj.getiName());
            }
        };
        grid_photo.setAdapter(mAdapter);
        //监听器，针对点击书籍显示信息
        grid_photo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (image[position]){
                    case R.mipmap.book1:
                        myRandom();
                        handler.sendEmptyMessage(1);
                        break;
                    case R.mipmap.book2:
                        myRandom();
                        handler.sendEmptyMessage(1);
                        break;
                    case R.mipmap.book3:
                        myRandom();
                        handler.sendEmptyMessage(1);
                        break;
                    case R.mipmap.book4:
                        myRandom();
                        handler.sendEmptyMessage(1);
                        break;
                    case R.mipmap.book5:
                        myRandom();
                        handler.sendEmptyMessage(1);
                        break;
                    case R.mipmap.book6:
                        myRandom();
                        handler.sendEmptyMessage(1);
                        break;
                     default:
                         break;
                }
            }
        });
    }
//    Random
    private void myRandom(){
        Random random = new Random();
        num_shujia = random.nextInt(8)%(8-2+2)+2;
        num_pai = random.nextInt(8)%(12-2+2)+2;
    }

}
