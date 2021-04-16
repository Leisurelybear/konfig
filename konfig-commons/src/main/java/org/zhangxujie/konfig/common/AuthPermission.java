package org.zhangxujie.konfig.common;

public enum AuthPermission {

    //所有权限
    AUTH_ALL_ROOT(101, "root"),

    //关于用户操作权限
    AUTH_USER_EDIT(111, "user:edit"),
    AUTH_USER_READ(112, "user:read"),

    //关于配置操作权限
    AUTH_CFG_EDIT(121, "cfg:edit"),
    AUTH_CFG_READ(122, "cfg:read");


    private Integer code;
    private String authName;

    AuthPermission(Integer code, String authName) {
        this.code = code;
        this.authName = authName;
    }

    public Integer getCode() {
        return code;
    }

    public String getAuthName() {
        return authName;
    }

    public static AuthPermission getAuthPermissionByAuthName(String authName){
        for (AuthPermission ele : values()) {
            if (ele.getAuthName().equals(authName)) {
                return ele;
            }
        }
        return null;
    }


}
