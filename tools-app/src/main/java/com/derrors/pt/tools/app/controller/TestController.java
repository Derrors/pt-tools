/**
 * ***************************************************** Copyright (C) 2023 bytedance.com. All Rights Reserved This file
 * is part of bytedance EA project. Unauthorized copy of this file, via any medium is strictly prohibited. Proprietary
 * and Confidential. ****************************************************
 **/
package com.derrors.pt.tools.app.controller;

import com.derrors.pt.tools.common.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 *
 * @author derrors
 * @date 2023/12/24
 */

@RestController
@RequestMapping("/config")
@RequiredArgsConstructor
public class TestController {


    @GetMapping("/hello")
    public Result<String> hello() {
        return Result.okWithData("Hello");
    }

}
