package com.project.springbootproject.controller;

import com.project.springbootproject.dto.BoardDto;
import com.project.springbootproject.service.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/*@RestController는 @Controller와 @ReponseBody를 합쳐놓은 어노테이션
  view 페이지가 필요 없는 API 응답에 어울리는 어노테이션*/

@Controller
@AllArgsConstructor /*생성자로 Bean 객체를 받는 방식을 해결해주는 어노테이션*/
public class BoardController {

        private BoardService boardService;

        /*게시글 목록*/
        @GetMapping("/")
        public String list(Model model) {
            List<BoardDto> boardList = boardService.getBoardList();

            model.addAttribute("boardList", boardList);
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

        /*게시글 상세조회 페이지*/
        @GetMapping("/post/{no}")
        /*@PathVariable("no") Long no*/
        /*유동적으로 변하는 PathVariable을 처리하는 방법*/
        /*URL 매핑하는 부분에서 {변수} 처리를 해주면, 메서드 파라미터로 @PathVariable("변수") 이렇게 받을 수 있음.*/
        public String detail(@PathVariable("no") Long no, Model model) {
            BoardDto boardDto = boardService.getPost(no);

            model.addAttribute("boardDto", boardDto);
            return "board/detail.html";
        }

        /*게시글 수정 페이지*/
        @GetMapping("/post/edit/{no}")
        public String edit(@PathVariable("no") Long no, Model model) {
            BoardDto boardDto = boardService.getPost(no);

            model.addAttribute("boardDto", boardDto);
            return "board/update.html";
        }

        /*게시글 수정*/
        @PostMapping("/post/edit/{no}")
        public String update(BoardDto boardDto) {
            /*게시글 추가에서 사용하는 boardService.savePost() 메서드를 같이 사용*/
            boardService.savePost(boardDto);

            return "redirect:/";
        }

        /*게시글 삭제*/

        @PostMapping("/post/{no}")
        public String delete(@PathVariable("no") Long no) {
            boardService.deletePost(no);

            return "redirect:/";
        }

}
