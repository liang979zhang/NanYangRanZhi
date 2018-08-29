package com.zcf.nanyangranzhi;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.zcf.nanyangranzhi.base.BaseActivity;
import com.zcf.nanyangranzhi.bean.CityAdapter;
import com.zcf.nanyangranzhi.bean.CityBean;
import com.zcf.nanyangranzhi.bean.CustomEditText;
import com.zcf.nanyangranzhi.bean.DividerItemDecoration;
import com.zcf.nanyangranzhi.bean.IndexBar;
import com.zcf.nanyangranzhi.bean.TitleItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 联系人 列表
 */
public class ContactsActivity extends BaseActivity {
    private static final String TAG = "zxt";
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_left)
    TextView tvLeft;
    @BindView(R.id.ll_back)
    FrameLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.fl_right)
    FrameLayout flRight;
    @BindView(R.id.school_friend_member_search_input)
    CustomEditText schoolFriendMemberSearchInput;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.indexBar)
    IndexBar indexBar;
    @BindView(R.id.tvSideBarHint)
    TextView tvSideBarHint;
    private RecyclerView mRv;
    private CityAdapter mAdapter;
    private LinearLayoutManager mManager;
    private List<CityBean> mDatas;

    private TitleItemDecoration mDecoration;

    /**
     * 右侧边栏导航区域
     */
    private IndexBar mIndexBar;

    /**
     * 显示指示器DialogText
     */
    private TextView mTvSideBarHint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tvTitle.setText("通讯录");
        llBack.setVisibility(View.GONE);
        EditText mSearchInput = (EditText) findViewById(R.id.school_friend_member_search_input);
        mSearchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ArrayList<CityBean> temp = new ArrayList<>(mDatas);
                for (CityBean data : mDatas) {
                    if (data.getCity().contains(charSequence)) {
                        temp.remove(data);
                        temp.add(data);
                    } else {
                        temp.remove(data);
                    }
                }

                if (mAdapter != null) {
                    mAdapter.refresh(temp);
                }


                if (temp.size() > 1) {
                    mDecoration.setmDatas(ContactsActivity.this, temp);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mRv = (RecyclerView) findViewById(R.id.rv);
        mRv.setLayoutManager(mManager = new LinearLayoutManager(this));
        //initDatas();
        initDatas(getResources().getStringArray(R.array.provinces));
        mRv.setAdapter(mAdapter = new CityAdapter(this, mDatas));
        mRv.addItemDecoration(mDecoration = new TitleItemDecoration(this, mDatas));
        //如果add两个，那么按照先后顺序，依次渲染。
        //mRv.addItemDecoration(new TitleItemDecoration2(this,mDatas));
        mRv.addItemDecoration(new DividerItemDecoration(ContactsActivity.this, DividerItemDecoration.VERTICAL_LIST));

        //使用indexBar
        mTvSideBarHint = (TextView) findViewById(R.id.tvSideBarHint);//HintTextView
        mIndexBar = (IndexBar) findViewById(R.id.indexBar);//IndexBar
        mIndexBar.setmPressedShowTextView(mTvSideBarHint)//设置HintTextView
                .setNeedRealIndex(true)//设置需要真实的索引
                .setmLayoutManager(mManager)//设置RecyclerView的LayoutManager
                .setmSourceDatas(mDatas);//设置数据源


    }

    @Override
    public int getview() {
        return R.layout.activity_contacts;
    }


    /**
     * 组织数据源
     *
     * @param data
     * @return
     */
    private void initDatas(String[] data) {
        mDatas = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            CityBean cityBean = new CityBean();
            cityBean.setCity(data[i]);//设置城市名称
            mDatas.add(cityBean);
        }
    }

    @OnClick(R.id.ll_back)
    public void onViewClicked() {
    }
}
