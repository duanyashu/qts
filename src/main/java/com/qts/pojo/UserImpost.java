package com.qts.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.converters.bigdecimal.BigDecimalStringConverter;
import com.github.duanyashu.ExcelPatternMsg;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


/**
 * @description:
 * @author: duanyashu
 * @time: 2020-07-13 09:23
 */
public class UserImpost {
    //名称
    @ExcelProperty(index = 0,value = "名称")
    @ColumnWidth(10)
    @Length(max = 10)
    private String name;

    //性别
    @ExcelProperty(index = 1,value = "性别")
    @ColumnWidth(8)
    @Length(max = 2)
    @NotBlank(message = "性别不能为空")
    @Pattern(regexp = ExcelPatternMsg.SEX,message = ExcelPatternMsg.SEX_MSG)
    private String sex;

    //年龄
    @ExcelProperty(index = 2,value = "年龄")
    @ColumnWidth(8)
    @Pattern(regexp = ExcelPatternMsg.NUMBER,message = ExcelPatternMsg.NUMBER_MSG)
    private String age;


    //生日
    @ExcelProperty(index = 3,value = "生日")
    @ColumnWidth(20)
    @Pattern(regexp = ExcelPatternMsg.DATE2,message = ExcelPatternMsg.DATE2_MSG)
    private String birthday;

    //金额
    @ExcelProperty(index = 4,value = "钱")
    @Pattern(regexp = ExcelPatternMsg.DECIMAL,message = ExcelPatternMsg.DECIMAL_MSG)
    private String money;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age='" + age + '\'' +
                ", birthday='" + birthday + '\'' +
                ", money='" + money + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
