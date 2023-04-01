package com.processmining.logdeploy.autodeploy.service;

import com.processmining.logdeploy.autodeploy.common.lang.Result;
import com.processmining.logdeploy.autodeploy.entity.Cluster;
import com.processmining.logdeploy.autodeploy.entity.Deploy;
import com.processmining.logdeploy.autodeploy.entity.FileDirNodeVo;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface CollectService {

    public List<FileDirNodeVo> projectDir = null;
    Result getNodes(Long project_id, String type);
    Result addDeploy(MultipartFile file, String type, Long nodeId, String directory, Long project_id, Boolean is_execute, String script, String code, String version);
    Result getDeploy(Long currentPage, Long pageSize, Long project_id);
    Result deleteDeploy(Deploy deploy);
    Result deleteDeployBatch(List<Map<String, Object>> mapList);
    Result queryDeploy(String selectLabel, String queryInfo, Long currentPage, Long pageSize, Long project_id);
    Result uploadServerScript(MultipartFile script, Long project_id, String applicationName);
    Result getServerScript(Long project_id, String applicationName, String script);
    Result getCollectFormDetail(Long id);
    Result addCollect(String name, Long deploy_id, Boolean is_default, String log_name, String use_case_name, Long project_id, List<Map<String, Object>> collectPackageList) throws IOException;
    Result getCollect(Long currentPage, Long pageSize, Long project_id);
    Result buildCollect(Long id);
    void downloadLog(Long id);
    Result previewAgentConfig(Long collect_id);
    Result previewConfig(Long collect_id);

    Result getServer(Long project_id);
    Result addCluster(Cluster cluster);

    Result dfsFile(String filePath, int d_id);

    public boolean findClassInJar(File file,List<String> useCaseList) throws IOException;
}
