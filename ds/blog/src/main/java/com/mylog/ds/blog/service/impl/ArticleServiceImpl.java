package com.mylog.ds.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mylog.ds.blog.entity.Article;
import com.mylog.ds.blog.entity.dto.ArticleDto;
import com.mylog.ds.blog.mapper.ArticleMapper;
import com.mylog.ds.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    /**
     * 获取全部符合条件的文章列表
     * @return
     */
    @Override
    public List<Article> queryRight(ArticleDto articleDto){
        QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>();
        if (articleDto.getIsDel() == null){
            articleDto.setIsDel("0");
        }
        if (articleDto.getIsLock() == null){
            articleDto.setIsLock("0");
        }
        articleQueryWrapper.eq("id", articleDto.getId());
        articleQueryWrapper.like("file_name", articleDto.getFileName());
        articleQueryWrapper.like("sub_title", articleDto.getSubTitle());
        articleQueryWrapper.eq("file_type", articleDto.getFileType());
        articleQueryWrapper.eq("user_id", articleDto.getUserId());

        List<Article> list = articleMapper.selectList(articleQueryWrapper);
        if (list != null){
            return list;
        }
        return null;
    }


    /**
     * 通过ID查询单条数据
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Article queryById(Integer id) {
        return this.articleMapper.queryById(id);
    }

    /**
     * 查询多条数据
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Article> queryAllByLimit(int offset, int limit) {
        return this.articleMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     * @param article 实例对象
     * @return 实例对象
     */
    @Override
    public Article insert(Article article) {

        Integer insert = articleMapper.insert(article);
        if (insert > 0){
            return article;
        }
        return null;
    }

    /**
     * 修改数据
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
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.articleMapper.deleteById(id) > 0;
    }
}