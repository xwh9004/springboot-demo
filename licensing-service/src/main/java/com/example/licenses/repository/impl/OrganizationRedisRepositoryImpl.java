package com.example.licenses.repository.impl;

import com.example.common.entity.Organization;
import com.example.licenses.repository.OrganizationRedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

/**
 * <p><b>Description:</b>
 * TODO
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 15:38 on 2020/6/11
 * @version V0.1
 * @classNmae OrganizationRedisRepositoryImpl
 */

@Repository
public class OrganizationRedisRepositoryImpl implements OrganizationRedisRepository {

    private static final String HASH_NAME = "organization";

    private HashOperations hashOperations;
    @Autowired
    private RedisTemplate redisTemplate;

    @PostConstruct
    private void init(){
        hashOperations = redisTemplate.opsForHash();
    }
    @Override
    public void saveOrganization(Organization organization) {
        hashOperations.put(HASH_NAME,organization.getOrganizationId(),organization);
    }

    @Override
    public void updateOrganization(Organization organization) {
        hashOperations.put(HASH_NAME,organization.getOrganizationId(),organization);
    }

    @Override
    public void deleteOrganization(String organizationId) {
        hashOperations.delete(HASH_NAME,organizationId);
    }

    @Override
    public Organization findOrganization(String organizationId) {
        return (Organization)hashOperations.get(HASH_NAME,organizationId);
    }
}
