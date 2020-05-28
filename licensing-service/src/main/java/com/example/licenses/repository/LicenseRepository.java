package com.example.licenses.repository;

import com.example.licenses.model.License;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p><b>Description:</b>
 * TODO
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 16:34 on 2020/5/28
 * @version V0.1
 * @classNmae LicenseRepository
 */
@Repository
public interface LicenseRepository extends CrudRepository<License,String>{

    List<License> findByOrganizationId(String organizationId);

    License findByOrganizationIdAndLicenseId(String organizationId,String licenseId);
}
