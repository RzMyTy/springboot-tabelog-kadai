package com.example.nagoyameshi.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class StripeService {
	 @Value("${stripe.api-key}")
     private String stripeApiKey;
	
	public String careateStripeSession(HttpServletRequest httpServletRequest) {
    Stripe.apiKey = stripeApiKey;
    String requestUrl = new String(httpServletRequest.getRequestURL());
    String priceId = "{price_1OiVjnIhYmnFrNDSUfqsSy0g}";
        
    SessionCreateParams params =
    		  SessionCreateParams.builder()
    		    .setMode(SessionCreateParams.Mode.SUBSCRIPTION)
    		    .addLineItem(
    		      SessionCreateParams.LineItem.builder()
    		        .setPrice("price_1OiVjnIhYmnFrNDSUfqsSy0g")
    		        .setQuantity(1L)
    		        .build()
    		    )
    		    .setUiMode(SessionCreateParams.UiMode.EMBEDDED)
    		    .setReturnUrl("http://localhost:8080//return?session_id={CHECKOUT_SESSION_ID}")
    		    .build();

    try {
        Session session = Session.create(params);
        return session.getId();
    } catch (StripeException e) {
        e.printStackTrace();
        return "";
    }
  }
}
