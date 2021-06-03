/**
 * FileName: Const
 * Author:   jason
 * Date:     2021/4/5 20:37
 * Description:
 */
package org.zhangxujie.konfig.common;

public class Const {

    //用户操作权限
    public final static String AUTH_ROOT = "root";
    public final static String AUTH_CFG_READ = "cfg:read";
    public final static String AUTH_CFG_EDIT = "cfg:edit";
    public final static String AUTH_USER_READ = "user:read";
    public final static String AUTH_USER_EDIT = "user:edit";

    //配置上线审核
    public final static int CFG_AUDIT_UNDO = 0;
    public final static int CFG_AUDIT_APPROVE = 1;
    public final static int CFG_AUDIT_REJECT = 2;

    //配置权限
    public final static int CFG_PERMISSION_ACCOUNT = 0;
    public final static int CFG_PERMISSION_GROUP = 1;

    //操作记录
    public final static String LOG_OPTYPE_USER = "USER";
    public final static String LOG_OPTYPE_GROUP = "GROUP";
    public final static String LOG_OPTYPE_CONFIG = "CONFIG";
    public final static String LOG_OPTYPE_PERMISSION = "PERMISSION";
    public final static String LOG_OPTYPE_OTHER = "OTHER";

    //Kafka推送相关
    public final static String MQ_TOPIC_CONFIG = "CONFIG_COLLECTION_";
    public final static Integer MQ_CONFIG_STATUS_ONLINE = 0;
    public final static Integer MQ_CONFIG_STATUS_DRAFT = 1;


}
