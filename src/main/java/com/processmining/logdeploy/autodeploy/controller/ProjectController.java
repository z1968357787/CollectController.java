package com.processmining.logdeploy.autodeploy.controller;

import com.processmining.logdeploy.autodeploy.entity.Project;
import com.processmining.logdeploy.autodeploy.common.lang.Result;
import com.processmining.logdeploy.autodeploy.service.ProjectService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@CrossOrigin
@RequestMapping("/project")
public class ProjectController {

    @Resource
    private ProjectService projectService;

    @RequiresAuthentication
    @PostMapping("/addProject")
    public Result addProject(@RequestBody Project project) {
        return projectService.addProject(project);
    }

    @RequiresAuthentication
    @GetMapping("/getAllProject")
    public Result getAllProject(@RequestParam("user_id") Long user_id) {
        return projectService.getAllProject(user_id);
    }

    @RequiresAuthentication
    @GetMapping("/deleteProject")
    public Result deleteProject(@RequestParam("id") Long id) {
        return projectService.deleteProject(id);
    }

    @RequiresAuthentication
    @GetMapping("/deleteCollect")
    public Result deleteCollect(@RequestParam("id") Long id) {
        return projectService.deleteCollect(id);
    }

    @RequiresAuthentication
    @GetMapping("/searchProjectAndCollect")
    public Result searchProjectAndCollect(@RequestParam("user_id") Long user_id, @RequestParam("queryText") String queryText) {
        return projectService.searchProjectAndCollect(user_id, queryText);
    }

}
