package com.mylog.ds.blog.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mylog.ds.blog.entity.Article;
import com.mylog.ds.blog.mapper.ArticleMapper;
import com.mylog.ds.blog.service.ArticleService;
import com.mylog.ds.blog.service.UserService;
import com.mylog.tools.lic.entity.Message;
import com.mylog.tools.lic.entity.Result;
import com.mylog.tools.lic.entity.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (Article)表服务实现类
 *
 * @author Dylan
 * @since 2020-06-14 20:24:19
 */
@Service
@Slf4j
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    UserService userService;

    /**
     * 获取全部符合条件的文章列表
     * 管理员查看所有文章
     * 目前可根据文章id 文章名 文章二级标题 文章类型来查询
     * 其中文章名和二级标题是模糊查询
     * @return
     */
    @Override
    public Result queryRight(Article article) {
        Result result = new Result();
        QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>();
        if ( null == userService.getUser() || Integer.valueOf(userService.getUser().getUsergroup())>1) {
            articleQueryWrapper.eq("is_del", 0);
            articleQueryWrapper.eq("is_lock", 0);
        }
        if (null != article.getId()){
            articleQueryWrapper.eq("id", article.getId());
        }
        if (null != article.getFileName()){
            articleQueryWrapper.like("file_name", article.getFileName());
        }
        if (null != article.getSubTitle()){
            articleQueryWrapper.like("sub_title", article.getSubTitle());
        }
        if (null != article.getFileType()){
            articleQueryWrapper.eq("file_type", article.getFileType());
        }
        if (null != article.getUserId()){
            articleQueryWrapper.eq("user_id", article.getUserId());
        }

        List<Article> list = articleMapper.selectList(articleQueryWrapper);
        if (list.size() > 0) {
            result = Result.success();
            result.put("data", list);
            return result;
        }
        return result.put("status", Status.QUERY_ERROR.getStatus()).put("msg", Message.QUERY_ERROR.getMsg());
    }


    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Article queryById(Integer id) {
        return this.articleMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Article> queryAllByLimit(int offset, int limit) {
        return this.articleMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param article 实例对象
     * @return 实例对象
     */
    @Override
    public Article insert(Article article) {

        Integer insert = articleMapper.insert(article);
        if (insert > 0) {
            return article;
        }
        return null;
    }

    /**
     * 修改数据
     *
     * @param article 实例对象
     * @return 实例对象
     */
    @Override
    public Article update(Article article) {
        this.articleMapper.update(article);
        return this.queryById(article.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.articleMapper.deleteById(id) > 0;
    }
}