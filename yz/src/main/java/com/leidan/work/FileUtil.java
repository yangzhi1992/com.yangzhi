package com.leidan.work;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class FileUtil {

	File file = null;
	boolean flag = false;
	public static final char EMPTY = ' ';

	/**
	 * 写入txt文件，可以在原文件内容的基础上追加内容(并判断目录是否存在，不存在则生成目录)
	 * 
	 * @param value
	 *            写入文件内容
	 * @param fileCatage
	 *            文件父目录；
	 * @param fileName
	 *            文件名字；
	 * @param code
	 *            文件的编码；
	 * @throws IOException
	 */
	public void WriteFile(String value, String fileCatage, String fileName,String code) {
		File file = null;
		try {
			file = new File(fileCatage);
			if (!file.isDirectory())
				file.mkdir();
			else {
				file = new File(fileCatage + fileName);
				if (!file.exists())
					file.createNewFile();
				FileOutputStream out = new FileOutputStream(file, true);
				out.write((value+"\r\n").getBytes(code));
				out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取某个目录下所有的文件的全路径和文件名的集合
	 * @param mulu
	 * @return
	 */
	public List<String> getAllFile(String mulu) {
		File file = new File(mulu);
		File[] files = file.listFiles();
		
		if(files == null || files.length <= 0){
			return null;
		}
		
		List<String> allFilePath = new ArrayList<String>();	
		for (File fileInfo : files) {
			if (fileInfo.isDirectory()) {
				getAllFile(fileInfo.toString());
			}else{
				allFilePath.add(fileInfo.toString());
			}
		}
		
		return allFilePath;
	}
	
	/**
	 * 读取文件中的内容到list中 list中每个元素是文件中的每一行数据
	 * @param filePath
	 * @param code
	 * @return
	 */
	public static List<String> readToList(String filePath ,String code) {
		BufferedReader reader = null;
		List<String> linelist = new ArrayList(); 
		try {
			File file = new File(filePath);
			if (!file.exists()){
				return null;
			}
			
			String line = null;
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),code));
			
			while ((line = reader.readLine()) != null) {
				if(line.startsWith("00")){
					linelist.add(line);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				if(reader != null){
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return linelist;
	}
	
	/**
	 * 将内容写到文件中的前面的逻辑
	 * 计算借的总金额，贷的总金额
	 * 每行的内容处理
	 * @param lineList
	 */
	public void writeFileBrfore(List<String> lineList){
		if(lineList == null && lineList.isEmpty()){
			return;
		}
		
		BigDecimal totalLendAmount = new BigDecimal(0.00);//借总金额
		BigDecimal totalLoanAmount = new BigDecimal(0.00);//贷总金额
		
		StringBuffer tempStr = new StringBuffer();
		List<String> listStr = new ArrayList();
		
		for(String line : lineList){
			tempStr.delete(0,tempStr.length());  
			
			String[] tempLine = line.split(" ");
			for(String s : tempLine){
				if("".equals(s) || "\t".equals(s)){
					continue;
				}else{
					tempStr.append(s+",");
				}
			}
			
			String[] tempLine2 = tempStr.toString().split(",");
			totalLendAmount = totalLendAmount.add(new BigDecimal(tempLine2[3].replace("\t", "")));
			totalLoanAmount = totalLoanAmount.add(new BigDecimal(tempLine2[4].replace("\t", "")));
			
			String brhNo = tempLine2[1].replace("\t", "");                 //8位-不需处理
			String brhName = getBrhName(tempLine2[2].replace("\t", ""));//String.format("%-30s", tempLine2[2].replace("\t", ""));//getBrhName(tempLine2[2].replace("\t", ""));   //30位-需要处理
			String lendAmount = String.format("%16s", tempLine2[3].replace("\t", "")); //16位-需要处理
			String loanAmount = String.format("%16s", tempLine2[4].replace("\t", "")); //16位-需要处理
			
			listStr.add(brhNo+" "+brhName+" "+lendAmount+" "+loanAmount);
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(new Date());
		
		String toFileRoot = "D://";
		String toFileName = "core1006";
		String toFilePath = toFileRoot + toFileName;
		File file = new File(toFilePath);
		if (file.exists()){
			file.delete();
		}
		
		WriteFile(date+"|"+totalLendAmount+"|"+totalLoanAmount, toFileRoot, toFileName,"ISO-8859-1");
		for(String str : listStr){
			WriteFile(str, "D://", "core1006","ISO-8859-1");
		}
	}
	
	/**
	 * 给银行名称填充到30位
	 * @param brhName
	 * @return
	 */
	public String getBrhName(String brhName){
		String str = brhName;
		int templength = 30 - str.length();
		StringBuffer sb = new StringBuffer();
		for(int i = 0 ; i< templength ; i++){
			sb.append(" ");
		}
		
		return str + sb.toString();
	}
	
	/**
	 * 给金额填充到16位，左补空格
	 * @param brhName
	 * @return
	 */
	public String getAmount(String amount){
		String str = amount;
		int templength = 16 - str.length();
		StringBuffer sb = new StringBuffer();
		for(int i = 0 ; i< templength ; i++){
			sb.append(" ");
		}
		
		return  sb.toString() + str;
	}
	
	public void doWork(){
		List<String> list = getAllFile("G://leidan");
		if(list == null || list.isEmpty()){
			return;
		}
		 
		//如果RZ1006，RD1006文件同时存在优先取RZ1006
		String doFile = null;
		for(String fileName : list){
			if(fileName.contains("RZ1006")){
				doFile = fileName;
				break;
			}
			
			if(fileName.contains("RD1006")){
				doFile = fileName;
				continue;
			}
		}
		
		List<String> lineList = readToList(doFile,"ISO-8859-1");
		
		writeFileBrfore(lineList);
	}

	public static void main(String[] args) {
		FileUtil fu = new FileUtil();
		fu.doWork();
	}
}