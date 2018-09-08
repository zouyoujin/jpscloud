package com.jpscloud.portal.web.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

import javax.validation.constraints.Email;

/**
 * <p>
 * 会员表
 * </p>
 *
 * @author kitty
 * @since 2018-09-08
 */
@TableName("tbl_members")
public class Members implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 会员Id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 会员账号
     */
    private String account;
    /**
     * 会员密码
     */
    private String password;
    /**
     * 会员类型:1:客户 2:商家 3:质管员
     */
    private Integer type;
    /**
     * 昵称
     */
    private String username;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 新房地址
     */
    private String address;
    /**
     * 邮箱
     */
    @Email(message="邮箱格式不正确!")
    private String email;
    /**
     * 意向风格
     */
    @TableField("intentional_style")
    private String intentionalStyle;
    /**
     * 身份证号
     */
    @TableField("card_no")
    private String cardNo;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIntentionalStyle() {
        return intentionalStyle;
    }

    public void setIntentionalStyle(String intentionalStyle) {
        this.intentionalStyle = intentionalStyle;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    @Override
    public String toString() {
        return "Members{" +
        ", id=" + id +
        ", account=" + account +
        ", password=" + password +
        ", type=" + type +
        ", username=" + username +
        ", phone=" + phone +
        ", address=" + address +
        ", email=" + email +
        ", intentionalStyle=" + intentionalStyle +
        ", cardNo=" + cardNo +
        "}";
    }
}
