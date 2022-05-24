package com.sojern.SWEHomeworkAssignment.controller;

import com.sojern.SWEHomeworkAssignment.service.CompareVersionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/compareVersions")
public class CompareVersionsController {

    @Autowired
    CompareVersionsService compareVersionsService;

    /*
    * compareVersions for version
    * @param version1:string first parameter to compare
    * @param version2:string second parameter to compare
    * @return (-1,0,1)
    * */
    @GetMapping
    @ResponseBody
    public Integer compareVersions(@RequestParam(required = true) String version1, @RequestParam(required = true) String version2){
        return compareVersionsService.compareVersions(version1,version2);
    }
}
