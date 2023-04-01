package com.processmining.logdeploy.autodeploy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Deploy implements Serializable {

    private Long id;

    @NotNull(message = "应用名不能为空")
    private String application_name;

    private Long server_id;
    private Long cluster_id;

    public enum Type {
        SERVER, CLUSTER
    }

    @NotNull(message = "应用部署类型不能为空")
    private Type type;

    @NotNull(message = "应用部署目录不能为空")
    private String directory;

    private LocalDateTime created;

    @NotNull(message = "应用压缩格式不能为空")
    private String compression_format;

    private Boolean is_execute;
    private String script_path;

    @NotNull(message = "项目id不能为空")
    private Long project_id;

    private String version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApplication_name() {
        return application_name;
    }

    public void setApplication_name(String application_name) {
        this.application_name = application_name;
    }

    public Long getServer_id() {
        return server_id;
    }

    public void setServer_id(Long server_id) {
        this.server_id = server_id;
    }

    public Long getCluster_id() {
        return cluster_id;
    }

    public void setCluster_id(Long cluster_id) {
        this.cluster_id = cluster_id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public String getCompression_format() {
        return compression_format;
    }

    public void setCompression_format(String compression_format) {
        this.compression_format = compression_format;
    }

    public Boolean getIs_execute() {
        return is_execute;
    }

    public void setIs_execute(Boolean is_execute) {
        this.is_execute = is_execute;
    }

    public String getScript_path() {
        return script_path;
    }

    public void setScript_path(String script_path) {
        this.script_path = script_path;
    }

    public Long getProject_id() {
        return project_id;
    }

    public void setProject_id(Long project_id) {
        this.project_id = project_id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
