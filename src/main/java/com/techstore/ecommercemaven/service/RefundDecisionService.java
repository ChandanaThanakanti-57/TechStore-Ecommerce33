package com.techstore.ecommercemaven.service;

import com.techstore.ecommercemaven.model.Order;
import com.techstore.ecommercemaven.model.Refund;
import org.springframework.stereotype.Service;

@Service
public class RefundDecisionService {

    public Refund checkRefund(Order order, Refund refund){
        int score = 0;
        StringBuilder reason = new StringBuilder();

        // Rule 1: High-value order
        if (order.getTotalAmount() > 5000) {
            score += 40;
            reason.append("High-value order. ");
        }

        // Rule 2: Order must be delivered
        if ("Delivered".equalsIgnoreCase(order.getStatus())) {
            score += 10;
            reason.append("Order delivered. ");
        }

        // Rule 3: Refund decision
        if (score < 50) {
            refund.setApprovalType("AUTO_APPROVED");
        } else {
            refund.setApprovalType("MANUAL_REVIEW");
        }

        refund.setRiskScore(score);
        refund.setRiskReason(reason.toString());

        return refund;
    }
}