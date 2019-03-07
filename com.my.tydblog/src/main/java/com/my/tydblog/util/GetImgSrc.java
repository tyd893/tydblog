package com.my.tydblog.util;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetImgSrc {
    public static List<String> getImgSrc(String htmlStr) {

        if( htmlStr == null ){

            return null;
        }

        String img = "";
        Pattern p_image;
        Matcher m_image;
        List<String> pics = new ArrayList<String>();

        String regEx_img = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
        p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
        m_image = p_image.matcher(htmlStr);
        while (m_image.find()) {
            img = img + "," + m_image.group();
            // Matcher m =
            // Pattern.compile("src=\"?(.*?)(\"|>|\\s+)").matcher(img); //匹配src
            Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img);

            while (m.find()) {
                pics.add(m.group(1));
            }
        }
        return pics;
    }

	public static void main(String[] args) {

		String str = "<div><img src=\"http://47.95.209.196/easyteaching-core/mobile/teachingrecords/getUrl?path=teachrecord/2018-08-10/20180810161528fce8f644e8224841a291ea7633efb395.jpeg\" alt=\"\"></div><br>";

		System.out.println( getImgSrc(str).get(0) );

	}

}
