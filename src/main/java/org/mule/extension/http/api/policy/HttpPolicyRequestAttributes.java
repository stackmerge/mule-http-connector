/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.extension.http.api.policy;

import static java.lang.System.lineSeparator;
import static org.mule.runtime.api.util.MultiMap.emptyMultiMap;
import static org.mule.runtime.http.api.domain.CaseInsensitiveMultiMap.emptyCaseInsensitiveMultiMap;

import org.mule.extension.http.api.BaseHttpRequestAttributes;
import org.mule.runtime.api.util.MultiMap;
import org.mule.runtime.http.api.domain.AbstractCaseInsensitiveMultiMap;
import org.mule.runtime.http.api.domain.CaseInsensitiveMultiMap;

import java.util.Map;

/**
 * {@link BaseHttpRequestAttributes} subclass that allows modification of request attributes and
 * creation through the expression language.
 *
 * @since 1.0
 */
public class HttpPolicyRequestAttributes extends BaseHttpRequestAttributes {

  private static final long serialVersionUID = 7856582596752161400L;

  public HttpPolicyRequestAttributes(AbstractCaseInsensitiveMultiMap headers, MultiMap<String, String> queryParams,
                                     Map<String, String> uriParams, String requestPath) {
    super(headers, queryParams, uriParams, requestPath);
  }

  public HttpPolicyRequestAttributes() {
    super(emptyCaseInsensitiveMultiMap(), emptyMultiMap(), emptyMultiMap(), "");
  }

  public void setHeaders(CaseInsensitiveMultiMap headers) {
    this.headers = headers;
  }

  public void setQueryParams(MultiMap<String, String> queryParams) {
    this.queryParams = queryParams;
  }

  public void setUriParams(Map<String, String> uriParams) {
    this.uriParams = uriParams;
  }

  @Override
  public String toString() {

    StringBuilder builder = new StringBuilder();
    builder.append(this.getClass().getName()).append(lineSeparator()).append("{")
        .append(lineSeparator()).append(TAB).append("Request path=").append(requestPath)
        .append(lineSeparator());

    buildMapToString(headers, "Headers", headers.entryList().stream(), builder);
    buildMapToString(queryParams, "Query Parameters", queryParams.entryList().stream(), builder);
    buildMapToString(uriParams, "URI Parameters", uriParams.entrySet().stream(), builder);
    builder.append("}");

    return builder.toString();
  }
}
