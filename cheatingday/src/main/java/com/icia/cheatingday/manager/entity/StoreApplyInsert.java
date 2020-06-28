package com.icia.cheatingday.manager.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain=true)
public class StoreApplyInsert {
	private int iNo;//입점신청번호
	private String mNum; //사업자등록번호
	private String mIrum;//사업자이름
	private String mTel;// 사업자 전화번호
	private String mEmail;// 이메일
	
}