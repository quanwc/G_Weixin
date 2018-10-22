package com.quanwc.controller;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.quanwc.bean.Kiwi;
import com.quanwc.service.KiwiService;

/**
 * kiwi controller
 * @author quanwenchao
 * @date 2018/10/11 23:38:40
 */
@RestController
@RequestMapping("kiwi")
public class KiwiController {

	@Autowired
	private KiwiService kiwiService;

	private void saveCheckKiwi(Kiwi kiwi) {
		if (null == kiwi) {
			throw new RuntimeException("parameter error");
		}
		if (kiwi.getFriendName() == null || kiwi.getFriendName().trim().isEmpty()) {
			throw new RuntimeException("parameter error");
		}
		if (kiwi.getReceiverName() == null || kiwi.getReceiverName().trim().isEmpty()) {
			throw new RuntimeException("parameter error");
		}
		if (kiwi.getReceiverPhone() == null || kiwi.getReceiverName().trim().isEmpty()) {
			throw new RuntimeException("parameter error");
		}
		if (kiwi.getAddress() == null || kiwi.getAddress().trim().isEmpty()) {
			throw new RuntimeException("parameter error");
		}
		if (kiwi.getProvince() == null || kiwi.getProvince().trim().isEmpty()) {
			throw new RuntimeException("parameter error");
		}
		if (kiwi.getCity() == null || kiwi.getCity().trim().isEmpty()) {
			throw new RuntimeException("parameter error");
		}
		if (kiwi.getStress() == null || kiwi.getStress().trim().isEmpty()) {
			throw new RuntimeException("parameter error");
		}
		if (kiwi.getExpress() == null || kiwi.getExpress().trim().isEmpty()) {
			throw new RuntimeException("parameter error");
		}
		if (kiwi.getSize() == null) {
			throw new RuntimeException("parameter error");
		}
		if (kiwi.getMoney() == null) {
			throw new RuntimeException("parameter error");
		}
		if (kiwi.getCreateTimestamp() == null) {
			throw new RuntimeException("parameter error");
		}

	}

	/**
	 * 新增
	 * @param kiwi
	 * @return
	 */
	@PostMapping(value = "save")
	public String save(@NotNull @RequestBody Kiwi kiwi) {
		saveCheckKiwi(kiwi);
		Integer sum = kiwiService.save(kiwi);
		return "true";
	}

	/**
	 * 列表
	 * @return
	 */
	@GetMapping(value = "list")
	public Object listKiwi() {
		return kiwiService.list();
	}

}
