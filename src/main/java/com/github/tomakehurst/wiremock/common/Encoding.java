/*
 * Copyright (C) 2011 Thomas Akehurst
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.tomakehurst.wiremock.common;

public class Encoding {

    private static Base64Encoder encoder = null;

    private static Base64Encoder getInstance() {
        if (encoder == null) {
            try {
                Class.forName("javax.xml.bind.DatatypeConverter");
                encoder = new DatatypeConverterBase64Encoder();
            } catch (ClassNotFoundException e) {
                encoder = new GuavaBase64Encoder();
            }
        }

        return encoder;
    }

    public static byte[] decodeBase64(String base64) {
        return base64 != null ?
               getInstance().decode(base64) :
               null;
    }

    public static String encodeBase64(byte[] content) {
        return content != null ?
               getInstance().encode(content) :
               null;
    }
}
