package com.example.exception;


import com.example.util.ResultVOUtil;
import com.example.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class UnifiedExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public ResultVO handlerException(Exception e){
        log.info("服务器内部异常，{}", e.getMessage());
        return ResultVOUtil.failMsg(e.getMessage());
    }

}
