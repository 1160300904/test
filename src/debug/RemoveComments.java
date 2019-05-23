package debug;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class RemoveComments {
  public List<String> removeComments(String[] source) {
    boolean inBlock = false;
    StringBuffer newline = new StringBuffer();
    List<String> ans = new ArrayList<>();
    for (String line : source) {
      int i = 0;
      char[] chars = line.toCharArray();
      if (!inBlock) {
        newline = new StringBuffer();
      }
      while (i < line.length()) {
        // i+1 <= line.length()----i+1 < line.length()
        if (!inBlock && i + 1 < line.length() && chars[i] == '/' && chars[i + 1] == '*') {
          inBlock = true;
        } else if (!inBlock && i + 1 < line.length() && chars[i] == '/' && chars[i + 1] == '/') {
          // consider'//'
          inBlock = false;
          break;
        } else if (inBlock && i + 1 < line.length() && chars[i] == '*' && chars[i + 1] == '/') {
          inBlock = false;
          i++;// or the '/' character will be added to the answer string.
        } else if (!inBlock) {
          newline.append(chars[i]);
        }
        i++;
      }
      if (!inBlock && newline.length() > 0) {
        ans.add(new String(newline));
      }
    }
    return ans;
  }
}
