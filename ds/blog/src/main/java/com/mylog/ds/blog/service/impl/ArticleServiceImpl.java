package com.mylog.ds.blog.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mylog.ds.blog.entity.Article;
import com.mylog.ds.blog.mapper.ArticleMapper;
import com.mylog.ds.blog.service.ArticleService;
import com.mylog.ds.blog.service.UserService;
import com.mylog.entitys.entitys.entity.Message;
import com.mylog.entitys.entitys.entity.Result;
import com.mylog.entitys.entitys.entity.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    @Resource
    private ArticleMapper articleMapper;

    @Resource
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
        Result result = new Result.Builder().build();
        QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>();
        if ( null == userService.getUser() || userService.getUser().getUsergroup() >1) {
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
     * 注意：不提供controller使用
     * @param article 实例对象
     * @return 实例对象
     */
    @Override
    public Article insert(Article article) {

        int insert = articleMapper.insert(article);
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
    public Result update(Article article) {
        // 根据id获取目标记录
       Article aimArticle = articleMapper.queryById(article.getId());
       // 修改非空的某几种属性
        if (article.getFileName() != null){
            aimArticle.setFileName(article.getFileName());
        }
        if (article.getSubTitle() != null){
            aimArticle.setSubTitle(article.getSubTitle());
        }
        if (article.getFileType() != null){
            aimArticle.setFileType(article.getFileType());
        }
        if (article.getDescription() != null){
            aimArticle.setDescription(article.getDescription());
        }
        // 修改目标对象
        if (userService.getUser().getId().equals(aimArticle.getUserId()) || userService.getUser().getUsergroup() < 1){
            // 上传者及管理员可以决定是否展示
            if (article.getIsLock()!=null){
                aimArticle.setIsLock(article.getIsLock());
            }
            int update = articleMapper.update(aimArticle,new UpdateWrapper<Article>().eq("id", article.getId()));
            if (update < 1){
                return new Result.Builder(Status.UPDATE_ERROR.getStatus(), Message.UPDATE_ERROR.getMsg()).build();
            }
            return new Result.Builder(Status.SUCCESS.getStatus(), Message.SUCCESS.getMsg()).data(aimArticle).build();
        }
        return new Result.Builder(Status.PERMISSION_ERROR.getStatus(), Message.PERMISSION_ERROR.getMsg()).build();
    }


    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public Result deleteById(Integer id) {
        Article article = articleMapper.queryById(id);
        if (userService.getUser().getId().equals(article.getUserId()) || userService.getUser().getUsergroup() < 1){

            article.setIsDel("1");
            int delNum = articleMapper.update(article, new UpdateWrapper<Article>().eq("id", id));
            if (delNum < 1){
                return new Result.Builder(Status.DELETE_ERROR.getStatus(), Message.DELETE_ERROR.getMsg()).build();
            }
            return Result.success();
        }
        return new Result.Builder(Status.PERMISSION_ERROR.getStatus(), Message.PERMISSION_ERROR.getMsg()).build();
    }
}