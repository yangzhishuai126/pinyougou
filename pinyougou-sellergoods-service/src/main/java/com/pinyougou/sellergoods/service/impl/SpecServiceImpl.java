package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbSpecificationMapper;
import com.pinyougou.mapper.TbSpecificationOptionMapper;
import com.pinyougou.pojo.*;
import com.pinyougou.sellergoods.service.SpecService;
import entity.PageResult;
import entity.Specification;
import org.springframework.beans.factory.annotation.Autowired;
import com.pinyougou.pojo.TbSpecificationOptionExample;
import java.util.List;

/**
 * Created by 20160816-PC on 2018/9/5.
 */
@Service
public class SpecServiceImpl implements SpecService {

    @Autowired
    private com.pinyougou.mapper.TbSpecificationOptionMapper TbSpecificationOptionMapper;

    @Autowired
    private com.pinyougou.mapper.TbSpecificationMapper TbSpecificationMapper;


    @Override
    public void add(Specification specification) {
        TbSpecificationMapper.insert(specification.getSpecification());//插入规格
        //循环插入规格选项
        for(TbSpecificationOption specificationOption:specification.getSpecificationOptionList()){
            specificationOption.setSpecId(specification.getSpecification().getId());
            TbSpecificationOptionMapper.insert(specificationOption);
        }
    }


    @Override
    public Specification findOne(Long id){
        //查询规格
        TbSpecification tbSpecification = TbSpecificationMapper.selectByPrimaryKey(id);
        //查询规格选项列表
        TbSpecificationOptionExample example =new TbSpecificationOptionExample();
        TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
        criteria.andSpecIdEqualTo(id);//根据规格ID查询
        List<TbSpecificationOption> optionList = TbSpecificationOptionMapper.selectByExample(example);
        //构建组合实体类返回结果
        Specification spec=new Specification();
        spec.setSpecification(tbSpecification);
        spec.setSpecificationOptionList(optionList);
        return spec;
    }


    @Override
    public void update(Specification specification){



        //保存修改的规格
        TbSpecificationMapper.updateByPrimaryKey(specification.getSpecification());//保存规格

        //删除原有的规格选项
        TbSpecificationOptionExample example=new TbSpecificationOptionExample();
        TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
        criteria.andSpecIdEqualTo(specification.getSpecification().getId());//指定规格ID为条件
        TbSpecificationOptionMapper.deleteByExample(example);//删除

        //循环插入规格选项
        for(TbSpecificationOption specificationOption:specification.getSpecificationOptionList()){
            specificationOption.setSpecId(specification.getSpecification().getId());
            TbSpecificationOptionMapper.insert(specificationOption);
        }
    }


    @Override
    public void delete(Long[] ids) {
        for(Long id:ids){
            TbSpecificationMapper.deleteByPrimaryKey(id);
            //删除原有的规格选项
            TbSpecificationOptionExample example=new TbSpecificationOptionExample();
            com.pinyougou.pojo.TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
            criteria.andSpecIdEqualTo(id);//指定规格ID为条件
            TbSpecificationOptionMapper.deleteByExample(example);//删除
        }
    }

    @Override
    public List<TbSpecification> findAll() {
        return TbSpecificationMapper.selectByExample(null);
    }

    @Override
    public PageResult findPage(int page, int rows) {
        PageHelper.startPage(page, rows);
        Page<TbSpecification> p = (Page<TbSpecification>) TbSpecificationMapper.selectByExample(null);
        return new PageResult(p.getTotal(), p.getResult());
    }


    @Override
    public PageResult findPage(TbSpecification TbSpecification, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        TbSpecificationExample example=new TbSpecificationExample();
        TbSpecificationExample.Criteria criteria = example.createCriteria();
        if(TbSpecification!=null){
            if(TbSpecification.getSpecName()!=null && TbSpecification.getSpecName().length()>0){
                criteria.andSpecNameLike("%"+TbSpecification.getSpecName()+"%");

            }

        }
        Page<TbSpecification> page= (Page<TbSpecification>)TbSpecificationMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
    }


}
