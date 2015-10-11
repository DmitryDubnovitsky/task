package com.java;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class FileData {
	
	public void getData(String path,int type) throws IOException{
		FileReader fr = new FileReader(path+".txt");
		FileWriter fw = new FileWriter(path+"Out.txt");
		BufferedReader br = new BufferedReader(fr);
		BufferedWriter bw = new BufferedWriter(fw);
		String s;
		while((s = br.readLine()) != null) {
			if(type==10){
				bw.write(String.valueOf(Calculation.evaluate(s,type)));
				bw.close();
			}
		     if(type==2){
			    bw.write(Integer.toBinaryString(Calculation.evaluate(s,type)));	
			    bw.close();
		    }
		   if(type==16){
			    bw.write(Integer.toHexString(Calculation.evaluate(s,type)));
			    bw.close();
		    }
		 }
		   fr.close();
		   fw.close();	
	   }

	public static void main(String[] args) throws IOException {
		FileData data =new FileData();
		Map<String,Integer>map=new HashMap<String,Integer>();
		map.put("C:\\Users\\ִלטענטי\\workspace\\Stack\\files\\Decimal", 10);
		map.put("C:\\Users\\ִלטענטי\\workspace\\Stack\\files\\Binary", 2);
		map.put("C:\\Users\\ִלטענטי\\workspace\\Stack\\files\\Hexadecimal", 16);
		Iterator<Map.Entry<String, Integer>> entries = map.entrySet().iterator();
		while (entries.hasNext()) {
		    Map.Entry<String, Integer> entry = entries.next();
		    data.getData(entry.getKey(), entry.getValue());
		}	
	}
}
