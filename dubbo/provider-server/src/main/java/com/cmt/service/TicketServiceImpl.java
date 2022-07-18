package com.cmt.service;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

/**
 * ClassName:TicketServiceImpl
 * Description:
 *
 * @Date:2021/10/12 13:39
 * @Author:cmt
 */
@Component
@Service
public class TicketServiceImpl implements TicketService{
    @Override
    public String getTicket() {
        return "cmt的Ticket";
    }
}
