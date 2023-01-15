package com.example.util;

import com.example.vo.ResultVO;

public class ResultVOUtil {
    public static ResultVO success(Object data){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(data);
        return resultVO;
    }

    public static ResultVO fail(String msg){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(-1);
        resultVO.setMsg(msg);
        return resultVO;
    }

    public static ResultVO failMsg(String msg){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(-1);
        resultVO.setMsg("失败");
        resultVO.setData(msg);
        return resultVO;
    }
}
