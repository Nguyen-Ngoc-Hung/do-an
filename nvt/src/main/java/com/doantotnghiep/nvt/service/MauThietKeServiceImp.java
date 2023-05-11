package com.doantotnghiep.nvt.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.doantotnghiep.nvt.common.Constants;
import com.doantotnghiep.nvt.dto.RequestDto;
import com.doantotnghiep.nvt.dto.ResponseDto;
import com.doantotnghiep.nvt.model.MauThietKe;
import com.doantotnghiep.nvt.repository.MauThietKeRepository;


@Service
public class MauThietKeServiceImp implements MauThietKeService {

    @Autowired
    MauThietKeRepository mauThietKeRepository;

    @Override
    public Map<String, Object> getMtkPaging(RequestDto requestDto) {
        // TODO Auto-generated method stub
        Map<String,Object> data = new HashMap<>();
        List<MauThietKe> list = new ArrayList<MauThietKe>();
        Long total = 0L;
        if (requestDto.getPage().getPageNumber() == null || requestDto.getPage().getPageNumber() == 0) {
            requestDto.getPage().setPageNumber(1);
        }
        if (requestDto.getPage().getPageSize() == null || requestDto.getPage().getPageSize() == 0) {
            requestDto.getPage().setPageSize(20);
        }
        Pageable pageable = PageRequest.of(requestDto.getPage().getPageNumber()-1, requestDto.getPage().getPageSize());

        Specification<MauThietKe> specification = new Specification<MauThietKe>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<MauThietKe> root, CriteriaQuery<?> query,CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        Page<MauThietKe> page = this.mauThietKeRepository.findAll(specification, pageable);
        // list = page.getContent();
        data.put("list",page.getContent());
        data.put("total",page.getTotalElements());
        return data;
    }

    @Override
    public ResponseDto createMtk(MauThietKe mauThietKe) {
        // TODO Auto-generated method stub
        ResponseDto res = new ResponseDto();
        List<MauThietKe> temp = this.mauThietKeRepository.findByTen(mauThietKe.getMaTk());
        if(temp.size() != 0){
            res.setErrCode(Constants.EXIST_CODE);
            res.setErrMsg(Constants.EXIST_MSG);
        }else{
            this.mauThietKeRepository.save(mauThietKe);
        }
        return res;
    }
    
}
