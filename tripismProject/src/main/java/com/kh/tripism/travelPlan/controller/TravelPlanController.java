package com.kh.tripism.travelPlan.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kh.tripism.travelPlan.model.service.PlanServiceImpl;
import com.kh.tripism.travelPlan.model.vo.Tour;

@Controller
public class TravelPlanController {
	
	@Autowired
	private PlanServiceImpl pService;
	
	@RequestMapping("plan.tr")
	public String planEnrollForm() {
		
		return "travelPlan/planEnrollForm";
	}
	
	@RequestMapping(value = "tourInsert.do",produces = "application/json; charset=utf-8")
	public String dataInsert(int pageNum,Model m) throws IOException {
		
		
		
		String url = "http://apis.data.go.kr/B551011/KorService1/areaBasedList1";
		url +="?numOfRows=300&pageNo="+pageNum+"&MobileOS=etc&MobileApp=tripism&serviceKey=VR7yfFJDTCiY0id7o4GoBl439nXxiBWsUoTxEc9jW5riF/oSnubIFVeefhSCJTfJA6mEayLvAWPu82nOvT8tiQ==&_type=json";
		
		
		URL requestUrl = new URL(url);
		HttpURLConnection urlConnection = (HttpURLConnection)requestUrl.openConnection();
		urlConnection.setRequestMethod("GET");
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		
		String line="";
		String responseText ="";
		while((line = br.readLine())!=null) {
			responseText += line;
		}
		br.close();
		urlConnection.disconnect();
//		System.out.println("responseText"+responseText);

		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(responseText);
		JsonObject rootob = element.getAsJsonObject().get("response").getAsJsonObject();
		JsonObject body = rootob.getAsJsonObject().get("body").getAsJsonObject();
		JsonObject items = body.getAsJsonObject().get("items").getAsJsonObject();
//		System.out.println(items);
		//System.out.println(items.getAsJsonObject().get("item").getAsJsonArray().size());
		//System.out.println((String)items.getAsJsonObject().get("item").getAsJsonArray().get(0).getAsJsonObject().get("addr1").getAsString());
		int result = pService.insertTour(items);
		
//		System.out.println(result);
		
		return "index";
	}
	
	@ResponseBody
	@RequestMapping(value = "tourList.do",produces = "application/json; charset=utf-8")
	public String selectTourList(int areaCode,int currentPage,String searchValue) {
//		int listCount = bService.selectListCount(areaCode);
		System.out.println(searchValue);
		
		ArrayList<Tour> list = pService.selectTourList(areaCode,currentPage,searchValue);
		return new Gson().toJson(list);
	}

}
