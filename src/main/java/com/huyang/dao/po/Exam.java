package com.huyang.dao.po;

import java.util.Date;
import java.util.List;

public class Exam {
    private String id;

    private String collegeId;

    private String proId;

    private String grade;

    private String classId;

    private String className;

    private String courseId;

    private String lessonNumber;

    private String name;

    private String teacherId;

    private String classRoom;

    private Integer peopleNum;

    private String peopleName;

    private Date startTime;

    private Date creatTime;

    private String creatPeopleId;

    private Date deadTime;

    private Byte status;

    private String remark;
    
    /**额外字段*/
    private String teacherName;
    

	private List<String> peopleList;

	
	
    public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public List<String> getPeopleList() {
		return peopleList;
	}

	public void setPeopleList(List<String> peopleList) {
		this.peopleList = peopleList;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId == null ? null : collegeId.trim();
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId == null ? null : proId.trim();
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade == null ? null : grade.trim();
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId == null ? null : classId.trim();
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId == null ? null : courseId.trim();
    }

    public String getLessonNumber() {
        return lessonNumber;
    }

    public void setLessonNumber(String lessonNumber) {
        this.lessonNumber = lessonNumber == null ? null : lessonNumber.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId == null ? null : teacherId.trim();
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom == null ? null : classRoom.trim();
    }

    public Integer getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(Integer peopleNum) {
        this.peopleNum = peopleNum;
    }

    public String getPeopleName() {
        return peopleName;
    }

    public void setPeopleName(String peopleName) {
        this.peopleName = peopleName == null ? null : peopleName.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public String getCreatPeopleId() {
        return creatPeopleId;
    }

    public void setCreatPeopleId(String creatPeopleId) {
        this.creatPeopleId = creatPeopleId == null ? null : creatPeopleId.trim();
    }

    public Date getDeadTime() {
        return deadTime;
    }

    public void setDeadTime(Date deadTime) {
        this.deadTime = deadTime;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	@Override
	public String toString() {
		return "Exam [id=" + id + ", collegeId=" + collegeId + ", proId=" + proId + ", grade=" + grade + ", classId="
				+ classId + ", className=" + className + ", courseId=" + courseId + ", lessonNumber=" + lessonNumber
				+ ", name=" + name + ", teacherId=" + teacherId + ", classRoom=" + classRoom + ", peopleNum="
				+ peopleNum + ", peopleName=" + peopleName + ", startTime=" + startTime + ", creatTime=" + creatTime
				+ ", creatPeopleId=" + creatPeopleId + ", deadTime=" + deadTime + ", status=" + status + ", remark="
				+ remark + ", peopleList=" + peopleList + "]";
	}
    
    
}