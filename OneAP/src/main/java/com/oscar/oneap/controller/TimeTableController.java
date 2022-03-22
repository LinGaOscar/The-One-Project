package com.oscar.oneap.controller;

import com.oscar.oneap.entity.SysUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/table")
public class TimeTableController {
    @GetMapping("/getTimeTable")
    @ResponseBody
    public  Map<String, String> getTimeTable() {
        Map<String, String> result = new HashMap<>();
        return result;
    }
}
