package com.pinyougou.sellergoods.service;

import com.pinyougou.pojo.TbSpecification;
import com.pinyougou.pojo.TbTypeTemplate;
import com.pinyougou.pojo.TbTypeTemplateExample;
import entity.PageResult;
import entity.Specification;

import java.util.List;
import java.util.Map;

/**
 * Created by 20160816-PC on 2018/9/6.
 */
public interface TypeTemplateService {


    public List<TbTypeTemplate> findAll();

    public PageResult findPage(int page, int rows);

    public PageResult findPage(TbTypeTemplate TbTypeTemplate, int pageNum, int pageSize);


    List<Map> selectOptionList();


    List<Map> selectOption();

    public void update(TbTypeTemplate TbTypeTemplate);

    public void delete(Long [] ids);

    public void add(TbTypeTemplate TbTypeTemplate);

    public TbTypeTemplate findOne(Long id);

}
