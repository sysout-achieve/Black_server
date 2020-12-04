package com.gunt.balck.springboot.web;

import com.gunt.balck.springboot.service.posts.PostsService;
import com.gunt.balck.springboot.web.dto.PostResponseDto;
import com.gunt.balck.springboot.web.dto.PostsSaveRequestDto;
import com.gunt.balck.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }

    @PostMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id, requestDto);
    }

    public PostResponseDto findById(@PathVariable Long id){
        return postsService.findById(id);
    }
}
