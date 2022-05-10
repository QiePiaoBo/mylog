package com.mylog.common.licence.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mylog.common.licence.entity.Group;
import com.mylog.common.licence.mapper.GroupMapper;
import com.mylog.common.licence.model.dto.GroupDTO;
import com.mylog.common.licence.model.vo.GroupVO;
import com.mylog.common.licence.service.IGroupService;
import com.mylog.common.licence.transformer.GroupTransformer;
import com.mylog.tools.model.model.exception.MyException;
import com.mylog.tools.model.model.page.MyPage;
import com.mylog.tools.model.model.result.DataResult;
import com.mylog.tools.model.model.result.HttpResult;
import com.mylog.tools.model.model.result.PageDataResult;
import com.mylog.tools.utils.utils.Safes;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Classname GroupServiceImpl
 * @Description GroupServiceImpl
 * @Date 5/10/2022 4:06 PM
 */
@Service
public class GroupServiceImpl implements IGroupService {

    @Resource
    private GroupMapper mapper;
    /**
     * 分页获取用户组
     * @param myPage
     * @return
     */
    @Override
    public HttpResult getPagedGroup(MyPage myPage) {
        myPage.checkValid();
        List<Group> groups = mapper.selectGroupList(myPage);
        List<GroupVO> groupVOS = Safes.of(groups)
                .stream()
                .map(GroupTransformer::group2GroupVO)
                .collect(Collectors.toList());
        return PageDataResult
                .success()
                .page(myPage.getPageNo())
                .size(myPage.getPageSize())
                .data(groupVOS)
                .total(mapper.selectGroupTotal())
                .build();
    }

    /**
     * 创建用户组
     * @param groupDTO
     * @return
     */
    @Override
    public HttpResult createGroup(GroupDTO groupDTO) {
        if (Objects.nonNull(groupDTO.getId())){
            throw new MyException("Error, found id in create obj.");
        }
        if (Objects.isNull(groupDTO.getGroupCode())){
            throw new MyException("Error, group code must be not null.");
        }
        QueryWrapper<Group> byGroupCode = Wrappers
                .query(new Group())
                .eq("group_code", groupDTO.getGroupCode());
        if (Objects.nonNull(mapper.selectCount(byGroupCode))){
            throw new MyException("Duplicate error, record already exists.");
        }
        mapper.insert(GroupTransformer.groupDTO2Group(groupDTO));
        Group group = mapper.selectOne(byGroupCode);
        return DataResult
                .success()
                .data(GroupTransformer.group2GroupVO(group))
                .build();
    }

}