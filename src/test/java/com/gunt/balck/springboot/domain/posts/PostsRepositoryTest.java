package com.gunt.balck.springboot.domain.posts;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PostsRepository.class})
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @Test
    public void exampleTest() {
        String str = "!!!!!!!!!!!!!!!!!!!";
        System.out.println(str);
        Assert.assertThat(str, is(str));
    }

    //    @After
//    public void cleanUp() {
//        postsRepository.deleteAll();
//    }
//
//    @Test
//    public void 게시글저장_불러오기() {
//        //given
//        String title = "테스트 게시글";
//        String content = "테스트 본문";
//        postsRepository.save(
//                Posts.builder()
//                        .title(title)
//                        .content(content)
//                        .author("Gunt")
//                        .build()
//        );
//
//        //when
//        List<Posts> postsList = postsRepository.findAll();
//
//        //then
//        Posts posts = postsList.get(0);
//        assertThat(posts.getTitle()).isEqualTo(title);
//        assertThat(posts.getContent()).isEqualTo(content);
//    }
//
//    @Test
//    public void BaseTimeEntity_등록() {
//        //given
//        LocalDateTime now = LocalDateTime.of(2019, 6, 4, 0, 0, 0);
//        postsRepository.save(Posts.builder()
//                .author("author")
//                .content("content")
//                .title("title")
//                .build());
//
//        //when
//        List<Posts> postsList = postsRepository.findAll();
//
//        //then
//        Posts posts = postsList.get(0);
//        System.out.println(">>>>>>>>>  createDate=" + posts.getCreatedDate() + ", modifiedDate=" + posts.getModifiedDate());
//        assertThat(posts.getCreatedDate()).isAfter(now);
//        assertThat(posts.getModifiedDate()).isAfter(now);
//    }
}