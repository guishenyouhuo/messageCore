package com.message.utils;
import java.io.BufferedReader;  
import java.io.DataOutputStream;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.io.UnsupportedEncodingException;  
import java.net.HttpURLConnection;  
import java.net.InetAddress;
import java.net.URL;  
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
  
/** 
 * 根据IP地址获取详细的地域信息 
 * 淘宝API : http://ip.taobao.com/service/getIpInfo.php?ip=218.192.3.42 
 * 新浪API : http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json&ip=218.192.3.42 
 */  
public class AddressUtils {  
    /** 
     * @param content 
     *            请求的参数 格式为：name=xxx&pwd=xxx 
     * @param encoding 
     *            服务器端请求编码。如GBK,UTF-8等 
     * @return 
     * @throws UnsupportedEncodingException 
     */  
    public static String getAddresses(String content, String encodingString)  
            throws UnsupportedEncodingException {  
        // 这里调用淘宝API  
        String urlStr = "http://ip.taobao.com/service/getIpInfo.php";  
        // 从http://whois.pconline.com.cn取得IP所在的省市区信息  
        String returnStr = getResult(urlStr, content, encodingString);  
        if (returnStr != null) {  
            // 处理返回的省市区信息  
            returnStr = decodeUnicode(returnStr);  
            String[] temp = returnStr.split(",");  
            if(temp.length<3){  
                return "0";//无效IP，局域网测试  
            }  
            return returnStr;  
        }  
        return null;  
    }  
    /** 
     * @param urlStr 
     *            请求的地址 
     * @param content 
     *            请求的参数 格式为：name=xxx&pwd=xxx 
     * @param encoding 
     *            服务器端请求编码。如GBK,UTF-8等 
     * @return 
     */  
    private static String getResult(String urlStr, String content, String encoding) {  
        URL url = null;  
        HttpURLConnection connection = null;  
        try {  
            url = new URL(urlStr);  
            connection = (HttpURLConnection) url.openConnection();// 新建连接实例  
            connection.setConnectTimeout(2000);// 设置连接超时时间，单位毫秒  
            connection.setReadTimeout(2000);// 设置读取数据超时时间，单位毫秒  
            connection.setDoOutput(true);// 是否打开输出流 true|false  
            connection.setDoInput(true);// 是否打开输入流true|false  
            connection.setRequestMethod("POST");// 提交方法POST|GET  
            connection.setUseCaches(false);// 是否缓存true|false  
            connection.connect();// 打开连接端口  
            DataOutputStream out = new DataOutputStream(connection  
                    .getOutputStream());// 打开输出流往对端服务器写数据  
            out.writeBytes(content);// 写数据,也就是提交你的表单 name=xxx&pwd=xxx  
            out.flush();// 刷新  
            out.close();// 关闭输出流  
            BufferedReader reader = new BufferedReader(new InputStreamReader(  
                    connection.getInputStream(), encoding));// 往对端写完数据对端服务器返回数据  
            // ,以BufferedReader流来读取  
            StringBuffer buffer = new StringBuffer();  
            String line = "";  
            while ((line = reader.readLine()) != null) {  
                buffer.append(line);  
            }  
            reader.close();  
            return buffer.toString();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            if (connection != null) {  
                connection.disconnect();// 关闭连接  
            }  
        }  
        return null;  
    }  
    /** 
     * unicode 转换成 中文 
     *  
     * @author fanhui 2007-3-15 
     * @param theString 
     * @return 
     */  
    public static String decodeUnicode(String theString) {  
        char aChar;  
        int len = theString.length();  
        StringBuffer outBuffer = new StringBuffer(len);  
        for (int x = 0; x < len;) {  
            aChar = theString.charAt(x++);  
            if (aChar == '\\') {  
                aChar = theString.charAt(x++);  
                if (aChar == 'u') {  
                    int value = 0;  
                    for (int i = 0; i < 4; i++) {  
                        aChar = theString.charAt(x++);  
                        switch (aChar) {  
                        case '0':  
                        case '1':  
                        case '2':  
                        case '3':  
                        case '4':  
                        case '5':  
                        case '6':  
                        case '7':  
                        case '8':  
                        case '9':  
                            value = (value << 4) + aChar - '0';  
                            break;  
                        case 'a':  
                        case 'b':  
                        case 'c':  
                        case 'd':  
                        case 'e':  
                        case 'f':  
                            value = (value << 4) + 10 + aChar - 'a';  
                            break;  
                        case 'A':  
                        case 'B':  
                        case 'C':  
                        case 'D':  
                        case 'E':  
                        case 'F':  
                            value = (value << 4) + 10 + aChar - 'A';  
                            break;  
                        default:  
                            throw new IllegalArgumentException(  
                                    "Malformed      encoding.");  
                        }  
                    }  
                    outBuffer.append((char) value);  
                } else {  
                    if (aChar == 't') {  
                        aChar = '\t';  
                    } else if (aChar == 'r') {  
                        aChar = '\r';  
                    } else if (aChar == 'n') {  
                        aChar = '\n';  
                    } else if (aChar == 'f') {  
                        aChar = '\f';  
                    }  
                    outBuffer.append(aChar);  
                }  
            } else {  
                outBuffer.append(aChar);  
            }  
        }  
        return outBuffer.toString();  
    }  
    
    public static void getAddressByIp(String ip) throws Exception {  
        // json_result用于接收返回的json数据  
        String json_result = null;  
        try {  
            json_result = AddressUtils.getAddresses("ip=" + ip, "utf-8");  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
        JSONObject json = (JSONObject)JSONObject.parse(json_result);  
        System.out.println("json数据： " + json + "长度：" + json.toString().length());  
        JSONObject dataJSON = (JSONObject)JSONObject.parse(json.get("data").toString());
        
        System.out.println("dataJSON： " + dataJSON + "长度：" + dataJSON.toString().length());  
        String country = dataJSON.get("country").toString();  
        String region = dataJSON.get("region").toString();  
        String region_id = dataJSON.get("region_id").toString();
        String city = dataJSON.get("city").toString();  
        String county = dataJSON.get("county").toString();  
        String isp = dataJSON.get("isp").toString();  
        String area = dataJSON.get("area").toString();  
        System.out.println("IP: " + ip);
        System.out.println("国家： " + country);  
        System.out.println("地区： " + area);  
        System.out.println("地区编号： " + region_id);  
        System.out.println("省份: " + region);  
        System.out.println("城市： " + city);  
        System.out.println("区/县： " + county);  
        System.out.println("互联网服务提供商： " + isp);  
          
        String address = country + "/";  
        address += region + "/";  
        address += city + "/";  
        address += county;  
        System.out.println(address);  
    }  
    
    
    public static String getIpAddr(HttpServletRequest request) {   
        String ip = request.getHeader("x-forwarded-for");   
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
            ip = request.getHeader("Proxy-Client-IP");   
        }   
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
            ip = request.getHeader("WL-Proxy-Client-IP");   
        }   
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
            ip = request.getRemoteAddr();   
            if(ip.equals("127.0.0.1")){     
                //根据网卡取本机配置的IP     
                InetAddress inet=null;     
                try {     
                    inet = InetAddress.getLocalHost();     
                } catch (UnknownHostException e) {     
                    e.printStackTrace();     
                }     
                ip= inet.getHostAddress();     
            }  
        }   
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割  
        if(ip != null && ip.length() > 15){    
            if(ip.indexOf(",")>0){     
                ip = ip.substring(0,ip.indexOf(","));     
            }     
        }     
        return ip;   
 }  
    // 测试  
    public static void main(String[] args) throws Exception {  
        AddressUtils addressUtils = new AddressUtils();  
        // 测试ip 219.136.134.157 中国=华南=广东省=广州市=越秀区=电信  
        String ip = "10.75.3.120";  
        
        getAddressByIp(ip);
//        String address = "";  
//        try {  
//            address = addressUtils.getAddresses("ip="+ip, "utf-8");  
//        } catch (UnsupportedEncodingException e) {  
//            // TODO Auto-generated catch block  
//            e.printStackTrace();  
//        }  
//        System.out.println(address);  
//        // 输出结果为：广东省,广州市,越秀区  
    }  
}  