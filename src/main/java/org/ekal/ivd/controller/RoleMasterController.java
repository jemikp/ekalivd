package org.ekal.ivd.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.ekal.ivd.dao.RoleMasterDao;
import org.ekal.ivd.dto.PaginationDTO;
import org.ekal.ivd.dto.RoleMasterDTO;
import org.ekal.ivd.util.CommonConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleMasterController {
	@Autowired
	RoleMasterDao roleMasterDao;

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createRole(@Valid @RequestBody RoleMasterDTO roleMasterDTO) {
		roleMasterDao.createRole(roleMasterDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(roleMasterDTO);
	}

//	@PutMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<?> updateUser(@PathVariable(name = "userId") int userId,@Valid @RequestBody UserDTO userDTO) {
//		userDao.updateUser(userId,userDTO);
//		return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
//	}
//
//	@GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<?> getUserById(@PathVariable(name = "userId") int userId) {
//		UserDTO userDTO = userDao.getById(userId);
//		return ResponseEntity.status(HttpStatus.OK).body(userDTO);
//	}
//
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllRoles(@RequestParam(name = "page", defaultValue = CommonConstant.PAGE) int page,
			@RequestParam(name = "size", defaultValue = CommonConstant.SIZE) int size){
		PaginationDTO<RoleMasterDTO> rolePageDTO = roleMasterDao.getAllRoles(page,size);
		return ResponseEntity.status(HttpStatus.OK).body(rolePageDTO);
	}
//
//	@GetMapping(value = "/otp/{whatsApp}",produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<?> sendWhatsAPPOtp(@PathVariable(name = "whatsApp") String whatsApp){
//		userDao.sendOtp(whatsApp);
//		return ResponseEntity.status(HttpStatus.OK).body("OTP Validated successfully");
//	}
//
//	@Autowired
//	StateRepository stateRepository;
//	@Autowired
//	DistrictRepository districtRepository;
//	@Autowired
//	CityRepository cityRepository;
//	@Autowired
//	VillageRepository villageRepository;
//	@GetMapping(value = "/json",produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<?> readJson() throws IOException {
//
//		String text = new String(Files.readAllBytes(Paths.get("D:\\Lalit\\India\\indian-cities-and-villages\\data.json")), StandardCharsets.UTF_8);
//		JSONArray array = new JSONArray(text);
//		System.out.println("=======>"+array.length());
//		for(int i=0;i<array.length();i++){
//			JSONObject stateObj = array.getJSONObject(i);
//			String stateName = stateObj.getString("state");
//			State state = new State();
//			state.setName(stateName);
//			state.setCountryId(1);
//			stateRepository.save(state);
//			int stateId = state.getId();
//			JSONArray disArray = stateObj.getJSONArray("districts");
//			for(int j=0;j<disArray.length();j++){
//				JSONObject disObj = disArray.getJSONObject(j);
//				String districtName = disObj.getString("district");
//				District district = new District();
//				district.setName(districtName);
//				district.setStateId(stateId);
//				districtRepository.save(district);
//				int districtId = district.getId();
//				JSONArray subDisArray = disObj.getJSONArray("subDistricts");
//				for(int k=0;k<subDisArray.length();k++){
//					JSONObject subDisObj = subDisArray.getJSONObject(k);
//					String subDistrict = subDisObj.getString("subDistrict");
//					City city = new City();
//					city.setName(subDistrict);
//					city.setDistrictId(districtId);
//					cityRepository.save(city);
//					int cityId = city.getId();
//
//					JSONArray villageArray = subDisObj.getJSONArray("villages");
//					for(int l=0;l<villageArray.length();l++){
//						if(null != villageArray.optString(l,"") && !"".equalsIgnoreCase(villageArray.optString(l,""))) {
//							Village village = new Village();
//							village.setName(villageArray.getString(l));
//							village.setCityId(cityId);
//							villageRepository.save(village);
//							System.out.println(stateName+" - "+districtName+" - "+subDistrict+" - "+villageArray.getString(l));
//						}
//					}
//				}
//			}
//		}
//		return ResponseEntity.status(HttpStatus.OK).body("OTP Validated successfully");
//	}
}
