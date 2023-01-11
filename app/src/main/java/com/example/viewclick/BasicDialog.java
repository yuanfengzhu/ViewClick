package com.example.viewclick;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;

import androidx.annotation.NonNull;

import butterknife.ButterKnife;

public abstract class BasicDialog extends Dialog {
    protected Context context;

    public BasicDialog(@NonNull Context context) {
        super(context);
        this.context = context;
        init();
    }

    private void init() {
        int layoutId = getdialogLayoutId();
        if (layoutId == 0) {
            return;
        }
        Window window = getWindow();
        //设置无标题栏（设置标题栏要在设置布局之前）
        window.requestFeature(Window.FEATURE_NO_TITLE);
        //设置背景
//        window.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.shape_cor_20_solid_white));
        //设置外部点击可消失
        setCanceledOnTouchOutside(false);
        //设置返回建不可点击
        setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    return true;
                }
                return false;
            }
        });

        View view = View.inflate(context, layoutId, null);
        setContentView(view);
        ButterKnife.bind(this,view);
        initView();
    }

    protected abstract void initView();

    protected abstract int getdialogLayoutId();
}
