package com.processmining.logdeploy.autodeploy.controller;

import com.processmining.logdeploy.autodeploy.common.lang.Result;
import com.processmining.logdeploy.autodeploy.entity.Server;
import com.processmining.logdeploy.autodeploy.service.ServerService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/server")
public class ServerController {

    @Autowired
    private ServerService serverService;

    @PostMapping("/addServer")
    public Result addServer(@RequestParam("file") MultipartFile file, Server server) {
        return serverService.addServer(file, server);
    }

    @PostMapping("/addServerWithoutFile")
    @RequiresAuthentication
    public Result addServerWithoutFile(@RequestBody Server server) {
        return serverService.addServerWithoutFile(server);
    }

    @GetMapping("/getServer")
    @RequiresAuthentication
    public Result getServer(@RequestParam("currentPage") Long currentPage, @RequestParam("pageSize") Long pageSize,
                            @RequestParam("project_id") Long project_id) {
        return serverService.getServer(currentPage, pageSize, project_id);
    }

    @PostMapping("/testServer")
    @RequiresAuthentication
    public Result testServer(@RequestBody Server server) {
        return serverService.testServer(server);
    }

    @PostMapping("/deleteServer")
    @RequiresAuthentication
    public Result deleteServer(@RequestBody Server server) {
        return serverService.deleteServer(server);
    }

    @PostMapping("/deleteServerBatch")
    @RequiresAuthentication
    public Result deleteServerBatch(@RequestBody List<Map<String, Object>> mapList) {
        return serverService.deleteServerBatch(mapList);
    }

    @GetMapping("/queryServer")
    @RequiresAuthentication
    public Result queryServer(@RequestParam("selectLabel") String selectLabel, @RequestParam("queryInfo") String queryInfo,
                              @RequestParam("currentPage") Long currentPage, @RequestParam("pageSize") Long pageSize,
                              @RequestParam("project_id") Long project_id) {
        return serverService.queryServer(selectLabel, queryInfo, currentPage, pageSize, project_id);
    }

}
