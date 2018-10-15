package com.quanwc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.quanwc.bean.Kiwi;

@Mapper
public interface KiwiMapper {

    /**
     * 新增
     * @param kiwi
     * @return
     */
	Integer save(Kiwi kiwi);

    /**
     * 列表
     * @return
     */
	List<Kiwi> list();
}