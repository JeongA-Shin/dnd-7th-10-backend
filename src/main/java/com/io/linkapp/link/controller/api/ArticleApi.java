package com.io.linkapp.link.controller.api;

import com.io.linkapp.link.request.ArticleRequest;
import com.io.linkapp.link.response.ArticleResponse;
import com.io.linkapp.link.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Article", tags = {"Article"})
@RequiredArgsConstructor
@RestController
public class ArticleApi {

    private final ArticleService articleService;

    @ApiOperation("링크 저장")
    @PostMapping("/article")
    public void add(@RequestBody @Valid ArticleRequest articleRequest) {
        articleService.add(articleRequest);
    }

    @ApiOperation("링크 전체 조회")
    @GetMapping("/articles")
    public List<ArticleResponse> getList() {
        return articleService.getList();
    }

    @ApiOperation("링크 조회")
    @GetMapping("/article")
    public ArticleResponse get(@RequestBody UUID uuid) {
        return articleService.findById(uuid);
    }

    @ApiOperation("링크 삭제")
    @DeleteMapping("/article")
    public void remove(@RequestBody UUID uuid) {
        articleService.remove(uuid);
    }

    @ApiOperation(value = "북마크 등록/해제", notes = "등록 상태에서 요청 시 해제, 해제 상태에서 요청 시 등록")
    @PatchMapping("/article/mark/{id}")
    public ArticleResponse bookmark(@PathVariable("id") UUID uuid){
        return articleService.bookmark(uuid);
    }
}
