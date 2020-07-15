package com.qts.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * @description: 字符串转日期的转换器
 * @author: duanyashu
 * @time: 2020-07-02 11:34
 */
public class StrToDateConverter implements Converter<String, Date> {


    @Override
    public Date convert(String source) {
        String format ="yyyy-MM-dd HH:mm:ss";
        if (Pattern.matches("^\\d{4}-\\d{2}-\\d{2}",source)){
            format="yyyy-MM-dd";
        }
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            return  dateFormat.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return null;
    }
}
