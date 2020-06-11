package com.example.common.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author created by Jesse Hsu at 16:06 on 2020/5/29
 * @version V0.1
 * @classNmae Organization
 */
@Data
@Entity
@Table(name = "organizations")
public class Organization implements Serializable {

    @Id
    @Column(name = "org_id",nullable = false)
    private String organizationId;
    @Column(name = "name",nullable = false)
    private String name;
    @Column(name = "contact_name",nullable = false)
    private String contactName;
    @Column(name = "contact_email",nullable = false)
    private String contactEmail;
    @Column(name = "contact_phone",nullable = false)
    private String contactPhone;

    public Organization withId (String organizationId){
        this.organizationId = organizationId;
        return this;
    }
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
