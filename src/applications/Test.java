package applications;

import centralObject.Nucleus;
import centralObject.NucleusFactory;
import javafx.application.Application;
import physicalObject.*;
import java.util.*;
import java.util.regex.*;
import java.io.*;
import java.time.LocalDateTime;

public class Test {

  // public static void main(String[] args) throws FileNotFoundException {
  // TODO Auto-generated method stub
  /*
   * TrackGame tg1=new TrackGame(); File f1=new File("src\\inputfile\\TrackGame.txt");
   */
  // tg1.initFromFile(f1);
  // System.out.println("pAp".matches("([[a-z][A-Z][0-9]]+)"));//label
  // System.out.println("Adsf_\\dsf".matches("([a-z[A-Z]]+)"));//word
  // System.out.println("afdsf dsfsdfs fdsSDF
  // sf".matches("([[a-z][A-Z][0-9]\\s]+)"));//sentence

  // System.out.println(".".matches("."));
  /*
   * System.out.println("Game ::= 100".matches("Game ::= (100|200|400)")); Pattern
   * p=Pattern.compile("(100|200|400)"); Matcher m=p.matcher("200"); System.out.println(m.find());
   */
  /*
   * String s="1/2;2/3;3/4;4/5;5/6"; Pattern p=Pattern.compile("([0-9]+)/([0-9]+);");
   * System.out.println(s.matches("(([0-9]+)/([0-9]+);)+")); Matcher m=p.matcher(s); while(m.find())
   * { System.out.println(m.group(0)); } Scanner sc=new Scanner(s); for(int i=0;i<s.length();i+=4) {
   * System.out.println(s.charAt(i)); System.out.println(s.charAt(i+2)); }
   */
  /*
   * String apppstr=
   * "App\\s*::=\\s*<([[a-z][A-Z][0-9]]+),([[a-z][A-Z][0-9]]+),([[a-z][A-Z][0-9]-_\\.]+)," +
   * "(\"[[a-z][A-Z][0-9]\\s]+\"),(\"[[a-z][A-Z][0-9]\\s]+\")>\\s*"; Pattern
   * athletep=Pattern.compile(apppstr); Matcher matcher; String
   * str="App ::= <Wechat,Tencent,13.2,\"The most popular " +
   * "social networking App in China\",\"Social network\">";
   * 
   */
  // System.out.println(str.matches(apppstr));

  /*
   * String sen="Period ::= Hour"; String senpa="Period\\s*::=\\s*(Hour|Day|Week|Month)\\s*";
   * Pattern p=Pattern.compile(senpa); Matcher m=p.matcher(sen); m.find();
   * System.out.println(m.group(1)); System.out.println(sen.matches(senpa));
   * 
   * String s1="Wechat"; String sp1="([[a-z][A-Z][0-9]]+)";
   */
  // System.out.println(s1.matches(sp1));

  /*
   * 
   * ApplicationFactory f=new AppEcosystemFactory(); PersonalAppEcosystem
   * p=(PersonalAppEcosystem)f.getApplication(); File file=new
   * File("src\\inputfile\\PersonalAppEcosystem_Medium2.txt"); p.initFromFile(file);
   * p.ArrangeEcos();int i=1; List<List<AppUseNote>> eco=p.getEcos(); List<MyDate>
   * periods=p.getPeriodList();
   */

  /*
   * for(List<AppUseNote> al:eco) { System.out.println("list "+i);i++; for(AppUseNote aun:al) {
   * System.out.println(aun.toString()); } }
   * System.out.println();System.out.println();System.out.println(); List<AppUseNote> l3=eco.get(2);
   * for(AppUseNote aun:l3) { System.out.println(aun.toString()); }
   */
  /*
   * System.out.println("-------Dates:"); for(MyDate d:periods) { System.out.println(d.toString());
   * }
   */

  // Application.launch(args);

  // }

}
