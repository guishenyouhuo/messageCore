package com.message.utils;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MysqlBackUp {
    public static Logger logger = LoggerFactory.getLogger(MysqlBackUp.class);
	public static boolean backUp(String dbName,String mysqlBackupPath, String command){
        String fPath = mysqlBackupPath+"/" + dbName + ".sql";
        Runtime rt = Runtime.getRuntime();
        boolean result = true;
        try {
            Process child = rt.exec(command);
            InputStream in = child.getInputStream();
            InputStreamReader input = new InputStreamReader(in,"utf8");
            String inStr;
            StringBuffer sb = new StringBuffer("");
            String outStr;
            BufferedReader br = new BufferedReader(input);
            while ((inStr = br.readLine()) != null) {     
                sb.append(inStr + "\r\n");     
            }     
            outStr = sb.toString(); 
            FileOutputStream fout = new FileOutputStream(fPath);
            OutputStreamWriter writer = new OutputStreamWriter(fout, "utf8");    
            writer.write(outStr);
            writer.flush();   
            in.close();     
            input.close();     
            br.close();     
            writer.close();     
            fout.close();     
            logger.info("MYSQL备份成功:{}", dbName);
        } catch (IOException e) {
            logger.error("MYSQL备份异常：{}", dbName, e);
            result = false;
        }
        return result;
    }
}
