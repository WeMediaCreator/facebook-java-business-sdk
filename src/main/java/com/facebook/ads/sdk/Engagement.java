/**
 * Copyright (c) 2015-present, Facebook, Inc. All rights reserved.
 * <p>
 * You are hereby granted a non-exclusive, worldwide, royalty-free license to
 * use, copy, modify, and distribute this software in source code or binary
 * form for use in connection with the web services and APIs provided by
 * Facebook.
 * <p>
 * As with any software that integrates with the Facebook platform, your use
 * of this software is subject to the Facebook Developer Principles and
 * Policies [http://developers.facebook.com/policy/]. This copyright notice
 * shall be included in all copies or substantial portions of the software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 */

package com.facebook.ads.sdk;

import com.facebook.ads.sdk.APIException.MalformedResponseException;
import com.google.gson.*;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Modifier;
import java.util.Map;

/**
 * This class is auto-generated.
 *
 * For any issues or feature requests related to this class, please let us know
 * on github and we'll fix in our codegen framework. We'll not be able to accept
 * pull request for this class.
 *
 */
public class Engagement extends APINode {
    @SerializedName("count")
    private Long mCount = null;
    @SerializedName("count_string")
    private String mCountString = null;
    @SerializedName("count_string_with_like")
    private String mCountStringWithLike = null;
    @SerializedName("count_string_without_like")
    private String mCountStringWithoutLike = null;
    @SerializedName("social_sentence")
    private String mSocialSentence = null;
    @SerializedName("social_sentence_with_like")
    private String mSocialSentenceWithLike = null;
    @SerializedName("social_sentence_without_like")
    private String mSocialSentenceWithoutLike = null;
    protected static Gson gson = null;

    public Engagement() {
    }

    public String getId() {
        return null;
    }

    public static Engagement loadJSON(String json, APIContext context, String header) {
        Engagement engagement = getGson().fromJson(json, Engagement.class);
        if (context.isDebug()) {
            JsonParser parser = new JsonParser();
            JsonElement o1 = parser.parse(json);
            JsonElement o2 = parser.parse(engagement.toString());
            if (o1.getAsJsonObject().get("__fb_trace_id__") != null) {
                o2.getAsJsonObject().add("__fb_trace_id__", o1.getAsJsonObject().get("__fb_trace_id__"));
            }
            if (!o1.equals(o2)) {
                context.log("[Warning] When parsing response, object is not consistent with JSON:");
                context.log("[JSON]" + o1);
                context.log("[Object]" + o2);
            }
        }
        engagement.context = context;
        engagement.rawValue = json;
        engagement.header = header;
        return engagement;
    }

    public static APINodeList<Engagement> parseResponse(String json, APIContext context, APIRequest request, String header) throws MalformedResponseException {
        APINodeList<Engagement> engagements = new APINodeList<Engagement>(request, json, header);
        JsonArray arr;
        JsonObject obj;
        JsonParser parser = new JsonParser();
        Exception exception = null;
        try {
            JsonElement result = parser.parse(json);
            if (result.isJsonArray()) {
                // First, check if it's a pure JSON Array
                arr = result.getAsJsonArray();
                for (int i = 0; i < arr.size(); i++) {
                    engagements.add(loadJSON(arr.get(i).getAsJsonObject().toString(), context, header));
                }
                return engagements;
            } else if (result.isJsonObject()) {
                obj = result.getAsJsonObject();
                if (obj.has("data")) {
                    if (obj.has("paging")) {
                        JsonObject paging = obj.get("paging").getAsJsonObject();
                        if (paging.has("cursors")) {
                            JsonObject cursors = paging.get("cursors").getAsJsonObject();
                            String before = cursors.has("before") ? cursors.get("before").getAsString() : null;
                            String after = cursors.has("after") ? cursors.get("after").getAsString() : null;
                            engagements.setCursors(before, after);
                        }
                        String previous = paging.has("previous") ? paging.get("previous").getAsString() : null;
                        String next = paging.has("next") ? paging.get("next").getAsString() : null;
                        engagements.setPaging(previous, next);
                        if (context.hasAppSecret()) {
                            engagements.setAppSecret(context.getAppSecretProof());
                        }
                    }
                    if (obj.get("data").isJsonArray()) {
                        // Second, check if it's a JSON array with "data"
                        arr = obj.get("data").getAsJsonArray();
                        for (int i = 0; i < arr.size(); i++) {
                            engagements.add(loadJSON(arr.get(i).getAsJsonObject().toString(), context, header));
                        }
                    } else if (obj.get("data").isJsonObject()) {
                        // Third, check if it's a JSON object with "data"
                        obj = obj.get("data").getAsJsonObject();
                        boolean isRedownload = false;
                        for (String s : new String[]{"campaigns", "adsets", "ads"}) {
                            if (obj.has(s)) {
                                isRedownload = true;
                                obj = obj.getAsJsonObject(s);
                                for (Map.Entry<String, JsonElement> entry : obj.entrySet()) {
                                    engagements.add(loadJSON(entry.getValue().toString(), context, header));
                                }
                                break;
                            }
                        }
                        if (!isRedownload) {
                            engagements.add(loadJSON(obj.toString(), context, header));
                        }
                    }
                    return engagements;
                } else if (obj.has("images")) {
                    // Fourth, check if it's a map of image objects
                    obj = obj.get("images").getAsJsonObject();
                    for (Map.Entry<String, JsonElement> entry : obj.entrySet()) {
                        engagements.add(loadJSON(entry.getValue().toString(), context, header));
                    }
                    return engagements;
                } else {
                    // Fifth, check if it's an array of objects indexed by id
                    boolean isIdIndexedArray = true;
                    for (Map.Entry entry : obj.entrySet()) {
                        String key = (String) entry.getKey();
                        if (key.equals("__fb_trace_id__")) {
                            continue;
                        }
                        JsonElement value = (JsonElement) entry.getValue();
                        if (
                                value != null &&
                                        value.isJsonObject() &&
                                        value.getAsJsonObject().has("id") &&
                                        value.getAsJsonObject().get("id") != null &&
                                        value.getAsJsonObject().get("id").getAsString().equals(key)
                        ) {
                            engagements.add(loadJSON(value.toString(), context, header));
                        } else {
                            isIdIndexedArray = false;
                            break;
                        }
                    }
                    if (isIdIndexedArray) {
                        return engagements;
                    }

                    // Sixth, check if it's pure JsonObject
                    engagements.clear();
                    engagements.add(loadJSON(json, context, header));
                    return engagements;
                }
            }
        } catch (Exception e) {
            exception = e;
        }
        throw new MalformedResponseException(
                "Invalid response string: " + json,
                exception
        );
    }

    @Override
    public APIContext getContext() {
        return context;
    }

    @Override
    public void setContext(APIContext context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return getGson().toJson(this);
    }


    public Long getFieldCount() {
        return mCount;
    }

    public Engagement setFieldCount(Long value) {
        this.mCount = value;
        return this;
    }

    public String getFieldCountString() {
        return mCountString;
    }

    public Engagement setFieldCountString(String value) {
        this.mCountString = value;
        return this;
    }

    public String getFieldCountStringWithLike() {
        return mCountStringWithLike;
    }

    public Engagement setFieldCountStringWithLike(String value) {
        this.mCountStringWithLike = value;
        return this;
    }

    public String getFieldCountStringWithoutLike() {
        return mCountStringWithoutLike;
    }

    public Engagement setFieldCountStringWithoutLike(String value) {
        this.mCountStringWithoutLike = value;
        return this;
    }

    public String getFieldSocialSentence() {
        return mSocialSentence;
    }

    public Engagement setFieldSocialSentence(String value) {
        this.mSocialSentence = value;
        return this;
    }

    public String getFieldSocialSentenceWithLike() {
        return mSocialSentenceWithLike;
    }

    public Engagement setFieldSocialSentenceWithLike(String value) {
        this.mSocialSentenceWithLike = value;
        return this;
    }

    public String getFieldSocialSentenceWithoutLike() {
        return mSocialSentenceWithoutLike;
    }

    public Engagement setFieldSocialSentenceWithoutLike(String value) {
        this.mSocialSentenceWithoutLike = value;
        return this;
    }


    synchronized /*package*/ static Gson getGson() {
        if (gson != null) {
            return gson;
        } else {
            gson = new GsonBuilder()
                    .excludeFieldsWithModifiers(Modifier.STATIC)
                    .excludeFieldsWithModifiers(Modifier.PROTECTED)
                    .disableHtmlEscaping()
                    .create();
        }
        return gson;
    }

    public Engagement copyFrom(Engagement instance) {
        this.mCount = instance.mCount;
        this.mCountString = instance.mCountString;
        this.mCountStringWithLike = instance.mCountStringWithLike;
        this.mCountStringWithoutLike = instance.mCountStringWithoutLike;
        this.mSocialSentence = instance.mSocialSentence;
        this.mSocialSentenceWithLike = instance.mSocialSentenceWithLike;
        this.mSocialSentenceWithoutLike = instance.mSocialSentenceWithoutLike;
        this.context = instance.context;
        this.rawValue = instance.rawValue;
        return this;
    }

    public static APIRequest.ResponseParser<Engagement> getParser() {
        return new APIRequest.ResponseParser<Engagement>() {
            public APINodeList<Engagement> parseResponse(String response, APIContext context, APIRequest<Engagement> request, String header) throws MalformedResponseException {
                return Engagement.parseResponse(response, context, request, header);
            }
        };
    }
}
