package errorHandling;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestTrackGameHandler {

  /*
   * Testing strategy: 1.handleAthlete 1）输入的错误类型：信息没被<,>括起来/信息缺失/运动员姓名格式错误/
   * 运动员国籍信息不是三个大写字母/年龄和号码不是正整数/最好成绩不是两位小数 2.handleGame 1）输入的错误类型：信息中无'='符号/比赛名称不是整数/比赛不是100或200或400
   * 3.handleNumOfTracks 1）输入的错误类型：信息中无'='符号/轨道数目不是整数/轨道数目不在[4,10]之间
   */
  @Test
  public void testTrackGame() {
    TrackGameHandler h = new TrackGameHandler();
    assertNull(h.filesyntaxHandling(null));
    assertNull(h.filesyntaxHandling("a"));
  }

  @Test
  public void testAthlete() {
    TrackGameHandler h = new TrackGameHandler();
    h.filesyntaxHandling("Athlete ::= <Bolt,1,JAM,38,9.94>");
    h.filesyntaxHandling("Athlete ::= <Bolt,1,JAM,38,9.94");
    h.filesyntaxHandling("Athlete ::= <Bolt,1,JAM,38>");
    h.filesyntaxHandling("Athlete ::= <Bo#lt,1,JAM,38,9.94>");
    h.filesyntaxHandling("Athlete ::= <Bolt,1,JABM,38,9.94>");
    h.filesyntaxHandling("Athlete ::= <Bolt,1.0,JAM,38,9.94>");
    h.filesyntaxHandling("Athlete ::= <Bolt,1,JAM,38.0,9.94>");
    h.filesyntaxHandling("Athlete ::= <Bolt,1,JAM,38,9.9>");
    h.filesyntaxHandling("Athlete ::= <Bolt,1,JAM,38,nine>");

  }

  @Test
  public void testGame() {
    TrackGameHandler h = new TrackGameHandler();
    h.filesyntaxHandling("Game ::= 100");
    h.filesyntaxHandling("Game ::= 200");
    h.filesyntaxHandling("Game ::= 400");
    h.filesyntaxHandling("Game ::100");
    h.filesyntaxHandling("Game ::= one hundred");
    h.filesyntaxHandling("Game ::= 1000");
  }

  @Test
  public void testTrack() {
    TrackGameHandler h = new TrackGameHandler();
    h.filesyntaxHandling("NumOfTracks ::= 5 ");
    h.filesyntaxHandling("NumOfTracks :: 5 ");
    h.filesyntaxHandling("NumOfTracks ::= five");
    h.filesyntaxHandling("NumOfTracks ::= 15");
    h.filesyntaxHandling("NumOfTracks ::= 1");
  }
}
