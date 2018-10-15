package com.quanwc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quanwc.bean.Kiwi;
import com.quanwc.mapper.KiwiMapper;

/**
 * kiwi的service
 * @author quanwenchao
 * @date 2018/10/12 22:27:50
 */
@Service
public class KiwiService {

	@Autowired
	private KiwiMapper kiwiMapper;

	/**
	 * 新增
	 * @param kiwi
	 * @return
	 */
	public Integer save(Kiwi kiwi) {
		if (null == kiwi) {
			return null;
		}
		return kiwiMapper.save(kiwi);
	}

	/**
	 * 列表
	 * @return
	 */
	public List<Kiwi> list() {
		return kiwiMapper.list();
	}
}
