package controller;

public interface LoginServiceInterface {

    // 定义客户端版本号clientversion
    public static final long versionID = 1L;
    public String login(String username, String password);
}
