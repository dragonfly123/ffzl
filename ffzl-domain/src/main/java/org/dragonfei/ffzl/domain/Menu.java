package org.dragonfei.ffzl.domain;


import org.dragonfei.ffzl.annotation.domain.Column;
import org.dragonfei.ffzl.annotation.domain.Foreign;
import org.dragonfei.ffzl.annotation.domain.Table;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Calendar;

/**
 * Created by longfei on 16-4-9.
 */
@Table(value = "T_MENU",desc = "菜单")
public class Menu implements Serializable {

    @Column(value="ID",pk = true, desc = "ID",required = true, unique = true)
    private BigInteger id;

    @Column(value = "TEXT",unique = true,required = true,desc = "菜单",minLength = 2,maxLength = 8)
    private String text;

    @Column(value = "ADDR",desc = "URL")
    private String addr;

    @Foreign(clazz = Menu.class)
    @Column(value = "PID",desc = "父级菜单")
    private BigInteger pid;

    @Column(value = "CREATETIME",desc="创建时间")
    private Calendar createTime;

    @Column(value = "LASTMODIFYTIME",desc="最后修改时间")
    private Calendar lastModifyTime;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public BigInteger getPid() {
        return pid;
    }

    public void setPid(BigInteger pid) {
        this.pid = pid;
    }

    public Calendar getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Calendar createTime) {
        this.createTime = createTime;
    }

    public Calendar getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Calendar lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }
}
