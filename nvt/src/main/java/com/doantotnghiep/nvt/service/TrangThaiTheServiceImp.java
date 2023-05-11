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

import com.doantotnghiep.nvt.dto.RequestDto;
import com.doantotnghiep.nvt.model.TrangThaiThe;
import com.doantotnghiep.nvt.repository.TrangThaiTheRepository;

@Service
public class TrangThaiTheServiceImp implements TrangThaiTheService {

    @Autowired
    TrangThaiTheRepository trangThaiTheRepository;

    @Override
    public Map<String, Object> getTrangThaiThePaging(RequestDto requestDto) {
        // TODO Lấy danh sách trang thái thẻ (Có phân trang)
        Map<String, Object> data = new HashMap<>();
        List<TrangThaiThe> list = new ArrayList<>();
        Long total = 0L;
        if (requestDto.getPage().getPageNumber() == null || requestDto.getPage().getPageNumber() == 0) {
            requestDto.getPage().setPageNumber(1);
        }
        if (requestDto.getPage().getPageSize() == null || requestDto.getPage().getPageSize() == 0) {
            requestDto.getPage().setPageSize(20);
        }
        Pageable pageable = PageRequest.of(requestDto.getPage().getPageNumber() -1 , requestDto.getPage().getPageSize());

        Specification<TrangThaiThe> specification = new Specification<TrangThaiThe>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<TrangThaiThe> root, CriteriaQuery<?> query,CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        Page<TrangThaiThe> page = this.trangThaiTheRepository.findAll(specification, pageable);
        data.put("list",page.getContent());
        data.put("total",page.getTotalElements());

        return data;
    }
    
}
