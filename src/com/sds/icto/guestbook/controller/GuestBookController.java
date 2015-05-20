package com.sds.icto.guestbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sds.icto.guestbook.dao.GuestBookDao;
import com.sds.icto.guestbook.vo.GuestBookVo;

@Controller
public class GuestBookController {
	@Autowired
	GuestBookDao guestbookDao;

	@RequestMapping(value={"/index","/list"})
	public String index(Model model) {
		List<GuestBookVo> list = guestbookDao.fetchList();
	
		model.addAttribute("list", list);
		return "index";
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(@RequestParam("name") String name,
			@RequestParam("password") String password,
			@RequestParam("message") String message) {

		GuestBookVo vo = new GuestBookVo();
		vo.setName(name);
		vo.setPassword(password);
		vo.setMessage(message);

		guestbookDao.insert(vo);
		return "redirect:/index";
	}
	
	@RequestMapping(value = "/delete/{no}", method = RequestMethod.GET)
	public String deleteForm(@PathVariable Long no, Model model){
		model.addAttribute("no", no);
		
		return "deleteform";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(@RequestParam("no") Long no, @RequestParam("password") String password){
		GuestBookVo vo =new GuestBookVo();
		vo.setNo(no);
		vo.setPassword(password);
		guestbookDao.delete(vo);
		return "redirect:/index";
	}
}
