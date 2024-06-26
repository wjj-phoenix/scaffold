package com.phoenix.scaffold.controller;

import com.phoenix.scaffold.annotation.Signature;
import com.phoenix.scaffold.entity.SysOperationLog;
import com.phoenix.scaffold.lang.Result;
import org.springframework.web.bind.annotation.*;

/**
 * @author wjj-phoenix
 * @since 2024-06-26
 */
@RestController
@RequestMapping("user")
public class SignTestController {

    @Signature
    @PostMapping("test/{id}")
    public Result<String> myController(@PathVariable String id, @RequestParam String client, @RequestBody SysOperationLog operationLog) {
        return Result.success(String.join(",", id, client, operationLog.toString()));
    }

}