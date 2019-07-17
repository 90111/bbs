package com.bbs.api.Admin;

import com.bbs.model.Post.PostTitleInfo;
import com.bbs.service.Post.Impl.PostTitleInfoServiceImpl;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RequiresAuthentication
@RequiresRoles({"admin", "moderator","district_owner"})
@RestController
@RequestMapping("/admin")
public class Post {

    @Autowired
    private PostTitleInfoServiceImpl postInfoService;


    @RequestMapping(value = "/searchByColum", method = RequestMethod.GET)
    public Map searchPost(String colum_name, String s, String nick_name, int page, int size) {
        System.out.println("调用searchByColum方法");
        if (s.isEmpty() || s == null || s.equals("")) {
            return null;
        }
        if (nick_name == null) nick_name="";
        Map<String, Object> map = new HashMap<>();
        try {
            PageInfo pageObj  = postInfoService.getPostTitleInfosByColum(colum_name, s, nick_name, page, size);
            List<Map<String, Object>> ls = pageObj.getList();
            if (ls == null) {
                return null;
            }
            map.put("code", "200");
            map.put("msg", "操作成功");
            map.put("ls", ls);
            map.put("num", pageObj.getTotal());
        } catch (Exception e) {
            map.put("code", "500");
            map.put("msg", "操作失败");
        }
        return map;
    }

    @RequestMapping(value = "/changePostState", method = RequestMethod.POST)
    public Map changePostState(int post_id, int state, String colum_name){
        System.out.println("调用changePostState方法");
        Map<String, Object> map = new HashMap<>();
        try{
            postInfoService.changePostState(post_id, colum_name, state);
            map.put("code", "200");
            map.put("msg", "操作成功");
        }catch (Exception e){
            e.printStackTrace();
            map.put("code", "500");
            map.put("msg", "操作失败");
        }
        return map;
    }

    @RequestMapping(value = "/changePostDis", method = RequestMethod.POST)
    public Map changePostDis(int post_id, int dis_id, String colum_name){
        System.out.println("调用changePostDis方法");
        Map<String, Object> map = new HashMap<>();
        try{
            postInfoService.changePostDis(post_id, colum_name, dis_id);
            map.put("code", "200");
            map.put("msg", "操作成功");
        }catch (Exception e){
            e.printStackTrace();
            map.put("code", "500");
            map.put("msg", "操作失败");
        }
        return map;
    }

    @RequestMapping(value = "/deletePosts", method = RequestMethod.POST)
    public Map delete(@RequestBody List<PostTitleInfo> ls) {
        System.out.println("调用deletePosts");
        Map<String, Object> map = new HashMap<>();
        try{
            postInfoService.batchDelete(ls);
            map.put("code", "200");
            map.put("msg", "操作成功");
        }catch (Exception e){
            e.printStackTrace();
            map.put("code", "500");
            map.put("msg", "操作失败");
        }
        return map;
    }
}
