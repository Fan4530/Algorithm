package DataStructureDesign;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by program on 12/17/2017.
 */

//简单的做法： 实现功能(读的多，也就是根据short返回长的操作多)
// encode: input long, return short
// long url --> （根据map<long url, int>）int number --> (by encoding) short url
//                                        short url --> map<long url,, short url>
//                                        short url return
//
//decode: input short, return long
// directly return map<short, long>
//
//
//Daily User: 100M(已知)
//        Daily usage per person: (Write) long2short 0.1, (Read) short2long 1（已知）
//        Daily request: Write 100M * 0.1 = 10M, Read 100M * 1 = 100M
//        QPS: Since a day is 86400s approximately 100K(记住这个数字).
//        Write 10M / 100 K = 100, Read 100M / 100 K = 1K
//
//        Peak QPS: Write 200, Read 2K
//        (Thousand lev el can be handled by a single SSD MySQL Machine)

public class TinyURL {
    HashMap<String, Integer> longToInt = new HashMap<>();
    HashMap<String, String> codeToLong = new HashMap<>();
    String dict = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        Integer digit = longToInt.get(longUrl);
        if(digit == null) {
            longToInt.put(longUrl, longToInt.size() + 1);
        }
        digit = longToInt.get(longUrl);
        StringBuilder sb = new StringBuilder();
        sb.append("http://tinyurl.com/");
        while(digit != 0) {
            sb.append(dict.charAt(digit % 62));
            digit /= 62;
        }
        String res = sb.toString();
        codeToLong.put(res, longUrl);
        return  res;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return codeToLong.get(shortUrl);
    }
}
