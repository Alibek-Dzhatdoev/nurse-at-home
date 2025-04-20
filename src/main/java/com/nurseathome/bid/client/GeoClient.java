package com.nurseathome.bid.client;

import com.nurseathome.bid.client.fallback.OauthClientFallback;
import com.nurseathome.bid.model.response.LocationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "geo", path = "/geo/api/v1", fallbackFactory = OauthClientFallback.class)
public interface GeoClient {

    @GetMapping("/location")
    LocationResponse getLocation(@RequestParam String geoUrl);
}
