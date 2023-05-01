package com.kh.tripism.travelPlan.model.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.kh.tripism.travelPlan.model.dao.PlanDao;
import com.kh.tripism.travelPlan.model.vo.Tour;

@Service
public class PlanServiceImpl implements PlanService {
	@Autowired
	PlanDao pDao = new PlanDao();
	@Autowired
	private SqlSessionTemplate sqlSession;
	@Override
	
	public int insertTour(JsonObject items) {
		
		
		Tour t = new Tour();
		int result = 0;
		
		
		for(int i=0;i<items.getAsJsonObject().get("item").getAsJsonArray().size();i++) {
			JsonObject item = items.getAsJsonObject().get("item").getAsJsonArray().get(i).getAsJsonObject();
			t.setSpotContentId(Integer.parseInt(item.get("contentid").getAsString()));
			t.setSpotTel(item.get("tel").getAsString());
			t.setSpotTitle(item.get("title").getAsString());
			t.setSpotContentType(Integer.parseInt(item.get("contenttypeid").getAsString()));
			t.setSpotImgPath(item.get("firstimage").getAsString());
			t.setSpotAddress(item.get("addr1").getAsString()+item.get("addr2").getAsString());
			t.setSpotPostalCode(item.get("zipcode").getAsString());
			t.setSpotMapx(item.get("mapx").getAsString());
			t.setSpotMapy(item.get("mapy").getAsString());
			t.setLargeCategoryNo(item.get("cat1").getAsString());
			t.setMiddleCategoryNo(item.get("cat2").getAsString());
			t.setSmallCategoryNo(item.get("cat3").getAsString());
			t.setAreaCategoryNo(item.get("areacode").getAsString());
			result += pDao.insertTour(sqlSession,t);
			
		}
		
		System.out.println(result);
		return 0;
		
	}

}