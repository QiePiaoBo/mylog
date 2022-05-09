package com.mylog.common.licence.service.impl;

import com.mylog.common.licence.entity.Access;
import com.mylog.common.licence.mapper.AccessMapper;
import com.mylog.common.licence.model.vo.AccessVO;
import com.mylog.common.licence.service.IAccessService;
import com.mylog.common.licence.transformer.AccessTransformer;
import com.mylog.tools.model.model.exception.MyException;
import com.mylog.tools.model.model.page.MyPage;
import com.mylog.tools.model.model.result.HttpResult;
import com.mylog.tools.model.model.result.PageDataResult;
import com.mylog.tools.utils.utils.Safes;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Dylan
 * @Description AccessService
 * @Date : 2022/5/9 - 23:45
 */
@Service
public class AccessService implements IAccessService {

    @Resource
    private AccessMapper mapper;

    @Override
    public HttpResult getPagedAccess(MyPage myPage) {
        if (!myPage.isValid()){
            throw new MyException("Invalid myPage object : " + myPage);
        }
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
}
