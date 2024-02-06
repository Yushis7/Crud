package com.example.board.controller;

import com.example.board.Service.BoardService;
import com.example.board.entity.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
//@ 치고 Controller치면 여기가 controller라는 걸 인식함
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/board/write") //localhost:8081/board/write
    //어느 url로 접근할건지 지정해주는 Getmapping
    public String boardWriteForm() {

        return "boardwrite";
    }

    @PostMapping("/board/writerpro")
//    public String boardWritepro(String title, String content) {
//
//        System.out.println("제목 : " + title);
//        System.out.println("내용 : " + content);

    public String boardWritepro(Board board, Model model, MultipartFile file) throws Exception {

//        System.out.println(board.getTitle());
        boardService.write(board,file);
        model.addAttribute("message", "글 작성이 완료");
        model.addAttribute("searchUrl", "/board/list");


        return "message";
    }

    @GetMapping("/board/list")
    public String boardList(Model model){

        model.addAttribute("list", boardService.boardList());

        return "boardlist";

    }

    @GetMapping("/board/view") // localhost:8081/board/view?id=1 이게 get방식
    public String boardView(Model model,Integer id){

        model.addAttribute("board", boardService.boardView(id));
        return "boardview";
    }

    @GetMapping("/board/delete")
    public String boardDelete(Integer id){
        boardService.boardDelete(id);
        return "redirect:/board/list";
    }

    @GetMapping("/board/modify/{id}")
    public String boardModify(@PathVariable("id") Integer id,Model model){
        model.addAttribute("board", boardService.boardView(id));
        return "boardmodify";
    }
    @PostMapping("/board/update/{id}")
    public  String boardUpdate(@PathVariable("id") Integer id,Board board, MultipartFile file)throws Exception{
        Board boardTemp = boardService.boardView(id);
        boardTemp.setTitle(board.getTitle());
        boardTemp.setContent(board.getContent());

        boardService.write(boardTemp,file);

        return "redirect:/board/list";
    }
}




//
//package com.study.board.controller;
//
//import com.study.board.entity.Board;
//import com.study.board.service.BoardService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.web.PageableDefault;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//
//@Controller
//public class BoardController {
//
//
//    @Autowired
//    private BoardService boardService;
//
//    @GetMapping("/board/write") //localhost:8090/board/write
//    public String boardWriteForm() {
//
//        return "boardwrite";
//    }
//
//    @PostMapping("/board/writepro")
//    public String boardWritePro(Board board, Model model, MultipartFile file) throws Exception{
//
//        boardService.write(board, file);
//
//        model.addAttribute("message", "글 작성이 완료되었습니다.");
//        model.addAttribute("searchUrl", "/board/list");
//
//        return "message";
//    }
//
//    @GetMapping("/board/list")
//    public String boardList(Model model,
//                            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
//                            String searchKeyword) {
//
//        Page<Board> list = null;
//
//        if(searchKeyword == null) {
//            list = boardService.boardList(pageable);
//        }else {
//            list = boardService.boardSearchList(searchKeyword, pageable);
//        }
//
//        int nowPage = list.getPageable().getPageNumber() + 1;
//        int startPage = Math.max(nowPage - 4, 1);
//        int endPage = Math.min(nowPage + 5, list.getTotalPages());
//
//        model.addAttribute("list", list);
//        model.addAttribute("nowPage", nowPage);
//        model.addAttribute("startPage", startPage);
//        model.addAttribute("endPage", endPage);
//
//        return "boardlist";
//    }
//
//    @GetMapping("/board/view") // localhost:8080/board/view?id=1
//    public String boardView(Model model, Integer id) {
//
//        model.addAttribute("board", boardService.boardView(id));
//        return "boardview";
//    }
//
//    @GetMapping("/board/modify/{id}")
//    public String boardModify(@PathVariable("id") Integer id,
//                              Model model) {
//
//        model.addAttribute("board", boardService.boardView(id));
//
//        return "boardmodify";
//    }
//
//    @PostMapping("/board/update/{id}")
//    public String boardUpdate(@PathVariable("id") Integer id, Board board, MultipartFile file) throws Exception{
//
//        Board boardTemp = boardService.boardView(id);
//        boardTemp.setTitle(board.getTitle());
//        boardTemp.setContent(board.getContent());
//
//        boardService.write(boardTemp, file);
//
//        return "redirect:/board/list";
//
//    }
//}