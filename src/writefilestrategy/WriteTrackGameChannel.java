package writefilestrategy;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import applications.TrackGame;
import circularOrbit.CircularOrbit;
import physicalObject.Athlete;

public class WriteTrackGameChannel implements WriteTrackGameFile{

  @Override
  public long writeFile(File file, int numOfTrack,
      List<CircularOrbit<String, Athlete>> orbitlist) {
    
    List<String> retlist=new LinkedList<>();
      
    retlist.add("CircularOrbitName::=TrackGame\n");
    retlist.add("NumOfTracks::="+numOfTrack+"\n");
    int orbitSize=orbitlist.size();
    retlist.add("TotalOribitAmount::="+orbitSize+"\n");
    for(int i=1;i<=orbitSize;i++) {
    retlist.add("OribitNumber::="+i+"\n");
    CircularOrbit<String,Athlete> orbit=orbitlist.get(i-1);
    List<HashSet<Athlete>> objects=orbit.getObjOnTracks();
    for(int j=0;j<objects.size();j++) {
      retlist.add("TrackIndex::="+(j+1)+"\n");
      HashSet<Athlete> athsOneTrack=objects.get(j);
      for(Athlete a:athsOneTrack) {
        retlist.add(a.toString()+"\n");
      }
     }
   }
    
    long begin=System.currentTimeMillis();
    
   try (FileChannel channel=new FileOutputStream(file).getChannel()){
    CharBuffer charbuf=CharBuffer.allocate(1024);
    CharsetEncoder encoder = Charset.defaultCharset().newEncoder();
    for(String str:retlist) {
      charbuf.put(str);
      charbuf.flip();
      ByteBuffer byteb=encoder.encode(charbuf);
      channel.write(byteb);
      charbuf.clear();
    }
  } catch (FileNotFoundException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
  } catch (IOException e1) {
    // TODO Auto-generated catch block
    e1.printStackTrace();
  }
       return System.currentTimeMillis()-begin;
  }
}
