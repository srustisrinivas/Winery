package com.mindtree.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;

import com.mindtree.entity.Winery;
import com.mindtree.queryBuilder.WineQueryBuilder;
import com.mindtree.util.WineUtil;

public class WineDaoImpl {
	 WineUtil client = new WineUtil();
	 List<Winery> wlist = null;
	
	AggregationBuilder  wineaggregation = AggregationBuilders.terms("aggsWinery").field("P_Winery.keyword");
	AggregationBuilder  wineType1aggregation = AggregationBuilders.terms("aggsWineType1").field("P_WineType1.keyword");
	AggregationBuilder  wineType2aggregation = AggregationBuilders.terms("aggsWineType2").field("P_WineType2.keyword");
	AggregationBuilder  flavouraggregation = AggregationBuilders.terms("aggsFlavors").field("P_Flavors.keyword");
	AggregationBuilder  regionaggregation = AggregationBuilders.terms("aggsRegion").field("P_Region.keyword");
	
	
	
	public  List<Winery> getWineDetails(SearchResponse searchResponse){
		wlist=new ArrayList<>();
		for (SearchHit searchhit : searchResponse.getHits()) {
			   Map<String,Object> wineMap=searchhit.getSource();
			   Winery winery = new Winery();
			   winery.setP_WineID(wineMap.get("P_WineID").toString());
			   if(wineMap.get("P_Wine")==null) {
				   winery.setP_Wine("N/A");
			   }else {
				   winery.setP_Wine(wineMap.get("P_Wine").toString());
			   }
			   if(wineMap.get("P_Description")==null) {
				   winery.setP_Wine("N/A");
			   }else {
			   winery.setP_Description(wineMap.get("P_Description").toString());
			   }
			   if(wineMap.get("P_Winery")==null) {
				   winery.setP_Wine("N/A");
			   }else {
			   winery.setP_Winery(wineMap.get("P_Winery").toString());
			   }
			   if(wineMap.get("P_Year")==null) {
				   winery.setP_Wine("N/A");
			   }else {
				   winery.setP_Year(wineMap.get("P_Year").toString()); 
			   }
			   if(wineMap.get("P_PriceStr")==null) {
				   winery.setP_Wine("N/A");
			   }else {
				   winery.setP_PriceStr(wineMap.get("P_PriceStr").toString());
			   }
			   if(wineMap.get("P_Score")==null) {
				   winery.setP_Wine("N/A");
			   }else {
				   winery.setP_Score(wineMap.get("P_Score").toString());
			   }
			   wlist.add(winery);
				 
		        }
		return wlist;
	}
	
	public Map<String,List<Object>> getWinery(SearchResponse searchResponse){
		Terms termsWinery = searchResponse.getAggregations().get("aggsWinery");
		Map<String,List<Object>> wineryMap = new HashMap();
		List<Object> countWinery = null;
		   for(Terms.Bucket entry :termsWinery.getBuckets()) {
			   countWinery = new ArrayList<>();
			   countWinery.add(entry.getDocCount());
			   wineryMap.put((String) entry.getKey(), countWinery);
		   }
		return wineryMap;
		
	}
	
	public Map<String,List<Object>> getWineType1(SearchResponse searchResponse){
		Terms termsWineType1 = searchResponse.getAggregations().get("aggsWineType1");
		Map<String,List<Object>> wineType1Map = new HashMap();
		List<Object> countWineType1 = null;
		   for(Terms.Bucket entry :termsWineType1.getBuckets()) {
			   countWineType1 = new ArrayList<>();
			   countWineType1.add(entry.getDocCount());
			   wineType1Map.put((String) entry.getKey(), countWineType1);
		   }
		return wineType1Map;
		
	}
	
	public Map<String,List<Object>> getWineType2(SearchResponse searchResponse){
		Terms termsWineType2 = searchResponse.getAggregations().get("aggsWineType2");
		Map<String,List<Object>> wineType2Map = new HashMap();
		List<Object> countWineType2 = null;
		   for(Terms.Bucket entry :termsWineType2.getBuckets()) {
			   countWineType2 = new ArrayList<>();
			   countWineType2.add(entry.getDocCount());
			   wineType2Map.put((String) entry.getKey(), countWineType2);
		   }
		return wineType2Map;
		
	}
	
	public Map<String,List<Object>> getFlavors(SearchResponse searchResponse){
		Terms termsFlavors = searchResponse.getAggregations().get("aggsFlavors");
		Map<String,List<Object>> flavorsMap = new HashMap();
		List<Object> countFlavors = null;
		   for(Terms.Bucket entry :termsFlavors.getBuckets()) {
			   countFlavors = new ArrayList<>();
			   countFlavors.add(entry.getDocCount());
			   flavorsMap.put((String) entry.getKey(), countFlavors);
		   }
		return flavorsMap;
		
	}
	
	public Map<String,List<Object>> getRegion(SearchResponse searchResponse){
		Terms termsRegion= searchResponse.getAggregations().get("aggsRegion");
		Map<String,List<Object>> regionMap = new HashMap();
		List<Object> countRegion = null;
		   for(Terms.Bucket entry :termsRegion.getBuckets()) {
			   countRegion = new ArrayList<>();
			   countRegion.add(entry.getDocCount());
			   regionMap.put((String) entry.getKey(), countRegion);
		   }
		return regionMap;
		
	}
	
	/*public List<Map<String,List<Object>>> sendTheList(String[] winedata){
		
		for(int i=0;i<6;i++) {
			System.out.println(winedata[i]);
		}
		
		return null;
	}*/
	 
	
	
	
	
	
	
	
	
	

}
