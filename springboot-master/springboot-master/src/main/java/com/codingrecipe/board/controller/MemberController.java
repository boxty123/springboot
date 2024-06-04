package com.codingrecipe.board.controller;

import com.codingrecipe.board.dto.MemberDTO;
import com.codingrecipe.board.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/saveMember")
    String join_member() {
        return "saveMember";
    }


    @PostMapping("/saveMember")
    public String save(@ModelAttribute MemberDTO memberDTO) {
        memberService.save(memberDTO);
        return "index";
    }


    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
        MemberDTO loginRet = memberService.login(memberDTO);
        if (loginRet != null) {

            session.setAttribute("loginName", loginRet.getName());
            return "redirect:/";
        } else {
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "index";
    }

    @PostMapping("/id-check")
    public @ResponseBody String idCheck(@RequestParam("memberID") String memberID) {
        String checkRet = memberService.idCheck(memberID);
        if (checkRet != null) {
            return "ok";
        } else {
            return "fail";
        }
    }

    @PostMapping("/login-check")
    public @ResponseBody String loginCheck(@RequestParam("memberID")String memberID,@RequestParam("memberPass")String memberPass){

        System.out.println("여기");
        //String checkRet=memberService
        return"";
    }
}
