package com.cltsp.servlet;

import com.cltsp.bean.LF805;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintStream;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by zclzc on 2016/7/14.
 */
public class LF805Server extends HttpServlet{
    // TODO: 2016/7/23 处理LF805设备的数据
    private static final String lf805_pre="300000accf23888ee4f4080509ac7efa00000000000000000000000000000000fd6c6317";
    private static final String lf805_stra="A000000000000000010";
    private static final String lf805_strb="00000000000000000000003142ca97\r";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //生产者，生产资料可以并行处理
        LinkedBlockingQueue<LF805> quene=new LinkedBlockingQueue<>(10);
        String data=req.getParameter("data");
        if (data!=null&&!data.equals(lf805_pre)){
        }
        PrintStream ps=new PrintStream(resp.getOutputStream());
        resp.setContentType("text/html");
        ps.println();
    }
}
