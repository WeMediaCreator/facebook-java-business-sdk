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
import java.util.List;
import java.util.Map;

/**
 * This class is auto-generated.
 *
 * For any issues or feature requests related to this class, please let us know
 * on github and we'll fix in our codegen framework. We'll not be able to accept
 * pull request for this class.
 *
 */
public class ReachFrequencyCurveLowerConfidenceRange extends APINode {
    @SerializedName("impression_lower")
    private List<Long> mImpressionLower = null;
    @SerializedName("num_points")
    private Long mNumPoints = null;
    @SerializedName("reach")
    private List<Long> mReach = null;
    @SerializedName("reach_lower")
    private List<Long> mReachLower = null;
    @SerializedName("uniq_video_views_2s_lower")
    private List<Long> mUniqVideoViews2sLower = null;
    @SerializedName("video_views_2s_lower")
    private List<Long> mVideoViews2sLower = null;
    protected static Gson gson = null;

    public ReachFrequencyCurveLowerConfidenceRange() {
    }

    public String getId() {
        return null;
    }

    public static ReachFrequencyCurveLowerConfidenceRange loadJSON(String json, APIContext context, String header) {
        ReachFrequencyCurveLowerConfidenceRange reachFrequencyCurveLowerConfidenceRange = getGson().fromJson(json, ReachFrequencyCurveLowerConfidenceRange.class);
        if (context.isDebug()) {
            JsonParser parser = new JsonParser();
            JsonElement o1 = parser.parse(json);
            JsonElement o2 = parser.parse(reachFrequencyCurveLowerConfidenceRange.toString());
            if (o1.getAsJsonObject().get("__fb_trace_id__") != null) {
                o2.getAsJsonObject().add("__fb_trace_id__", o1.getAsJsonObject().get("__fb_trace_id__"));
            }
            if (!o1.equals(o2)) {
                context.log("[Warning] When parsing response, object is not consistent with JSON:");
                context.log("[JSON]" + o1);
                context.log("[Object]" + o2);
            }
        }
        reachFrequencyCurveLowerConfidenceRange.context = context;
        reachFrequencyCurveLowerConfidenceRange.rawValue = json;
        reachFrequencyCurveLowerConfidenceRange.header = header;
        return reachFrequencyCurveLowerConfidenceRange;
    }

    public static APINodeList<ReachFrequencyCurveLowerConfidenceRange> parseResponse(String json, APIContext context, APIRequest request, String header) throws MalformedResponseException {
        APINodeList<ReachFrequencyCurveLowerConfidenceRange> reachFrequencyCurveLowerConfidenceRanges = new APINodeList<ReachFrequencyCurveLowerConfidenceRange>(request, json, header);
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
                    reachFrequencyCurveLowerConfidenceRanges.add(loadJSON(arr.get(i).getAsJsonObject().toString(), context, header));
                }
                return reachFrequencyCurveLowerConfidenceRanges;
            } else if (result.isJsonObject()) {
                obj = result.getAsJsonObject();
                if (obj.has("data")) {
                    if (obj.has("paging")) {
                        JsonObject paging = obj.get("paging").getAsJsonObject();
                        if (paging.has("cursors")) {
                            JsonObject cursors = paging.get("cursors").getAsJsonObject();
                            String before = cursors.has("before") ? cursors.get("before").getAsString() : null;
                            String after = cursors.has("after") ? cursors.get("after").getAsString() : null;
                            reachFrequencyCurveLowerConfidenceRanges.setCursors(before, after);
                        }
                        String previous = paging.has("previous") ? paging.get("previous").getAsString() : null;
                        String next = paging.has("next") ? paging.get("next").getAsString() : null;
                        reachFrequencyCurveLowerConfidenceRanges.setPaging(previous, next);
                        if (context.hasAppSecret()) {
                            reachFrequencyCurveLowerConfidenceRanges.setAppSecret(context.getAppSecretProof());
                        }
                    }
                    if (obj.get("data").isJsonArray()) {
                        // Second, check if it's a JSON array with "data"
                        arr = obj.get("data").getAsJsonArray();
                        for (int i = 0; i < arr.size(); i++) {
                            reachFrequencyCurveLowerConfidenceRanges.add(loadJSON(arr.get(i).getAsJsonObject().toString(), context, header));
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
                                    reachFrequencyCurveLowerConfidenceRanges.add(loadJSON(entry.getValue().toString(), context, header));
                                }
                                break;
                            }
                        }
                        if (!isRedownload) {
                            reachFrequencyCurveLowerConfidenceRanges.add(loadJSON(obj.toString(), context, header));
                        }
                    }
                    return reachFrequencyCurveLowerConfidenceRanges;
                } else if (obj.has("images")) {
                    // Fourth, check if it's a map of image objects
                    obj = obj.get("images").getAsJsonObject();
                    for (Map.Entry<String, JsonElement> entry : obj.entrySet()) {
                        reachFrequencyCurveLowerConfidenceRanges.add(loadJSON(entry.getValue().toString(), context, header));
                    }
                    return reachFrequencyCurveLowerConfidenceRanges;
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
                            reachFrequencyCurveLowerConfidenceRanges.add(loadJSON(value.toString(), context, header));
                        } else {
                            isIdIndexedArray = false;
                            break;
                        }
                    }
                    if (isIdIndexedArray) {
                        return reachFrequencyCurveLowerConfidenceRanges;
                    }

                    // Sixth, check if it's pure JsonObject
                    reachFrequencyCurveLowerConfidenceRanges.clear();
                    reachFrequencyCurveLowerConfidenceRanges.add(loadJSON(json, context, header));
                    return reachFrequencyCurveLowerConfidenceRanges;
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


    public List<Long> getFieldImpressionLower() {
        return mImpressionLower;
    }

    public ReachFrequencyCurveLowerConfidenceRange setFieldImpressionLower(List<Long> value) {
        this.mImpressionLower = value;
        return this;
    }

    public Long getFieldNumPoints() {
        return mNumPoints;
    }

    public ReachFrequencyCurveLowerConfidenceRange setFieldNumPoints(Long value) {
        this.mNumPoints = value;
        return this;
    }

    public List<Long> getFieldReach() {
        return mReach;
    }

    public ReachFrequencyCurveLowerConfidenceRange setFieldReach(List<Long> value) {
        this.mReach = value;
        return this;
    }

    public List<Long> getFieldReachLower() {
        return mReachLower;
    }

    public ReachFrequencyCurveLowerConfidenceRange setFieldReachLower(List<Long> value) {
        this.mReachLower = value;
        return this;
    }

    public List<Long> getFieldUniqVideoViews2sLower() {
        return mUniqVideoViews2sLower;
    }

    public ReachFrequencyCurveLowerConfidenceRange setFieldUniqVideoViews2sLower(List<Long> value) {
        this.mUniqVideoViews2sLower = value;
        return this;
    }

    public List<Long> getFieldVideoViews2sLower() {
        return mVideoViews2sLower;
    }

    public ReachFrequencyCurveLowerConfidenceRange setFieldVideoViews2sLower(List<Long> value) {
        this.mVideoViews2sLower = value;
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

    public ReachFrequencyCurveLowerConfidenceRange copyFrom(ReachFrequencyCurveLowerConfidenceRange instance) {
        this.mImpressionLower = instance.mImpressionLower;
        this.mNumPoints = instance.mNumPoints;
        this.mReach = instance.mReach;
        this.mReachLower = instance.mReachLower;
        this.mUniqVideoViews2sLower = instance.mUniqVideoViews2sLower;
        this.mVideoViews2sLower = instance.mVideoViews2sLower;
        this.context = instance.context;
        this.rawValue = instance.rawValue;
        return this;
    }

    public static APIRequest.ResponseParser<ReachFrequencyCurveLowerConfidenceRange> getParser() {
        return new APIRequest.ResponseParser<ReachFrequencyCurveLowerConfidenceRange>() {
            public APINodeList<ReachFrequencyCurveLowerConfidenceRange> parseResponse(String response, APIContext context, APIRequest<ReachFrequencyCurveLowerConfidenceRange> request, String header) throws MalformedResponseException {
                return ReachFrequencyCurveLowerConfidenceRange.parseResponse(response, context, request, header);
            }
        };
    }
}
