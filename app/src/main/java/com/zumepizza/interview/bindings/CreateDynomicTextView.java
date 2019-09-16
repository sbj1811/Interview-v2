package com.zumepizza.interview.bindings;

import android.content.Context;
import android.widget.TextView;

import com.zumepizza.interview.R;

public class CreateDynomicTextView {

    private Context mContext;
    private TextView textView;
    private String text;
    public CreateDynomicTextView(){}
    public CreateDynomicTextView(Context context,String text){
        this.mContext = context;
        this.text = text;

    }
    public void setTextView(){
        textView = new TextView(mContext);
    }
    public TextView getTextView(){
        textView = new TextView(mContext);
        textView.setText(text);
        textView.setTextColor(mContext.getColor(R.color.dark_grey));
        return textView;
    }

}
