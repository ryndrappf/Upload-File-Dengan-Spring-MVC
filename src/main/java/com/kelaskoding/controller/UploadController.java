package com.kelaskoding.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UploadController {

    private static String UPLOADED_PATH = "D:/FGA-JAVA 2022/";

    @GetMapping("/")
    public String index(){
        return "index";
    }


    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes){
        if(file.isEmpty()){
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:status";
        }
        try {
          byte[] bytes = file.getBytes();
          Path path = Paths.get(UPLOADED_PATH + file.getOriginalFilename());
          Files.write(path, bytes);

          redirectAttributes.addFlashAttribute("message", "You successfully uploaded");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:status";
    }
    
    @GetMapping("/status")
    public String uploadStatus(){
        return "status";
    }
}
