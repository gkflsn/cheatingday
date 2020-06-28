package com.icia.cheatingday.notice.service.mvc;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icia.cheatingday.common.dto.Page;
import com.icia.cheatingday.notice.dao.NoticeDao;
import com.icia.cheatingday.notice.dto.NoticeDto;
import com.icia.cheatingday.notice.entity.Notice;
import com.icia.cheatingday.util.PagingUtil;

@Service
public class NoticeService {
	@Autowired
	private NoticeDao dao;
	@Autowired
	private ModelMapper modelMapper;
	

	public Page list(int pageno, String aUsername) {
		int countOfBoard = dao.count(aUsername);
		Page page = PagingUtil.getPage(pageno, countOfBoard);
		int srn = page.getStartRowNum();
		int ern = page.getEndRowNum();
		List<Notice> noticelist = dao.findAll(srn, ern);
		List<NoticeDto.DtoForList> dtolist = new ArrayList<>();
		for(Notice notice:noticelist) {
			NoticeDto.DtoForList dto = modelMapper.map(notice, NoticeDto.DtoForList.class);
			dto.setNWriteTimeStr(notice.getNWriteTime().format(DateTimeFormatter.ofPattern("yyyy년MM월dd일")));
			dtolist.add(dto);
		}
		page.setNlist(dtolist);
		return page;
	}
	public NoticeDto.DtoForRead read(Integer nNo, String aUsername) {
		Notice notice = dao.findById(nNo);
		NoticeDto.DtoForRead dto
			= modelMapper.map(notice, NoticeDto.DtoForRead.class);
		if(aUsername!=null && aUsername.equals(dto.getAUsername())==false)
			dao.update(Notice.builder().nNo(nNo).nReadCnt(0).build());
		String str = notice.getNWriteTime().format(DateTimeFormatter.ofPattern("yyyy년MM월dd일"));
		dto.setNWriteTimeStr(str);
		return dto;
	}
	public int write(NoticeDto.DtoForWrite dto) {
		Notice notice = modelMapper.map(dto, Notice.class);
		dao.insert(notice);
		return notice.getNNo();
	}
}
