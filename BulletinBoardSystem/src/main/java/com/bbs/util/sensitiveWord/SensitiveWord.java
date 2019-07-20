package com.bbs.util.sensitiveWord;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 *
 * blog.csdn.net/hubiao_0618/article/details/45076871
 *
 *
 */
public class SensitiveWord {
    private StringBuilder replaceAll;// 初始化
    private String encoding = "UTF-8";
    private String replceStr = "*";
    private int replceSize = 500;
    private static final String fileName = "CensorWords.txt";
    private List<String> arrayList;
    public Set<String> sensitiveWordSet;
    public List<String> sensitiveWordList;

    public SensitiveWord(String replceStr, int replceSize) {
        this.replceStr = fileName;
        this.replceSize = replceSize;
    }

    public SensitiveWord() {
    }

    public StringBuilder getReplaceAll() {
        return replaceAll;
    }

    public void setReplaceAll(StringBuilder replaceAll) {
        this.replaceAll = replaceAll;
    }

    public String getReplceStr() {
        return replceStr;
    }

    public void setReplceStr(String replceStr) {
        this.replceStr = replceStr;
    }

    public int getReplceSize() {
        return replceSize;
    }

    public void setReplceSize(int replceSize) {
        this.replceSize = replceSize;
    }

    public List<String> getArrayList() {
        return arrayList;
    }

    public void setArrayList(List<String> arrayList) {
        this.arrayList = arrayList;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    /**
     * 将敏感字转换为*符号
     *
     * @param str
     * @return
     */
    public String filterInfo(String str) {
        sensitiveWordSet = new HashSet<String>();
        sensitiveWordList = new ArrayList<>();
        StringBuilder buffer = new StringBuilder(str);
        HashMap<Integer, Integer> hash = new HashMap<Integer, Integer>(arrayList.size());
        String temp;
        for (int x = 0; x < arrayList.size(); x++) {
            temp = arrayList.get(x);
            int findIndexSize = 0;
            for (int start = -1; (start = buffer.indexOf(temp, findIndexSize)) > -1;) {
                findIndexSize = start + temp.length();
                Integer mapStart = hash.get(start);
                if (mapStart == null || (mapStart != null && findIndexSize > mapStart)) {
                    hash.put(start, findIndexSize);
                }
            }
        }
        Collection<Integer> values = hash.keySet();
        for (Integer startIndex : values) {
            Integer endIndex = hash.get(startIndex);
            String sensitive = buffer.substring(startIndex, endIndex);
            if (!sensitive.contains("*")) {
                sensitiveWordSet.add(sensitive);
                sensitiveWordList.add(sensitive);
            }
            buffer.replace(startIndex, endIndex, replaceAll.substring(0, endIndex - startIndex));
        }
        hash.clear();
        return buffer.toString();
    }

    /**
     * 初始化读取铭感文件库
     */
    public void InitializationWork() {
        replaceAll = new StringBuilder(replceSize);
        for (int x = 0; x < replceSize; x++) {
            replaceAll.append(replceStr);
        }
        arrayList = new ArrayList<String>();
        InputStreamReader read = null;
        BufferedReader bufferedReader = null;
        try {
            read = new InputStreamReader(SensitiveWord.class.getClassLoader().getResourceAsStream(fileName), encoding);
            bufferedReader = new BufferedReader(read);
            for (String txt = null; (txt = bufferedReader.readLine()) != null;) {
                if (!arrayList.contains(txt))
                    arrayList.add(txt);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != bufferedReader)
                    bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (null != read)
                    read.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 判断是否有敏感词汇
     *
     * @param str
     * @return
     */
    public static boolean checkSenstiveWord(String str) {
        // 初始敏感词库
        SensitiveWord sw = new SensitiveWord();
        sw.InitializationWork();
        str = sw.filterInfo(str);
        if (str.contains("*")) {
            return true;
        }
        return false;
    }

    public static String filterInfoAfter(String str) {
        // 初始敏感词库
        SensitiveWord sw = new SensitiveWord();
        sw.InitializationWork();
        str = sw.filterInfo(str);
        return str;
    }


}