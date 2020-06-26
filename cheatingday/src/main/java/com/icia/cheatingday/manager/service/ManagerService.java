package com.icia.cheatingday.manager.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.icia.cheatingday.manager.dao.MenuDao;
import com.icia.cheatingday.manager.entity.MenuEntity;

@Service
public class ManagerService {

	@Autowired
	private MenuDao dao;
	@Value("c:/upload/menusajin")
	private String profileFolder;
	@Value("http://localhost:8081/menusajin/")
	private String profilePath;
	
	//메뉴리스트
	public List<MenuEntity> menuList() {
		List<MenuEntity> result = new ArrayList<MenuEntity>();
		List<MenuEntity> list = dao.findAll();
		for(MenuEntity menuEntity:list) {
			result.add(menuEntity);
		}
		return result;
	}

	//메뉴읽기
		public MenuEntity menuRead(int menuno) {
			return dao.findById(menuno);
		}

		//메뉴쓰기 - 메뉴사진 올리는 기능
		public void write(MenuEntity menu, MultipartFile sajin) throws IllegalStateException, IOException {
			if(sajin!=null && sajin.isEmpty()==false) {//사진이 존재하면서 사진형식이면
				if(sajin.getContentType().toLowerCase().startsWith("image/")==true) {
					//사진이 맞으면
					int lastIndexOfDot = sajin.getOriginalFilename().lastIndexOf('.');
					String extension = sajin.getOriginalFilename().substring(lastIndexOfDot+1);
					File profile = new File(profileFolder, menu.getMenuname()+"."+extension);
					
					sajin.transferTo(profile);
					menu.setMenusajin(profilePath+profile.getName());
				} else {
					//메뉴사진이라고 올린 파일이 사진이 아닌 경우 => menubasic.jpg
					menu.setMenusajin(profilePath +"menubasic.jpg");
				} 
				
				}else {
					//메뉴사진을 안올린 경우 => menubasic.jpg
					menu.setMenusajin(profilePath + "menubasic.jpg");
				}
			 dao.insert(menu);
			}
		
	//메뉴업뎃
	public int menuUpdate(MenuEntity menu, MultipartFile sajin) throws IllegalStateException, IOException {
		if(sajin!=null && !sajin.isEmpty()) {
			if(sajin.getContentType().toLowerCase().startsWith("image/")==true) {
					int lastIndexOfDot = sajin.getOriginalFilename().lastIndexOf('.');
					String extension = sajin.getOriginalFilename().substring(lastIndexOfDot+1);
					File file = new File(profileFolder, menu.getMenuname()+"."+extension);
					sajin.transferTo(file);
					menu.setMenusajin(profilePath + file.getName());
				}
			}
			 return dao.update(menu);
		}
	
	//메뉴삭제
	public Object menuDelete(int menuno) {
		
		return dao.delete(menuno);
		}

	}
	

