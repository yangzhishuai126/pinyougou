package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbSpecificationOptionMapper;
import com.pinyougou.mapper.TbTypeTemplateMapper;
import com.pinyougou.pojo.*;
import com.pinyougou.sellergoods.service.TypeTemplateService;
import entity.PageResult;
import entity.Specification;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Created by 20160816-PC on 2018/9/6.
 */
@Service
public class TypeTemplateServiceImpl implements TypeTemplateService {

        @Autowired
    private TbTypeTemplateMapper tbTypeTemplateMapper;

    @Override
    public List<TbTypeTemplate> findAll() {

        return tbTypeTemplateMapper.selectByExample(null);
    }


    @Override
    public PageResult findPage(int page, int rows) {
        PageHelper.startPage(page, rows);
        Page<TbTypeTemplate> p = (Page<TbTypeTemplate>) tbTypeTemplateMapper.selectByExample(null);
        return new PageResult(p.getTotal(), p.getResult());
    }


    @Override
    public PageResult findPage(TbTypeTemplate TbTypeTemplate, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        TbTypeTemplateExample example=new TbTypeTemplateExample();
        TbTypeTemplateExample.Criteria criteria = example.createCriteria();
        if(TbTypeTemplate!=null){
            if(TbTypeTemplate.getName()!=null && TbTypeTemplate.getName().length()>0){
                criteria.andNameLike("%"+TbTypeTemplate.getName()+"%");

            }

        }
        Page<TbTypeTemplate> page= (Page<TbTypeTemplate>) tbTypeTemplateMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
    }

    public List<Map> selectOptionList() {
        return tbTypeTemplateMapper.selectOptionList();
    }

    public List<Map> selectOption() {
        return tbTypeTemplateMapper.selectOption();
    }


    @Override
    public void add(TbTypeTemplate TbTypeTemplate) {
        tbTypeTemplateMapper.insert(TbTypeTemplate);//插入规格

    }


    @Override
    public TbTypeTemplate findOne(Long id){
        //查询规格
       return tbTypeTemplateMapper.selectByPrimaryKey(id);

    }


    @Override
    public void update(TbTypeTemplate TbTypeTemplate) {


        //保存修改的规格
        tbTypeTemplateMapper.updateByPrimaryKey(TbTypeTemplate);//保存规格


    }

    @Override
    public void delete(Long[] ids) {
       for(Long id:ids){
           tbTypeTemplateMapper.deleteByPrimaryKey(id);

        }
    }




}
