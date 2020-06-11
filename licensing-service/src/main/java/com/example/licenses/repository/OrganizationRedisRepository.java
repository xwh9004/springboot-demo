package com.example.licenses.repository;

import com.example.common.entity.Organization;

/**
 * <p><b>Description:</b>
 * TODO
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 15:36 on 2020/6/11
 * @version V0.1
 * @classNmae OrganizationRedisRepository
 */

public interface OrganizationRedisRepository {
    void saveOrganization(Organization organization);
    void updateOrganization(Organization organization);
    void deleteOrganization(String organizationId);
    Organization findOrganization(String organizationId);
}
