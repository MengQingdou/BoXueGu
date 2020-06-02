package com.example.boxuegu.bean;

public class ExercisesBean {
    public int id;         // 每章习题Id
    public String title;   // 每章习题标题
    public String content; // 每章习题的数目
    public int background; // 每章习题前边的序号背景
    public int subjectId;  // 每道习题的ID
    public String subject; // 每道习题的题干
    public String a;       // 每道题的A选项
    public String b;       // 每道题的B选项
    public String c;       // 每道题的C选项
    public String d;       // 每道题的D选项
    public int answer;      // 每道题的正确答案
    /**
     * select为0表示所选选项是对的，1表示选中的A选项是错的，2表示选中的B选项是错的。
     * 3表示选中的C选址是错的，4表示选中的D选项是错的
     */
    public int select;
}
