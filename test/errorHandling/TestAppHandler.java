package errorHandling;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestAppHandler {
    /*
     * Testing strategy: 1.FilesyntaxHandling
     * 1）输入的字符串中有哪类信息：App/User/Period/InstallLog/UninstallLog UsageLog/Relation
     * 2.handleRelation 1）输入的错误类型：信息没有被<,>括起来/仅有一个或没有app/任意一个app的名称 为非法的
     * 3.handleUseLog 1）输入的错误类型：信息没有被<,>括起来/句中信息缺失/一个app的名称 为非法的/时间的格式不对/句中的时长不是一个正数
     * 4.handleUnInstallLog 1）输入的错误类型：信息没有被<,>括起来/句中信息缺失/一个app的名称
     * 为非法的/时间的格式不对/句中的时长不是一个正数 5.handleInstallLog
     * 1）输入的错误类型：信息没有被<,>括起来/句中信息缺失/一个app的名称 为非法的/时间的格式不对/句中的时长不是一个正数 6.handleApp
     * 1）输入的错误类型：信息没有被<,>括起来/句中信息缺失/一个app的名称 为非法的/app公司信息非法/app版本信息非法/app功能信息非法
     * app商业领域信息非法 7.handleUser 1）输入的错误类型：信息没有'-'符号/句中无用户名/用户名中有空格/用户名中有其他非法字符
     * 8.handlePeriod 1）输入的错误类型：信息没有'-'符号/句中无分割信息 /period中不是Hour|Day|Week|Month中的一个
     * 
     */
    @Test
    public void testappHandlerNull() {
        PersonalAppHandler h = new PersonalAppHandler();
        assertNull(h.FilesyntaxHandling(null));

    }

    @Test
    public void testRelation() {
        PersonalAppHandler h = new PersonalAppHandler();
        h.FilesyntaxHandling("Relaions ::= <Wechat1,QQ+>");
        h.FilesyntaxHandling("Relation ::= <Wechat1,QQ+>");
        h.FilesyntaxHandling("Relation ::= <Wechat1+,QQ>");
        h.FilesyntaxHandling("Relation ::= Wechat1,QQ");
        h.FilesyntaxHandling("Relation ::= <Wechat1>");
        h.FilesyntaxHandling("Relation ::= <Wechat1,QQ>");
    }

    @Test
    public void testhandleUseLog() {
        PersonalAppHandler h = new PersonalAppHandler();

        h.FilesyntaxHandling("UsageLog ::= <2019-01-01,15:00:00,Wechat,2>");
        h.FilesyntaxHandling("UsageLog ::= 2019-01-01,15:00:00,Wechat,2");
        h.FilesyntaxHandling("UsageLog ::= <2019-01-01,15:00:00,Wechat>");
        h.FilesyntaxHandling("UsageLog ::= <2019=01=01,15:00:00,Wechat,2>");
        h.FilesyntaxHandling("UsageLog ::= <2019-01-01,15.00.00,Wechat,2>");
        h.FilesyntaxHandling("UsageLog ::= <2019-01-01,15:00:00,We+chat,2>");
        h.FilesyntaxHandling("UsageLog ::= <2019-01-01,15:00:00,Wechat,-2>");
    }

    @Test
    public void testhandleUnInstallLog() {
        PersonalAppHandler h = new PersonalAppHandler();

        h.FilesyntaxHandling("UninstallLog ::= <2019-01-02,13:00:28,Didi>");
        h.FilesyntaxHandling("UninstallLog ::= 2019-01-02,13:00:28,Didi>");
        h.FilesyntaxHandling("UninstallLog ::= <2019-01-02,13:00:28>");
        h.FilesyntaxHandling("UninstallLog ::= <2019=01-02,13:00:28,Didi>");
        h.FilesyntaxHandling("UninstallLog ::= <2019-01-02,13.00:28,Didi>");
        h.FilesyntaxHandling("UninstallLog ::= <2019-01-02,13:00:28,Didi+>");
    }

    @Test
    public void testhandleInstallLog() {
        PersonalAppHandler h = new PersonalAppHandler();

        h.FilesyntaxHandling("InstallLog ::= <2019-01-02,13:19:30,BaiduMap>");
        h.FilesyntaxHandling("InstallLog ::= <2019-01-02,13:19:30,BaiduMap");
        h.FilesyntaxHandling("InstallLog ::= <2019-01-02,13:19:30>");
        h.FilesyntaxHandling("InstallLog ::= <2019=01-02,13:19:30,BaiduMap>");
        h.FilesyntaxHandling("InstallLog ::= <2019-01-02,13.19:30,BaiduMap>");
        h.FilesyntaxHandling("InstallLog ::= <2019-01-02,13:19:30,BaiduMap+>");
    }

    @Test
    public void testhandleApp() {
        PersonalAppHandler h = new PersonalAppHandler();

        h.FilesyntaxHandling("App ::= <Didi,Didi,ver03.32,\"The most popular car sharing App in China\",\"Travel\">");
        h.FilesyntaxHandling("App ::= <Didi,Didi,ver03.32,\"The most popular car sharing App in China\",\"Travel\"");
        h.FilesyntaxHandling("App ::= <Didi,Didi,ver03.32,\"The most popular car sharing App in China\">");
        h.FilesyntaxHandling("App ::= <Didi+,Didi,ver03.32,\"The most popular car sharing App in China\",\"Travel\">");
        h.FilesyntaxHandling("App ::= <Didi,Didi~+,ver03.32,\"The most popular car sharing App in China\",\"Travel\">");
        h.FilesyntaxHandling("App ::= <Didi,Didi,ver03.3~!2,\"The most popular car sharing App in China\",\"Travel\">");
        h.FilesyntaxHandling("App ::= <Didi,Didi,ver03.32,The most popular car sharing App in China\",\"Travel\">");
        h.FilesyntaxHandling("App ::= <Didi,Didi,ver03.32,\"The most popular car sharing App in China,\"Travel\">");
        h.FilesyntaxHandling("App ::= <Didi,Didi,ver03.32,\"The most popular car sharing App!+ in China\",\"Travel\">");
        h.FilesyntaxHandling("App ::= <Didi,Didi,ver03.32,\"The most popular car sharing App in China\",Travel\">");
        h.FilesyntaxHandling("App ::= <Didi,Didi,ver03.32,\"The most popular car sharing App in China\",\"Travel>");
        h.FilesyntaxHandling("App ::= <Didi,Didi,ver03.32,\"The most popular car sharing App in China\",\"Tra%$vel\">");
    }

    @Test
    public void testhandleUsername() {
        PersonalAppHandler h = new PersonalAppHandler();

        h.FilesyntaxHandling("User ::= TimWong");
        h.FilesyntaxHandling("User :: TimWong");
        h.FilesyntaxHandling("User ::= Tim Wong");
        h.FilesyntaxHandling("User ::= ");
        h.FilesyntaxHandling("User ::= Tim!$%Wong");
    }

    @Test
    public void testhandlePeriod() {
        PersonalAppHandler h = new PersonalAppHandler();

        h.FilesyntaxHandling("Period ::= Day");
        h.FilesyntaxHandling("Period ::= Hour");
        h.FilesyntaxHandling("Period ::= Month");
        h.FilesyntaxHandling("Period ::= Week");
        h.FilesyntaxHandling("Period ::= Minute");
        h.FilesyntaxHandling("Period ::= ");
        h.FilesyntaxHandling("Period ::");
        h.FilesyntaxHandling("Period ::= Da##y");
    }
}
