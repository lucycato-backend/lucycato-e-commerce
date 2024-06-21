package org.lucycato.ordercommandservice.adapter.in.web.request;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.lucycato.ordercommandservice.application.port.in.command.coupon.component.*;

import java.io.IOException;

public class CouponComponentDeserializer extends JsonDeserializer<CouponComponentCommand> {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public CouponComponentCommand deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        String type = node.get("type").asText();

        if ("CategoryComponent".equals(type)) {
            return mapper.treeToValue(node, CategoryComponentCommand.class);
        } else if ("DiscountFixedComponent".equals(type)) {
            return mapper.treeToValue(node, DiscountFixedComponentCommand.class);
        } else if ("DiscountRateComponent".equals(type)) {
            return mapper.treeToValue(node, DiscountRateComponentCommand.class);
        }

        throw new IllegalArgumentException("Unknown component type: " + type);
    }
}
