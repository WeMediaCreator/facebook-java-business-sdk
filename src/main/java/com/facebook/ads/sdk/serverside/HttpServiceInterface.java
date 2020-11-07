package com.facebook.ads.sdk.serverside;

import java.util.Map;

public interface HttpServiceInterface {
    EventResponse executeRequest(
            String url,
            HttpMethodEnum httpMethod,
            Map<String, String> headers,
            HttpServiceParams params
    );
}
