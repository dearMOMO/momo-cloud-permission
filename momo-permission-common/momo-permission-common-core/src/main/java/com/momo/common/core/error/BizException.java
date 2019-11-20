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
package com.momo.common.core.error;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName: BizException
 * @Author: Jie Li
 * @Date 2019-11-14 16:03
 * @Description: 业务错误
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 **/
@Getter
@Setter
@ToString
@Builder
//@EqualsAndHashCode(of = {"id"})
public class BizException extends RuntimeException {
    private ErrorEnum error = null;
    private String message;

    public BizException() {

    }

    public BizException(String message) {
        this.message = message;
    }

    public BizException(ErrorEnum error) {
        this.error = error;
        this.message = error.getErrorMessage();
    }

    public BizException(ErrorEnum error, String message) {
        this.error = error;
        this.message = message;
    }

    public static BizException fail(String message) {
        return BizException.builder().message(message).error(ErrorEnum.ERROR_BIZ_FAIL).build();
    }

    public static BizException failHandler(String message) {
        return BizException.builder().message(message).error(ErrorEnum.ERROR_BIZ_FAIL_HANDLER).build();
    }

    public ErrorEnum getError() {
        return error;
    }

    public void setError(ErrorEnum error) {
        this.error = error;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
