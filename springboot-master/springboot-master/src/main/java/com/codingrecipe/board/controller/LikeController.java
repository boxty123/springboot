package com.codingrecipe.board.controller;
import com.codingrecipe.board.service.LikeService;
import com.codingrecipe.board.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/like")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    private final MemberService memberService;


    @GetMapping("/{id}/check")
    public @ResponseBody String check(@PathVariable Long id, HttpSession session) {

        Long memberId = memberService.findByName((String) session.getAttribute("loginName"));

        return likeService.check(id, memberId);
    }

    @PostMapping("/{id}/upLike")
    public @ResponseBody void upLike(@PathVariable Long id, HttpSession session){
        Long memberId = memberService.findByName((String) session.getAttribute("loginName"));

        likeService.uplike(id,memberId);

    }

    @PostMapping("/{id}/downLike")
    public @ResponseBody void downLike(@PathVariable Long id, HttpSession session){
        Long memberId = memberService.findByName((String) session.getAttribute("loginName"));

        likeService.downLike(id,memberId);

    }

}