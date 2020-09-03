package com.handyfarm.entity;

import java.sql.Time;
import java.sql.Timestamp;

public class HandyFarmDTO {
	// 테이블 컬럼에 해당하는 값 선언 (table - attribute)
	String phone_number;                // 회원 - 핸드폰 번호
	String token;                       // 회원 - 토큰
	String latitude;                    // 회원 - 위도
	String longitude;                   // 회원 - 경도
	String greenhouse_id;               // 온실 - 온실 ID
	String robo_serial;                 // 로보 - 로보 시리얼번호
	String img_path;                    // 로보 - 사진 경로
	String nickname;                    // 로보 - 별명
	String sensor_id;                   // 센서 - 센서 ID
	String sensor_type;                 // 센서 - 센서 Type
	float sensor_value;                 // 센서측정값 - 센서측정값
	float reading_avg_value;            // 측정평균값 - 측정평균값
	int total_crops_number;             // 작물 갯수 - 전체 작물 갯수
	int ripe_crops_number;              // 작물 갯수 - 익은 작물 갯수
	String equipment_id;                // 설비 - 설비 ID
	String equipment_name;              // 설비 - 설비 이름
                                        // 설비 제어 event - 미정
	String cultivar_number;             // 작물 - 품종번호
	String crops_name;                  // 작물 - 작물 이름
	String cal_number;                  // 일정 - 일정번호
	String cal_title;                   // 일정 - 일정제목
	Time cal_start;                     // 일정 - 시작 시간
	Time cal_end;                       // 일정 - 종료 시간
	String cal_memo;                    // 일정 - 메모
	float cal_yield_kg;                 // 일정 - 수확량 (kg)
	Timestamp cal_yield_time;           // 일정 - 수확시기
	float sum_mrn_min_temperature;      // 경작 조건 - 여름 오전 최저 온도
	float sum_mrn_max_temperature;      // 경작 조건 - 여름 오전 최고 온도
	float sum_aft_min_temperature;      // 경작 조건 - 여름 오후 최저 온도
	float sum_aft_max_temperature;      // 경작 조건 - 여름 오후 최고 온도
	float sum_ngh_min_temperature;      // 경작 조건 - 여름 야간 최저 온도
	float sum_ngh_max_temperature;      // 경작 조건 - 여름 야간 최고 온도
	float win_day_min_temperature;      // 경작 조건 - 겨울 주간 최저 온도
	float win_day_max_temperature;      // 경작 조건 - 겨울 주간 최고 온도
	float win_ngh_min_temperature;      // 경작 조건 - 겨울 야간 최저 온도
	float win_ngh_max_temperature;      // 경작 조건 - 겨울 야간 최고 온도
	float min_humidity;                 // 경작 조건 - 최저 습도
	float max_humidity;                 // 경작 조건 - 최고 습도
	float max_co2;                      // 경작 조건 - 최대 이산화탄소
	float min_soil_moisture;            // 경작 조건 - 최저 토양 수분도
	float max_soil_moisture;            // 경작 조건 - 최고 토양 수분도
	float min_sunshine;                 // 경작 조건 - 최저 일조량
	float min_soil_temperature;         // 경작 조건 - 토양 속 최저 온도
	float max_soil_temperature;         // 경작 조건 - 토양 속 최고 온도
	                                    // 외부 event - 미정
	boolean wish;                       // 찜 - 찜
	boolean push;                       // 알림 관리 - 메시지 알림
	boolean sound;                      // 알림 관리 - 소리
	boolean vibration;                  // 알림 관리 - 진동
	float push_sum_mrn_min_temperature; // 알림 관리 - 알림_여름 오전 최저 온도
	float push_sum_mrn_max_temperature; // 알림 관리 - 알림_여름 오전 최고 온도
	float push_sum_aft_min_temperature; // 알림 관리 - 알림_여름 오후 최저 온도
	float push_sum_aft_max_temperature; // 알림 관리 - 알림_여름 오후 최고 온도
	float push_sum_ngh_min_temperature; // 알림 관리 - 알림_여름 야간 최저 온도
	float push_sum_ngh_max_temperature; // 알림 관리 - 알림_여름 야간 최고 온도
	float push_win_day_min_temperature; // 알림 관리 - 알림_겨울 주간 최저 온도
	float push_win_day_max_temperature; // 알림 관리 - 알림_겨울 주간 최고 온도
	float push_win_ngh_min_temperature; // 알림 관리 - 알림_겨울 야간 최저 온도
	float push_win_ngh_max_temperature; // 알림 관리 - 알림_겨울 야간 최고 온도
	float push_min_humidity;            // 알림 관리 - 알림_최저 습도
	float push_max_humidity;            // 알림 관리 - 알림_최고 습도
	float push_max_co2;                 // 알림 관리 - 알림_최대 이산화탄소
	float push_min_soil_moisture;       // 알림 관리 - 알림_최저 토양 수분도
	float push_max_soil_moisture;       // 알림 관리 - 알림_최고 토양 수분도
	float push_min_sunshine;            // 알림 관리 - 알림_최저 일조량
	float push_min_soil_temperature;    // 알림 관리 - 알림_토양 속 최저 온도
	float push_max_soil_temperature;    // 알림 관리 - 알림_토양 속 최고 온도
	boolean harvestable_60;             // 알림 관리 - 수확 가능 비율 60%
	boolean harvestable_70;             // 알림 관리 - 수확 가능 비율 70%
	boolean harvestable_80;             // 알림 관리 - 수확 가능 비율 80%
	boolean harvestable_90;             // 알림 관리 - 수확 가능 비율 90%
	
	// 매개변수 생성자
	public HandyFarmDTO(String phone_number, String token, String latitude, String longitude, String greenhouse_id,
			String robo_serial, String img_path, String nickname, String sensor_id, String sensor_type,
			float sensor_value, float reading_avg_value, int total_crops_number, int ripe_crops_number,
			String equipment_id, String equipment_name, String cultivar_number, String crops_name, String cal_number,
			String cal_title, Time cal_start, Time cal_end, String cal_memo, float cal_yield_kg,
			Timestamp cal_yield_time, float sum_mrn_min_temperature, float sum_mrn_max_temperature,
			float sum_aft_min_temperature, float sum_aft_max_temperature, float sum_ngh_min_temperature,
			float sum_ngh_max_temperature, float win_day_min_temperature, float win_day_max_temperature,
			float win_ngh_min_temperature, float win_ngh_max_temperature, float min_humidity, float max_humidity,
			float max_co2, float min_soil_moisture, float max_soil_moisture, float min_sunshine,
			float min_soil_temperature, float max_soil_temperature, boolean wish, boolean push, boolean sound,
			boolean vibration, float push_sum_mrn_min_temperature, float push_sum_mrn_max_temperature,
			float push_sum_aft_min_temperature, float push_sum_aft_max_temperature, float push_sum_ngh_min_temperature,
			float push_sum_ngh_max_temperature, float push_win_day_min_temperature, float push_win_day_max_temperature,
			float push_win_ngh_min_temperature, float push_win_ngh_max_temperature, float push_min_humidity,
			float push_max_humidity, float push_max_co2, float push_min_soil_moisture, float push_max_soil_moisture,
			float push_min_sunshine, float push_min_soil_temperature, float push_max_soil_temperature,
			boolean harvestable_60, boolean harvestable_70, boolean harvestable_80, boolean harvestable_90) {
		super();
		this.phone_number = phone_number;
		this.token = token;
		this.latitude = latitude;
		this.longitude = longitude;
		this.greenhouse_id = greenhouse_id;
		this.robo_serial = robo_serial;
		this.img_path = img_path;
		this.nickname = nickname;
		this.sensor_id = sensor_id;
		this.sensor_type = sensor_type;
		this.sensor_value = sensor_value;
		this.reading_avg_value = reading_avg_value;
		this.total_crops_number = total_crops_number;
		this.ripe_crops_number = ripe_crops_number;
		this.equipment_id = equipment_id;
		this.equipment_name = equipment_name;
		this.cultivar_number = cultivar_number;
		this.crops_name = crops_name;
		this.cal_number = cal_number;
		this.cal_title = cal_title;
		this.cal_start = cal_start;
		this.cal_end = cal_end;
		this.cal_memo = cal_memo;
		this.cal_yield_kg = cal_yield_kg;
		this.cal_yield_time = cal_yield_time;
		this.sum_mrn_min_temperature = sum_mrn_min_temperature;
		this.sum_mrn_max_temperature = sum_mrn_max_temperature;
		this.sum_aft_min_temperature = sum_aft_min_temperature;
		this.sum_aft_max_temperature = sum_aft_max_temperature;
		this.sum_ngh_min_temperature = sum_ngh_min_temperature;
		this.sum_ngh_max_temperature = sum_ngh_max_temperature;
		this.win_day_min_temperature = win_day_min_temperature;
		this.win_day_max_temperature = win_day_max_temperature;
		this.win_ngh_min_temperature = win_ngh_min_temperature;
		this.win_ngh_max_temperature = win_ngh_max_temperature;
		this.min_humidity = min_humidity;
		this.max_humidity = max_humidity;
		this.max_co2 = max_co2;
		this.min_soil_moisture = min_soil_moisture;
		this.max_soil_moisture = max_soil_moisture;
		this.min_sunshine = min_sunshine;
		this.min_soil_temperature = min_soil_temperature;
		this.max_soil_temperature = max_soil_temperature;
		this.wish = wish;
		this.push = push;
		this.sound = sound;
		this.vibration = vibration;
		this.push_sum_mrn_min_temperature = push_sum_mrn_min_temperature;
		this.push_sum_mrn_max_temperature = push_sum_mrn_max_temperature;
		this.push_sum_aft_min_temperature = push_sum_aft_min_temperature;
		this.push_sum_aft_max_temperature = push_sum_aft_max_temperature;
		this.push_sum_ngh_min_temperature = push_sum_ngh_min_temperature;
		this.push_sum_ngh_max_temperature = push_sum_ngh_max_temperature;
		this.push_win_day_min_temperature = push_win_day_min_temperature;
		this.push_win_day_max_temperature = push_win_day_max_temperature;
		this.push_win_ngh_min_temperature = push_win_ngh_min_temperature;
		this.push_win_ngh_max_temperature = push_win_ngh_max_temperature;
		this.push_min_humidity = push_min_humidity;
		this.push_max_humidity = push_max_humidity;
		this.push_max_co2 = push_max_co2;
		this.push_min_soil_moisture = push_min_soil_moisture;
		this.push_max_soil_moisture = push_max_soil_moisture;
		this.push_min_sunshine = push_min_sunshine;
		this.push_min_soil_temperature = push_min_soil_temperature;
		this.push_max_soil_temperature = push_max_soil_temperature;
		this.harvestable_60 = harvestable_60;
		this.harvestable_70 = harvestable_70;
		this.harvestable_80 = harvestable_80;
		this.harvestable_90 = harvestable_90;
	}

	// getter/setter
	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getGreenhouse_id() {
		return greenhouse_id;
	}

	public void setGreenhouse_id(String greenhouse_id) {
		this.greenhouse_id = greenhouse_id;
	}

	public String getRobo_serial() {
		return robo_serial;
	}

	public void setRobo_serial(String robo_serial) {
		this.robo_serial = robo_serial;
	}

	public String getImg_path() {
		return img_path;
	}

	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getSensor_id() {
		return sensor_id;
	}

	public void setSensor_id(String sensor_id) {
		this.sensor_id = sensor_id;
	}

	public String getSensor_type() {
		return sensor_type;
	}

	public void setSensor_type(String sensor_type) {
		this.sensor_type = sensor_type;
	}

	public float getSensor_value() {
		return sensor_value;
	}

	public void setSensor_value(float sensor_value) {
		this.sensor_value = sensor_value;
	}

	public float getReading_avg_value() {
		return reading_avg_value;
	}

	public void setReading_avg_value(float reading_avg_value) {
		this.reading_avg_value = reading_avg_value;
	}

	public int getTotal_crops_number() {
		return total_crops_number;
	}

	public void setTotal_crops_number(int total_crops_number) {
		this.total_crops_number = total_crops_number;
	}

	public int getRipe_crops_number() {
		return ripe_crops_number;
	}

	public void setRipe_crops_number(int ripe_crops_number) {
		this.ripe_crops_number = ripe_crops_number;
	}

	public String getEquipment_id() {
		return equipment_id;
	}

	public void setEquipment_id(String equipment_id) {
		this.equipment_id = equipment_id;
	}

	public String getEquipment_name() {
		return equipment_name;
	}

	public void setEquipment_name(String equipment_name) {
		this.equipment_name = equipment_name;
	}

	public String getCultivar_number() {
		return cultivar_number;
	}

	public void setCultivar_number(String cultivar_number) {
		this.cultivar_number = cultivar_number;
	}

	public String getCrops_name() {
		return crops_name;
	}

	public void setCrops_name(String crops_name) {
		this.crops_name = crops_name;
	}

	public String getCal_number() {
		return cal_number;
	}

	public void setCal_number(String cal_number) {
		this.cal_number = cal_number;
	}

	public String getCal_title() {
		return cal_title;
	}

	public void setCal_title(String cal_title) {
		this.cal_title = cal_title;
	}

	public Time getCal_start() {
		return cal_start;
	}

	public void setCal_start(Time cal_start) {
		this.cal_start = cal_start;
	}

	public Time getCal_end() {
		return cal_end;
	}

	public void setCal_end(Time cal_end) {
		this.cal_end = cal_end;
	}

	public String getCal_memo() {
		return cal_memo;
	}

	public void setCal_memo(String cal_memo) {
		this.cal_memo = cal_memo;
	}

	public float getCal_yield_kg() {
		return cal_yield_kg;
	}

	public void setCal_yield_kg(float cal_yield_kg) {
		this.cal_yield_kg = cal_yield_kg;
	}

	public Timestamp getCal_yield_time() {
		return cal_yield_time;
	}

	public void setCal_yield_time(Timestamp cal_yield_time) {
		this.cal_yield_time = cal_yield_time;
	}

	public float getSum_mrn_min_temperature() {
		return sum_mrn_min_temperature;
	}

	public void setSum_mrn_min_temperature(float sum_mrn_min_temperature) {
		this.sum_mrn_min_temperature = sum_mrn_min_temperature;
	}

	public float getSum_mrn_max_temperature() {
		return sum_mrn_max_temperature;
	}

	public void setSum_mrn_max_temperature(float sum_mrn_max_temperature) {
		this.sum_mrn_max_temperature = sum_mrn_max_temperature;
	}

	public float getSum_aft_min_temperature() {
		return sum_aft_min_temperature;
	}

	public void setSum_aft_min_temperature(float sum_aft_min_temperature) {
		this.sum_aft_min_temperature = sum_aft_min_temperature;
	}

	public float getSum_aft_max_temperature() {
		return sum_aft_max_temperature;
	}

	public void setSum_aft_max_temperature(float sum_aft_max_temperature) {
		this.sum_aft_max_temperature = sum_aft_max_temperature;
	}

	public float getSum_ngh_min_temperature() {
		return sum_ngh_min_temperature;
	}

	public void setSum_ngh_min_temperature(float sum_ngh_min_temperature) {
		this.sum_ngh_min_temperature = sum_ngh_min_temperature;
	}

	public float getSum_ngh_max_temperature() {
		return sum_ngh_max_temperature;
	}

	public void setSum_ngh_max_temperature(float sum_ngh_max_temperature) {
		this.sum_ngh_max_temperature = sum_ngh_max_temperature;
	}

	public float getWin_day_min_temperature() {
		return win_day_min_temperature;
	}

	public void setWin_day_min_temperature(float win_day_min_temperature) {
		this.win_day_min_temperature = win_day_min_temperature;
	}

	public float getWin_day_max_temperature() {
		return win_day_max_temperature;
	}

	public void setWin_day_max_temperature(float win_day_max_temperature) {
		this.win_day_max_temperature = win_day_max_temperature;
	}

	public float getWin_ngh_min_temperature() {
		return win_ngh_min_temperature;
	}

	public void setWin_ngh_min_temperature(float win_ngh_min_temperature) {
		this.win_ngh_min_temperature = win_ngh_min_temperature;
	}

	public float getWin_ngh_max_temperature() {
		return win_ngh_max_temperature;
	}

	public void setWin_ngh_max_temperature(float win_ngh_max_temperature) {
		this.win_ngh_max_temperature = win_ngh_max_temperature;
	}

	public float getMin_humidity() {
		return min_humidity;
	}

	public void setMin_humidity(float min_humidity) {
		this.min_humidity = min_humidity;
	}

	public float getMax_humidity() {
		return max_humidity;
	}

	public void setMax_humidity(float max_humidity) {
		this.max_humidity = max_humidity;
	}

	public float getMax_co2() {
		return max_co2;
	}

	public void setMax_co2(float max_co2) {
		this.max_co2 = max_co2;
	}

	public float getMin_soil_moisture() {
		return min_soil_moisture;
	}

	public void setMin_soil_moisture(float min_soil_moisture) {
		this.min_soil_moisture = min_soil_moisture;
	}

	public float getMax_soil_moisture() {
		return max_soil_moisture;
	}

	public void setMax_soil_moisture(float max_soil_moisture) {
		this.max_soil_moisture = max_soil_moisture;
	}

	public float getMin_sunshine() {
		return min_sunshine;
	}

	public void setMin_sunshine(float min_sunshine) {
		this.min_sunshine = min_sunshine;
	}

	public float getMin_soil_temperature() {
		return min_soil_temperature;
	}

	public void setMin_soil_temperature(float min_soil_temperature) {
		this.min_soil_temperature = min_soil_temperature;
	}

	public float getMax_soil_temperature() {
		return max_soil_temperature;
	}

	public void setMax_soil_temperature(float max_soil_temperature) {
		this.max_soil_temperature = max_soil_temperature;
	}

	public boolean isWish() {
		return wish;
	}

	public void setWish(boolean wish) {
		this.wish = wish;
	}

	public boolean isPush() {
		return push;
	}

	public void setPush(boolean push) {
		this.push = push;
	}

	public boolean isSound() {
		return sound;
	}

	public void setSound(boolean sound) {
		this.sound = sound;
	}

	public boolean isVibration() {
		return vibration;
	}

	public void setVibration(boolean vibration) {
		this.vibration = vibration;
	}

	public float getPush_sum_mrn_min_temperature() {
		return push_sum_mrn_min_temperature;
	}

	public void setPush_sum_mrn_min_temperature(float push_sum_mrn_min_temperature) {
		this.push_sum_mrn_min_temperature = push_sum_mrn_min_temperature;
	}

	public float getPush_sum_mrn_max_temperature() {
		return push_sum_mrn_max_temperature;
	}

	public void setPush_sum_mrn_max_temperature(float push_sum_mrn_max_temperature) {
		this.push_sum_mrn_max_temperature = push_sum_mrn_max_temperature;
	}

	public float getPush_sum_aft_min_temperature() {
		return push_sum_aft_min_temperature;
	}

	public void setPush_sum_aft_min_temperature(float push_sum_aft_min_temperature) {
		this.push_sum_aft_min_temperature = push_sum_aft_min_temperature;
	}

	public float getPush_sum_aft_max_temperature() {
		return push_sum_aft_max_temperature;
	}

	public void setPush_sum_aft_max_temperature(float push_sum_aft_max_temperature) {
		this.push_sum_aft_max_temperature = push_sum_aft_max_temperature;
	}

	public float getPush_sum_ngh_min_temperature() {
		return push_sum_ngh_min_temperature;
	}

	public void setPush_sum_ngh_min_temperature(float push_sum_ngh_min_temperature) {
		this.push_sum_ngh_min_temperature = push_sum_ngh_min_temperature;
	}

	public float getPush_sum_ngh_max_temperature() {
		return push_sum_ngh_max_temperature;
	}

	public void setPush_sum_ngh_max_temperature(float push_sum_ngh_max_temperature) {
		this.push_sum_ngh_max_temperature = push_sum_ngh_max_temperature;
	}

	public float getPush_win_day_min_temperature() {
		return push_win_day_min_temperature;
	}

	public void setPush_win_day_min_temperature(float push_win_day_min_temperature) {
		this.push_win_day_min_temperature = push_win_day_min_temperature;
	}

	public float getPush_win_day_max_temperature() {
		return push_win_day_max_temperature;
	}

	public void setPush_win_day_max_temperature(float push_win_day_max_temperature) {
		this.push_win_day_max_temperature = push_win_day_max_temperature;
	}

	public float getPush_win_ngh_min_temperature() {
		return push_win_ngh_min_temperature;
	}

	public void setPush_win_ngh_min_temperature(float push_win_ngh_min_temperature) {
		this.push_win_ngh_min_temperature = push_win_ngh_min_temperature;
	}

	public float getPush_win_ngh_max_temperature() {
		return push_win_ngh_max_temperature;
	}

	public void setPush_win_ngh_max_temperature(float push_win_ngh_max_temperature) {
		this.push_win_ngh_max_temperature = push_win_ngh_max_temperature;
	}

	public float getPush_min_humidity() {
		return push_min_humidity;
	}

	public void setPush_min_humidity(float push_min_humidity) {
		this.push_min_humidity = push_min_humidity;
	}

	public float getPush_max_humidity() {
		return push_max_humidity;
	}

	public void setPush_max_humidity(float push_max_humidity) {
		this.push_max_humidity = push_max_humidity;
	}

	public float getPush_max_co2() {
		return push_max_co2;
	}

	public void setPush_max_co2(float push_max_co2) {
		this.push_max_co2 = push_max_co2;
	}

	public float getPush_min_soil_moisture() {
		return push_min_soil_moisture;
	}

	public void setPush_min_soil_moisture(float push_min_soil_moisture) {
		this.push_min_soil_moisture = push_min_soil_moisture;
	}

	public float getPush_max_soil_moisture() {
		return push_max_soil_moisture;
	}

	public void setPush_max_soil_moisture(float push_max_soil_moisture) {
		this.push_max_soil_moisture = push_max_soil_moisture;
	}

	public float getPush_min_sunshine() {
		return push_min_sunshine;
	}

	public void setPush_min_sunshine(float push_min_sunshine) {
		this.push_min_sunshine = push_min_sunshine;
	}

	public float getPush_min_soil_temperature() {
		return push_min_soil_temperature;
	}

	public void setPush_min_soil_temperature(float push_min_soil_temperature) {
		this.push_min_soil_temperature = push_min_soil_temperature;
	}

	public float getPush_max_soil_temperature() {
		return push_max_soil_temperature;
	}

	public void setPush_max_soil_temperature(float push_max_soil_temperature) {
		this.push_max_soil_temperature = push_max_soil_temperature;
	}

	public boolean isHarvestable_60() {
		return harvestable_60;
	}

	public void setHarvestable_60(boolean harvestable_60) {
		this.harvestable_60 = harvestable_60;
	}

	public boolean isHarvestable_70() {
		return harvestable_70;
	}

	public void setHarvestable_70(boolean harvestable_70) {
		this.harvestable_70 = harvestable_70;
	}

	public boolean isHarvestable_80() {
		return harvestable_80;
	}

	public void setHarvestable_80(boolean harvestable_80) {
		this.harvestable_80 = harvestable_80;
	}

	public boolean isHarvestable_90() {
		return harvestable_90;
	}

	public void setHarvestable_90(boolean harvestable_90) {
		this.harvestable_90 = harvestable_90;
	}
}