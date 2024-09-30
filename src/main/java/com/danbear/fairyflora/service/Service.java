package com.danbear.fairyflora.service;

import java.util.List;

public record Service(
    Integer id,
    String serviceName,
    List<listOfService> serviceList,
    String serviceCode,
    String totalPrice,
    String description

) {
}
