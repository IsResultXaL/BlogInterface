package com.caogen.blog.dto;

/**
 * 日志记录信息
 */
public class LogInfo {

    private String className;

    private String methodName;

    private String params;

    private Long exeuTime;

    private String remark;

    private String createDate;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public Long getExeuTime() {
        return exeuTime;
    }

    public void setExeuTime(Long exeuTime) {
        this.exeuTime = exeuTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "LogInfoAnno{" +
                "className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", params='" + params + '\'' +
                ", exeuTime=" + exeuTime +
                ", remark='" + remark + '\'' +
                ", createDate='" + createDate + '\'' +
                '}';
    }
}
