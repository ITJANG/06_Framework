package edu.kh.project.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import edu.kh.project.entity.Board;
import edu.kh.project.service.BoardService;

@Controller
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/")
    public String writeForm(Model model) {
        model.addAttribute("board", new Board());
        model.addAttribute("list", boardService.findAll());
        return "write";
    }

    @PostMapping("/submit")
    public String submit(@ModelAttribute Board board) {
        System.out.println("제목: " + board.getTitle());
        System.out.println("내용(HTML): " + board.getContent());
        boardService.save(board);
        return "redirect:/";
    }
    
    @PostMapping("/image/upload")
    @ResponseBody
    public String uploadImage(@RequestParam("image") MultipartFile image) throws IOException {
        // 실제 파일 저장 경로 (절대경로 예시 - 원하는 위치로 바꾸세요)
        String uploadDir = System.getProperty("user.dir") + "/uploads/";

        // 폴더가 없다면 생성
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // 파일명 생성 및 저장
        String fileName = UUID.randomUUID() + "_" + image.getOriginalFilename();
        File dest = new File(uploadDir + fileName);
        image.transferTo(dest);

        // 정적 자원으로 서빙할 수 있도록 설정 필요 (WebMvcConfigurer)
        return "/images/" + fileName;
    }
}
