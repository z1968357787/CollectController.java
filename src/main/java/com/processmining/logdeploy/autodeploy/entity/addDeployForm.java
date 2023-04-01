package com.processmining.logdeploy.autodeploy.entity;


import org.springframework.web.multipart.MultipartFile;

public class addDeployForm {
    private MultipartFile file;
    private String type;
    private  Long nodeId;
    private  String directory;
    private  Long project_id;
    private  Boolean is_execute;
    private  String script;
    private  String code;
    private  String version;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public Long getProject_id() {
        return project_id;
    }

    public void setProject_id(Long project_id) {
        this.project_id = project_id;
    }

    public Boolean getIs_execute() {
        return is_execute;
    }

    public void setIs_execute(Boolean is_execute) {
        this.is_execute = is_execute;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
