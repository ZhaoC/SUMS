package com.capstone.prime.faces.beans;

import javax.faces.bean.ManagedBean;

/**
 * Created by  ZhaoC on 11/14/16.
 */
@ManagedBean
public class EditorView {

    private String newsTitle;
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }
}