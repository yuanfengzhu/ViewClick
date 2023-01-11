package com.example.viewclick;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;

import butterknife.Bind;
import butterknife.OnClick;

public class SimpleDialog extends BasicDialog {
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.content)
    TextView content;
    @Bind(R.id.passive)
    TextView passive;
    @Bind(R.id.positive)
    TextView positive;
    @Bind(R.id.image)
    ImageView imageView;

    private OnCancelClickListener onCancelClickListener;
    private OnConfirmClickListener onConfirmClickListener;
    private OnCheckedChangeListener onCheckedChangeListener;
    private boolean isChecked;

    public SimpleDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getdialogLayoutId() {
        return R.layout.simple_dialog;
    }


    @OnClick({R.id.passive, R.id.positive})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.passive:
                dismiss();
                if (onCancelClickListener != null) {
                    onCancelClickListener.onCancel();
                }

                if (onCheckedChangeListener != null) {
                    onCheckedChangeListener.onChecked(isChecked);
                }
                break;
            case R.id.positive:
                dismiss();
                if (onConfirmClickListener != null) {
                    onConfirmClickListener.onConfirm();
                }

                break;
        }
    }

    public interface OnCancelClickListener {
        void onCancel();
    }

    public interface OnConfirmClickListener {
        void onConfirm();
    }

    public interface OnCheckedChangeListener {
        void onChecked(boolean isChecked);
    }

    public interface OnTheDismissListener {
        void OnTheDismiss(DialogInterface dialog);
    }

    public SimpleDialog setOnCancelClickListener(OnCancelClickListener onCancelClickListener) {
        this.onCancelClickListener = onCancelClickListener;
        return this;
    }

    public SimpleDialog setOnConfirmClickListener(OnConfirmClickListener onConfirmClickListener) {
        this.onConfirmClickListener = onConfirmClickListener;
        return this;
    }

    public SimpleDialog setOnCheckedChangeListener(OnCheckedChangeListener onCheckedChangeListener) {
        this.onCheckedChangeListener = onCheckedChangeListener;
        return this;
    }

    public SimpleDialog setTheOnDismissListener(final OnTheDismissListener onTheDismissListener) {
        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (onTheDismissListener != null) {
                    onTheDismissListener.OnTheDismiss(dialog);
                }
            }
        });
        return this;
    }

    public SimpleDialog setTitle(String strtitle) {
        title.setText(strtitle);
        return this;
    }

    public SimpleDialog setTitleGone(boolean isGone) {
        title.setVisibility(isGone ? View.GONE : View.VISIBLE);
        return this;
    }

    public SimpleDialog setContent(String strContent) {
        content.setText(strContent);
        return this;
    }

    public SimpleDialog setContentColor(int resId) {
        content.setTextColor(resId);
        return this;
    }

    public SimpleDialog setContentGravity(int gravity) {
        content.setGravity(gravity);
        return this;
    }

    public SimpleDialog setContentAlignment(int textAlignment) {
        content.setTextAlignment(textAlignment);
        return this;
    }

    /**
     * 是否展示消极
     *
     * @return
     */
    public SimpleDialog setShowPassive() {
        passive.setVisibility(View.VISIBLE);
        return this;
    }

    /**
     * 设置消极文本
     *
     * @param passiveText
     * @return
     */
    public SimpleDialog setPassiveText(String passiveText) {
        passive.setText(passiveText);
        passive.setVisibility(View.VISIBLE);
        return this;
    }

    /**
     * 设置积极文本
     *
     * @param positiveText
     * @return
     */
    public SimpleDialog setPositiveText(String positiveText) {
        positive.setText(positiveText);
        return this;
    }


    public SimpleDialog setImageView(@DrawableRes int resourceId) {
        imageView.setVisibility(View.VISIBLE);
        imageView.setImageResource(resourceId);
        return this;
    }

    @Override
    public void show() {
        try {
            if(context instanceof Activity) {
                if (!((Activity)context).isFinishing()) {
                    super.show();
                }
            }
        } catch (Exception e) {

        }
    }
}
