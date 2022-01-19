package com.project.springbootproject.controller;

import com.project.springbootproject.dto.BoardDto;
import com.project.springbootproject.service.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


/*@RestController는 @Controller와 @ReponseBody를 합쳐놓은 어노테이션
  view 페이지가 필요 없는 API 응답에 어울리는 어노테이션*/

@Controller
@AllArgsConstructor /*생성자로 Bean 객체를 받는 방식을 해결해주는 어노테이션*/
public class BoardController {

        private BoardService boardService;

        @GetMapping("/")
        public String list() {
            return "board/list.html";
        }

        @GetMapping("/post")
        public String write() {
            return "board/write.html";
        }


        /*게시글을 DB에 INSERT 하는 메서드*/
        @PostMapping("/post")
        public String write(BoardDto boardDto) {
            boardService.savePost(boardDto);
            return "redirect:/";
        }
}
