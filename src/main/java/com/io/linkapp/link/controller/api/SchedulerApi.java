package com.io.linkapp.link.controller.api;

import com.io.linkapp.config.quartz.QuartzService;
import com.io.linkapp.config.security.auth.PrincipalDetails;
import com.io.linkapp.link.controller.predicate.TagFormPredicate;
import com.io.linkapp.link.request.PushRequest;
import com.io.linkapp.link.request.TagRequest;
import com.io.linkapp.link.response.TagResponse.GetAll;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Quartz", tags = {"Quartz"})
@RequiredArgsConstructor
@RestController
public class SchedulerApi {

    private final QuartzService service;
    
//
//    @SneakyThrows
//    @ApiOperation("5분 간격 테스트")
//    @GetMapping("/quartz")
//    public void testQuartz(){
//        System.out.println("testApi");
//        service.init(); //여기로 넘겨준 다음에
//    }
    
    
    @SneakyThrows
    @ApiOperation("알림 스케줄러 설정")
    @PostMapping("/quartz")
    public void testQuartzForReal(@RequestBody PushRequest pushRequest,@AuthenticationPrincipal PrincipalDetails principalDetails){ //여기서 사용자 정보 Auth객체로 받고
        System.out.println("quartzForReal Api");
        service.init2(pushRequest, principalDetails.getUser()); //여기로 넘겨준 다음에
    }
    
    
    @SneakyThrows
    @ApiOperation("현재 등록된 모든 알람 정보 삭제")
    @DeleteMapping("/clear")
    public void clear(){
        //System.out.println("clear quartz");
        service.resetScheduler();
    }
    
    
}