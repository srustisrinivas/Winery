package com.mindtree.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.analysis.CharArrayMap.EntrySet;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;

import com.mindtree.entity.Winery;
import com.mindtree.queryBuilder.WineQueryBuilder;
import com.mindtree.util.WineUtil;

/**
 * Servlet implementation class WineController
 */
@WebServlet("/WineServlet")
public class WineServlet extends HttpServlet {

	WineUtil client = new WineUtil();
	Map<String, String> breadcrumbsG = new LinkedHashMap();
	String sortTerm = "P_PriceStr";
	String orderTerm = "DESC";
	int pageNum = 0;

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WineServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// WineDetails List
		List<Winery> wineList = null;
		Map<String, String> breadcrumbsL = new LinkedHashMap();

		if (request.getParameter("saver") != null) {
			pageNum = 0;
		}

		String searchTerm = request.getParameter("searchTerm");
		System.out.println("st " + searchTerm);
		String winery = request.getParameter("winery");
		System.out.println("winery " + winery);
		String wineType1 = request.getParameter("wineType1");
		System.out.println("wineType1 " + wineType1);
		String wineType2 = request.getParameter("wineType2");
		System.out.println("wineType2 " + wineType2);
		String flavor = request.getParameter("flavor");
		System.out.println("flavor " + flavor);
		String region = request.getParameter("region");
		System.out.println("region " + region);
		
		
		
		//breadcrumbs global variables
				if(winery!=null && winery!="") {
					breadcrumbsG.put("winery", winery);
				}
				if(wineType1!=null && wineType1!="") {
					breadcrumbsG.put("wineType1", wineType1);
				}
				if(wineType2!=null && wineType2!="") {
					breadcrumbsG.put("wineType2", wineType2);
				}
				if(flavor!=null && flavor!="") {
					breadcrumbsG.put("flavor", flavor);
				}
				if(region!=null && region!="") {
					breadcrumbsG.put("region", region);
				}
				
				
				String homeBread= request.getParameter("homeBread");
				

		// storing the input in a string array
		String[] input = new String[8];
		input[0] = searchTerm;
		if(homeBread == null || homeBread=="") {
			input[1] = winery;
			input[2] = wineType1;
			input[3] = wineType2;
			input[4] = flavor;
			input[5] = region;
		}else {
			String homePage=request.getParameter("homePage");
			if(homePage!=null && homePage.equals("hPage")&& homePage!="") {
				breadcrumbsG=new LinkedHashMap<>();
			}else {
				String bcMapKey=request.getParameter("bcMapKey");
				String bcMapValue=request.getParameter("bcMapValue");
				for(Entry<String,String> entry:breadcrumbsG.entrySet()) {
					breadcrumbsL.put(entry.getKey(), entry.getValue());
					if(entry.getKey().equals(bcMapKey)) {
						break;
					}
				}
				if(breadcrumbsL.containsKey("winery")) {
					input[1]=breadcrumbsL.get("winery");
				}else {
					input[1]=winery;
				}
				if(breadcrumbsL.containsKey("wineType1")) {
					input[2]=breadcrumbsL.get("wineType1");
				}else {
					input[2]=wineType1;
				}
				if(breadcrumbsL.containsKey("wineType2")) {
					input[3]=breadcrumbsL.get("wineType2");
				}else {
					input[3]=wineType2;
				}
				if(breadcrumbsL.containsKey("flavor")) {
					input[4]=breadcrumbsL.get("flavor");
				}else {
					input[4]=flavor;
				}
				if(breadcrumbsL.containsKey("region")) {
					input[5]=breadcrumbsL.get("region");
				}else {
					input[5]=region;
				}
				breadcrumbsG=breadcrumbsL;
				breadcrumbsG.entrySet().forEach(System.out::println);
				System.out.println("Global Map!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				}
		}
		
		
		

		QueryBuilder queryBuilder = WineQueryBuilder.searchConditionFiltration(input);
		System.out.println(queryBuilder);

		// sorting the fileds pricestr and score
		if (request.getParameter("sortField") != null) {
			sortTerm = request.getParameter("sortField");
		}

		// orderby with ascending and descending
		if (request.getParameter("orderBy") != null) {
			orderTerm = request.getParameter("orderBy");
		}

		// manipulation of pagination
		if (request.getParameter("pagination") != null) {
			if (request.getParameter("pagination").equals("prev") && pageNum > 0) {
				pageNum--;
			} else if (request.getParameter("pagination").equals("next") && pageNum < 9) {
				pageNum++;
			}
		}

		// aggregration
		AggregationBuilder wineaggregation = AggregationBuilders.terms("aggsWinery").field("P_Winery.keyword");
		AggregationBuilder wineType1aggregation = AggregationBuilders.terms("aggsWineType1")
				.field("P_WineType1.keyword");
		AggregationBuilder wineType2aggregation = AggregationBuilders.terms("aggsWineType2")
				.field("P_WineType2.keyword");
		AggregationBuilder flavouraggregation = AggregationBuilders.terms("aggsFlavors").field("P_Flavors.keyword");
		AggregationBuilder regionaggregation = AggregationBuilders.terms("aggsRegion").field("P_Region.keyword");

		// Search response
		SearchResponse searchResponse = null;
		if (orderTerm.equals("ASC")) {
			searchResponse = WineUtil.getConnection().prepareSearch("wine_data").setTypes("winemapping")
					.setQuery(queryBuilder).addAggregation(wineaggregation).addAggregation(wineType1aggregation)
					.addAggregation(wineType2aggregation).addAggregation(flavouraggregation)
					.addAggregation(regionaggregation).setFrom(pageNum * 10)
					.addSort(SortBuilders.fieldSort(sortTerm).order(SortOrder.ASC)).setSize(10).setExplain(false)
					.execute().actionGet();
		} else {
			searchResponse = WineUtil.getConnection().prepareSearch("wine_data").setTypes("winemapping")
					.setQuery(queryBuilder).addAggregation(wineaggregation).addAggregation(wineType1aggregation)
					.addAggregation(wineType2aggregation).addAggregation(flavouraggregation)
					.addAggregation(regionaggregation).setFrom(pageNum * 10)
					.addSort(SortBuilders.fieldSort(sortTerm).order(SortOrder.DESC)).setSize(10).setExplain(false)
					.execute().actionGet();
		}

		// diplay totalhits
		long numRecords = searchResponse.getHits().getTotalHits();

		// Sending the whole List
		wineList = new ArrayList<>();
		for (SearchHit searchhit : searchResponse.getHits()) {
			Map<String, Object> wineMap = searchhit.getSource();
			Winery wineryObject = new Winery();
			wineryObject.setP_WineID(wineMap.get("P_WineID").toString());
			if (wineMap.get("P_Wine") == null) {
				wineryObject.setP_Wine("N/A");
			} else {
				wineryObject.setP_Wine(wineMap.get("P_Wine").toString());
			}
			if (wineMap.get("P_Description") == null) {
				wineryObject.setP_Wine("N/A");
			} else {
				wineryObject.setP_Description(wineMap.get("P_Description").toString());
			}
			if (wineMap.get("P_Winery") == null) {
				wineryObject.setP_Wine("N/A");
			} else {
				wineryObject.setP_Winery(wineMap.get("P_Winery").toString());
			}
			if (wineMap.get("P_Year") == null) {
				wineryObject.setP_Wine("N/A");
			} else {
				wineryObject.setP_Year(wineMap.get("P_Year").toString());
			}
			if (wineMap.get("P_PriceStr") == null) {
				wineryObject.setP_Wine("N/A");
			} else {
				wineryObject.setP_PriceStr(wineMap.get("P_PriceStr").toString());
			}
			if (wineMap.get("P_Score") == null) {
				wineryObject.setP_Wine("N/A");
			} else {
				wineryObject.setP_Score(wineMap.get("P_Score").toString());
			}
			wineList.add(wineryObject);

		}
		int count = wineList.size();

		// winery aggregration
		Terms termsWinery = searchResponse.getAggregations().get("aggsWinery");
		Map<String, Long> wineryMap = new HashMap();
		Long countWinery = null;
		for (Terms.Bucket entry : termsWinery.getBuckets()) {
			countWinery = entry.getDocCount();
			wineryMap.put((String) entry.getKey(), countWinery);
		}

		// wineType1 aggregration
		Terms termsWineType1 = searchResponse.getAggregations().get("aggsWineType1");
		Map<String, Long> wineType1Map = new HashMap();
		Long countWineType1 = null;
		for (Terms.Bucket entry : termsWineType1.getBuckets()) {
			countWineType1 = entry.getDocCount();
			wineType1Map.put((String) entry.getKey(), countWineType1);
			System.out.println(entry.getKey() + "  " + countWineType1);
		}

		// wineType2 aggregration
		Terms termsWineType2 = searchResponse.getAggregations().get("aggsWineType2");
		Map<String, Long> wineType2Map = new HashMap();
		Long countWineType2 = null;
		for (Terms.Bucket entry : termsWineType2.getBuckets()) {
			countWineType2 = entry.getDocCount();
			wineType2Map.put((String) entry.getKey(), countWineType2);
		}

		// flavor aggregration
		Terms termsFlavors = searchResponse.getAggregations().get("aggsFlavors");
		Map<String, Long> flavorsMap = new HashMap();
		Long countFlavors = null;
		for (Terms.Bucket entry : termsFlavors.getBuckets()) {
			countFlavors = entry.getDocCount();
			flavorsMap.put((String) entry.getKey(), countFlavors);
		}

		// region aggregration
		Terms termsRegion = searchResponse.getAggregations().get("aggsRegion");
		Map<String, Long> regionMap = new HashMap();
		Long countRegion = null;
		for (Terms.Bucket entry : termsRegion.getBuckets()) {
			countRegion = entry.getDocCount();
			regionMap.put((String) entry.getKey(), countRegion);
		}

		request.setAttribute("searchTerm", searchTerm);
		request.setAttribute("winery", winery);
		request.setAttribute("wineType1", wineType1);
		request.setAttribute("wineType2", wineType2);
		request.setAttribute("flavor", flavor);
		request.setAttribute("region", region);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("count", count);
		request.setAttribute("breadcrumbsMap",breadcrumbsG);
		request.setAttribute("numRecords", numRecords);
		request.setAttribute("wineDetailsList", wineList);
		request.setAttribute("wineryMap", wineryMap);
		request.setAttribute("wineType1Map", wineType1Map);
		request.setAttribute("wineType2Map", wineType2Map);
		request.setAttribute("flavorsMap", flavorsMap);
		request.setAttribute("regionMap", regionMap);

		if (wineList.size() < 1) {
			System.out.println("=====================" + wineList.size());
			request.getRequestDispatcher("/noresults.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/results.jsp").forward(request, response);
		}

	}

}
