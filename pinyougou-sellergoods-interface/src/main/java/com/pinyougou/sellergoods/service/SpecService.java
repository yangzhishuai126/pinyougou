package com.pinyougou.sellergoods.service;

import com.pinyougou.pojo.TbBrand;
import com.pinyougou.pojo.TbSpecification;
import entity.PageResult;
import entity.Specification;

import java.util.List;

/**
 * Created by 20160816-PC on 2018/9/5.
 */
public interface SpecService {

    public void add(Specification specification);

    public Specification findOne(Long id);

    public void update(Specification specification);

    public void delete(Long [] ids);

    public List<TbSpecification> findAll();

    public PageResult findPage(int page, int rows);

    public PageResult findPage(TbSpecification TbSpecification, int pageNum, int pageSize);
}
