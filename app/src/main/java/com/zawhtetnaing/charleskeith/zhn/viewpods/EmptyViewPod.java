package com.zawhtetnaing.charleskeith.zhn.viewpods;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zawhtetnaing.charleskeith.zhn.R;
import com.zawhtetnaing.charleskeith.zhn.utils.GlideApp;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EmptyViewPod extends RelativeLayout {

    @BindView(R.id.iv_empty)
    ImageView ivEmpty;

    @BindView(R.id.tv_empty)
    TextView tvEmpty;
    public EmptyViewPod(Context context) {
        super(context);
    }

    public EmptyViewPod(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EmptyViewPod(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setEmptyData(String imageUrl, String emptyMessage){
        GlideApp.with(getContext()).
                load(imageUrl).
                into(ivEmpty);

        tvEmpty.setText(emptyMessage);
    }

    public void setEmptyData(int emptyImageResource, String emptyMessage){
        ivEmpty.setImageResource(emptyImageResource);
        tvEmpty.setText(emptyMessage);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }
}
