package com.hnust.movie.controller;

import com.hnust.movie.entity.SelectedCategoryVO;
import com.hnust.movie.entity.po.Category;
import com.hnust.movie.entity.po.Location;
import com.hnust.movie.entity.po.MovieInfo;
import com.hnust.movie.entity.po.Year;
import com.hnust.movie.entity.vo.CategorySearchVO;
import com.hnust.movie.entity.vo.ResultEntity;
import com.hnust.movie.entity.vo.SearchResultVO;
import com.hnust.movie.service.DatabaseService;
import com.hnust.movie.service.SearchService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Title:用于搜索的controller
 * @Author: ggh
 * @Date: 2020/5/16 20:41
 */
@Controller
public class SearchController {

    @Autowired
    private SearchService searchService;

    @Autowired
    private DatabaseService databaseService;

    /**
    *@title:
    *@description: 根据关键字搜索
    *@param: kw :关键字
    *@param: from ：起始页
    *@param: size：页面大小
    *@param: modelMap
    *@author:ggh
    *@updateTime: 2020/5/22 10:14
    **/
    @RequestMapping("/search.html")
    public String search(@RequestParam(value = "kw", required = false) String kw,
                         @RequestParam(value = "from", defaultValue = "1", required = false) int from,
                         @RequestParam(value = "size", defaultValue = "10", required = false) int size,
                         ModelMap modelMap) {

        if (StringUtils.isNotBlank(kw)) {

            ResultEntity<SearchResultVO> movieInfoByKw = searchService.getMovieInfoByKw(kw, from, size);
            modelMap.addAttribute("movieInfoResult", movieInfoByKw);
            return "search";
        } else {
            return "error";
        }

    }

    @RequestMapping("/category/search")
    //pcid=0&category=喜剧&area=大陆&year=2020&letter=A&sort=人气
    public String categorySearch(
            @RequestParam(value = "pcid", required = false, defaultValue = "0") int pcid,
            @RequestParam(value = "category", required = false, defaultValue = "全部") String category,
            @RequestParam(value = "area", required = false, defaultValue = "全部") String location,
            @RequestParam(value = "year", required = false, defaultValue = "全部") String year,
            @RequestParam(value = "letter", required = false, defaultValue = "全部") String letter,
            @RequestParam(value = "sort", required = false, defaultValue = "releaseDate") String sort,
            @RequestParam(value = "from", required = false, defaultValue = "1") int from,
            @RequestParam(value = "size", required = false, defaultValue = "30") int size,
            ModelMap modelMap,
            HttpServletRequest request) {

        //获取本次请求url
        StringBuffer requestURL = request.getRequestURL();
        List<String> urls = getUrl(pcid, category, location, year, letter, sort);
        String lastUrl = urls.get(0);
        String hotDegreeUrl = urls.get(1); //按人气排序的url
        String recommendUrl = urls.get(2); //按推荐排序的url
        String updateUrl = urls.get(3);     //按更新排序的url


        //得到电影所有类别数据
        ResultEntity<CategorySearchVO> allCategoriesInfo = databaseService.getAllCategoriesInfo();

        ResultEntity<SearchResultVO> movieInfoByCategories = searchService.getMovieInfoByCategories(pcid, category, location, year, letter, sort, from, size);

        List<Category> collectCategory = null;
        List<Location> collectLocation = null;
        List<Year> collectYear = null;

        if (pcid == 0) {//过滤出电影信息
            collectCategory = allCategoriesInfo.getData().getCategoryList().stream().filter(x -> x.getParentCategory() == 0).collect(Collectors.toList());
            collectLocation = allCategoriesInfo.getData().getLocationList().stream().filter(x -> x.getParentCategory() == 0).collect(Collectors.toList());
            collectYear = allCategoriesInfo.getData().getYearList().stream().filter(x -> x.getParentCategory() == 0).collect(Collectors.toList());

        } else if (pcid == 1) { //过滤出动漫信息
            collectCategory = allCategoriesInfo.getData().getCategoryList().stream().filter(x -> x.getParentCategory() == 1).collect(Collectors.toList());
            collectLocation = allCategoriesInfo.getData().getLocationList().stream().filter(x -> x.getParentCategory() == 1).collect(Collectors.toList());
            collectYear = allCategoriesInfo.getData().getYearList().stream().filter(x -> x.getParentCategory() == 1).collect(Collectors.toList());
        }

        if (collectCategory != null && collectLocation != null && collectYear != null) {
            modelMap.addAttribute("categoryList", collectCategory);
            modelMap.addAttribute("locationList", collectLocation);
            modelMap.addAttribute("yearList", collectYear);

            SelectedCategoryVO selectedCategoryVO = new SelectedCategoryVO();
            selectedCategoryVO.setPcid(pcid);
            selectedCategoryVO.setCategory(category);
            selectedCategoryVO.setArea(location);
            selectedCategoryVO.setYear(year);
            selectedCategoryVO.setLetter(letter);
            selectedCategoryVO.setSort(sort);

            modelMap.addAttribute("selectedCategory", selectedCategoryVO);
            modelMap.addAttribute("movieInfoList", movieInfoByCategories);
            modelMap.addAttribute("lastUrl", lastUrl);
            modelMap.addAttribute("hotDegreeUrl", hotDegreeUrl);
            modelMap.addAttribute("recommendUrl", recommendUrl);
            modelMap.addAttribute("updateUrl", updateUrl);

            return "categorySearch";
        } else {
            return "error";
        }


    }

    /**
    *@title:
    *@description: 根据参数生成url
    *@param: pcid
    *@param: category
    *@param: location
    *@param: year
    *@param: letter
    *@param: sort
    *@author:ggh
    *@updateTime: 2020/5/21 20:15
    **/
    private List<String> getUrl(int pcid, String category, String location, String year, String letter, String sort) {

        ArrayList<String> list = new ArrayList<>();
        String lastUrl = "/category/search?";

        if (pcid != 0) {
            lastUrl = lastUrl + "pcid=" + pcid + "&";

        }
        if (!"全部".equals(category)) {
            lastUrl = lastUrl + "category=" + category + "&";
        }
        if (!"全部".equals(location)) {
            lastUrl = lastUrl + "area=" + location + "&";
        }
        if (!"全部".equals(year)) {
            lastUrl = lastUrl + "year=" + year + "&";
        }
        if (!"全部".equals(letter)) {
            lastUrl = lastUrl + "letter=" + letter + "&";
        }

        String hotDegreeUrl = lastUrl+"sort=hotDegree";  //按人气排序的url
        String recommendUrl = lastUrl+"sort=recommend"; //按推荐排序的url
        String updateUrl = lastUrl+"sort=releaseDate"; //按推荐排序的url
        if (!"releaseDate".equals(sort)) {
            lastUrl = lastUrl + "sort=" + sort + "&";
        }


        if (!lastUrl.endsWith("?")){
            lastUrl = lastUrl.substring(0,lastUrl.length()-1);
        }

        list.add(lastUrl);
        list.add(hotDegreeUrl);
        list.add(recommendUrl);
        list.add(updateUrl);

        return list;

    }


}
