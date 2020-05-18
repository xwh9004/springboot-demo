package com.example.web.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p><b>Description:</b>
 * TODO
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 9:59 on 2020/5/18
 * @version V0.1
 * @classNmae User
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String userName;

    private String password;

    private String roles;
}
