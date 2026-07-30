package com.techstore.ecommercemaven.controller;


import com.techstore.ecommercemaven.model.Refund;
import com.techstore.ecommercemaven.model.Order;
import com.techstore.ecommercemaven.service.RefundService;
import com.techstore.ecommercemaven.service.OrderService;
import com.techstore.ecommercemaven.service.RefundDecisionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/admin")
public class RefundController {


    private final RefundService refundService;

    private final OrderService orderService;


    @Autowired
    private RefundDecisionService refundDecisionService;



    public RefundController(
            RefundService refundService,
            OrderService orderService) {

        this.refundService = refundService;
        this.orderService = orderService;

    }



    // Mark Refund Completed
    @GetMapping("/refund-complete/{id}")
    public String completeRefund(
            @PathVariable Long id) {


        refundService.completeRefund(id);


        return "redirect:/admin/refunds";
    }





    // Create Refund
    @GetMapping("/refund/create/{id}")
    public String createRefund(
            @PathVariable Long id) {


        Order order = orderService.getOrderById(id);



        if(order != null) {


            Refund refund = new Refund();


            refund.setOrderId(order.getId());

            refund.setCustomerName(
                    order.getCustomerName()
            );


            refund.setUserEmail(
                    order.getUserEmail()
            );


            refund.setRefundAmount(
                    order.getTotalAmount()
            );


            refund.setRefundMethod(
                    order.getPaymentMethod()
            );


            refund.setStatus("PENDING");


            refund.setComments(
                    "Refund created for cancelled order"
            );



            // Smart Refund Approval Logic
            refundDecisionService.checkRefund(
                    order,
                    refund
            );



            refundService.createRefund(refund);

        }



        return "redirect:/admin/refunds";

    }

}