package kr.co.team.res.common.utill;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class MessageHandler {
    // 얼러트만
    public static void alert(HttpServletResponse response, String message){

        try {
            response.setContentType("text/html; charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.write("<script>alert('"+message+"');</script>");
            writer.flush();
            writer.close();
        } catch(Exception e) {
            e.printStackTrace();
        }

    }
    // 얼러트 후 이전 페이지로 이동
    public static void alertAndBack(HttpServletResponse response , String message){

        try {
            response.setContentType("text/html; charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.write("<script>alert('"+message+"');history.go(-1);</script>");
            writer.flush();
            writer.close();
        } catch(Exception e) {
            e.printStackTrace();
        }

    }
    // 얼러트 후 창닫기
    public static void alertAndClose(HttpServletResponse response , String message){
        try {
            response.setContentType("text/html; charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.write("<script>alert('"+message+"');window.close();</script>");
            writer.flush();
            writer.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
