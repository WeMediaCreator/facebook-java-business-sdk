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
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
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
public class PageCategory extends APINode {
    @SerializedName("api_enum")
    private String mApiEnum = null;
    @SerializedName("fb_page_categories")
    private List<PageCategory> mFbPageCategories = null;
    @SerializedName("id")
    private String mId = null;
    @SerializedName("name")
    private String mName = null;
    protected static Gson gson = null;

    public PageCategory() {
    }

    public String getId() {
        return getFieldId();
    }

    public static PageCategory loadJSON(String json, APIContext context, String header) {
        PageCategory pageCategory = getGson().fromJson(json, PageCategory.class);
        if (context.isDebug()) {
            JsonParser parser = new JsonParser();
            JsonElement o1 = parser.parse(json);
            JsonElement o2 = parser.parse(pageCategory.toString());
            if (o1.getAsJsonObject().get("__fb_trace_id__") != null) {
                o2.getAsJsonObject().add("__fb_trace_id__", o1.getAsJsonObject().get("__fb_trace_id__"));
            }
            if (!o1.equals(o2)) {
                context.log("[Warning] When parsing response, object is not consistent with JSON:");
                context.log("[JSON]" + o1);
                context.log("[Object]" + o2);
            }
        }
        pageCategory.context = context;
        pageCategory.rawValue = json;
        pageCategory.header = header;
        return pageCategory;
    }

    public static APINodeList<PageCategory> parseResponse(String json, APIContext context, APIRequest request, String header) throws MalformedResponseException {
        APINodeList<PageCategory> pageCategorys = new APINodeList<PageCategory>(request, json, header);
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
                    pageCategorys.add(loadJSON(arr.get(i).getAsJsonObject().toString(), context, header));
                }
                return pageCategorys;
            } else if (result.isJsonObject()) {
                obj = result.getAsJsonObject();
                if (obj.has("data")) {
                    if (obj.has("paging")) {
                        JsonObject paging = obj.get("paging").getAsJsonObject();
                        if (paging.has("cursors")) {
                            JsonObject cursors = paging.get("cursors").getAsJsonObject();
                            String before = cursors.has("before") ? cursors.get("before").getAsString() : null;
                            String after = cursors.has("after") ? cursors.get("after").getAsString() : null;
                            pageCategorys.setCursors(before, after);
                        }
                        String previous = paging.has("previous") ? paging.get("previous").getAsString() : null;
                        String next = paging.has("next") ? paging.get("next").getAsString() : null;
                        pageCategorys.setPaging(previous, next);
                        if (context.hasAppSecret()) {
                            pageCategorys.setAppSecret(context.getAppSecretProof());
                        }
                    }
                    if (obj.get("data").isJsonArray()) {
                        // Second, check if it's a JSON array with "data"
                        arr = obj.get("data").getAsJsonArray();
                        for (int i = 0; i < arr.size(); i++) {
                            pageCategorys.add(loadJSON(arr.get(i).getAsJsonObject().toString(), context, header));
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
                                    pageCategorys.add(loadJSON(entry.getValue().toString(), context, header));
                                }
                                break;
                            }
                        }
                        if (!isRedownload) {
                            pageCategorys.add(loadJSON(obj.toString(), context, header));
                        }
                    }
                    return pageCategorys;
                } else if (obj.has("images")) {
                    // Fourth, check if it's a map of image objects
                    obj = obj.get("images").getAsJsonObject();
                    for (Map.Entry<String, JsonElement> entry : obj.entrySet()) {
                        pageCategorys.add(loadJSON(entry.getValue().toString(), context, header));
                    }
                    return pageCategorys;
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
                            pageCategorys.add(loadJSON(value.toString(), context, header));
                        } else {
                            isIdIndexedArray = false;
                            break;
                        }
                    }
                    if (isIdIndexedArray) {
                        return pageCategorys;
                    }

                    // Sixth, check if it's pure JsonObject
                    pageCategorys.clear();
                    pageCategorys.add(loadJSON(json, context, header));
                    return pageCategorys;
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


    public String getFieldApiEnum() {
        return mApiEnum;
    }

    public PageCategory setFieldApiEnum(String value) {
        this.mApiEnum = value;
        return this;
    }

    public List<PageCategory> getFieldFbPageCategories() {
        return mFbPageCategories;
    }

    public PageCategory setFieldFbPageCategories(List<PageCategory> value) {
        this.mFbPageCategories = value;
        return this;
    }

    public PageCategory setFieldFbPageCategories(String value) {
        Type type = new TypeToken<List<PageCategory>>() {
        }.getType();
        this.mFbPageCategories = PageCategory.getGson().fromJson(value, type);
        return this;
    }

    public String getFieldId() {
        return mId;
    }

    public PageCategory setFieldId(String value) {
        this.mId = value;
        return this;
    }

    public String getFieldName() {
        return mName;
    }

    public PageCategory setFieldName(String value) {
        this.mName = value;
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

    public PageCategory copyFrom(PageCategory instance) {
        this.mApiEnum = instance.mApiEnum;
        this.mFbPageCategories = instance.mFbPageCategories;
        this.mId = instance.mId;
        this.mName = instance.mName;
        this.context = instance.context;
        this.rawValue = instance.rawValue;
        return this;
    }

    public static APIRequest.ResponseParser<PageCategory> getParser() {
        return new APIRequest.ResponseParser<PageCategory>() {
            public APINodeList<PageCategory> parseResponse(String response, APIContext context, APIRequest<PageCategory> request, String header) throws MalformedResponseException {
                return PageCategory.parseResponse(response, context, request, header);
            }
        };
    }
}
