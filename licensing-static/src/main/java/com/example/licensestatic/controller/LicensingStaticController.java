package com.example.licensestatic.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p><b>Description:</b>
 * TODO
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 13:22 on 2020/6/5
 * @version V0.1
 * @classNmae LicensingStaticController
 */
@Slf4j
@RequestMapping( value = "/static/organizations/{organizationId}/licenses" )
@RestController
public class LicensingStaticController {

    @RequestMapping()
    public String getLicensing(){
//        log.info("LicensingStaticController CorrelationId ={} ", UserContextHolder.getContext().getCorrelationId());


        return "hello test";
    }

}
