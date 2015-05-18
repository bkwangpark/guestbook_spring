package com.sds.icto.guestbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sds.icto.guestbook.dao.GuestBookDao;
import com.sds.icto.guestbook.vo.GuestBookVo;

@Controller
public class GuestBookController {
	@Autowired
	GuestBookDao guestbookDao;

	@RequestMapping("/index")
	public String index(Model model) {
		List<GuestBookVo> list = guestbookDao.fetchList();
		model.addAttribute("list", list);
		return "/views/index.jsp";
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(@RequestParam("name") String name,
			@RequestParam("pass") String pwd,
			@RequestParam("content") String msg) {

		GuestBookVo vo = new GuestBookVo();
		vo.setName(name);
		vo.setPwd(pwd);
		vo.setMsg(msg);

		guestbookDao.insert(vo);
		return "redirect:/index";
	}
}
