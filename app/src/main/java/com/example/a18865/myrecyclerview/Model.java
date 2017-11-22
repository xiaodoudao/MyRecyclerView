package com.example.a18865.myrecyclerview;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by 18865 on 2017/11/14.
 */

public class Model {
    private String string;
    private TextView textView;
    private ImageView imageView;

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public TextView getTextView() {
        return textView;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}
