package com.nurseathome.bid.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@Accessors(chain = true)
@FieldDefaults(level = PRIVATE)
public class TokenRequest {

    @JsonProperty("grant_type")
    String grantType;

    @JsonProperty("redirect_uri")
    String redirectUri;
}
