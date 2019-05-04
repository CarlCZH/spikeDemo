package com.hui.miaosha.exception;

import com.hui.miaosha.result.CodeMsg;

/**
 * @Author: CarlChen
 * @Despriction: 全局异常
 * @Date: Create in 22:43 2019\4\13 0013
 */
public class GobalException extends RuntimeException {

    private CodeMsg codeMsg;

    public GobalException(CodeMsg codeMsg){
        this.codeMsg = codeMsg;
    }

    public CodeMsg getCodeMsg() {
        return codeMsg;
    }
}
