package com.mylog.common.licence.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mylog.common.licence.entity.Access;
import com.mylog.common.licence.mapper.AccessMapper;
import com.mylog.common.licence.model.dto.AccessDTO;
import com.mylog.common.licence.model.vo.AccessVO;
import com.mylog.common.licence.service.IAccessService;
import com.mylog.common.licence.transformer.AccessTransformer;
import com.mylog.tools.model.model.exception.MyException;
import com.mylog.tools.model.model.page.MyPage;
import com.mylog.tools.model.model.result.DataResult;
import com.mylog.tools.model.model.result.HttpResult;
import com.mylog.tools.model.model.result.PageDataResult;
import com.mylog.tools.utils.utils.Safes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Dylan
 * @Description AccessService
 * @Date : 2022/5/9 - 23:45
 */
@Slf4j
@Service
public class AccessServiceImpl implements IAccessService {

    @Resource
    private AccessMapper mapper;

    @Override
    public HttpResult getPagedAccess(MyPage myPage) {
        myPage.checkValid();
        List<Access> accesses = mapper.selectAccessList(myPage);

        List<AccessVO> accessVOS = Safes
                .of(accesses)
                .stream()
                .map(AccessTransformer::access2AccessVO)
                .collect(Collectors.toList());

        return PageDataResult
                .success()
                .page(myPage.getPageNo())
                .size(myPage.getPageSize())
                .data(accessVOS)
                .total(mapper.selectAccessTotal())
                .build();
    }

    @Override
    public HttpResult createAccess(AccessDTO accessDTO) {
        if (Objects.isNull(accessDTO)){
            throw new MyException("Error, create obj is null.");
        }
        if (Objects.nonNull(accessDTO.getId())){
            throw new MyException("Error, id found in create obj.");
        }
        if (Objects.isNull(accessDTO.getAccessCode())){
            throw new MyException("Error, access code must be not null");
        }
        QueryWrapper<Access> query = Wrappers.query(new Access()).eq("access_code", accessDTO.getAccessCode());
        if (mapper.selectCount(query) > 0){
            throw new MyException("Duplicate error, record already exists.");
        }
        Access access = AccessTransformer.accessDTO2Access(accessDTO);
        int inserted = mapper.insert(access);
        if (inserted <= 0){
            log.error("Error insert access: {}", access);
        }
        Access returnAccess = mapper
                .selectOne(query.eq("access_code", accessDTO.getAccessCode()));
        return DataResult.success().data(AccessTransformer.access2AccessVO(returnAccess)).build();
    }

    @Override
    public HttpResult getById(Integer id) {
        if (Objects.isNull(id)){
            throw new MyException("Error id in getById.");
        }
        Access access = mapper.selectById(id);
        return DataResult
                .success()
                .data(AccessTransformer.access2AccessVO(access))
                .build();
    }

    @Override
    public HttpResult deleteById(Integer id) {
        if (Objects.isNull(id)){
            throw new MyException("Error id in getById.");
        }
        Access access = mapper.selectById(id);
        access.setDelFlag(1);
        mapper.updateById(access);
        return DataResult
                .success()
                .data(AccessTransformer.access2AccessVO(access))
                .build();
    }

    /**
     * 根据id更新权限信息
     * @param accessDTO
     * @return
     */
    @Override
    public HttpResult updateById(AccessDTO accessDTO) {
        if (Objects.isNull(accessDTO.getId())){
            throw new MyException("Error, id must be not null while update.");
        }
        Access access = AccessTransformer.accessDTO2Access(accessDTO);
        mapper.updateById(access);
        return DataResult
                .success()
                .data(accessDTO)
                .build();
    }
}
