package com.cmt.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * ClassName:User
 * Description:
 *
 * @Date:2021/10/10 8:52
 * @Author:cmt
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class User {

    private String name;
    private int age;
}
