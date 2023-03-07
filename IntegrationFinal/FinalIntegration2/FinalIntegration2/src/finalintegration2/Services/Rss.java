/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalintegration2.Services;
import java.io.*;
import java.net.*;

public class Rss {

    public static void main(String[] args) {
        System.out.println(readRSS("https://www.imdb.com/news/ni63983161"));
       System.out.println(readRSS("https://rss.app/feeds/tcUbBqXKZsw6YYNd.xml")); 
    }

    public static String readRSS(String urlAddress) {
        try {
            URL rssUrl = new URL(urlAddress);
            BufferedReader in = new BufferedReader(new InputStreamReader(rssUrl.openStream()));
            String sourceCode = "";
            String line;
            while ((line = in.readLine()) != null) {
                if (line.contains("<title>")) {
                    int firstPos = line.indexOf("<title>");
                    String temp = line.substring(firstPos);
                    temp = temp.replace("<title>", "");
                    int lastPos = temp.indexOf("</title>");
                    temp = temp.substring(0, lastPos);
                    temp = removeCDATA(temp); // Remove CDATA tags and surrounding characters
                    sourceCode += temp + "\n";
                }
            }
            in.close();
            return sourceCode;
        } catch (MalformedURLException ue) {
            System.out.println("Malformed URL");
        } catch (IOException ioe) {
            System.out.println("Something went wrong reading the contents");
        }
        return null;
    }

    public static String removeCDATA(String input) {
        String output = input.replaceAll("<!\\[CDATA\\[|\\]\\]>", "");
        return output;
    }
}