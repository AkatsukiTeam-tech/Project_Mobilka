package com.example.project;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ExpandableTextView extends LinearLayout {
    private TextView mContentTextView;
    private TextView mExpansionButton;
    private int mMaxLine = 3;
    private CharSequence mContent;
    private boolean mIsExpansion;

    public ExpandableTextView(Context context) {
        super(context);
        init(context);
    }

    public ExpandableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ExpandableTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setOrientation(VERTICAL);

        LayoutInflater.from(context).inflate(R.layout.view_expandable, this);

        mContentTextView = findViewById(R.id.tv_content);
        mExpansionButton = findViewById(R.id.v_expansion);

        mContentTextView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        if (mContentTextView.getWidth() == 0) {
                            return;
                        }
                        mContentTextView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        setText(mContent);
                    }
                }
        );

        mExpansionButton.setOnClickListener(
                new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        toggleExpansionStatus();
                    }
                }
        );
        mExpansionButton.setVisibility(GONE);
    }

    private void toggleExpansionStatus() {
        mIsExpansion = !mIsExpansion;

        if (mIsExpansion) {
            mExpansionButton.setText("Свернуть");
            mContentTextView.setMaxLines(Integer.MAX_VALUE);
        } else {
            mExpansionButton.setText("Подробне...");
            mContentTextView.setMaxLines(mMaxLine);
        }
    }

    public void setMaxLine(int maxLine) {
        this.mMaxLine = maxLine;
        setText(mContent);
    }

    public void setText(CharSequence text) {
        mContent = text;

        if (mContentTextView.getWidth() == 0) {
            return;
        }
        mContentTextView.setMaxLines(Integer.MAX_VALUE);
        mContentTextView.setText(mContent);

        int lineCount = mContentTextView.getLineCount();

        if (lineCount > mMaxLine) {
            mExpansionButton.setVisibility(VISIBLE);
            mExpansionButton.setText("Подробне...");

            mContentTextView.setMaxLines(mMaxLine);
            mIsExpansion = false;

        } else {
            mExpansionButton.setVisibility(GONE);
        }
    }

}