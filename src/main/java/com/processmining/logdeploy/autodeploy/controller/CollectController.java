package com.processmining.logdeploy.autodeploy.controller;

import cn.hutool.json.JSONObject;
import com.processmining.logdeploy.autodeploy.common.lang.Result;
import com.processmining.logdeploy.autodeploy.entity.Cluster;
import com.processmining.logdeploy.autodeploy.entity.Deploy;
import com.processmining.logdeploy.autodeploy.entity.addDeployForm;
import com.processmining.logdeploy.autodeploy.service.CollectService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/collect")
public class CollectController {

    @Autowired
    private CollectService collectService;

    @GetMapping("/getNodes")
    @RequiresAuthentication
    public Result getNodes(@RequestParam("project_id") Long project_id, @RequestParam("type") String type) {
        System.out.println("getNodes");
        return collectService.getNodes(project_id, type);
    }

    @PostMapping("/addDeploy")
    public Result addDeploy(@RequestParam("file") MultipartFile file, @RequestParam("type") String type,
                            @RequestParam("nodeId") Long nodeId, @RequestParam("directory") String directory,
                            @RequestParam("project_id") Long project_id, @RequestParam("is_execute") Boolean is_execute,
                            @RequestParam("script") String script, @RequestParam("code") String code,
                            @RequestParam("DeployVersion") String version) {
        System.out.println("start");
        long start = System.currentTimeMillis();
        Result result = collectService.addDeploy(file, type, nodeId, directory, project_id, is_execute, script, code, version);
        long end = System.currentTimeMillis();
        System.out.println("Total time: " + (end - start));
        //System.out.println("addDeployForm"+);
        return result;
    }

    @PostMapping("/addDeployNew")
    public Result addDeployNew(@RequestBody List<addDeployForm> forms) {
        System.out.println("start");
        long start = System.currentTimeMillis();
        Result result = new Result();
        for(addDeployForm form : forms) {
            result = collectService.addDeploy(form.getFile(), form.getType(), form.getNodeId(), form.getDirectory(), form.getProject_id(), form.getIs_execute(),form.getScript(), form.getCode(), form.getVersion());
        }
        long end = System.currentTimeMillis();
        System.out.println("Total time: " + (end - start));
        //System.out.println("addDeployForm"+);
        return result;
    }

    @GetMapping("/getDeploy")
    @RequiresAuthentication
    public Result getDeploy(@RequestParam("currentPage") Long currentPage, @RequestParam("pageSize") Long pageSize,
                            @RequestParam("project_id") Long project_id) {
        return collectService.getDeploy(currentPage, pageSize, project_id);
    }

    @PostMapping("/deleteDeploy")
    @RequiresAuthentication
    public Result deleteDeploy(@RequestBody Deploy deploy) {
        return collectService.deleteDeploy(deploy);
    }

    @PostMapping("/deleteDeployBatch")
    @RequiresAuthentication
    public Result deleteDeployBatch(@RequestBody List<Map<String, Object>> mapList) {
         return collectService.deleteDeployBatch(mapList);
    }

    @GetMapping("/queryDeploy")
    @RequiresAuthentication
    public Result queryDeploy(@RequestParam("selectLabel") String selectLabel, @RequestParam("queryInfo") String queryInfo,
                              @RequestParam("currentPage") Long currentPage, @RequestParam("pageSize") Long pageSize,
                              @RequestParam("project_id") Long project_id) {
        return collectService.queryDeploy(selectLabel, queryInfo, currentPage, pageSize, project_id);
    }

    @PostMapping("/uploadServerScript")
    public Result uploadServerScript(@RequestParam("file") MultipartFile script,
                                     @RequestParam("project_id") Long project_id,
                                     @RequestParam("applicationName") String applicationName) {
        return collectService.uploadServerScript(script, project_id, applicationName);
    }

    @GetMapping("/getServerScript")
    @RequiresAuthentication
    public Result getServerScript(@RequestParam("project_id") Long project_id,
                                  @RequestParam("applicationName") String applicationName,
                                  @RequestParam("script") String script) {
        return collectService.getServerScript(project_id, applicationName, script);
    }

    @GetMapping("/getServer")
    @RequiresAuthentication
    Result getServer(@RequestParam("project_id") Long project_id) {
        return collectService.getServer(project_id);
    }

    @PostMapping("/addCluster")
    @RequiresAuthentication
    Result addCluster(@RequestBody Cluster cluster) {
        return collectService.addCluster(cluster);
    }

    // 采集模块
    @GetMapping("/getCollectFormDetail")
    @RequiresAuthentication
    public Result getCollectFormDetail(@RequestParam("id") Long id) {
        return collectService.getCollectFormDetail(id);
    }

    @PostMapping("/addCollect")
    @RequiresAuthentication
    public Result addCollect(@RequestBody JSONObject jsonParam) throws IOException {
        String name = jsonParam.get("name").toString();
        Long deploy_id = jsonParam.getLong("deploy_id");
        Boolean is_default = jsonParam.getBool("is_default");
        String log_name = jsonParam.get("log_name").toString();
        String use_case_name = jsonParam.get("use_case_name").toString();
        Long project_id = jsonParam.getLong("project_id");
        List<Map<String, Object>> collectPackageList = (List<Map<String, Object>>) jsonParam.get("collectPackageList");
        return collectService.addCollect(name, deploy_id, is_default, log_name, use_case_name, project_id, collectPackageList);
    }

    @GetMapping("/getCollect")
    @RequiresAuthentication
    public Result getCollect(@RequestParam("currentPage") Long currentPage, @RequestParam("pageSize") Long pageSize,
                             @RequestParam("project_id") Long project_id) {
        return collectService.getCollect(currentPage, pageSize, project_id);
    }

    @PostMapping("/buildCollect")
    @RequiresAuthentication
    public Result buildCollect(@RequestBody JSONObject jsonParam) {
        Long id = jsonParam.getLong("id");
        Result result = collectService.buildCollect(id);
        if (result.getMsg().equals("日志为空")) {
            System.out.println("日志为空");
            result = collectService.buildCollect(id);
        }
        return result;
    }

    @GetMapping("/downloadLog")
    public void downloadLog(@RequestParam("id") Long id) {
        collectService.downloadLog(id);
    }

    @GetMapping("/previewAgentConfig")
    @RequiresAuthentication
    public Result previewAgentConfig(@RequestParam("collect_id") Long collect_id) {
        return collectService.previewAgentConfig(collect_id);
    }

    @GetMapping("/previewConfig")
    @RequiresAuthentication
    public Result previewConfig(@RequestParam("collect_id") Long collect_id) {
        return collectService.previewConfig(collect_id);
    }

}
