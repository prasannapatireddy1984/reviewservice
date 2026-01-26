package com.rentacostume.reviews.clients;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
//import org.springframework.web.reactive.function.client.WebClient;

@Component
public class OrderClient {
   // private final WebClient webClient;
    private final boolean enabled;

    public OrderClient(@Value("${reviews.verifyPurchase.enabled}") boolean enabled) {
        System.out.println("inside orderClient constructor");
        this.enabled = enabled;
    }

 /*   public OrderClient(@Value("${reviews.orderService.baseUrl}") String baseUrl,
                       @Value("${reviews.verifyPurchase.enabled}") boolean enabled) {
      //  this.webClient = WebClient.builder().baseUrl(baseUrl).build();
        this.enabled = enabled;
    }*/

    public boolean hasPurchased(String userId, String productId) {
        System.out.println("inside orderClient hasPurchased method");

        if (!enabled) return false; // feature flag off -> treat as not verified
        // You can replace this with a dedicated endpoint later.
        // For now, returns false to avoid coupling your current OrderService.
        try {
            // Example (future): GET /api/orders/verify?userId=&productId=
           /* Boolean ok = webClient.get()
                    .uri(uri -> uri.path("/api/orders/verify")
                            .queryParam("userId", userId)
                            .queryParam("productId", productId).build())
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();
            return ok != null && ok;*/
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
