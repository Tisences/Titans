package com.tisen.titans.utils;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;

import java.util.List;

/**文本工具类
 * Created by tisen on 2016/12/14 18:16.
 * Email tisences@qq.com
 */
public class TextUtil {

    /**
     * 判断文本是否为空 null或去除空格后长度为0 时为空，
     * @param str
     * @return
     */
    public static boolean isEmpty(String str){
        return str == null || str.trim().length()==0;
    }



    public static SpannableString getSpannable(List<TextInfo>texts){
        StringBuffer text = new StringBuffer();
        for(TextInfo info:texts){
            text.append(info.text);
        }
        SpannableString spannable = new SpannableString(text.toString());
        int count = 0;
        for(TextInfo info:texts){
            int length = info.text.length();
            spannable.setSpan(new RelativeSizeSpan(info.size),count,count+length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannable.setSpan(new ForegroundColorSpan(info.color),count,count+length,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            count = count+length;
        }
        return spannable;
    }

    public static SpannableString getSpannable(String text1,int color1,float size1,String text2,int color2,float size2){
        SpannableString spannable = new SpannableString(text1+text2);

        spannable.setSpan(new RelativeSizeSpan(size1),0,text1.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new ForegroundColorSpan(color1),0,text1.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new RelativeSizeSpan(size2),text1.length(),text1.length()+text2.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new ForegroundColorSpan(color2),text1.length(),text1.length()+text2.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return spannable;
    }
    public class TextInfo{
        String text;
        int color;
        float size;

        public TextInfo(String text, int color, float size) {
            this.text = text;
            this.color = color;
            this.size = size;
        }
    }
}
