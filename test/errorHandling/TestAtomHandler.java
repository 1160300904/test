package errorHandling;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestAtomHandler {

  /*
   * Testing strategy: 1.FilesyntaxHandling 1）输入是否为null
   * 2）输入中包括：NumberOfElectron/ElementName/NumberOfTracks/其他 2.handleNumberOfElectron
   * 1）输入中的错误类型：整体格式错误/轨道上物体的格式不符合语法 3.handleElementName 1）输入中是否有错误
   * 2）输入的错误类型：无元素名称/表达式中无'='符号/元素名称字母格式错误 4.handleNumOfTracks 1）输入中是否有错误
   * 2）输入的错误类型：表达式中无'='符号/轨道数不是整数/轨道数没用数字描述，而用 其他格式
   * 
   */
  @Test
  public void testAtomHandler() {
    AtomStructureHandler h = new AtomStructureHandler();
    assertNull(h.filesyntaxHandling("A"));
    String str = "The syntax of element numbers on track is \"int\\int\"";
    assertEquals(str, h.filesyntaxHandling("NumberOfElectron ::= 1/2;2/8;3/18.0;4/8;5/1"));
    str = "ElementName ::=";
    h.filesyntaxHandling(str);
    h.filesyntaxHandling(null);
    str = "ElementName ::= Rba";
    h.filesyntaxHandling(str);
    str = "NumberOfTracks ::= 11.1";
    h.filesyntaxHandling(str);
    str = "NumberOfTracks ::= -5";
    h.filesyntaxHandling(str);
    str = "NumberOfElectron ::= 1/2,2/8,3/18,4/8,5/1";
    h.filesyntaxHandling(str);
    str = "NumberOfElectron ::= 1/2;2/8;3/18.0;4/8;5/1";
    h.filesyntaxHandling(str);
    str = "NumberOfElectron ::= one in the first orbit";
    h.filesyntaxHandling(str);
    str = "NumberOfElectron ::= one in the first orbit";
    h.filesyntaxHandling(str);
    str = "ElementName :: Rb";
    h.filesyntaxHandling(str);
    str = "ElementName ::= b";
    h.filesyntaxHandling(str);
    str = "ElementName ::= RD";
    h.filesyntaxHandling(str);
    str = "ElementName ::= Rb";
    h.filesyntaxHandling(str);
    str = "ElementName ::= R";
    h.filesyntaxHandling(str);
    str = "NumberOfTracks :: 5";
    h.filesyntaxHandling(str);
    str = "NumberOfTracks ::= 5";
    h.filesyntaxHandling(str);

  }
}
