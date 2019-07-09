package com.bbs.api.Post;

import com.bbs.model.Post.PostTitleInfo;
import com.bbs.service.Post.Impl.PostInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/anon/post")
public class PostController {

    @Autowired
    private PostInfoServiceImpl postInfoService;

    @RequestMapping(value = "/getPostTitles", method = RequestMethod.GET)
    public List<PostTitleInfo> getPostTitles(int id) throws Exception {
        System.out.println("调用getPostTitles方法");

        return postInfoService.getPostTitleInfos(id);
    }

    @RequestMapping(value = "/getPostTitleContent", method = RequestMethod.GET)
    public PostTitleInfo getPostTitleContent(int id) throws Exception{
        System.out.println("调用getPostTitleContent");

        return postInfoService.getPostTitleContent(id);
    }
}
