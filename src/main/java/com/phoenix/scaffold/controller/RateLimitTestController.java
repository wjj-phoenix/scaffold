package com.phoenix.scaffold.controller;

import com.phoenix.scaffold.annotation.RateLimit;
import com.phoenix.scaffold.lang.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wjj-phoenix
 * @since 2024-06-26
 */
@Slf4j
@RestController
public class RateLimitTestController {

    @RateLimit
    @GetMapping("/limit")
    public Result<String> limit() {
        log.info("limit");
        return Result.success();
    }

    @RateLimit(limit = 5)
    @GetMapping("/limit1")
    public Result<String> limit1() {
        log.info("limit1");
        return Result.success();
    }

    @GetMapping("/nolimit")
    public Result<String> noRateLimiter() {
        log.info("no limit");
        return Result.success();
    }

}