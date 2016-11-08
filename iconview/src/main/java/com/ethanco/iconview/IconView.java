package com.ethanco.iconview;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import static com.ethanco.iconview.Utils.px2sp;
import static com.ethanco.iconview.Utils.sp2px;

/**
 * @Description 一个ImageView一个textView的集成Layout
 * Created by YOLANDA on 2015-12-09.
 */
public class IconView extends LinearLayout {
    private View contentView;
    private ImageView iconImg;
    private TextView iconTv;
    private boolean canClick = true;

    public IconView(Context context) {
        super(context);
        init(context, null);
    }

    public IconView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public IconView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater mInflater = LayoutInflater.from(context);
        contentView = mInflater.inflate(R.layout.layout_icon, this);
        iconImg = (ImageView) contentView.findViewById(R.id.imgView_iconview);
        iconTv = (TextView) contentView.findViewById(R.id.tv_iconview);

        if (null != attrs) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.IconView);
            Drawable iconBackground = ta.getDrawable(R.styleable.IconView_iconSrc);
            String iconText = ta.getString(R.styleable.IconView_iconText);
            int iconTextSize = px2sp(getContext(),
                    ta.getDimension(R.styleable.IconView_iconTextSize, sp2px(getContext(), 18)));
            //int iconTextColor = ta.getColor(R.styleable.IconView_iconTextColor, Color.BLACK);
            //Resources resource=getContext().getResources();
            ColorStateList iconTextColorList = ta.getColorStateList(R.styleable.IconView_iconTextColor);
            canClick = ta.getBoolean(R.styleable.IconView_canClick, true);
            ta.recycle();

            if (iconBackground != null) {
                iconImg.setImageDrawable(iconBackground);
            }
            if (!TextUtils.isEmpty(iconText)) {
                iconTv.setText(iconText);
            }
            iconTv.setTextSize(iconTextSize);
            //iconTv.setTextColor(iconTextColor);
            if (iconTextColorList != null) {
                iconTv.setTextColor(iconTextColorList);
            }
        }

        if (canClick) {
            iconImg.setDuplicateParentStateEnabled(true);
            iconTv.setDuplicateParentStateEnabled(true);
            this.setClickable(true);
        }
    }

    public void setOnIconClickListener(OnClickListener l) {
        contentView.setOnClickListener(l);
    }
}
