package com.handyfarm.entity;

import java.sql.Time;
import java.sql.Timestamp;

public class HandyFarmDTO {
	// 테이블 컬럼에 해당하는 값 선언
	String phoneNumber;
	String location;
	String authNumber;
	String serialNumber;
	String photo;
	String nickname;
	int temperature;
	int humidity;
	int co2;
	int soilMoisture;
	int sunshine;
	int harvestable;
	int soilTemperature;
	Timestamp updateTime;
	String calNumber;
	String calTitle;
	Time start;
	Time end;
	String memo;
	int yield;
	String yieldUnit;
	String plantNumber;
	String plantName;
	String explan;
	int push;
	int sound;
	int vibration;
	String faqNumber;
	String category;
	String question;
	String answer;
	String qusNumber;
	String qusCategory;
	String title;
	String content;
	String file;
	String plantType;
	
	// 매개변수 생성자
	public HandyFarmDTO(String phoneNumber, String location, String authNumber, String serialNumber, String photo,
			String nickname, int temperature, int humidity, int co2, int soilMoisture, int sunshine, int harvestable,
			int soilTemperature, Timestamp updateTime, String calNumber, String calTitle, Time start, Time end,
			String memo, int yield, String yieldUnit, String plantNumber, String plantName, String explan, int push,
			int sound, int vibration, String faqNumber, String category, String question, String answer,
			String qusNumber, String qusCategory, String title, String content, String file, String plantType) {
		super();
		this.phoneNumber = phoneNumber;
		this.location = location;
		this.authNumber = authNumber;
		this.serialNumber = serialNumber;
		this.photo = photo;
		this.nickname = nickname;
		this.temperature = temperature;
		this.humidity = humidity;
		this.co2 = co2;
		this.soilMoisture = soilMoisture;
		this.sunshine = sunshine;
		this.harvestable = harvestable;
		this.soilTemperature = soilTemperature;
		this.updateTime = updateTime;
		this.calNumber = calNumber;
		this.calTitle = calTitle;
		this.start = start;
		this.end = end;
		this.memo = memo;
		this.yield = yield;
		this.yieldUnit = yieldUnit;
		this.plantNumber = plantNumber;
		this.plantName = plantName;
		this.explan = explan;
		this.push = push;
		this.sound = sound;
		this.vibration = vibration;
		this.faqNumber = faqNumber;
		this.category = category;
		this.question = question;
		this.answer = answer;
		this.qusNumber = qusNumber;
		this.qusCategory = qusCategory;
		this.title = title;
		this.content = content;
		this.file = file;
		this.plantType = plantType;
	}
	
	// getter/setter
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getAuthNumber() {
		return authNumber;
	}

	public void setAuthNumber(String authNumber) {
		this.authNumber = authNumber;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getTemperature() {
		return temperature;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

	public int getHumidity() {
		return humidity;
	}

	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}

	public int getCo2() {
		return co2;
	}

	public void setCo2(int co2) {
		this.co2 = co2;
	}

	public int getSoilMoisture() {
		return soilMoisture;
	}

	public void setSoilMoisture(int soilMoisture) {
		this.soilMoisture = soilMoisture;
	}

	public int getSunshine() {
		return sunshine;
	}

	public void setSunshine(int sunshine) {
		this.sunshine = sunshine;
	}

	public int getHarvestable() {
		return harvestable;
	}

	public void setHarvestable(int harvestable) {
		this.harvestable = harvestable;
	}

	public int getSoilTemperature() {
		return soilTemperature;
	}

	public void setSoilTemperature(int soilTemperature) {
		this.soilTemperature = soilTemperature;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public String getCalNumber() {
		return calNumber;
	}

	public void setCalNumber(String calNumber) {
		this.calNumber = calNumber;
	}

	public String getCalTitle() {
		return calTitle;
	}

	public void setCalTitle(String calTitle) {
		this.calTitle = calTitle;
	}

	public Time getStart() {
		return start;
	}

	public void setStart(Time start) {
		this.start = start;
	}

	public Time getEnd() {
		return end;
	}

	public void setEnd(Time end) {
		this.end = end;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public int getYield() {
		return yield;
	}

	public void setYield(int yield) {
		this.yield = yield;
	}

	public String getYieldUnit() {
		return yieldUnit;
	}

	public void setYieldUnit(String yieldUnit) {
		this.yieldUnit = yieldUnit;
	}

	public String getPlantNumber() {
		return plantNumber;
	}

	public void setPlantNumber(String plantNumber) {
		this.plantNumber = plantNumber;
	}

	public String getPlantName() {
		return plantName;
	}

	public void setPlantName(String plantName) {
		this.plantName = plantName;
	}

	public String getExplan() {
		return explan;
	}

	public void setExplan(String explan) {
		this.explan = explan;
	}

	public int getPush() {
		return push;
	}

	public void setPush(int push) {
		this.push = push;
	}

	public int getSound() {
		return sound;
	}

	public void setSound(int sound) {
		this.sound = sound;
	}

	public int getVibration() {
		return vibration;
	}

	public void setVibration(int vibration) {
		this.vibration = vibration;
	}

	public String getFaqNumber() {
		return faqNumber;
	}

	public void setFaqNumber(String faqNumber) {
		this.faqNumber = faqNumber;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getQusNumber() {
		return qusNumber;
	}

	public void setQusNumber(String qusNumber) {
		this.qusNumber = qusNumber;
	}

	public String getQusCategory() {
		return qusCategory;
	}

	public void setQusCategory(String qusCategory) {
		this.qusCategory = qusCategory;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getPlantType() {
		return plantType;
	}

	public void setPlantType(String plantType) {
		this.plantType = plantType;
	}
}