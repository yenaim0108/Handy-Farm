package com.handyfarm.entity;

import java.sql.*;

public class HandyFarmDTO {
	// 테이블 컬럼에 해당하는 값 선언 (table - attribute)
	// 회원
	String phone_number;                // 회원 - 핸드폰 번호
	String token;                       // 회원 - 토큰
	String latitude;                    // 회원 - 위도
	String longitude;                   // 회원 - 경도
	
	// 온실
	String gh_id;                       // 온실 - 온실 ID
	String gh_img;                      // 온실 - 온실 사진
	String gh_nickname;                 // 온실 - 온실 별명
	
	// 로보
	String robo_serial;                 // 로보 - 로보 시리얼번호
	String robo_img;                    // 로보 - 사진 경로
	String robo_nickname;               // 로보 - 별명
	
	// 센서
	String sensor_id;                   // 센서 - 센서 ID
	String sensor_type;                 // 센서 - 센서 Type
	
	// 센서측정값
	String value_id;                    // 센서측정값 - 측정값 ID
	float sensor_value;                 // 센서측정값 - 센서측정값
	Timestamp measure_time;             // 센서측정값 - 측정시각
	
	// 측정평균값
	String avg_id;                      // 측정평균값 - 평균값 ID
	float reading_avg_value;            // 측정평균값 - 측정평균값
	
	// 수확 가능 비율
	String hrv_id;                      // 수확 가능 비율 - 수확 가능 비율 ID
	float harvestable;                  // 수확 가능 비율 - 수확 가능 비율
	Timestamp upload_time;              // 수확 가능 비율 - 업로드 시각
	
	// 설비
	String equipment_id;                // 설비 - 설비 ID
	String equipment_name;              // 설비 - 설비 이름
	
	// 설비 제어 이력
	String control_log_id;              // 설비 제어 이력 - 제어 이력 ID
    boolean control_status;             // 설비 제어 이력 - 제어 상태
    Timestamp control_time;             // 설비 제어 이력 - 제어 시간
    
    // 작물
	String cultivar_number;             // 작물 - 품종번호
	String crops_name;                  // 작물 - 작물 이름
	float sum_mrn_min_temperature;      // 작물 - 여름 오전 최저 온도
	float sum_mrn_max_temperature;      // 작물 - 여름 오전 최고 온도
	float sum_aft_min_temperature;      // 작물 - 여름 오후 최저 온도
	float sum_aft_max_temperature;      // 작물 - 여름 오후 최고 온도
	float sum_ngh_min_temperature;      // 작물 - 여름 야간 최저 온도
	float sum_ngh_max_temperature;      // 작물 - 여름 야간 최고 온도
	float win_day_min_temperature;      // 작물 - 겨울 주간 최저 온도
	float win_day_max_temperature;      // 작물 - 겨울 주간 최고 온도
	float win_ngh_min_temperature;      // 작물 - 겨울 야간 최저 온도
	float win_ngh_max_temperature;      // 작물 - 겨울 야간 최고 온도
	float min_humidity;                 // 작물 - 최저 습도
	float max_humidity;                 // 작물 - 최고 습도
	float max_co2;                      // 작물 - 최대 이산화탄소
	float min_soil_moisture;            // 작물 - 최저 토양 수분도
	float max_soil_moisture;            // 작물 - 최고 토양 수분도
	float min_sunshine;                 // 작물 - 최저 일조량
	float min_soil_temperature;         // 작물 - 토양 속 최저 온도
	float max_soil_temperature;         // 작물 - 토양 속 최고 온도
	
	// 일정
	String cal_number;                  // 일정 - 일정번호
	String cal_title;                   // 일정 - 일정제목
	Time cal_start;                     // 일정 - 시작 시간
	Time cal_end;                       // 일정 - 종료 시간
	String cal_memo;                    // 일정 - 메모
	float cal_yield_kg;                 // 일정 - 수확량 (kg)
	Timestamp cal_yield_time;           // 일정 - 수확시기
	
	// 실시간 정보
	String link;                        // 실시간 정보 - 링크
	int views;                          // 실시간 정보 - 조회수
	String title;                       // 실시간 정보 - 제목
	String content;                     // 실시간 정보 - 내용
	Date date;                          // 실시간 정보 - 작성일
	String category;                    // 실시간 정보 - 카테고리
	String img;                         // 실시간 정보 - 이미지
	
	// 찜
	boolean wish;                       // 찜 - 찜
	
	// 알림 관리
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
	
	// 푸시 알림 이력
	String plog_id;                     // 푸시 알림 이력 - 푸시 이력 ID
	String push_msg;                    // 푸시 알림 이력 - 푸시 알림 메시지
	Timestamp push_date;                // 푸시 알림 이력 - 푸시 알림 보낸 시각
	String push_category;               // 푸시 알림 이력 - 푸시 알림 분류
	boolean read_status;                // 푸시 알림 이력 - 읽음 상태
	
	// 매개변수 생성자
	public HandyFarmDTO(String phone_number, String token, String latitude, String longitude, String gh_id,
			String gh_img, String gh_nickname, String robo_serial, String robo_img, String robo_nickname,
			String sensor_id, String sensor_type, String value_id, float sensor_value, Timestamp measure_time,
			String avg_id, float reading_avg_value, String hrv_id, float harvestable, Timestamp upload_time,
			String equipment_id, String equipment_name, String control_log_id, boolean control_status,
			Timestamp control_time, String cultivar_number, String crops_name, float sum_mrn_min_temperature,
			float sum_mrn_max_temperature, float sum_aft_min_temperature, float sum_aft_max_temperature,
			float sum_ngh_min_temperature, float sum_ngh_max_temperature, float win_day_min_temperature,
			float win_day_max_temperature, float win_ngh_min_temperature, float win_ngh_max_temperature,
			float min_humidity, float max_humidity, float max_co2, float min_soil_moisture, float max_soil_moisture,
			float min_sunshine, float min_soil_temperature, float max_soil_temperature, String cal_number,
			String cal_title, Time cal_start, Time cal_end, String cal_memo, float cal_yield_kg,
			Timestamp cal_yield_time, String link, int views, String title, String content, Date date, String category,
			String img, boolean wish, boolean push, boolean sound, boolean vibration,
			float push_sum_mrn_min_temperature, float push_sum_mrn_max_temperature, float push_sum_aft_min_temperature,
			float push_sum_aft_max_temperature, float push_sum_ngh_min_temperature, float push_sum_ngh_max_temperature,
			float push_win_day_min_temperature, float push_win_day_max_temperature, float push_win_ngh_min_temperature,
			float push_win_ngh_max_temperature, float push_min_humidity, float push_max_humidity, float push_max_co2,
			float push_min_soil_moisture, float push_max_soil_moisture, float push_min_sunshine,
			float push_min_soil_temperature, float push_max_soil_temperature, boolean harvestable_60,
			boolean harvestable_70, boolean harvestable_80, boolean harvestable_90, String plog_id, String push_msg,
			Timestamp push_date, String push_category, boolean read_status) {
		super();
		this.phone_number = phone_number;
		this.token = token;
		this.latitude = latitude;
		this.longitude = longitude;
		this.gh_id = gh_id;
		this.gh_img = gh_img;
		this.gh_nickname = gh_nickname;
		this.robo_serial = robo_serial;
		this.robo_img = robo_img;
		this.robo_nickname = robo_nickname;
		this.sensor_id = sensor_id;
		this.sensor_type = sensor_type;
		this.value_id = value_id;
		this.sensor_value = sensor_value;
		this.measure_time = measure_time;
		this.avg_id = avg_id;
		this.reading_avg_value = reading_avg_value;
		this.hrv_id = hrv_id;
		this.harvestable = harvestable;
		this.upload_time = upload_time;
		this.equipment_id = equipment_id;
		this.equipment_name = equipment_name;
		this.control_log_id = control_log_id;
		this.control_status = control_status;
		this.control_time = control_time;
		this.cultivar_number = cultivar_number;
		this.crops_name = crops_name;
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
		this.cal_number = cal_number;
		this.cal_title = cal_title;
		this.cal_start = cal_start;
		this.cal_end = cal_end;
		this.cal_memo = cal_memo;
		this.cal_yield_kg = cal_yield_kg;
		this.cal_yield_time = cal_yield_time;
		this.link = link;
		this.views = views;
		this.title = title;
		this.content = content;
		this.date = date;
		this.category = category;
		this.img = img;
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
		this.plog_id = plog_id;
		this.push_msg = push_msg;
		this.push_date = push_date;
		this.push_category = push_category;
		this.read_status = read_status;
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

	public String getGh_id() {
		return gh_id;
	}

	public void setGh_id(String gh_id) {
		this.gh_id = gh_id;
	}

	public String getGh_img() {
		return gh_img;
	}

	public void setGh_img(String gh_img) {
		this.gh_img = gh_img;
	}

	public String getGh_nickname() {
		return gh_nickname;
	}

	public void setGh_nickname(String gh_nickname) {
		this.gh_nickname = gh_nickname;
	}

	public String getRobo_serial() {
		return robo_serial;
	}

	public void setRobo_serial(String robo_serial) {
		this.robo_serial = robo_serial;
	}

	public String getRobo_img() {
		return robo_img;
	}

	public void setRobo_img(String robo_img) {
		this.robo_img = robo_img;
	}

	public String getRobo_nickname() {
		return robo_nickname;
	}

	public void setRobo_nickname(String robo_nickname) {
		this.robo_nickname = robo_nickname;
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

	public String getValue_id() {
		return value_id;
	}

	public void setValue_id(String value_id) {
		this.value_id = value_id;
	}

	public float getSensor_value() {
		return sensor_value;
	}

	public void setSensor_value(float sensor_value) {
		this.sensor_value = sensor_value;
	}

	public Timestamp getMeasure_time() {
		return measure_time;
	}

	public void setMeasure_time(Timestamp measure_time) {
		this.measure_time = measure_time;
	}

	public String getAvg_id() {
		return avg_id;
	}

	public void setAvg_id(String avg_id) {
		this.avg_id = avg_id;
	}

	public float getReading_avg_value() {
		return reading_avg_value;
	}

	public void setReading_avg_value(float reading_avg_value) {
		this.reading_avg_value = reading_avg_value;
	}

	public String getHrv_id() {
		return hrv_id;
	}

	public void setHrv_id(String hrv_id) {
		this.hrv_id = hrv_id;
	}

	public float getHarvestable() {
		return harvestable;
	}

	public void setHarvestable(float harvestable) {
		this.harvestable = harvestable;
	}

	public Timestamp getUpload_time() {
		return upload_time;
	}

	public void setUpload_time(Timestamp upload_time) {
		this.upload_time = upload_time;
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

	public String getControl_log_id() {
		return control_log_id;
	}

	public void setControl_log_id(String control_log_id) {
		this.control_log_id = control_log_id;
	}

	public boolean isControl_status() {
		return control_status;
	}

	public void setControl_status(boolean control_status) {
		this.control_status = control_status;
	}

	public Timestamp getControl_time() {
		return control_time;
	}

	public void setControl_time(Timestamp control_time) {
		this.control_time = control_time;
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

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
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

	public String getPlog_id() {
		return plog_id;
	}

	public void setPlog_id(String plog_id) {
		this.plog_id = plog_id;
	}

	public String getPush_msg() {
		return push_msg;
	}

	public void setPush_msg(String push_msg) {
		this.push_msg = push_msg;
	}

	public Timestamp getPush_date() {
		return push_date;
	}

	public void setPush_date(Timestamp push_date) {
		this.push_date = push_date;
	}

	public String getPush_category() {
		return push_category;
	}

	public void setPush_category(String push_category) {
		this.push_category = push_category;
	}

	public boolean isRead_status() {
		return read_status;
	}

	public void setRead_status(boolean read_status) {
		this.read_status = read_status;
	}
}