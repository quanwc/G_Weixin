package com.quanwc.bean;

import lombok.Data;

/**
 * 猕猴桃(kiwi)的购买信息
 * @author quanwenchao
 * @date 2018/10/11 23:39:10
 */
@Data
public class Kiwi {

	/**
	 * 主键id
	 */
	private Integer id;

	/**
	 * 朋友名字
	 */
	private String friendName;

	/**
	 * 收件人姓名
	 */
	private String receiverName;

	/**
	 * 收件人电话
	 */
	private String receiverPhone;

	/**
	 * 收件人详细地址（包括省、市、街道）
	 */
	private String address;

	/**
	 * 收件人所在的省
	 */
	private String province;

	/**
	 * 收件人所在的城市
	 */
	private String city;

	/**
	 * 收件人所在的街道
	 */
	private String stress;

	/**
	 * 快递
	 */
	private String express;

	/**
	 * 猕猴桃的规格（5斤、10斤）
	 */
	private Integer size;

	/**
	 * 付款金额
	 */
	private Double money;

	/**
	 * 创建时间（购买日期）
	 */
	private Long createTimestamp;

}
