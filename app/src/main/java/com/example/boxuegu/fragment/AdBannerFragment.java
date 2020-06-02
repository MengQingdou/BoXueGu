package com.example.boxuegu.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.boxuegu.R;

public class AdBannerFragment extends Fragment {
    private String ab;      // 广告
    private ImageView iv;   // 图片
    public static AdBannerFragment newInstance(Bundle args){
        AdBannerFragment af = new AdBannerFragment();
        af.setArguments(args);
        return af;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arg = getArguments();
        // 获取广告图片名称，这是从哪里传过来的呢？嗯是从AdBannerAdapter传过来的
        ab = arg.getString("ad");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(ab != null){
            if("banner_1".equals(ab)){
                iv.setImageResource(R.drawable.banner_1);
            }else if("banner_2".equals(ab)){
                iv.setImageResource(R.drawable.banner_2);
            }else if("banner_3".equals(ab)){
                iv.setImageResource(R.drawable.banner_3);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(iv != null){
            iv.setImageDrawable(null);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        iv = new ImageView(getActivity());
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        iv.setLayoutParams(lp);
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        return iv;
    }
}
