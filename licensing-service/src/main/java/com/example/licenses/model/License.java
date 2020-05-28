package com.example.licenses.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * <p><b>Description:</b>
 * TODO
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 16:44 on 2020/5/27
 * @version V0.1
 * @classNmae License
 */
@Entity
@Data
@Table(name = "licenses")
public class License implements Serializable {

    @Id
    @Column(name = "license_id",nullable = false)
    private String licenseId;

    @Column(name = "license_type",nullable = false)
    private String licenseType;

    @Column(name = "product_name",nullable = false)
    private String productName;
    @Column(name = "organization_id",nullable = false)
    private String organizationId;
    @Column(name = "comment",nullable = false)
    private String comment;

    public License withLicenseId(String licenseId){
        this.licenseId = licenseId;
        return this;
    }
    public License withLicenseType(String licenseType){
        this.licenseType = licenseType;
        return this;
    }
    public License withProductName(String productName){
        this.productName = productName;
        return this;
    }

    public License withComment(String comment){
        this.comment = comment;
        return this;
    }

    public License withOrganization(String organizationId){
        this.organizationId = organizationId;
        return this;
    }
}
