package errorHandling;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestTrackGameHandler {

    /*
     * Testing strategy: 1.handleAthlete 1）输入的错误类型：信息没被<,>括起来/信息缺失/运动员姓名格式错误/
     * 运动员国籍信息不是三个大写字母/年龄和号码不是正整数/最好成绩不是两位小数 2.handleGame
     * 1）输入的错误类型：信息中无'='符号/比赛名称不是整数/比赛不是100或200或400 3.handleNumOfTracks
     * 1）输入的错误类型：信息中无'='符号/轨道数目不是整数/轨道数目不在[4,10]之间
     */
    @Test
    public void testTrackGame() {
        TrackGameHandler h = new TrackGameHandler();
        assertNull(h.FilesyntaxHandling(null));
        assertNull(h.FilesyntaxHandling("a"));
    }

    @Test
    public void testAthlete() {
        TrackGameHandler h = new TrackGameHandler();
        h.FilesyntaxHandling("Athlete ::= <Bolt,1,JAM,38,9.94>");
        h.FilesyntaxHandling("Athlete ::= <Bolt,1,JAM,38,9.94");
        h.FilesyntaxHandling("Athlete ::= <Bolt,1,JAM,38>");
        h.FilesyntaxHandling("Athlete ::= <Bo#lt,1,JAM,38,9.94>");
        h.FilesyntaxHandling("Athlete ::= <Bolt,1,JABM,38,9.94>");
        h.FilesyntaxHandling("Athlete ::= <Bolt,1.0,JAM,38,9.94>");
        h.FilesyntaxHandling("Athlete ::= <Bolt,1,JAM,38.0,9.94>");
        h.FilesyntaxHandling("Athlete ::= <Bolt,1,JAM,38,9.9>");
        h.FilesyntaxHandling("Athlete ::= <Bolt,1,JAM,38,nine>");

    }

    @Test
    public void testGame() {
        TrackGameHandler h = new TrackGameHandler();
        h.FilesyntaxHandling("Game ::= 100");
        h.FilesyntaxHandling("Game ::= 200");
        h.FilesyntaxHandling("Game ::= 400");
        h.FilesyntaxHandling("Game ::100");
        h.FilesyntaxHandling("Game ::= one hundred");
        h.FilesyntaxHandling("Game ::= 1000");
    }

    @Test
    public void testTrack() {
        TrackGameHandler h = new TrackGameHandler();
        h.FilesyntaxHandling("NumOfTracks ::= 5 ");
        h.FilesyntaxHandling("NumOfTracks :: 5 ");
        h.FilesyntaxHandling("NumOfTracks ::= five");
        h.FilesyntaxHandling("NumOfTracks ::= 15");
        h.FilesyntaxHandling("NumOfTracks ::= 1");
    }
}
