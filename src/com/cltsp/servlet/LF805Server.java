package com.cltsp.servlet;

import com.cltsp.bean.Turgoscope;
import com.cltsp.util.LFDateServ;
import com.cltsp.util.NumConver;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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
        String data;
        if(!lf805_pre.equals(data=req.getParameter("data")))
        {
            //lf805_pre是发数据前的握手协议
            //下面是对发来的数据进行处理
            char datachar[]=data.toCharArray();
            Turgoscope turgoscope=new Turgoscope();
            turgoscope.setDatetime(LFDateServ.parseLfTime(
                    String.copyValueOf(datachar,35,7)
            ));
            turgoscope.setMacAddress(
                    String.copyValueOf(datachar,6,16)
            );
            turgoscope.setMaxBp(NumConver.HexStringToIntStr(
                    String.copyValueOf(datachar,44,2))
            );
            turgoscope.setMinBp(NumConver.HexStringToIntStr(
                    String.copyValueOf(datachar,46,2))
            );
            turgoscope.setHr(NumConver.HexStringToIntStr(
                    String.copyValueOf(datachar,48,2))
            );
            //模拟Http将数据发送到服务器端，PS：直接发送数据还是json文件.
            //构建Json
        }
        PrintWriter pw=resp.getWriter();
        resp.setContentType("text/html");
        String date= LFDateServ.produceLfTime();
        StringBuffer sb=new StringBuffer();
        sb.append(lf805_stra).append(date).append(lf805_strb);
        pw.println(sb.toString());
    }
}
