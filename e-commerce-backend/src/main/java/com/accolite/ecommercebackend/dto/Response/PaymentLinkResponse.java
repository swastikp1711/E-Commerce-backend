package com.accolite.ecommercebackend.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class PaymentLinkResponse {
	private String payment_link_url;
	private String payment_link_id;
}
