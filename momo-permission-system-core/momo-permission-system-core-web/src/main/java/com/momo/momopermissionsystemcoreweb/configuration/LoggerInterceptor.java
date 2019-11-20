/**
 * Copyright (c) 2018-2019, Jie Li 李杰 (mqgnsds@163.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.momo.momopermissionsystemcoreweb.configuration;

import io.micrometer.core.lang.NonNull;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import okio.Buffer;
import okio.BufferedSource;

import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.charset.UnsupportedCharsetException;

/**
 * @ClassName: LoggerInterceptor
 * @Author: Jie Li
 * @Date 2019-10-25 16:50
 * @Description: TODO
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 **/
@Slf4j
public class LoggerInterceptor implements Interceptor {
    private static final Charset UTF8 = StandardCharsets.UTF_8;

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);

        ResponseBody responseBody = response.body();
        long contentLength = responseBody.contentLength();

        if (!bodyEncoded(response.headers())) {
            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE); // Buffer the entire body.
            Buffer buffer = source.buffer();

            Charset charset = UTF8;
            MediaType contentType = responseBody.contentType();
            if (contentType != null) {
                try {
                    charset = contentType.charset(UTF8);
                } catch (UnsupportedCharsetException e) {
                    return response;
                }
            }

            if (!isPlaintext(buffer)) {
                return response;
            }

            if (contentLength != 0) {
                String result = buffer.clone().readString(charset);
                log.info(" response.url():" + response.request().url());
                log.info(" response.body():" + result);
                //得到所需的string，开始判断是否异常
                //***********************do something*****************************

            }

        }
        return response;


//        // 拦截请求，获取到该次请求的request
//        Request request = chain.request();
//        // 执行本次网络请求操作，返回response信息
//        Response response = chain.proceed(request);
//        for (String key : request.headers().toMultimap().keySet()) {
//            log.info("zp_test header: {" + key + " : " + request.headers().toMultimap().get(key) + "}");
//        }
//        log.info("zp_test url: {}" , request.url().uri().toString());
//        ResponseBody responseBody = response.body();
//        if (HttpHeaders.hasBody(response) && responseBody != null) {
//            BufferedReader bufferedReader = new BufferedReader(new
//                    InputStreamReader(responseBody.byteStream(), StandardCharsets.UTF_8));
//            String result;
//            while ((result = bufferedReader.readLine()) != null) {
//                log.info("zp_testresponse: {}" , result);
//            }
//        }
//        // 注意，这样写，等于重新创建Request，获取新的Response，避免在执行以上代码时，
//        // 调用了responseBody.string()而不能在返回体中再次调用。
//        return response.newBuilder().body(responseBody).build();
    }

    private boolean bodyEncoded(Headers headers) {
        String contentEncoding = headers.get("Content-Encoding");
        return contentEncoding != null && !contentEncoding.equalsIgnoreCase("identity");
    }

    static boolean isPlaintext(Buffer buffer) throws EOFException {
        try {
            Buffer prefix = new Buffer();
            long byteCount = buffer.size() < 64 ? buffer.size() : 64;
            buffer.copyTo(prefix, 0, byteCount);
            for (int i = 0; i < 16; i++) {
                if (prefix.exhausted()) {
                    break;
                }
                int codePoint = prefix.readUtf8CodePoint();
                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException e) {
            return false; // Truncated UTF-8 sequence.
        }
    }
}
