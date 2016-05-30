package com.huang.superbracelet.common.constant;

/**
 * Created by 黄家远 on 16/4/14.
 */
public class UrlConstant {

    /**
     * 项目的ip
     **/
    private static final String IP = "http://meditool.cn/";

    /**
     * 项目的url头部
     **/
    private static final String urlHead = IP + "Apiuserv2/";

    /**
     * 登录的url
     **/
    public static final String login_url = urlHead + "login?";

    /**
     * 医讯头条url
     */
    public static final String newsinfo_url = IP + "Apisubscriptionv2/getarticlelst?";


    //------------------------------------------考试-------------------------------------------------

    /**
     * 考试ip
     **/
    private static final String KAOSHI_IP = "http://202.113.245.4/Swcf/Calculator.svc/";

    /**
     * 考试的url头部
     **/
    private static final String KAOSHI_HEAD = KAOSHI_IP + "Data/";

    /**
     * 考试登录的url
     **/
    public static final String KAOSHI_LOGIN = KAOSHI_HEAD + "denglu?";

    /**
     * 考试科目的url
     **/
    public static final String KAOSHI_SUBJECT = KAOSHI_HEAD + "kutypelist?";

    /**
     * 考试题目的url
     **/
    public static final String KAOSHI_EXAMLIST = KAOSHI_HEAD + "Examlclist?";

    /**
     * 提交分数的url
     **/
    public static final String KAOSHI_INSERT_GRADE = KAOSHI_HEAD + "insertzygrade?";

    /**
     * 进度排行,正确率排行,总分排行的url
     **/
    public static final String KAOSHI_GRADE_LIST = KAOSHI_HEAD + "zygradelist?";

    /**
     * 获取个人答题进度
     **/
    public static final String KAOSHI_GET_GRADE = KAOSHI_HEAD + "getzygrade?";

    /**
     * 所有错题数目统计
     **/
    public static final String KAOSHI_ERRORLIST = KAOSHI_HEAD + "errorlist?";

    /**
     * 未答对的错题数目统计
     **/
    public static final String KAOSHI_ERRORLIST_W = KAOSHI_HEAD + "errorlistw?";

    /**
     * 获取第几轮的错题
     **/
    public static final String KAOSHI_ROUNDERROR = KAOSHI_HEAD + "rounderror?";

    /**
     * 未答对的错题
     **/
    public static final String KAOSHI_ROUNDERROR_W = KAOSHI_HEAD + "rounderrorw?";

    /**
     * 用户每关的分数
     **/
    public static final String KAOSHI_GRADE_SHOW = KAOSHI_HEAD + "getzygradeshow?";

    /**
     * 错题集提交（批量提交）
     **/
    public static final String KAOSHI_INSERT_ERRORLIST = KAOSHI_IP + "inserterrorlist?";
}
