package com.example.common.entity;

import lombok.Data;

/**
 * <p><b>Description:</b>
 * TODO
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 16:06 on 2020/5/29
 * @version V0.1
 * @classNmae Organization
 */
@Data
public class Organization {
    private String name;

    private String contactName;

    private String contactEmail;

    private String contactPhone;

    public Organization withName (String name){
        this.name = name;
        return this;
    }
    public Organization withContactName (String contactName){
        this.contactName = contactName;
        return this;
    }
    public Organization withContactEmail (String contactEmail){
        this.contactEmail = contactEmail;
        return this;
    }
    public Organization withContactPhone (String contactPhone){
        this.contactPhone = contactPhone;
        return this;
    }
}
