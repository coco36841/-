package com.cmt.service;

import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.stereotype.Service;

/**
 * ClassName:UserService
 * Description:
 *
 * @Date:2021/10/12 13:42
 * @Author:cmt
 */
@Service
public class UserService {

    @Reference
    TicketService ticketService;

    public void buyTicket(){
        System.out.println("我得到了"+ticketService.getTicket());
    }
}
